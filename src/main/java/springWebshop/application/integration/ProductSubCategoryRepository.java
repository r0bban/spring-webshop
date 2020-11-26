package springWebshop.application.integration;

import org.springframework.data.jpa.repository.JpaRepository;
import springWebshop.application.model.domain.ProductSubCategory;
import springWebshop.application.model.domain.ProductType;

import java.util.Optional;

public interface ProductSubCategoryRepository extends JpaRepository<ProductSubCategory, Long> {

    Optional<ProductSubCategory> findByName(String name);
}
