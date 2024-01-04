package backend_group_5.we_lead_bootcamp.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class BaseModel implements Serializable {
    private Long id;
}