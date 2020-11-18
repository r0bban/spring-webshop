package springWebshop.application.integration;

import org.springframework.data.jpa.repository.JpaRepository;
import springWebshop.application.model.domain.Order;
import springWebshop.application.model.domain.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
