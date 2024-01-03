package backend_group_5.we_lead_bootcamp.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Builder
@ToString(callSuper = true)
public class Order extends BaseModel{
    private User user;
    private Store store;
    private final Set<OrderItem> orderItems = new HashSet<>();
//    HashSet → No duplicate items, there is no order between items.
    private OrderStatus orderStatus;
    private PaymentMethod paymentMethod;
    private BigDecimal orderTotal;
    private String orderNumber;
    private String orderNote;
    private Date CreateDate;
    private Date UpdateDate;

}
