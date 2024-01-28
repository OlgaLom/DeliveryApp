package backend_group_5.we_lead_bootcamp.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class Review extends BaseModel{

    @Column(nullable = false)
    private Double rating;
    @Column(nullable = false,length = 500)
    private String comment;

    @ManyToOne
    @JoinColumn(name ="STORE_ID")
    private Store store;


}
