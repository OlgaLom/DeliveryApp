package backend_group_5.we_lead_bootcamp.transfer.resource;

import backend_group_5.we_lead_bootcamp.model.OrderStatus;
import backend_group_5.we_lead_bootcamp.model.PaymentMethod;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.Value;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@ToString(callSuper = true)
public class OrderResource extends BaseResource{
    private UserResource user;
    private Set<OrderItemResource> orderItems = new HashSet<>();
    @JsonFormat(shape = JsonFormat.Shape.STRING)  // We declared in our application yml the default date format.
    private Date CreateOrderDate;
    private OrderStatus orderStatus;
    private PaymentMethod paymentMethod;
    private Integer orderTotal;
}
