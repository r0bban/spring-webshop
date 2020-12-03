package springWebshop.application.service.order;

import springWebshop.application.model.domain.Address;
import springWebshop.application.model.domain.Order;
import springWebshop.application.model.domain.user.CustomerAddress;
import springWebshop.application.model.dto.ShoppingCartDTO;
import springWebshop.application.service.ServiceResponse;

public interface OrderService {

    ServiceResponse<Order> getOrderById(long id);

    ServiceResponse<Order> getAllOrders();

    ServiceResponse<Order> getAllOrders(int page);

    ServiceResponse<Order> getAllOrders(int page, int size);

    ServiceResponse<Order> create(Order newOrder);

    ServiceResponse<Order> createOrderFromShoppingCart(ShoppingCartDTO shoppingCartDTO,
                                                       long customerId, Address deliveryAddress);

    ServiceResponse<Order> cancelOrderById(long id);

    boolean orderIsCancelable(Order order);

    ServiceResponse<Order> setStatus(Order.OrderStatus orderStatus);

    //	ServiceResponse<Order> update(Order updatedOrder);
//	ServiceResponse<Order> productBySegmentation(ProductSearchConfig productSearchConfig);
//	ServiceResponse<Order> ProductBySearchString(ProductSearchConfig productSearchConfig);

}
