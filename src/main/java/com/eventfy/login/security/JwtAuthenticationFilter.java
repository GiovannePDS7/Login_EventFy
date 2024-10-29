package com.eventfy.login.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.time.Duration;
import java.time.temporal.Temporal;
import java.util.Date;
import java.util.function.Function;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Value("${token.security.key}")
    private String secretKey; // mesma chave usada para assinar o token
    private static final Duration EXPIRATION_TIME = Duration.ofHours(2); // 10 horas de validade

    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME.toMillis()))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    public boolean validateToken(String token) {
        return !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return getExpirationDateFromToken(token).before(new Date());
    }

    public String getUsuarioAutenticado(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    private Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
        return claimsResolver.apply(claims);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        // Verifica se o cabeçalho Authorization possui o prefixo Bearer
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7); // Remove o prefixo "Bearer "

            // Valida o token
            if (validateToken(token)) {
                String username = getUsuarioAutenticado(token); // Extrai o usuário autenticado

                // Cria o token de autenticação do Spring Security
                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(username, null, null);
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                // Define o contexto de segurança do Spring
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        // Continua a cadeia de filtros
        filterChain.doFilter(request, response);
    }
}
