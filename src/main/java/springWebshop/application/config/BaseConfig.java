package springWebshop.application.config;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springWebshop.application.integration.ProductCategoryRepository;
import springWebshop.application.integration.ProductRepository;
import springWebshop.application.integration.ProductTypeRepository;
import springWebshop.application.model.domain.Product;
import springWebshop.application.model.domain.ProductCategory;
import springWebshop.application.model.domain.ProductType;

@Configuration
public class BaseConfig {
	@Autowired
	ProductRepository productRepository;
	@Autowired
	ProductTypeRepository typeRepo;
	@Autowired
	ProductCategoryRepository catRepo;
    @Bean
    public CommandLineRunner testStuffInHere() {

        return (args) -> {
            System.out.println("Put custom code in this method for easy testing capabilities");
            ProductType productTypeNuevo = new ProductType("Typo nuevo");
            productTypeNuevo.setId(0L);

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
                productRepository.save(product);
//                Set<ProductType> productTypeSet = new HashSet<>();
//
//                productTypeSet.add(i < 51
//                        ? typeRepo.findByName("Furniture")
//                        : typeRepo.findByName("Tools"));
//                product.getProductCategories().add(i < 51
//                        ? catRepo.findByName("Sofa")
//                        : catRepo.findByName("Hammer"));
//
//                if (i % 3 == 0) {
//                    productTypeSet.add(typeRepo.findById(3L).get());
//                }
//                if(i == 77) productTypeSet.add(productTypeNuevo);
//                product.setProductTypes(productTypeSet);
                
                product.getProductTypes().add(i < 51
                		? typeRepo.findByName("Furniture")
                				: typeRepo.findByName("Tools"));
                product.getProductCategories().add(i < 51
                		? catRepo.findByName("Sofa")
                				: catRepo.findByName("Hammer"));
                
                if (i % 3 == 0) {
                	product.getProductTypes().add(typeRepo.findById(3L).get());
                }
                if(i == 77) product.addProductType(productTypeNuevo);

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
