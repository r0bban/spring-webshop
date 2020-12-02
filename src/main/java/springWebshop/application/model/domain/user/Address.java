package springWebshop.application.model.domain.user;


import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
//@AllArgsConstructor
//@Embeddable
@Entity
public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	long id;
	private String street;
	private int zipCode;
	private String city;
	private String country;

	private AddressType addressType;
	
	private boolean defaultAddress;

	public Address(String street, int zipCode, String city, String country) {
		this.street = street;
		this.zipCode = zipCode;
		this.city = city;
		this.country = country;
	}
}
