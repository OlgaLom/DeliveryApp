package backend_group_5.we_lead_bootcamp.transfer;

import lombok.Data;

@Data
public class JwtAuthenticationResponse {
    private String token;
    private String refreshToken;


}
