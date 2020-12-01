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
	private double vatPercentage;
	private String name;
	private String description;
	private String fullImageUrl;
	private String thumbnailUrl;
	private boolean published;

	@ManyToOne
	private ProductType productType;


}
