package springWebshop.application.model.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
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
@Entity(name ="Orders")
public class Order {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@OneToMany(fetch = FetchType.EAGER,mappedBy = "order", cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REMOVE})
	private List<OrderLine> orderLines;
	
	private int totalNumberOfItem;

	private double totalSum;
	
	private double totalVatSum; 
	
//	@ElementCollection
//	private List<Double> vatPercentages;

	private double totalDiscount;
	
	private double totalPayable;
	
	private Currency currency;

	@Override
	public String toString() {
		return "Order [id=" + id + ", orderLines=" + orderLines + ", totalNumberOfItem=" + totalNumberOfItem
				+ ", totalSum=" + totalSum + ", totalVatSum=" + totalVatSum + ", vatPercentages=" + ""
				+ ", totalDiscount=" + totalDiscount + ", totalPayable=" + totalPayable + ", currency=" + currency
				+ "]";
	}
	public Order() {
		orderLines = new ArrayList<OrderLine>();
	}
	public Order(int totalNumberOfItem, double totalSum, double totalVatSum, double totalDiscount,
			double totalPayable) {
		super();
		orderLines = new ArrayList<OrderLine>();
		this.totalNumberOfItem = totalNumberOfItem;
		this.totalSum = totalSum;
		this.totalVatSum = totalVatSum;
		this.totalDiscount = totalDiscount;
		this.totalPayable = totalPayable;
	}
	
	public void addOrderLine(OrderLine orderLine) {
		orderLines.add(orderLine);
	}
	
	

}
