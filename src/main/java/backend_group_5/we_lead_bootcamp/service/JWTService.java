package backend_group_5.we_lead_bootcamp.service;

import org.springframework.security.core.userdetails.UserDetails;

import java.util.HashMap;

public interface JWTService {
    String generateToken(UserDetails userDetails);

    String extractUsername(String jwt);

    boolean isTokenValid(String token, UserDetails userDetails);




    Object generateRefreshToken(HashMap<String, Object> extraClaims, UserDetails userDetails);
}
