package springWebshop.application.service.order;

import springWebshop.application.model.domain.Order;
import springWebshop.application.service.ServiceResponse;

public interface OrderService {
    
	ServiceResponse<Order> getOrderById(long id);
	ServiceResponse<Order> getAllOrders();
	ServiceResponse<Order> getAllOrders(int page);
	ServiceResponse<Order> getAllOrders(int page, int size);
	ServiceResponse<Order> create (Order newOrder);
	ServiceResponse<Order> update(Order updatedOrder);
	
//	ServiceResponse<Order> productBySegmentation(ProductSearchConfig productSearchConfig);
//	ServiceResponse<Order> ProductBySearchString(ProductSearchConfig productSearchConfig);

}
