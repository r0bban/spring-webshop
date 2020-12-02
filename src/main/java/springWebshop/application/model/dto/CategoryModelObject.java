package springWebshop.application.model.dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import lombok.Getter;
import lombok.Setter;
import springWebshop.application.model.domain.ProductCategory;

@Getter
@Setter
public class CategoryModelObject {
	
	


	@Override
	public String toString() {
		return "CategoryModelObject [selectedCat=" + selectedCat + ", selectedSub=" + selectedSub + ", selectedType="
				+ selectedType + ", categories=" + categories + ", subCategories=" + subCategories + ", types=" + types
				+ "]";
	}

	private long selectedCat;
	private long selectedSub;
	private long selectedType;
	
	private List<ProductCategoryDTO> categories;
	private List<ProductSubCategoryDTO> subCategories;
	private List<ProductTypeDTO> types;
	
	public CategoryModelObject() {
		categories = new  ArrayList<>();
		subCategories = new  ArrayList<>();
		types = new  ArrayList<>();
		
	}


}
