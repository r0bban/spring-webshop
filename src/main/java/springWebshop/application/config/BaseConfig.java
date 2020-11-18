package springWebshop.application.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springWebshop.application.integration.ProductCategoryRepository;
import springWebshop.application.integration.ProductRepository;
import springWebshop.application.model.domain.Product;
import springWebshop.application.model.domain.ProductCategory;

@Configuration
public class BaseConfig {


    @Bean
    public CommandLineRunner testStuffInHere(ProductRepository productRepository, ProductCategoryRepository productCategoryRepository) {

        return (args) -> {
            System.out.println("Put custom code in this method for easy testing capabilities");
            ProductCategory cat1 = new ProductCategory();
            cat1.setName("Soffa");
            ProductCategory cat2 = new ProductCategory();
            cat2.setName("Bord");
            productCategoryRepository.save(cat1);
            productCategoryRepository.save(cat2);

            for (int i = 0; i < 100; i++) {

                Product product = new Product();
                product.setTitle("Product " + i);
                product.getProductCategories().add(i < 51
                        ? productCategoryRepository.findById(1L).get()
                        : productCategoryRepository.findById(1L).get());
                productRepository.save(product);
            }

        };


    }


}
