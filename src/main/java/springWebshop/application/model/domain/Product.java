package springWebshop.application.model.domain;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private double basePrice;
	private String name;
	private String description;
	private String fullImageUrl;
	private String thumbnailUrl;

	@ManyToOne
	private ProductType productType;

}
