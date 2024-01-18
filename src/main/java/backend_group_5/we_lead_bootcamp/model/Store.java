package backend_group_5.we_lead_bootcamp.model;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@Builder
@ToString(callSuper = true)
public class Store extends BaseModel {

    private Long id;

    @NotBlank(message = "Name field is required")
    private String name;
    @NotBlank(message = "Address field is required")
    private String address;
    @NotNull(message = "Phone number is required")
    private Integer phone;
    @NotNull(message = "VAT Number is required")
    private Integer vatNumber;
    @DecimalMin(value = "0.0", inclusive = false, message = "The minimum order amount must be greater than zero")
    private BigDecimal minOrderAmount;

    //use total amount of products as default order price (o,ti valw sto kalathi dld arkei na einai >0)
    public BigDecimal getDefaultMinOrderAmount(){
        BigDecimal totalOrderAmount = calculateTotalOrderAmount();
        return totalOrderAmount != null ? totalOrderAmount : BigDecimal.ZERO;
    }

    //auto na fugei apo to Store model class se @OrderService
    public BigDecimal calculateTotalOrderAmount() {
        BigDecimal total = BigDecimal.ZERO;
        for (OrderItem item : orderItems) {
            total = total.add(item.getProduct().getPrice().multiply(new BigDecimal(item.getQuantity())));
        }
        return total;
    }
    //minimum order amount that a store wishes for an order (zero = products being default)

    private List<OrderItem> orderItems;
    //an fugei to panw method tote logika to orderItems dn xreiazetai na einai sto Store class

    @NotNull(message = "Category field is required")
    private StoreCategory category;

    private Product product;


}
