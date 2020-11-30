package springWebshop.application.integration;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import springWebshop.application.model.domain.Order;

public interface OrderRepository  extends JpaRepository<Order, Long>{
	

}
