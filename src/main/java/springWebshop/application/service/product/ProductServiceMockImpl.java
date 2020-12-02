package springWebshop.application.service.product;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import springWebshop.application.model.domain.Product;
import springWebshop.application.model.domain.ProductCategory;
import springWebshop.application.model.domain.ProductSubCategory;
import springWebshop.application.model.domain.ProductType;
import springWebshop.application.service.ServiceResponse;
@Service
//@Primary
public class ProductServiceMockImpl implements ProductService {

	List<Product> productStore;
	static int idGenerator = 0;

	public ProductServiceMockImpl() {
		productStore = new ArrayList<Product>();
		for (int i = 0; i < 5; i++) {
			Product localProduct = new Product();
			localProduct.setId(++idGenerator);
			localProduct.setName("Product " + i);
			ProductCategory z = new ProductCategory("ProductCategory " + (i*4));
			ProductSubCategory y = new ProductSubCategory("ProductSubCategory " + (i+33),z);
			ProductType x = new ProductType("ProductType " + (i-20),y);
			localProduct.setProductType(x);
			productStore.add(localProduct);
		}
		Product product = new Product();
		product.setName("Johannes");
		product.setId(++idGenerator);
		productStore.add(product);
		product = new Product();
		product.setName("Hednan");
		product.setId(++idGenerator);
		productStore.add(product);
		product = new Product();
		product.setName("Robert");
		product.setId(++idGenerator);
		productStore.add(product);
	}

	@Override
	public ServiceResponse<Product> getProductById(long id) {
		ArrayList<Product> responseObjects = new ArrayList<>();
		ArrayList<String> errorMessages = new ArrayList<>();
		try {
			responseObjects.add(productStore.get(new Long(id - 1).intValue()));

		} catch (IndexOutOfBoundsException e) {
			errorMessages.add("Couldnt find product with id: " + id);
		}
		ServiceResponse<Product> serviceResponse = new ServiceResponse<>(responseObjects, errorMessages);

		return serviceResponse;
	}

	@Override
	public ServiceResponse<Product> getProductByName(String string) {
		ArrayList<Product> responseObjects = new ArrayList<>();
		ArrayList<String> errorMessages = new ArrayList<>();
		Product localProduct = new Product();
		localProduct.setName(string);

		List<Product> list = productStore.stream().filter(t -> t.getName().compareToIgnoreCase(string) == 0)
				.collect(Collectors.toList());
		responseObjects.addAll(list);
		
		if(list.size() == 0) {
			errorMessages.add("Couldnt find product with name: " + string);
			System.out.println(errorMessages);
			
		}

		ServiceResponse<Product> serviceResponse = new ServiceResponse<>(responseObjects, errorMessages);

		return serviceResponse;
	}

	@Override
	public ServiceResponse<Product> getProducts() {
		System.out.println("Mock GetAllProducts");
		ArrayList<Product> responseObjects = new ArrayList<>();
		ArrayList<String> errorMessages = new ArrayList<>();
		responseObjects.addAll(productStore);
		ServiceResponse<Product> serviceResponse = new ServiceResponse<>(responseObjects, errorMessages);

		return serviceResponse;
	}

	@Override
	public ServiceResponse<Product> getProducts(int page, int size) {
		ArrayList<Product> responseObjects = new ArrayList<>();
		ArrayList<String> errorMessages = new ArrayList<>();
		responseObjects.addAll(paginatedList(page, size));
		ServiceResponse<Product> serviceResponse = new ServiceResponse<>(responseObjects, errorMessages);

		return serviceResponse;
	}

	private List<Product> paginatedList(int page, int size) {
		ArrayList<Product> list = new ArrayList<>();

		for (int i = page * size; i < page * size + size; i++) {
			list.add(productStore.get(i));
		}

		return list;
	}

	@Override
	public ServiceResponse<Product> create(Product newProduct) {
		ArrayList<Product> responseObjects = new ArrayList<>();
		ArrayList<String> errorMessages = new ArrayList<>();
		newProduct.setId(++idGenerator);
		productStore.add(newProduct);
		ServiceResponse<Product> serviceResponse = new ServiceResponse<>(responseObjects, errorMessages);

		return serviceResponse;
	}

	@Override
	public ServiceResponse<Product> update(Product updatedProduct) {
		ArrayList<Product> responseObjects = new ArrayList<>();
		ArrayList<String> errorMessages = new ArrayList<>();
		productStore.set(Long.numberOfTrailingZeros(updatedProduct.getId()), updatedProduct);
		ServiceResponse<Product> serviceResponse = new ServiceResponse<>(responseObjects, errorMessages);

		return serviceResponse;
	}


	@Override
	public ServiceResponse<Product> getProducts(int page) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ServiceResponse<Product> getProducts(ProductSearchConfig productSearchConfig) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ServiceResponse<Product> getProducts(ProductSearchConfig productSearchConfig, int page) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ServiceResponse<Product> getProducts(ProductSearchConfig productSearchConfig, int page, int size) {
		// TODO Auto-generated method stub
		return null;
	}


}
