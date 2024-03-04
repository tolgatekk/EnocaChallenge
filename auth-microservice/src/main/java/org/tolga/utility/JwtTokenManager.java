package org.tolga.utility;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.tolga.exception.AuthServiceException;
import org.tolga.exception.ErrorType;

import java.util.Date;
import java.util.Optional;


@Component
public class JwtTokenManager {

    @Value("${authserviceconfig.secrets.secret-key}")
    String secretKey;
    @Value("${authserviceconfig.secrets.issuer}")
    String issuer;

    private final Long expirationTime=1000L*60*13;
   public Optional<String> createToken(Long id) {
        try {
            return Optional.of(JWT.create()
                    .withAudience()
                    .withClaim("id", id)
                    .withIssuer(issuer)
                    .withIssuedAt(new Date(System.currentTimeMillis()))
                    .withExpiresAt(new Date(System.currentTimeMillis() + expirationTime))
                    .sign(Algorithm.HMAC512(secretKey)));
        } catch (Exception e) {
            return Optional.empty();
        }
    }
    public Boolean verifyToken(String token){
        try {
            Algorithm algorithm=Algorithm.HMAC512(secretKey);
            JWTVerifier verifier=JWT.require(algorithm).withIssuer(issuer).build();
            DecodedJWT decodedJWT = verifier.verify(token);

            if (decodedJWT==null){
                return false;
            }

        }catch (Exception e){
            return false;
        }
        return true;
    }
    public Optional<Long> decodeToken(String token){
        try {
            Algorithm algorithm=Algorithm.HMAC512(secretKey);
            JWTVerifier verifier=JWT.require(algorithm).withIssuer(issuer).build();
            DecodedJWT decodedJWT = verifier.verify(token);

            if (decodedJWT==null){
                return Optional.empty();
            }

            Long id = decodedJWT.getClaim("id").asLong();
            return Optional.of(id);
        }catch (Exception e){
            return Optional.empty();
        }
    }

    public Optional<Long> getIdFromToken(String token){
        Algorithm algorithm=Algorithm.HMAC512(secretKey);
        JWTVerifier verifier= JWT.require(algorithm).withIssuer(issuer).build();
        DecodedJWT decodedJWT= verifier.verify(token);

        if (decodedJWT==null){
            throw new AuthServiceException(ErrorType.INVALID_TOKEN);
        }

        Long id=decodedJWT.getClaim("id").asLong();
        return Optional.of(id);
    }

}