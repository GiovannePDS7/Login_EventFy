package com.eventfy.login.security;

import com.eventfy.login.dto.OrganizadorAuth;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.time.Duration;
import java.util.Date;

@Component
public class JwtToken {

    @Value("${token.security.key}")
    private String jwtSecurityKey;

    @Value("${token.security.expiration-time}")
    private Duration jwtExpirationTime;

    private Key getChaveAssinatura() {
        return Keys.hmacShaKeyFor(jwtSecurityKey.getBytes());
    }

    private Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getChaveAssinatura())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public String gerar(UserDetails userDetails) {
        OrganizadorAuth organizadorAuth = (OrganizadorAuth) userDetails;
        return Jwts.builder()
                .setSubject(organizadorAuth.getEmailOrganizador())
                .claim("id", organizadorAuth.getId())
                .claim("nome", organizadorAuth.getNomeOrganizador())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationTime.toMillis()))
                .signWith(getChaveAssinatura(), SignatureAlgorithm.HS256)
                .compact();
    }

    public OrganizadorAuth getUsuarioAutenticado(String token) {
        Claims claims = getClaims(token);
        return OrganizadorAuth.builder()
                .id(claims.get("id", Long.class))
                .emailOrganizador(claims.getSubject())
                .nomeOrganizador(claims.get("nome", String.class))
                .build();
    }
}
