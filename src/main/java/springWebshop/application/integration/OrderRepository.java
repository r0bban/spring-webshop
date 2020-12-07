package springWebshop.application.integration;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import springWebshop.application.model.domain.Order;
import springWebshop.application.model.domain.Product;
import springWebshop.application.service.order.OrderSearchConfig;

public interface OrderRepository  extends JpaRepository<Order, Long>, OrderRepositoryCustom{

    Page<Order> getOrders(OrderSearchConfig config, int page, int size);

}
