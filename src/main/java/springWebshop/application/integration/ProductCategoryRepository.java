package springWebshop.application.integration;

import java.util.List;
import java.util.Optional;

import javax.persistence.PersistenceContext;

import org.springframework.data.jpa.repository.JpaRepository;

import springWebshop.application.model.domain.ProductCategory;
import springWebshop.application.model.dto.SegmentDTO;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long> {

	
    Optional<ProductCategory> findByName(String name);
    
}
