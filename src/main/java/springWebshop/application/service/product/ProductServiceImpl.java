package springWebshop.application.service.product;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import springWebshop.application.integration.ProductRepository;
import springWebshop.application.integration.ProductTypeRepository;
import springWebshop.application.model.domain.Product;
import springWebshop.application.service.ServiceErrorMessages;
import springWebshop.application.service.ServiceResponse;

@Service
@Primary
public class ProductServiceImpl implements ProductService {

    final ProductRepository productRepository;
    final ProductTypeRepository productTypeRepository;

    final int defaultPageSize = 10;
    final int maxPageSize = 30;


    public ProductServiceImpl(ProductRepository productRepository, ProductTypeRepository productTypeRepository) {
        this.productRepository = productRepository;
        this.productTypeRepository = productTypeRepository;
    }

    @Override
    public ServiceResponse<Product> getProductById(long id) {
        ServiceResponse<Product> response = new ServiceResponse<>();
        try {
            Optional<Product> product = productRepository.findById(id);
            if (!product.isPresent()) {
                response.addErrorMessage(ServiceErrorMessages.PRODUCT.couldNotFind(id));
            } else {
                response.addResponseObject(product.get());
            }
        } catch (Exception e) {
            response.addErrorMessage(ServiceErrorMessages.PRODUCT.couldNotFind(id));
        }
        return response;
    }

    @Override
    public ServiceResponse<Product> getProductByName(String name) {
        ServiceResponse<Product> response = new ServiceResponse<>();
        try {
            response.setResponseObjects(productRepository.findByName(name));
        } catch (Exception e) {
            response.addErrorMessage("Problem occurred when searching for product");
        }
        return response;
    }

    @Override
    public ServiceResponse<Product> getAllProducts() {
        return getAllProductPageAndSize(0, defaultPageSize);
    }

    @Override
    public ServiceResponse<Product> getAllProducts(int page, int size) {
        return getAllProductPageAndSize(page, size);
    }

    @Override
    public ServiceResponse<Product> getAllProducts(int page) {
        return getAllProductPageAndSize(page, defaultPageSize);
    }

    private ServiceResponse<Product> getAllProductPageAndSize(int page, int size) {
        ServiceResponse<Product> response = new ServiceResponse<>();
        if (size <= maxPageSize) try {
            response.setResponseObjects(productRepository.findAll(PageRequest.of(page, size)).getContent());
        } catch (Exception e) {
            response.addErrorMessage(ServiceErrorMessages.PRODUCT.couldNotFind() + "s page " + page + ".");
        }
        else
            response.addErrorMessage("You have requested " + size + "products. Max allowed page size is " + maxPageSize);
        return response;
    }

    @Override
    public ServiceResponse<Product> create(Product newProduct) {
        ServiceResponse<Product> response = new ServiceResponse<>();
        List<String> errors = new ArrayList<>();

        if (isValidNewProduct(newProduct, errors)) try {
            response.addResponseObject(productRepository.save(newProduct));
        } catch (Exception e) {
            response.addErrorMessage(ServiceErrorMessages.PRODUCT.couldNotCreate());
        }
        response.setErrorMessages(errors);
        return response;
    }

    private boolean isValidNewProduct(Product newProduct, List<String> errors) {
        boolean isValid = true;
        if (!isNewProduct(newProduct)) {
            isValid = false;
            errors.add("Product id is provided. Id should not be provided for a new product");
        }
        if (!hasValidProductType(newProduct)) {
            isValid = false;
            errors.add("Provided product type does not exist. New Product must be associated to existing Product Type.");
        }
        return isValid;
    }

    @Override
    public ServiceResponse<Product> update(Product updatedProduct) {
        ServiceResponse<Product> response = new ServiceResponse<>();
        List<String> errors = new ArrayList<>();

        if (isExistingProduct(updatedProduct, errors)) {
            try {
                response.addResponseObject(productRepository.save(updatedProduct));
            } catch (Exception e) {
                response.addErrorMessage(ServiceErrorMessages.PRODUCT.couldNotUpdate(updatedProduct.getId()));
            }
        }
        return response;
    }

    @Override
    public ServiceResponse<Product> productBySegmentation(ProductSearchConfig productSearchConfig) {
        return null;
    }

    @Override
    public ServiceResponse<Product> ProductBySearchString(ProductSearchConfig productSearchConfig) {
        return null;
    }

    boolean hasValidProductType(Product product) {
        return product.getProductType() != null
                ? productTypeRepository.existsById(product.getProductType().getId())
                : false;
    }

    boolean isNewProduct(Product product) {
        return product.getId() == 0L
                ? true
                : false;
    }

    boolean isExistingProduct(Product product, List<String> errors) {
        boolean isExisting = true;
        if (!productRepository.existsById(product.getId())) {
            isExisting = false;
            errors.add(ServiceErrorMessages.PRODUCT.couldNotFind(product.getId()));
        }
        return isExisting;
    }

}
