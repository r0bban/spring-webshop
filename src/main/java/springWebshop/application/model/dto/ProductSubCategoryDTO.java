package springWebshop.application.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductSubCategoryDTO {
	private long id;
	private String name;
	public ProductSubCategoryDTO(long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	@Override
	public String toString() {
		return "ProductSubCategoryDTO [id=" + id + ", name=" + name + "]";
	}
	

}
