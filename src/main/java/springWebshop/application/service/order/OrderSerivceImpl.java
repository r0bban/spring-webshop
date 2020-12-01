package springWebshop.application.service.order;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import springWebshop.application.integration.OrderRepository;
import springWebshop.application.model.domain.Order;
import springWebshop.application.model.domain.Order;
import springWebshop.application.model.domain.Order;
import springWebshop.application.model.domain.OrderStatus;
import springWebshop.application.model.dto.ShoppingCartDTO;
import springWebshop.application.service.ServiceErrorMessages;
import springWebshop.application.service.ServiceResponse;

@Service
public class OrderSerivceImpl implements OrderService {

	final
    OrderRepository orderRepository;
	
	  final int defaultPageSize = 10;
	    final int maxPageSize = 30;

    public OrderSerivceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }


    @Override
	public ServiceResponse<Order> getOrderById(long id) {
		// TODO Auto-generated method stub
		return null;
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
        ServiceResponse<Order> response = new ServiceResponse<>();
        if (size <= maxPageSize) try {
            response.setResponseObjects(orderRepository.findAll(PageRequest.of(page, size)).getContent());
        } catch (Exception e) {
            response.addErrorMessage(ServiceErrorMessages.PRODUCT.couldNotFind() + "s page " + page + ".");
        }
        else
            response.addErrorMessage("You have requested " + size + "orders. Max allowed page size is " + maxPageSize);
        return response;
    }

    @Override
    public ServiceResponse<Order> create(Order newOrder) {
        ServiceResponse<Order> response = new ServiceResponse<>();
        List<String> errors = new ArrayList<>();
            response.addResponseObject(orderRepository.save(newOrder));
        return response;
    }

    @Override
    public ServiceResponse<Order> createOrderFromShoppingCart(ShoppingCartDTO shoppingCartDTO) {
        return null;
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
