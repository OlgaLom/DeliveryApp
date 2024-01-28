package backend_group_5.we_lead_bootcamp.model;

import backend_group_5.we_lead_bootcamp.model.enums.PaymentMethod;
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
@Table(name="ORDERS" ,indexes = {@Index(columnList = "orderNumber")})
@SequenceGenerator(name="idGenerator", sequenceName = "ORDERS_SEQ", initialValue = 1, allocationSize = 1 )
public class Order extends BaseModel{
    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private User user;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY,optional = false )
    private Store store;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    // mappedBy → Used for bidirectional relationships, we declare that a field with name 'order' in orderItems maps back to order entity
    // cascade → "ALL" we declare that all operations that performed on the order can be performed on the orderItem
    // orphanRemoval → "True" specify that if an orderItem does not have a related order, so it is an orphan item, it must be removed/deleted.
    @OneToMany(mappedBy = "order",fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    // Width Builder.Default we ensure that our Set orderItems, it will have a default value
    @Builder.Default
    private final Set<OrderItem> orderItems = new HashSet<>();
    // HashSet → No duplicate items, there is no order between items.

    // Enumerated → Specify that this field is persistent, and it will be saved in our database as string. [EnumType.ORDINAL is for integers ]
    @Enumerated(EnumType.STRING)
    @Column(length = 11, nullable = false)
    private OrderStatus orderStatus;

    @Enumerated(EnumType.STRING)
    @Column(length = 11, nullable = false)
    private PaymentMethod paymentMethod;

    @NotNull
    @OneToOne( cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private OrderAddress orderAddressList;

    @NotNull
    // precision → How many digits the number can have.
    // scale → how many decimals the number can have
    @Column(precision = 10, scale = 2, nullable = false)
    private BigDecimal orderTotal;

    @NotNull
    @Column(length = 16, nullable = false)
    private String orderNumber;

//    @ToString.Exclude
    private String orderNote;

    // @Temporal → we map the Date with SQL TIMESTAMP
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date createDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date updateDate;

}
