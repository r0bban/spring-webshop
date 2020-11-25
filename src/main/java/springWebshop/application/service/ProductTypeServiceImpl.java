package springWebshop.application.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import springWebshop.application.integration.ProductTypeRepository;
import springWebshop.application.model.domain.ProductType;

@Service
public class ProductTypeServiceImpl implements ProductTypeService {

	@Autowired
	ProductTypeRepository productTypeRepository;
	
	@Override
	public boolean save(ProductType productType) {
		productTypeRepository.save(productType);
		return true;
	
	}

	@Override
	public Optional<ProductType> getById(long id) {
		return productTypeRepository.findById(id);
	}

	@Override
	public Optional<ProductType> getByName(String name) {
		return productTypeRepository.findByName(name);
	}

	@Override
	public List<ProductType> getAllProductTypes() {
		return productTypeRepository.findAll();
	}

}
