package springWebshop.application.integration;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import springWebshop.application.model.domain.ProductCategory;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long> {

    Optional<ProductCategory> findByName(String name);
}
