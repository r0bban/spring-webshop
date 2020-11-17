package springWebshop.application.model.domain.user;



import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "customers")
public class Customer extends Account {
	
	
	@ElementCollection
	private List<Address> addresses;
	
	@ManyToOne
	private Company company;
	

}
