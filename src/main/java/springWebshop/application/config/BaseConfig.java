package springWebshop.application.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springWebshop.application.integration.ProductCategoryRepository;
import springWebshop.application.integration.ProductRepository;
import springWebshop.application.integration.ProductTypeRepository;
import springWebshop.application.model.domain.Product;
import springWebshop.application.model.domain.ProductCategory;
import springWebshop.application.model.domain.ProductType;

import java.util.stream.Collectors;

@Configuration
public class BaseConfig {


    @Bean
    public CommandLineRunner testStuffInHere(ProductRepository productRepository, ProductTypeRepository typeRepo) {

        return (args) -> {
            System.out.println("Put custom code in this method for easy testing capabilities");
            ProductType type1 = new ProductType();
            type1.setName("Soffa");
            ProductType type2 = new ProductType();
            type2.setName("Bord");
            ProductType type3 = new ProductType();
            type3.setName("IceCream");
            typeRepo.save(type1);
            typeRepo.save(type2);
            typeRepo.save(type3);


            for (int i = 0; i < 100; i++) {

                Product product = new Product();
                product.setTitle("Product " + i);
                product.getProductTypes().add(i < 51
                        ? typeRepo.findByName("Soffa")
                        : typeRepo.findByName("Bord"));
                if(i % 3 == 0) {product.getProductTypes().add(typeRepo.findById(3L).get());}
                productRepository.save(product);
            }

            productRepository.findAll().forEach(product ->
                    System.out.println(product));

            productRepository.findByProductTypes_Name("Soffa").forEach(product ->
                    System.out.println(product.getTitle() + product.getProductTypes().stream().map(ProductType::getName).collect(Collectors.toList()))
                    );

        };


    }


}
