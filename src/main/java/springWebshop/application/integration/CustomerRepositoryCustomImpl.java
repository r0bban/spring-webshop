package springWebshop.application.integration;

import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;
import springWebshop.application.model.domain.user.Customer;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;
import javax.swing.text.html.Option;
import java.util.Optional;

@Repository
public class CustomerRepositoryCustomImpl implements CustomerRepositoryCustom {
    @PersistenceContext
    EntityManager em;

    @Override
    public Optional<Customer> findById(long id) {

//        Criteria criteria = session.createCriteria(User.class);
//        criteria.setFetchMode("roles", FetchMode.EAGER);
        try {
            CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
            CriteriaQuery criteriaQuery = criteriaBuilder.createQuery(Customer.class);
            Root<Customer> customer = criteriaQuery.from(Customer.class);
            customer.fetch("addresses", JoinType.INNER);
            criteriaQuery.select(customer)
                    .distinct(true);
            TypedQuery<Customer> typedQuery = em.createQuery(criteriaQuery);
            return Optional.of(typedQuery.getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }
}
