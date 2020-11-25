package springWebshop.application.model.domain.user;



import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.ConstraintMode;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.Constraint;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="Companies")
public class Company{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private String name;
	private int VAT;
	
	private int phoneNumber;
	private int mobileNumber;
	
//	@ElementCollection(fetch = FetchType.EAGER)
//	private List<Address> addresses = new ArrayList<>();
	
	@OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
	@JoinColumn(name = "companyId")
	private List<Customer> customers = new ArrayList<>();

	@Override
	public String toString() {
		return "Company [id=" + id + ", name=" + name + ", VAT=" + VAT + ", phoneNumber=" + phoneNumber
				+ ", mobileNumber=" + mobileNumber + " customer=" + customers + "]";
	}
	
	
	
	
	
	
}
