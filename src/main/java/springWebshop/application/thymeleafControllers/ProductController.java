package springWebshop.application.thymeleafControllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import springWebshop.application.model.domain.Product;
import springWebshop.application.model.domain.ProductCategory;
import springWebshop.application.model.domain.ProductSubCategory;
import springWebshop.application.model.domain.ProductType;
import springWebshop.application.model.dto.ProductFormModel;
import springWebshop.application.model.dto.SessionModel;
import springWebshop.application.model.dto.ShoppingCartDTO;
import springWebshop.application.service.ServiceResponse;
import springWebshop.application.service.product.ProductCategoryService;
import springWebshop.application.service.product.ProductService;
import springWebshop.application.service.product.ProductTypeService;

@Controller
@RequestMapping("webshop")
@SessionAttributes({"sessionModel"})
public class ProductController {

	@Autowired
	ProductService productService;

	@Autowired
	ProductCategoryService productCategoryService;

	@Autowired
	ProductTypeService productTypeService;

	@ModelAttribute("categories")
	private List<ProductCategory> getAllCategoriesFromService() {
		return productCategoryService.getAllProductCategories();
	}

	@ModelAttribute("types")
	private List<ProductSubCategory> getAllTypesFromService() {
		return productTypeService.getAllProductTypes();
	}

	@ModelAttribute("sessionModel")
	private SessionModel getShoppingCart() {
		return new SessionModel();
	}
	
	
//
//	@PostConstruct
//	void init() {
//		productCategoryService.save(new ProductCategory("Tools"));
//		productCategoryService.save(new ProductCategory("Furniture"));
//		productCategoryService.save(new ProductCategory("Grocery"));
//
//		productTypeService.save(new ProductSubCategory("Hammers"));
//		productTypeService.save(new ProductSubCategory("Couches"));
//		productTypeService.save(new ProductSubCategory("Ice Cream"));*//*
//	}

	@GetMapping()
	public String home(Model model) {
		model.addAttribute("newProduct", new ProductFormModel());
		return "createNewProduct";
	}

	@PostMapping()
	public String postHome(ProductFormModel postData, Model model) {
		System.out.println(postData);
		model.addAttribute("newProduct", new ProductFormModel());
		postData.getDomainProduct().setProductType(new ProductType());
		productService.create(postData.getDomainProduct());
		return "createNewProduct";
	}

	@GetMapping(path = { "products" })
	public String getAllProducts(@ModelAttribute("sessionModel") SessionModel session,
			@RequestParam(required = false, name = "page") Optional<Integer> pathPage, Model m) {
		int currentPage = pathPage.isPresent() ? pathPage.get() : session.getProductPage();
		
		ServiceResponse<Product> response = productService.getAllProducts(currentPage > 0 ? currentPage - 1 : 0, 10);
		m.addAttribute("allProducts", response.getResponseObjects());
		System.out.println(response.getCurrentPage());
		System.out.println(response.getTotalItems());
		System.out.println(response.getTotalPages());
		// Doesnt return Error Message? Empty list
		System.out.println(response.getErrorMessages());
		session.setProductPage(currentPage);
		m.addAttribute("totalPages", response.getTotalPages());
		m.addAttribute("sessionModel", session);

		return "displayProducts";
	}
	
	@GetMapping("/products/product/{id}")
	public String getProduct(Model m,@PathVariable("id") long productId) {
		System.out.println("GetProduct");
		ServiceResponse<Product> response = productService.getProductById(productId);
		m.addAttribute("currentProduct", response.getResponseObjects().get(0));
		
		return "displayProduct";
	}
	@PostMapping("/products/product/{id}")
	public String postProduct(Product product,
			@ModelAttribute("shoppingCart") ShoppingCartDTO cart,
			 @RequestParam(name="cartAction",required = false) Optional<String> action,Model m) {
		System.out.println("POST");
		System.out.println(product);
		System.out.println("What to do with the basket?" + action);
		if(action.isPresent()) {
			
			if(action.get().compareToIgnoreCase("add")==0) {
				cart.addItem(product);
			}
			else if(action.get().compareToIgnoreCase("remove")==0) {
				cart.removeItem(product);
			}
			
		}
		m.addAttribute("currentProduct", product);
		
		System.out.println(cart);
		
		return "displayProduct";
	}

	

//	@PostMapping("products")
//	public String postProduct(Product product, Model m) {
//		System.out.println(product);
//		productService.create(product);
//		m.addAttribute("newProduct", new Product());
//		m.addAttribute("allProducts", productService.getAllProducts(0, 2).getResponseObjects());
//		return "displayProducts";
//	}
//
//	@PostMapping("/category/newCategory")
//	public String postCategory(ProductFormModel postData, Model model) {
//		System.out.println("Category:"+postData.getNewCategory());
//		productCategoryService.save(new ProductCategory(postData.getNewCategory()));
//		model.addAttribute("newProduct", new ProductFormModel());
//		return "forward:/webshop/createNewProduct";
//	}
//	@PostMapping("/type/newType")
//	public String postType(ProductFormModel postData, Model model) {
//		System.out.println("Type:"+postData.getNewType());
//		productTypeService.save(new ProductSubCategory(postData.getNewType()));
//		model.addAttribute("newProduct", new ProductFormModel());
//		return "forward:/webshop/";
//	}

}
