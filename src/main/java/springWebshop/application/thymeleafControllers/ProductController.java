package springWebshop.application.thymeleafControllers;

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
import springWebshop.application.model.domain.ProductType;
import springWebshop.application.model.dto.CategoryModelObject;
import springWebshop.application.model.dto.ProductFormModel;
import springWebshop.application.model.dto.SessionModel;
import springWebshop.application.service.ServiceResponse;
import springWebshop.application.service.product.ProductCategoryService;
import springWebshop.application.service.product.ProductSearchConfig;
import springWebshop.application.service.product.ProductSegmentationService;
import springWebshop.application.service.product.ProductServiceMockImpl;
import springWebshop.application.service.product.ProductTypeService;

@Controller
@RequestMapping("webshop")
@SessionAttributes({"sessionModel"})
public class ProductController {

	/// ÄNDRADE FRÅN INTERFACE TILL KONKRETE IMPL FÖRATT KOMMA ÅT EGNA Metode
	@Autowired
	ProductServiceMockImpl productService;

	@Autowired
	ProductCategoryService productCategoryService;
	@Autowired
	ProductSegmentationService productSegmentationService;

	@Autowired
	ProductTypeService productTypeService;


	@ModelAttribute("sessionModel")
	private SessionModel getShoppingCart() {
		return new SessionModel(productService);
	}

	@ModelAttribute("categoriesAvailable")
	private CategoryModelObject getAllCategories(){
		CategoryModelObject model = new CategoryModelObject();
		model.setCategories(productSegmentationService.getAllCategories());
		System.out.println(model);
		return model;
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

	@GetMapping(path = { "products","products/{category}","products/{category}/{subcategory}","products/{category}/{subcategory}/{type}" })
	public String getAllProducts(
			@PathVariable(name = "category",required = false) Optional<String> category,
			@PathVariable(name = "subcategory",required = false) Optional<String> subcategory,
			@PathVariable(name = "type",required = false) Optional<String> type,
			@ModelAttribute("sessionModel") SessionModel session,
			@RequestParam(required = false, name = "page") Optional<Integer> pathPage, Model m) {
		
		selectFilteredProducts(category,subcategory,type);
		int currentPage = pathPage.isPresent() ? pathPage.get() : session.getProductPage();
		
	
		
		ServiceResponse<Product> response = productService.getProducts(null,currentPage > 0 ? currentPage - 1 : 0, 10);
//		ServiceResponse<Product> response = productService.getProducts(currentPage > 0 ? currentPage - 1 : 0, 10);
		m.addAttribute("allProducts", response.getResponseObjects());
		
		
		// Doesnt return Error Message? Empty list
		session.setProductPage(currentPage);
		m.addAttribute("totalPages", response.getTotalPages());
		m.addAttribute("sessionModel", session);

		
		
		
		
		
		
		return "displayProducts";
	}
	
	private void selectFilteredProducts(Optional<String> category, Optional<String> subcategory, Optional<String> type) {
		System.out.println(category);
		System.out.println(subcategory);
		System.out.println(type);
	}

	@PostMapping(path = { "products" })
	public String postAddItemToCart(@ModelAttribute("categoriesAvailable") CategoryModelObject categoryDTO,
			@RequestParam("id") Optional<Integer> productId,@ModelAttribute("sessionModel") SessionModel session,
			@RequestParam(required = false, name = "page") Optional<Integer> pathPage, Model m) {
		System.out.println("POST from products");
		System.out.println(categoryDTO);
		ProductSearchConfig config = new ProductSearchConfig();
		config.setProductCategoryId(categoryDTO.getSelectedCat());
		config.setProductSubCategoryId(categoryDTO.getSelectedSub());
		config.setProductTypeId(categoryDTO.getSelectedType());
		
		
		if(productId.isPresent())
			session.getCart().addItem(productId.get());
		int currentPage = pathPage.isPresent() ? pathPage.get() : session.getProductPage();
		
		ServiceResponse<Product> response = productService.getProducts(config,currentPage > 0 ? currentPage - 1 : 0, 10);
//		if(config.getProductSubCategoryId()>0) {
//			categoryDTO.setSelectedSub(pro);
//			if(config.getProductTypeId()>0) {
//				// Fetch All Types connected in heirarchy
//				categoryDTO.setSelectedType(selectedType);
//			}
//			
//		}
		
		
		m.addAttribute("allProducts", response.getResponseObjects());
		// Doesnt return Error Message? Empty list
		
		m.addAttribute("totalPages", response.getTotalPages());
		m.addAttribute("sessionModel", session);

		return "displayProducts";
	}
	
	@GetMapping("/products/product/{id}")
	public String getProduct(Model m,@PathVariable("id") long productId,
			@ModelAttribute("sessionModel") SessionModel session) {
		System.out.println("GetProduct");
		ServiceResponse<Product> response = productService.getProductById(productId);
		m.addAttribute("currentProduct", response.getResponseObjects().get(0));
//		m.addAttribute("quantity", session.getCart().getProductMap())
		return "displayProduct";
	}
	
	@PostMapping(value = "/products/product/{id}",params = "cartAction=Add")
	public String postAddItemToCart(Product product, @PathVariable("id") Optional<Integer> productId,@ModelAttribute("sessionModel") SessionModel session, Model m) {
		if(productId.isPresent())
			session.getCart().addItem(productId.get());
		System.out.println("POST");
		m.addAttribute("currentProduct", product);
		System.out.println(session.getCart());
		return "displayProduct";
	}
	@PostMapping(value = "/products/product/{id}",params = "cartAction=Remove")
	public String postRemoveItemToCart(Product product, @PathVariable("id") Optional<Integer> productId, @ModelAttribute("sessionModel") SessionModel session, Model m) {
		if(productId.isPresent())
		session.getCart().removeItem(productId.get());
		System.out.println("POST");
		m.addAttribute("currentProduct", product);
		System.out.println(session.getCart());
		return "displayProduct";
	}
	
	
	
	@GetMapping("shoppingcart")
	public String getShoppingCart(@ModelAttribute SessionModel sesionModel, Model m) {
		System.out.println(sesionModel);
		
		
		
		return "displayShoppingCart";
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
