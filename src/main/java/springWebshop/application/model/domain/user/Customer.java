package springWebshop.application.model.domain.user;



import java.util.ArrayList;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Entity(name = "customers")
public class Customer extends Account {
	
	
	@ElementCollection(fetch = FetchType.EAGER)
	private List<Address> addresses;
	

	@Override
	public String toString() {
		return "Customer [addresses=" + addresses + " toString()=" + super.toString() + "]";
	}
	
	public Customer() {
		addresses = new ArrayList<>();
	}
	
	

}
