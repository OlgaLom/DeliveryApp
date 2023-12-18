package backend_group_5.we_lead_bootcamp.model;

import lombok.Getter;

import java.security.PublicKey;

@Getter
public enum OrderStatus {
    IN_PROGRESS, COMPLETED, CANCELLED;

    public static OrderStatus getDefault(){return IN_PROGRESS;}
}
