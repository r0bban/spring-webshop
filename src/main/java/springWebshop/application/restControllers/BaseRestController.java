package springWebshop.application.restControllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BaseRestController {

	
	@GetMapping("/rest")
	public String home() {
		return "Welcome to rest";
	}
	
	
}
