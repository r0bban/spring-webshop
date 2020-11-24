package springWebshop.application.integration;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import springWebshop.application.model.domain.ProductType;

public interface ProductTypeRepository extends JpaRepository<ProductType, Long> {

    Optional<ProductType> findByName(String name);
}
