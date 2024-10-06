package com.airbnb.service;

import com.airbnb.entity.AppUser;
import com.airbnb.payload.AppUserDto;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.annotation.PostConstruct;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.Date;

@Data
@Service
public class JWTService {

    @Value("${jwt.algorithim.key}")
    private String algorithimKey;

    @Value("${jwt.issuer}")
    private String issuer;

    @Value("${jwt.expiry.duration}")
    private int expiryTime;

    private Algorithm algorithm;

    private static final String USER_NAME = "username";

    @PostConstruct
    public void postConstruct()
            throws UnsupportedEncodingException {
         algorithm = Algorithm.HMAC256(algorithimKey);

    }

    public String generateToken(AppUser appUser){
        return JWT.create().
                withClaim(USER_NAME,appUser.getUserName()).
                withExpiresAt(new Date(System.currentTimeMillis()+expiryTime)).
                withIssuer(issuer).
                sign(algorithm);
    }


    public String getUserName(String tokenVal) {

        DecodedJWT decodedJWT = JWT.require(algorithm).withIssuer(issuer).build().verify(tokenVal);
        return decodedJWT.getClaim(USER_NAME).asString();

    }
}
