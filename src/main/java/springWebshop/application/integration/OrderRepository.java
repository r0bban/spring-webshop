package springWebshop.application.integration;

import org.springframework.data.jpa.repository.JpaRepository;
import springWebshop.application.model.domain.Order;

public interface OrderRepository extends JpaRepository <Order, Long> {
}
