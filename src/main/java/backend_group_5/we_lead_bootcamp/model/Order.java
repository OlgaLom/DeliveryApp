package backend_group_5.we_lead_bootcamp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Builder
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="ORDERS")
@SequenceGenerator(name="idGenerator", sequenceName = "ORDERS_SEQ", initialValue = 1, allocationSize = 1 )
public class Order extends BaseModel{
    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private User user;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY,optional = false )
    private Store store;


    private final Set<OrderItem> orderItems = new HashSet<>();
//    HashSet → No duplicate items, there is no order between items.
    private OrderStatus orderStatus;
    private PaymentMethod paymentMethod;
    private Address address;
    private BigDecimal orderTotal;
    private String orderNumber;
    private String orderNote;
    private Date createDate;
    private Date updateDate;

}
