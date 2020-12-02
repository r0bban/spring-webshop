package springWebshop.application.service.product;

import java.util.ArrayList;
import java.util.List;

import springWebshop.application.model.domain.ProductCategory;
import springWebshop.application.model.domain.ProductSubCategory;
import springWebshop.application.model.domain.ProductType;
import springWebshop.application.model.dto.ProductCategoryDTO;
import springWebshop.application.model.dto.ProductSubCategoryDTO;
import springWebshop.application.model.dto.ProductTypeDTO;

public interface ProductSegmentationService {
	
	List<ProductCategoryDTO> getAllCategories();
	List<ProductSubCategoryDTO> getAllSubCategories(long categoryId);
	List<ProductTypeDTO> getAllTypes(long subCategoryId);
	
	int getNoCategories();
	int getNoSubCategories();
	int getNoTypes();
	
	ArrayList<ProductType> getTypeStore();
	ArrayList<ProductSubCategory> getSubCategoryStore();
	ArrayList<ProductCategory> getCategoryStore();
	
	

}
