package com.url.shortener.security.jwt;

import java.util.stream.Collectors;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;

import java.security.Key;
import java.util.*;

import com.url.shortener.service.UserDetailsImpl;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

@Component
public class JwtUtils {

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.expiration}")
    private int jwtExpiration;

    public String getJwtFromHeader(HttpServletRequest request){
        String bearerToken = request.getHeader("Authorization");
        if(bearerToken != null && bearerToken.startsWith("Bearer ")){
            return bearerToken.substring(7);
        }
        return null;
    }
    
    public String generateToken(UserDetailsImpl userDetails){
        String username = userDetails.getEmail();
        String roles = userDetails.getAuthorities()
                        .stream()
                        .map(authority -> authority.getAuthority())
                        .collect(Collectors.joining(","));
        return Jwts.builder()
                .subject(username)
                .claim("roles", roles)
                .issuedAt(new Date())
                .expiration(new Date(new Date().getTime() + jwtExpiration))
                .signWith(key())
                .compact();
    }

    public String getUsernameFromJwtToken(String token){
        // System.out.println("payload: "+Jwts.parser()
        //         .verifyWith((SecretKey) key())
        //         .build().parseSignedClaims(token)
        //         .getPayload());
        return Jwts.parser()
                .verifyWith((SecretKey) key())
                .build().parseSignedClaims(token)
                .getPayload().getSubject();
    }

    public Boolean validateToken(String authToken){
        try {
            Jwts.parser().verifyWith((SecretKey) key())
            .build()
            .parseSignedClaims(authToken);
            return true;
        }catch (JwtException e){
            throw new RuntimeException();
        }
        catch (Exception e) {
            throw new RuntimeException();
        }
    }

    private Key key(){
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
    }

}
