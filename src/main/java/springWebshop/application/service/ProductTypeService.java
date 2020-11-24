package springWebshop.application.service;

import java.util.List;
import java.util.Optional;

import springWebshop.application.model.domain.ProductType;

public interface ProductTypeService {
	
	boolean save(ProductType productType);
	Optional<ProductType> getById(long id);
	Optional<ProductType> getByName(String name);
	List<ProductType> getAllProductTypes();
	
	
}
