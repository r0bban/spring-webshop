package springWebshop.application.integration;

import org.springframework.data.jpa.repository.JpaRepository;
import springWebshop.application.model.domain.user.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
