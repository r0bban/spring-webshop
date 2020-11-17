package springWebshop.application.model.domain;

import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name ="Orders")
public class Order {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@OneToMany(fetch = FetchType.LAZY,mappedBy = "order")
	private List<OrderLine> orderLines;
	
	private int totalNumberOfItem;

	private double totalSum;
	
	private double totalVatSum; 
	
	@ElementCollection
	private List<Double> vatPercentages;

	private double totalDiscount;
	
	private double totalPayable;
	
	private Currency currency;

}
