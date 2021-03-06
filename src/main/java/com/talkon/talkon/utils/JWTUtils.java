package com.talkon.talkon.utils;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;

public class JWTUtils {
    public static Integer expiry = 1_800_000_000;
    public static String secret = "SD784SRTED34JNBNJ@@*&p45kbNBKRIIHB@456#$%&f%&t#sxyASX345";

    public static Date getExpiry() {
        return new Date(System.currentTimeMillis() + expiry);
    }


    public static Date getExpiryForRefreshToken() {
        return new Date(System.currentTimeMillis() + TimeUnit.DAYS.toMillis(40));
    }

    public static Algorithm getAlgorithm() {
        return Algorithm.HMAC256(secret.getBytes());
    }


    public static JWTVerifier getVerifier() {
        return JWT.require(getAlgorithm()).build();
    }
}
