package springWebshop.application.service;

import java.util.List;
import java.util.Optional;

import springWebshop.application.model.domain.ProductSubCategory;

public interface ProductTypeService {
	
	boolean save(ProductSubCategory productSubCategory);
	Optional<ProductSubCategory> getById(long id);
	Optional<ProductSubCategory> getByName(String name);
	List<ProductSubCategory> getAllProductTypes();
	
	
}
