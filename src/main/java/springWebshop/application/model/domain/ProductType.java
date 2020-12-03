package springWebshop.application.model.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Data
@Entity
@NoArgsConstructor
/**
 * Product type is most specific (lowest) level of product segmentation
 * which is a child entity of Product Subcategory.
 */
public class ProductType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @NotBlank
    String name;

    @ManyToOne
    @NotNull
    private ProductSubCategory productSubCategory;

    public ProductType(String name, ProductSubCategory productSubCategory) {
        this.name = name;
        this.productSubCategory = productSubCategory;
    }

	@Override
	public String toString() {
		return "ProductType [name=" + name + ", productSubCategory=" + productSubCategory.toString() + "]";
	}
    
	public String getFullyQualifiedName() {
		return productSubCategory.getFullyQualifiedName() + "/" +  name;
	}
    

}
