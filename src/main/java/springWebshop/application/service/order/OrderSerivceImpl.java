package springWebshop.application.service.order;

import java.util.*;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import springWebshop.application.integration.CustomerRepository;
import springWebshop.application.integration.OrderRepository;
import springWebshop.application.integration.ProductRepository;
import springWebshop.application.model.domain.Order;
import springWebshop.application.model.domain.OrderLine;
import springWebshop.application.model.domain.Order.OrderStatus;
import springWebshop.application.model.domain.Product;
import springWebshop.application.model.domain.user.CustomerAddress;
import springWebshop.application.model.domain.user.Customer;
import springWebshop.application.model.dto.ShoppingCartDTO;
import springWebshop.application.service.ServiceErrorMessages;
import springWebshop.application.service.ServiceResponse;

import javax.persistence.EntityNotFoundException;

@Service
public class OrderSerivceImpl implements OrderService {

    final
    OrderRepository orderRepository;
    final ProductRepository productRepository;
    final CustomerRepository customerRepository;

    final int defaultPageSize = 10;
    final int maxPageSize = 30;

    public OrderSerivceImpl(OrderRepository orderRepository, ProductRepository productRepository, CustomerRepository customerRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.customerRepository = customerRepository;
    }


    @Override
    public ServiceResponse<Order> getOrderById(long id) {
        ServiceResponse<Order> response = new ServiceResponse<>();
        try {
            Optional<Order> order = orderRepository.findById(id);
            if (!order.isPresent()) {
                response.addErrorMessage(ServiceErrorMessages.ORDER.couldNotFind(id));
            } else {
                response.addResponseObject(order.get());
            }
        } catch (Exception e) {
            response.addErrorMessage(ServiceErrorMessages.ORDER.couldNotFind(id));
        }
        return response;
    }

    @Override
    public ServiceResponse<Order> getAllOrders() {
        return getAllOrderPageAndSize(0, defaultPageSize);
    }

    @Override
    public ServiceResponse<Order> getAllOrders(int page, int size) {
        return getAllOrderPageAndSize(page, size);
    }

    @Override
    public ServiceResponse<Order> getAllOrders(int page) {
        return getAllOrderPageAndSize(page, defaultPageSize);
    }

    private ServiceResponse<Order> getAllOrderPageAndSize(int page, int size) {
        ServiceResponse<Order> serviceResponse = new ServiceResponse<>();
        if (size <= maxPageSize)
            try {
                Page<Order> response = orderRepository.findAll(PageRequest.of(page, size));
                setPageMetaData(response, serviceResponse);
                serviceResponse.setResponseObjects(response.getContent());
            } catch (Exception e) {
                serviceResponse.addErrorMessage(ServiceErrorMessages.ORDER.couldNotFind() + "s page " + page + ".");
            }
        else
            serviceResponse.addErrorMessage(
                    "You have requested " + size + "orders. Max allowed page size is " + maxPageSize);
        return serviceResponse;
    }

    private void setPageMetaData(Page page, ServiceResponse serviceResponse) {
        serviceResponse.setTotalPages(page.getTotalPages());
        serviceResponse.setTotalItems((int) page.getTotalElements());
        serviceResponse.setCurrentPage(page.getNumber());
    }

    @Override
    public ServiceResponse<Order> create(Order newOrder) {
        System.out.println("--------public ServiceResponse<Order> create(Order newOrder) {--------");
        ServiceResponse<Order> response = new ServiceResponse<>();
        List<String> errors = new ArrayList<>();

        if (hasValidCustomerAssociation(newOrder, errors)
                && hasValidDeliveryAddress(newOrder, errors))
            try {
                newOrder.setCreated(new Date());
                response.addResponseObject(orderRepository.save(newOrder));
            } catch (Exception e) {
                response.addErrorMessage(ServiceErrorMessages.ORDER.couldNotCreate());
            }
        response.setErrorMessages(errors);
        return response;
    }

    boolean hasValidCustomerAssociation(Order order, List<String> errors) {
        System.out.println("--------hasValidCustomerAssociation--------");

        if (order.getCustomer() != null
                && !customerRepository.existsById(order.getCustomer().getId())) {
            return true;
        } else {
            errors.add("Customer is not assigned to order OR customer does not exist." +
                    "\n New Order must be associated to existing Customer.");
            return false;
        }
    }

    boolean hasValidDeliveryAddress(Order order, List<String> errors) {
        System.out.println("-----hasValidDeliveryAddress-----");
        if (order.getDeliveryAddress() != null) {
            return true;
        } else {
            errors.add("Delivery address is not assigned to order." +
                    "\n New Order must have delivery address.");
            return false;
        }
    }

    @Override
    public ServiceResponse<Order> createOrderFromShoppingCart(ShoppingCartDTO shoppingCartDTO,
                                                              long customerId, CustomerAddress deliveryAddress) {
        System.out.println("-----createOrderFromShoppingCart-----");

        ServiceResponse<Order> response = new ServiceResponse<>();
        List<String> errors = new ArrayList<>();
        List<Product> productList = new ArrayList<>();
        Order newOrder = new Order();

        System.out.println("-----Optional<Customer> customer = customerRepository.findById(customerId);-----");
        Optional<Customer> customer = customerRepository.findById(customerId);
        newOrder.setCustomer(customer.isPresent()
                ? customer.get()
                : null);

        System.out.println("-----newOrder.setDeliveryAddress(deliveryAddress);-----");
        newOrder.setDeliveryAddress(deliveryAddress);

//        validateAndFillProductList(shoppingCartDTO, productList, errors);
//        prepareOrderFromShoppingCart(newOrder, shoppingCartDTO, productList, errors);

        if (validateAndFillProductList(shoppingCartDTO, productList, errors)
                && prepareOrderFromShoppingCart(newOrder, shoppingCartDTO, productList, errors)) {
            try {
                return create(newOrder);
            } catch (Exception e) {
                response.addErrorMessage(ServiceErrorMessages.ORDER.couldNotCreate());
            }
        }
        response.setErrorMessages(errors);
        return response;
    }

    private boolean prepareOrderFromShoppingCart(Order order, ShoppingCartDTO shoppingCartDTO, List<Product> produstList, List<String> errors) {
        System.out.println("-----prepareOrderFromShoppingCart-----");
        try {
            produstList.forEach(product -> {
                order.addOrderLine(getOrderLineFromProduct(product,
                        shoppingCartDTO.getProductMap().getOrDefault(product, 0)));
            });
            return true;
        } catch (Error error) {
            errors.add(ServiceErrorMessages.ORDER.couldNotCreate());
            return false;
        }

    }

    private OrderLine getOrderLineFromProduct(Product product, int quantity) {
        OrderLine orderLine = new OrderLine();
        double lineSum = product.getBasePrice() * quantity;
        double lineVAT = product.getBasePrice() * product.getVatPercentage() * quantity;
        orderLine.setProductId(product.getId());
        orderLine.setItemQuantity(quantity);
        orderLine.setSum(lineSum);
        orderLine.setVatSum(lineVAT);
        orderLine.setSumPayable(lineSum + lineVAT);
        return orderLine;
    }

    private boolean validateAndFillProductList(ShoppingCartDTO shoppingCartDTO, List<Product> productList, List<String> errors) {

        System.out.println("-----validateAndFillProductList-----");

        shoppingCartDTO.getProductMap().forEach((product, integer) -> {
            try {
                productList.add(productRepository.getOne(product.getId()));
            } catch (EntityNotFoundException e) {
                errors.add(ServiceErrorMessages.PRODUCT.couldNotFind(product.getId()));
            }
        });
        return shoppingCartDTO.getProductMap().size() == productList.size();
    }

    @Override
    public ServiceResponse<Order> cancelOrderById(long id) {
        return null;
    }

    @Override
    public boolean orderIsCancelable(Order order) {
        return false;
    }

    @Override
    public ServiceResponse<Order> setStatus(OrderStatus orderStatus) {
        return null;
    }

}
