package springWebshop.application.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import springWebshop.application.integration.ProductRepository;
import springWebshop.application.integration.ProductTypeRepository;
import springWebshop.application.model.domain.Product;

@Service
public class ProductServiceImpl implements ProductSerivce {

    final ProductRepository productRepository;
    final ProductTypeRepository productTypeRepository;


    public ProductServiceImpl(ProductRepository productRepository, ProductTypeRepository productTypeRepository) {
        this.productRepository = productRepository;
        this.productTypeRepository = productTypeRepository;
    }

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
    public Product create(Product newProduct) {
        if(!validProductType(newProduct)) throw new RuntimeException("Could not create product since defined product type does not exist");

        if (newProduct.getId() == 0L) {
            try {
                return productRepository.save(newProduct);
            } catch (Exception e) {
                throw new RuntimeException("Could not create new product" + e);
            }
        } else throw new RuntimeException("This is not a new product");
    }

    @Override
    public Product update(Product updatedProduct) {
        if (productRepository.existsById(updatedProduct.getId())) {
            try {
                return productRepository.save(updatedProduct);
            } catch (IllegalArgumentException e) {
                throw new RuntimeException("Product found, but failed to update product with id: " + updatedProduct.getId() + e);
            }
        } else throw new RuntimeException("Could not find product id: " + updatedProduct.getId());
    }

    boolean validProductType(Product product) {
        return product.getProductType()!=null
                ? productTypeRepository.existsById(product.getProductType().getId())
                : false;
    }

}
