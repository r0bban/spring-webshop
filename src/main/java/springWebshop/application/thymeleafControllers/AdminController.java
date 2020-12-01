package springWebshop.application.thymeleafControllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import springWebshop.application.model.domain.Product;
import springWebshop.application.model.dto.ShoppingCartDTO;
import springWebshop.application.service.ServiceResponse;
import springWebshop.application.service.product.ProductService;

@Controller
@RequestMapping("webshop/admin")
public class AdminController {
	
	@Autowired
	ProductService productService;

	@GetMapping(path = { "products" })
	public String getAllProducts( Model m) {
		ServiceResponse<Product> response = productService.getAllProducts();
		m.addAttribute("allProducts", response.getResponseObjects());

		return "adminProductsView";
	}
	
	@GetMapping(path = { "orders" })
	public String getAllOrders(Model m) {

		return "adminOrdersView";
	}
	
	@GetMapping(path = { "users" })
	public String getAllUsers( Model m) {

		return "adminUsersView";
	}
	
	
}
