package springWebshop.application.model.domain.user;


import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Address {

	private int zipCode;
	private String street;
	private String country;
	
	private AddressType addressType;
	
	private boolean defaultAddress;
	
	
}
