package com.example.loverbe.service.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtService {

    private String secretCode = "1237219732";

    private Long expTime = 36000l;

    public String createTokenLogin(Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String token = Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + expTime * 1000))
                .signWith(SignatureAlgorithm.HS512, secretCode)
                .compact();
        return token;
    }

    public String getUsernameByToken(String token) {
        String username = Jwts.parser()
                .setSigningKey(secretCode)
                .parseClaimsJws(token)
                .getBody().getSubject();
        return username;
    }

}
