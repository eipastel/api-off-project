package com.gerenciador.financas.security.jwt;

import com.gerenciador.financas.service.UserDetailsImpl;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.MalformedInputException;
import java.security.Key;
import java.util.Date;

@Component
public class JwtUtils {
    @Value("${projeto.jwtSecret}")
    private String jwtSecret;

    @Value("${projeto.jwtSecret}")
    private int jwtExpirationMs;

    public String generateTokenFromUserDetailsImpl(UserDetailsImpl userDetail) {
        return Jwts.builder().setSubject(userDetail.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + jwtExpirationMs))
                .signWith(getSigninKey(), SignatureAlgorithm.HS512).compact();
    }


    public Key getSigninKey() {
        SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
        return key;

    }

    public boolean validateJwtToken  (String authToken){
        try {
            Jwts.parser().setSigningKey(getSigninKey()).build().parseClaimsJws(authToken);
            return true;

        }catch (MalformedJwtException e) {
        System.out.println("token invalido" + e.getMessage());
        }catch (ExpiredJwtException e) {
            System.out.println("token Expirado" + e.getMessage());
        }catch (UnsupportedJwtException e) {
            System.out.println("token não suportado" + e.getMessage());
        }catch (IllegalArgumentException e) {
            System.out.println("argumento invalido" + e.getMessage());
        }

        return false;

    }
}
