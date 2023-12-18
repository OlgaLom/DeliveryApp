package backend_group_5.we_lead_bootcamp.model;

import com.fasterxml.jackson.databind.ser.Serializers;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@ToString(callSuper = true)
public class OrderItem extends BaseModel {
    private Product product;
    private Integer quantity;
    private BigDecimal price;
}
