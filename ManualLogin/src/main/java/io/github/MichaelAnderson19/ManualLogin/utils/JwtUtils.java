package io.github.MichaelAnderson19.ManualLogin.utils;

import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Date;

/**
 * a collection of utility methods for dealing with jwts including
 * validating, generating and getting claims from them
 */
@Slf4j
@Component
public class JwtUtils {

    @Value("${app.jwt.secret}")
    private String jwtSecret;

    @Value("${app.jwt.expirationLength.Seconds:86400}")
    private int jwtExpirationMinutes;


    public String getEmailFromToken(String jwt){
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(jwt).getBody().getSubject();
    }

    /**
     * parsing the jwt into a collections of claims will fail if the jwt is not valid
     * @param jwt
     * @return
     */
    public boolean validateJwt(String jwt) {
        try {
            Jws<Claims>  claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(jwt);
            return true;
        } catch(Exception e) {
            String errorMessage = "Invalid JWT %s";
            if(e instanceof UnsupportedJwtException exception)
                log.error(errorMessage.formatted("provided token is not a claims JWS"));
            if(e instanceof MalformedJwtException exception)
                log.error(errorMessage.formatted("provided token is not a valid claims JWS"));
            if(e instanceof SignatureException exception)
                log.error(errorMessage.formatted("signature validation has failed"));
            if(e instanceof ExpiredJwtException exception)
                log.error(errorMessage.formatted("the provided token has expired"));
            if(e instanceof IllegalArgumentException exception)
                log.error(errorMessage.formatted("the provided token is null or empty"));
        }
        return false;
    }

    public String generateToken(final String email){

            final Instant now = Instant.now();
            return Jwts.builder()
                    .setSubject(email)
                    .setIssuedAt(Date.from(now))
                    .setExpiration(Date.from(now.plusSeconds(86400)))
                    .signWith(SignatureAlgorithm.HS512, jwtSecret)
                    .compact();

    }




}
