package springWebshop.application.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import springWebshop.application.integration.ProductRepository;
import springWebshop.application.model.domain.Product;

@Service
public class ProductServiceImpl implements ProductSerivce {

	@Autowired
	ProductRepository productRepository;

	@Override
	public Optional<Product> getProductById() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Product> getProductByName(String name) {
		return productRepository.findByName(name);
	}

	@Override
	public List<Product> getAllProducts() {
		return productRepository.findAll();
	}

	@Override
	public boolean save(Product newProduct) {
		try {
			productRepository.save(newProduct);
			return true;
		} catch (IllegalArgumentException e) {
			return false;
		}
	}

}
