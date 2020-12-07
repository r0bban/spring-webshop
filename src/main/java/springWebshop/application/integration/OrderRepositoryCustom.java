package springWebshop.application.integration;

import org.springframework.data.domain.Page;
import springWebshop.application.model.domain.Order;
import springWebshop.application.service.order.OrderSearchConfig;

public interface OrderRepositoryCustom {
    Page<Order> getOrders(OrderSearchConfig config, int page, int size);
}
