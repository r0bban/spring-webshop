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

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Configuration
public class BaseConfig {


    @Bean
    public CommandLineRunner testStuffInHere(ProductRepository productRepository, ProductTypeRepository typeRepo, ProductCategoryRepository catRepo) {

        return (args) -> {
            System.out.println("Put custom code in this method for easy testing capabilities");
            ProductType productTypeNuevo = new ProductType("Typo nuevo");

            ProductType type1 = new ProductType();
            type1.setName("Furniture");
            ProductType type2 = new ProductType();
            type2.setName("Tools");
            ProductType type3 = new ProductType();
            type3.setName("Grocery");
            typeRepo.save(type1);
            typeRepo.save(type2);
            typeRepo.save(type3);

            ProductCategory cat1 = new ProductCategory();
            cat1.setName("Sofa");
            ProductCategory cat2 = new ProductCategory();
            cat2.setName("Hammer");
            catRepo.save(cat1);
            catRepo.save(cat2);


            for (int i = 0; i < 100; i++) {

                Product product = new Product();
                product.setTitle("Product " + i);
                Set<ProductType> productTypeSet = new HashSet<>();

                productTypeSet.add(i < 51
                        ? typeRepo.findByName("Furniture")
                        : typeRepo.findByName("Tools"));
                product.getProductCategories().add(i < 51
                        ? catRepo.findByName("Sofa")
                        : catRepo.findByName("Hammer"));

                if (i % 3 == 0) {
                    productTypeSet.add(typeRepo.findById(3L).get());
                }
//                if(i == 77) productTypeSet.add(productTypeNuevo);
                product.setProductTypes(productTypeSet);

                productRepository.save(product);
            }

            productRepository.findAll().forEach(product ->
                    System.out.println(product));

            productRepository.findByProductTypes_Name("Grocery").forEach(product ->
                    System.out.println(product.getTitle()
                            + product.getProductTypes()
                            .stream()
                            .map(ProductType::getName)
                            .collect(Collectors.toList())
                            + product.getProductCategories()
                            .stream()
                            .map(ProductCategory::getName)
                            .collect(Collectors.toList()))
            );

        };


    }


}
