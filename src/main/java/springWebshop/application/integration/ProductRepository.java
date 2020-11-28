package springWebshop.application.integration;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import springWebshop.application.model.domain.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

    //    List <Product> findByProductTypes_Name(final String typeName);
    List<Product> findByName(String name);
}
