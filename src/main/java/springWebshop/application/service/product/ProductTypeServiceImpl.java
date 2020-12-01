package springWebshop.application.service.product;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import springWebshop.application.integration.ProductTypeRepository;
import springWebshop.application.model.domain.ProductSubCategory;

@Service
public class ProductTypeServiceImpl implements ProductTypeService {
	@Override
	public boolean save(ProductSubCategory productSubCategory) {
		return false;
	}

	@Override
	public Optional<ProductSubCategory> getById(long id) {
		return Optional.empty();
	}

	@Override
	public Optional<ProductSubCategory> getByName(String name) {
		return Optional.empty();
	}

	@Override
	public List<ProductSubCategory> getAllProductTypes() {
		return null;
	}

//	@Autowired
//	ProductTypeRepository productTypeRepository;
//
//	@Override
//	public boolean save(ProductSubCategory productSubCategory) {
//		productTypeRepository.save(productSubCategory);
//		return true;
//
//	}
//
//	@Override
//	public Optional<ProductSubCategory> getById(long id) {
//		return productTypeRepository.findById(id);
//	}
//
//	@Override
//	public Optional<ProductSubCategory> getByName(String name) {
//		return productTypeRepository.findByName(name);
//	}
//
//	@Override
//	public List<ProductSubCategory> getAllProductTypes() {
//		return productTypeRepository.findAll();
//	}

}
