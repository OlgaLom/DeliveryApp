package backend_group_5.we_lead_bootcamp.transfer.resource;

import backend_group_5.we_lead_bootcamp.model.Address;
import backend_group_5.we_lead_bootcamp.model.OrderStatus;
import backend_group_5.we_lead_bootcamp.model.enums.PaymentMethod;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@ToString(callSuper = true)
public class OrderResource extends BaseResource{
    private UserResource user;
    private StoreResource store;
    private Set<OrderItemResource> orderItems = new HashSet<>();
    private OrderStatus orderStatus;
    private PaymentMethod paymentMethod;
    private Address address;
    private BigDecimal orderTotal;
    private String orderNumber;
    private String orderNote;
    @JsonFormat(shape = JsonFormat.Shape.STRING)  // We declared in our application yml the default date format.
    private Date createOrderDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Date updateOrderDate;

}
