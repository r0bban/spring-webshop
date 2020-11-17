package springWebshop.application.model.domain.user;



import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name="Companies")
public class Company{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private int VAT;
	
	private int phoneNumber;
	private int mobileNumber;
	
	@ElementCollection
	private List<Address> addresses;
	
	@OneToMany
	private List<Account> customer;
}
