package springWebshop.application.integration;

import springWebshop.application.model.domain.user.Customer;

import java.util.Optional;

public interface CustomerRepositoryCustom {
    Optional<Customer> findById(long id);
}
