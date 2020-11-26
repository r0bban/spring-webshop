package springWebshop.application.config;

import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.thymeleaf.ThymeleafProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.templateresolver.FileTemplateResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;

import springWebshop.application.integration.*;
import springWebshop.application.model.domain.Product;
import springWebshop.application.model.domain.ProductCategory;
import springWebshop.application.model.domain.ProductSubCategory;
import springWebshop.application.model.domain.ProductType;
import springWebshop.application.model.domain.user.Company;
import springWebshop.application.model.domain.user.Customer;
import springWebshop.application.service.ProductSerivce;

@Configuration
public class BaseConfig {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    ProductTypeRepository typeRepo;
    @Autowired
    ProductCategoryRepository catRepo;
    @Autowired
    ProductSubCategoryRepository subCatRepo;

    @Autowired
    AccountRepository accountRepository;
    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    ProductSerivce productService;
    @Autowired
    private ThymeleafProperties properties;
    @Value("${spring.thymeleaf.templates_root:}")
    private String templatesRoot;

    @Bean
    public CommandLineRunner testStuffInHere() {


        return (args) -> {
            ProductCategory category = new ProductCategory("Möbler");
            catRepo.save(category);

            ProductSubCategory subCategory = new ProductSubCategory("Stol", catRepo.findByName("Möbler").get());
            subCatRepo.save(subCategory);

            ProductSubCategory subCat2 = new ProductSubCategory();
            subCat2.setId(1L);
            ProductType prodType = new ProductType("Gungstol", subCat2);
            typeRepo.save(prodType);

            ProductType prodType2 = new ProductType();
            prodType2.setId(1L);


            Product product = new Product();
            product.setName("Testprodukt");
            product.setProductType(prodType2);
//            product.setProductType(typeRepo.findByName("Gungstol").get());
            productRepository.save(product);
//            productTypeCategoryTests();
//        	simpleServiceTest();


//            System.out.println("Put custom code in this method for easy testing capabilities");
//            productTypeCategoryTests();
//
//        	companyCustomerTests();
//
//        	Optional<Product> product = productRepository.findById(4L);
//        	if(product.isPresent()) {
//        		System.out.println("Deleting:"+product.toString());
//        		productRepository.delete(product.get());
//        	}


        };


    }
//
//    private void simpleServiceTest() {
//        ProductCategory existingCategory = new ProductCategory("Alpha");
//        catRepo.save(existingCategory);
//        for (int i = 0; i < 10; i++) {
//            Product product = new Product();
//            product.setName("" + i);
//            product.addProductCategory(new ProductCategory("" + (i - 25)));
//            productService.save(product);
//        }
//
//
//        Optional<Product> product = productService.getProductByName("5");
//        if (product.isPresent()) {
//            System.out.println(product.get());
//            Product localProduct = product.get();
//            localProduct.addProductCategory(new ProductCategory("Beta"));
//            Optional<ProductCategory> exCat = catRepo.findByName("Alpha");
//            if (exCat.isPresent())
//                localProduct.addProductCategory(exCat.get());
//            productService.save(localProduct);
//        }
//        productService.getAllProducts().forEach(p -> System.out.println(p + "\n"));
//    }
//
//    private void companyCustomerTests() {
//        Company company1 = new Company();
//        company1.setName("Ikea");
//        Company company2 = new Company();
//        company2.setName("ABAB");
//        Company company3 = new Company();
//        company3.setName("SWECO");
//
//        Customer customer1 = new Customer();
//        customer1.setFirstName("Johannes");
//        Customer customer2 = new Customer();
//        customer2.setFirstName("Robert");
//        Customer customer3 = new Customer();
//        customer3.setFirstName("Kalle");
//        Customer customer4 = new Customer();
//        customer4.setFirstName("Tess");
//        accountRepository.save(customer4);
//
//        company2.getCustomers().add(customer1);
//        company2.getCustomers().add(customer2);
//        company3.getCustomers().add(customer3);
//
//
//        companyRepository.save(company1);
//        companyRepository.save(company2);
//        companyRepository.save(company3);
//        companyRepository.test().forEach(company -> System.out.println(company + "\n"));
//
//        Optional<Company> oldCompany = companyRepository.findById(3L);
//        if (oldCompany.isPresent()) {
//            companyRepository.delete(oldCompany.get());
//        }
//    }
//
//    private void productTypeCategoryTests() {
//        ProductSubCategory type1 = new ProductSubCategory();
//        type1.setName("Furniture");
//        ProductSubCategory type2 = new ProductSubCategory();
//        type2.setName("Tools");
//        ProductSubCategory type3 = new ProductSubCategory();
//        type3.setName("Grocery");
//        typeRepo.save(type1);
//        typeRepo.save(type2);
//        typeRepo.save(type3);
//
//        ProductCategory cat1 = new ProductCategory();
//        cat1.setName("Sofa");
//        ProductCategory cat2 = new ProductCategory();
//        cat2.setName("Hammer");
//        catRepo.save(cat1);
//        catRepo.save(cat2);
//
//
//        for (int i = 0; i < 100; i++) {
//
//            Product product = new Product();
//            product.setName("Product " + i);
//
//            product.addProductType(i < 51
//                    ? typeRepo.findByName("Furniture").get()
//                    : typeRepo.findByName("Tools").get());
//            product.getProductCategories().add(i < 51
//                    ? catRepo.findByName("Sofa").get()
//                    : catRepo.findByName("Hammer").get());
//
//            if (i % 3 == 0) {
//                product.getProductTypes().add(typeRepo.findById(3L).get());
//            }
////            if (i == 77) product.addProductType(productTypeNuevo);
//
//            productRepository.save(product);
//
//        }
//
//        productRepository.findAll().forEach(product ->
//                System.out.println(product));
//
//        productRepository.findByProductTypes_Name("Grocery").forEach(product ->
//                System.out.println(product.getName()
//                        + product.getProductTypes()
//                        .stream()
//                        .map(ProductSubCategory::getName)
//                        .collect(Collectors.toList())
//                        + product.getProductCategories()
//                        .stream()
//                        .map(ProductCategory::getName)
//                        .collect(Collectors.toList()))
//        );


//    }

    @Bean
    public ITemplateResolver defaultTemplateResolver() {
        FileTemplateResolver resolver = new FileTemplateResolver();
        resolver.setSuffix(properties.getSuffix());
        resolver.setPrefix(templatesRoot);
        resolver.setTemplateMode(properties.getMode());
        resolver.setCacheable(properties.isCache());
        return resolver;
    }


}
