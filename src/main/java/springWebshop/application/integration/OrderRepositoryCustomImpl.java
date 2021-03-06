package springWebshop.application.integration;

import org.springframework.data.domain.Page;
import springWebshop.application.model.domain.Order;
import springWebshop.application.service.order.OrderSearchConfig;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderRepositoryCustomImpl extends AbstractCustomRepository<Order> implements OrderRepositoryCustom {
    @Override
    public Page<Order> getOrders(OrderSearchConfig config, int page, int size) {
        List<Predicate> predicates = new ArrayList<>();

        try {
            CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
            CriteriaQuery<Order> criteriaQuery = criteriaBuilder.createQuery(Order.class);
            Root<Order> orderRoot = criteriaQuery.from(Order.class);
            if (config.getCustomerId() != null) {
                predicates.add(criteriaBuilder.equal(
                        orderRoot.get("customer").get("id"),
                        config.getCustomerId()));
            }
            if (config.getCreatedEarliest() != null) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(orderRoot.get("created"), config.getCreatedEarliest()));
            }
            if (config.getCreatedLatest() != null) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(orderRoot.get("created"), config.getCreatedLatest()));
            }
            if (config.getDispatchedEarliest() != null) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(orderRoot.get("dispatched"), config.getDispatchedEarliest()));
            }
            if (config.getDispatchedLatest() != null) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(orderRoot.get("dispatched"), config.getDispatchedLatest()));
            }
            if (config.getSentForDeliveryEarliest() != null) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(orderRoot.get("inDelivery"), config.getSentForDeliveryEarliest()));
            }
            if (config.getSentForDeliveryLatest() != null) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(orderRoot.get("inDelivery"), config.getSentForDeliveryLatest()));
            }
            if (config.getMinTotalSum() != null) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(orderRoot.get("totalSum"), config.getMinTotalSum()));
            }
            if (config.getMaxTotalSum() != null) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(orderRoot.get("totalSum"), config.getMaxTotalSum()));
                predicates.add(criteriaBuilder.isNotNull(orderRoot.get("totalSum")));
            }

            if (config.getStatus() != null) {
                if (config.getStatus() == Order.OrderStatus.CANCELED) {
                    predicates.add(criteriaBuilder.isNotNull(orderRoot.get("canceled")));
                } else {
                    predicates.add(criteriaBuilder.isNull(orderRoot.get("canceled")));
                    switch (config.getStatus()) {
                        case NOT_HANDLED:
                            predicates.add(criteriaBuilder.isNull(orderRoot.get("dispatched")));
                            predicates.add(criteriaBuilder.isNull(orderRoot.get("inDelivery")));
                            predicates.add(criteriaBuilder.isNull(orderRoot.get("deliveryComplete")));
                            break;
                        case DISPATCHED:
                            predicates.add(criteriaBuilder.isNotNull(orderRoot.get("dispatched")));
                            predicates.add(criteriaBuilder.isNull(orderRoot.get("inDelivery")));
                            predicates.add(criteriaBuilder.isNull(orderRoot.get("deliveryComplete")));
                            break;
                        case DELIVERY:
                            predicates.add(criteriaBuilder.isNotNull(orderRoot.get("inDelivery")));
                            predicates.add(criteriaBuilder.isNull(orderRoot.get("deliveryComplete")));
                            break;
                        case DELIVERY_COMPLETED:
                            predicates.add(criteriaBuilder.isNotNull(orderRoot.get("deliveryComplete")));
                            break;
                    }
                }
            }

            criteriaQuery.select(orderRoot).distinct(true).where(predicates.toArray(new Predicate[0]));
            if (config.getSortBy() != null) {
                criteriaQuery.orderBy(criteriaBuilder.desc(orderRoot.get(config.getSortBy().name())));
            }

            TypedQuery<Order> typedQuery = em.createQuery(criteriaQuery);

            return getPaginatedResult(page, size, predicates, typedQuery, Order.class);
        } catch (NoResultException e) {
            return null;
        }
    }
}
