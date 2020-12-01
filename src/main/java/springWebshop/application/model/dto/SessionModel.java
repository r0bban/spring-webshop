package springWebshop.application.model.dto;

import lombok.Getter;
import lombok.Setter;
import springWebshop.application.model.domain.user.Account;
import springWebshop.application.service.product.ProductService;

@Getter
@Setter
public class SessionModel {

	
	
	Account user;
	ShoppingCartDTO cart;
	int productPage;
	
	public SessionModel(ProductService productService) {
		// Start session as guest
		cart = new ShoppingCartDTO(productService);
		productPage = 1;
	}


}
