package springWebshop.application.service.product;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import springWebshop.application.integration.ProductCategoryRepository;
import springWebshop.application.integration.ProductSubCategoryRepository;
import springWebshop.application.integration.ProductTypeRepository;
import springWebshop.application.model.domain.ProductCategory;
import springWebshop.application.model.domain.ProductSubCategory;
import springWebshop.application.model.domain.ProductType;
import springWebshop.application.model.dto.ProductCategoryDTO;
import springWebshop.application.model.dto.ProductSubCategoryDTO;
import springWebshop.application.model.dto.ProductTypeDTO;

@Service("ProductSegmentationServiceImpl")
public class ProductSegmentationServiceImpl implements ProductSegmentationService {
	
	@Autowired
	ProductCategoryRepository productCategoryRepository;
	
	@Autowired
	ProductSubCategoryRepository productSubCategoryRepository;
	
	@Autowired
	ProductTypeRepository productTypeRepository;
	
	

	@Override
	public List<ProductCategoryDTO> getAllCategories() {
		return productCategoryRepository.findAll().stream()
				.map(productCategory -> new ProductCategoryDTO(productCategory.getId(), productCategory.getName()))
				.collect(Collectors.toList());
	}

	@Override
	public List<ProductSubCategoryDTO> getSubCategoriesByCategoryId(long categoryId) {
		return productSubCategoryRepository.findAll().stream()
				.filter(subCategory -> subCategory.getProductCategory().getId() == categoryId)
				.map(productSubcategory -> new ProductSubCategoryDTO(productSubcategory.getId(),
						productSubcategory.getName()))
				.collect(Collectors.toList());
	}

	@Override
	public List<ProductTypeDTO> getTypesBySubCategoryId(long subCategoryId) {
		return productTypeRepository.findAll().stream()
				.filter(type -> type.getProductSubCategory().getId() == subCategoryId)
				.map(productType -> new ProductTypeDTO(productType.getId(), productType.getName()))
				.collect(Collectors.toList());
	}

	@Override
	public int getNoCategories() {
		return (int) productCategoryRepository.count();
	}

	@Override
	public int getNoSubCategories() {
		return (int) productSubCategoryRepository.count();
	}

	@Override
	public int getNoTypes() {
		return (int) productTypeRepository.count();
	}

	@Override
	public ArrayList<ProductType> getTypeStore() {
		return null;
	}

	@Override
	public ArrayList<ProductSubCategory> getSubCategoryStore() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<ProductCategory> getCategoryStore() {
		// TODO Auto-generated method stub
		return null;
	}

}
