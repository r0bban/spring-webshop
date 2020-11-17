package springWebshop.application.thymeleafControllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BaseThymeleafController {

	
	@GetMapping("/")
	public String home() {
		return "index";
	}
	
	
}
