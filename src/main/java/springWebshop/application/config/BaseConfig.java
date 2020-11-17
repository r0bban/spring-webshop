package springWebshop.application.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BaseConfig {

	
	@Bean
	public CommandLineRunner testStuffInHere() {
		
		return (args)->{
			System.out.println("Put custom code in this method for easy testing capabilities");
		};
		
	}
	
	
	
}
