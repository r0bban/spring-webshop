package springWebshop.application.model.dto;

import lombok.Getter;
import lombok.Setter;
import springWebshop.application.model.domain.user.Account;

@Getter
@Setter
public class SessionModel {

	Account user;
	ShoppingCartDTO cart;
	int productPage;
	
	public SessionModel() {
		// Start session as guest
		cart = new ShoppingCartDTO();
		productPage = 1;
	}


}
