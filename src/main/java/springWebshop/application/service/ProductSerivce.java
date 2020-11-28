package springWebshop.application.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import springWebshop.application.model.domain.Product;

public interface ProductSerivce {
	
	ServiceResponse<Product> getProductById();
	//Todo decide list or unique
	ServiceResponse<Product> getProductByName(String string);
	ServiceResponse<Product> getAllProducts();
	ServiceResponse<Product> getAllProducts(int page, int size);
	ServiceResponse<Product> create (Product newProduct);
	ServiceResponse<Product> update(Product updatedProduct);
	ServiceResponse<Product> productBySegmentation(ProductSearchConfig productSearchConfig);
	ServiceResponse<Product> ProductBySearchString(ProductSearchConfig productSearchConfig);
	ServiceResponse<Product> getProductById(long id);



}
