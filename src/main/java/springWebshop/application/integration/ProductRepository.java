package springWebshop.application.integration;

import org.springframework.data.jpa.repository.JpaRepository;
import springWebshop.application.model.domain.Product;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List <Product> findByProductTypes_Name(final String typeName);
}
