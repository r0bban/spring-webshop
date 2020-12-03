package springWebshop.application.service.order;

import java.util.*;

import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import springWebshop.application.integration.CustomerRepository;
import springWebshop.application.integration.OrderRepository;
import springWebshop.application.integration.ProductRepository;
import springWebshop.application.model.domain.Address;
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
@Primary
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
        if (order.getCustomer() != null
                && customerRepository.existsById(order.getCustomer().getId())) {
            return true;
        } else {
            errors.add("\nCustomer is not assigned to order OR customer does not exist." +
                    "\nNew Order must be associated to existing Customer.");
            return false;
        }
    }

    boolean hasValidDeliveryAddress(Order order, List<String> errors) {
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
                                                              long customerId, Address deliveryAddress) {
        ServiceResponse<Order> response = new ServiceResponse<>();
        List<String> errors = new ArrayList<>();
        List<Product> productList = new ArrayList<>();
        Order newOrder = new Order();

        Optional<Customer> customer = customerRepository.findById(customerId);
        newOrder.setCustomer(customer.isPresent()
                ? customer.get()
                : null);
        newOrder.setDeliveryAddress(deliveryAddress);

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
        orderLine.setVatPercentage(product.getVatPercentage());
        return orderLine;
    }

    private boolean validateAndFillProductList(ShoppingCartDTO shoppingCartDTO, List<Product> productList, List<String> errors) {
        shoppingCartDTO.getProductMap().forEach((product, integer) -> {
            try {
                Optional<Product> validProduct = productRepository.findById(product.getId());
                if (validProduct.isPresent()) {
                    productList.add(validProduct.get());
                } else {
                    errors.add(ServiceErrorMessages.PRODUCT.couldNotFind(product.getId()));
                }
            } catch (Exception e) {
                errors.add("Severe error while validating adding products to order. Contact store admin.");
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
