package springWebshop.application.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import springWebshop.application.model.domain.Product;

public interface ProductSerivce {
	
	Optional<Product> getProductById();
	Optional<Product> getProductByName(String string);
	List<Product> getAllProducts();
	boolean save(Product newProduct);
	
	
	
}
