package springWebshop.application.model.domain.user;



import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import springWebshop.application.model.domain.Order;

@Getter
@Setter
@AllArgsConstructor
@Entity(name = "customers")
public class Customer extends Account {
	
	
//	@ElementCollection(fetch = FetchType.LAZY)
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Address> addresses;

	@OneToMany (mappedBy = "customer")
	private Set<Order> orders;

	@Override
	public String toString() {
		return "Customer [addresses=" + addresses + " toString()=" + super.toString() + "]";
	}
	
	public Customer() {
		addresses = new ArrayList<>();
	}

	public void addAddress(Address address){
		this.addresses.add(address);
	}
}
