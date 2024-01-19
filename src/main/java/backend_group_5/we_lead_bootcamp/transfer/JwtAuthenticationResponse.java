package backend_group_5.we_lead_bootcamp.transfer;

import lombok.Data;

@Data
public class JwtAuthenticationResponse {

    private String token;

    public JwtAuthenticationResponse(String token) {

        this.token = token;
    }


    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}
