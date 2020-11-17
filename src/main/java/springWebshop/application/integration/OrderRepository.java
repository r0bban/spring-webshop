package springWebshop.application.integration;

import org.springframework.data.jpa.repository.JpaRepository;
import springWebshop.application.model.Order;

public interface OrderRepository extends JpaRepository <Order, Long> {
}
