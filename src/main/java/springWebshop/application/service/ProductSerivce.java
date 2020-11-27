package springWebshop.application.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import springWebshop.application.model.domain.Product;

public interface ProductSerivce {
	
	Optional<Product> getProductById();
	//Todo decide list or unique
	Optional<Product> getProductByName(String string);
	List<Product> getAllProducts();
	List<Product> getAllProducts(int page, int size);
	Product create (Product newProduct);
	Product update(Product updatedProduct);
	List<Product> ProductBySegmentation(ProductSearchConfig productSearchConfig);
	List<Product> ProductBySearchString(ProductSearchConfig productSearchConfig);



}
