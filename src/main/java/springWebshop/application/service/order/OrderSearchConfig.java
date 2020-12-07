package springWebshop.application.service.order;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class OrderSearchConfig {

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

}
