package springWebshop.application.model.dto;

import lombok.Getter;
import lombok.Setter;
import springWebshop.application.model.domain.user.Account;
import springWebshop.application.service.product.ProductSegmentationService;
import springWebshop.application.service.product.ProductService;

@Getter
@Setter
public class SessionModel {

	
	


	@Override
	public String toString() {
		return "SessionModel [productPage=" + productPage + ", categoryModel=" + categoryModel + "]";
	}

	private Account user;
	private ShoppingCartDTO cart;
	private int productPage;
	private CategoryModelObject categoryModel;
	
	public SessionModel(ProductService productService, ProductSegmentationService productSegmentationService) {
		// Start session as guest
		cart = new ShoppingCartDTO(productService);
		productPage = 1;
		categoryModel = new CategoryModelObject();
		categoryModel.setCategories(productSegmentationService.getAllCategories());
	}


}
