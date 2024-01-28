package backend_group_5.we_lead_bootcamp.transfer.resource;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(callSuper = true)
public class OrderAddressResource extends BaseResource {
    private String address;
    private Integer streetNumber;
    private String city;
}
