package springWebshop.application.model.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="Orderlines")
public class OrderLine {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	private int numberOfItems;

	private double sum;
	
	private double vatSum;

	private double vatPercentage;

	private double discount;
	
	private double sumPayable;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn
	private Order order;
	
	private Currency currency;

}
