package backend_group_5.we_lead_bootcamp.model;

import lombok.Getter;

@Getter
public enum PaymentMethod {
    COD, CREDIT_CARD, PAYPAL;

    public static PaymentMethod getDefault(){ return COD;}

}
