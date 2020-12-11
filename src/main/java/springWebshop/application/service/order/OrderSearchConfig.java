package springWebshop.application.service.order;

import lombok.*;
import springWebshop.application.model.domain.Order;

import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderSearchConfig {

    Order.OrderStatus status;
    Date createdEarliest;
    Date createdLatest;
    Date dispatchedEarliest;
    Date dispatchedLatest;
    Date sentForDeliveryEarliest;
    Date sentForDeliveryLatest;
    Double minTotalSum;
    Double maxTotalSum;
    Long customerId;
    SortBy sortBy;

    public static enum SortBy {
        created,
        dispatched,
        inDelivery,
        deliveryComplete,
        canceled,
        totalSum
    }

    @Override
    public String toString() {
        return "OrderSearchConfig{" +
                "\nstatus=" + status +
                "\ncreatedEarliest=" + createdEarliest +
                "\ncreatedLatest=" + createdLatest +
                "\ndispatchedEarliest=" + dispatchedEarliest +
                "\ndispatchedLatest=" + dispatchedLatest +
                "\nsentForDeliveryEarliest=" + sentForDeliveryEarliest +
                "\nsentForDeliveryLatest=" + sentForDeliveryLatest +
                "\nminTotalSum=" + minTotalSum +
                "\nmaxTotalSum=" + maxTotalSum +
                "\ncustomerId=" + customerId +
                "\nsortBy=" + sortBy +
                '}';
    }
}
