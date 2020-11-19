package springWebshop.application.integration;

import org.springframework.data.jpa.repository.JpaRepository;
import springWebshop.application.model.domain.ProductType;

public interface ProductTypeRepository extends JpaRepository<ProductType, Long> {

    ProductType findByName(String name);
}
