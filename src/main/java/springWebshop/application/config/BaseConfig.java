package springWebshop.application.config;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.thymeleaf.ThymeleafProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.templateresolver.FileTemplateResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;

import springWebshop.application.integration.AccountRepository;
import springWebshop.application.integration.CompanyRepository;
import springWebshop.application.integration.ProductRepository;
import springWebshop.application.integration.ProductTypeRepository;
import springWebshop.application.model.domain.user.Company;
import springWebshop.application.model.domain.user.Customer;

@Configuration
public class BaseConfig {

	@Autowired
	ProductRepository productRepository;
	@Autowired
	ProductTypeRepository typeRepo;
	
	@Autowired
	AccountRepository accountRepository;
	@Autowired
	CompanyRepository companyRepository;
	
	
	
    @Bean
    public CommandLineRunner testStuffInHere() {

        return (args) -> {
            System.out.println("Put custom code in this method for easy testing capabilities");
//            ProductType type1 = new ProductType();
//            type1.setName("Soffa");
//            ProductType type2 = new ProductType();
//            type2.setName("Bord");
//            ProductType type3 = new ProductType();
//            type3.setName("IceCream");
//            typeRepo.save(type1);
//            typeRepo.save(type2);
//            typeRepo.save(type3);
//
//
//            for (int i = 0; i < 100; i++) {
//
//                Product product = new Product();
//                product.setTitle("Product " + i);
//                product.getProductTypes().add(i < 51
//                        ? typeRepo.findByName("Soffa")
//                        : typeRepo.findByName("Bord"));
//                if(i % 3 == 0) {product.getProductTypes().add(typeRepo.findById(3L).get());}
//                productRepository.save(product);
//            }
//
//            productRepository.findAll().forEach(product ->
//                    System.out.println(product));
//
//            productRepository.findByProductTypes_Name("Soffa").forEach(product ->
//                    System.out.println(product.getTitle() + product.getProductTypes().stream().map(ProductType::getName).collect(Collectors.toList()))
//                    );
            
        	Company company1 = new Company();
        	company1.setName("Ikea");
        	Company company2 = new Company();
        	company2.setName("ABAB");
        	Company company3 = new Company();
        	company3.setName("SWECO");
        	
        	Customer customer1 = new Customer();
        	customer1.setFirstName("Johannes");
        	Customer customer2 = new Customer();
        	customer2.setFirstName("Robert");
        	Customer customer3 = new Customer();
        	customer3.setFirstName("Kalle");
        	Customer customer4 = new Customer();
        	customer4.setFirstName("Tess");
        	accountRepository.save(customer4);
        	
//        	customer1.setCompany(company2);
//        	customer2.setCompany(company2);
//        	customer3.setCompany(company3);
        	
        	company2.getCustomers().add(customer1);
        	company2.getCustomers().add(customer2);
        	company3.getCustomers().add(customer3);
        	
        	
        	
        	
        	companyRepository.save(company1);
        	companyRepository.save(company2);
        	companyRepository.save(company3);
        	companyRepository.test().forEach(company->System.out.println(company + "\n"));
        	
        	Optional<Company> oldCompany = companyRepository.findById(3L);
        	if(oldCompany.isPresent()) {
        		companyRepository.delete(oldCompany.get());
        	}
        	
//        	companyRepository.findAll().forEach(System.out::println);
//        	accountRepository.findAll().forEach(System.out::println);
        	
        	
        	
        	
        };


    }
    
	@Autowired
	private ThymeleafProperties properties;

	@Value("${spring.thymeleaf.templates_root:}")
	private String templatesRoot;
    
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
