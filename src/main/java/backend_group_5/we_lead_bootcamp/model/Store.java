package backend_group_5.we_lead_bootcamp.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString(callSuper = true)
public class Store extends BaseModel {

    private long id;
    private String name;
    private String address;
    private Integer phone;
    private Integer vatNumber;
    //least order amount
    private StoreCategory category;
    private Product product;


}
