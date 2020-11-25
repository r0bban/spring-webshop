package springWebshop.application.model.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Data
/**
 * Product category is the second level of product segmentation.
 * Such as Couch, Tables, Chairs etc.
 */
public class ProductCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    String name;
	public ProductCategory(String name) {
		this.name = name;
	}
    
    
}
