package springWebshop.application.integration;

import org.springframework.data.jpa.repository.JpaRepository;

import springWebshop.application.model.domain.user.Account;

public interface AccountRepository extends JpaRepository<Account, Long>{

}
