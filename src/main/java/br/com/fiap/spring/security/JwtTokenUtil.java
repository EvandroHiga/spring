package br.com.fiap.spring.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.Date;
import java.util.HashMap;

@Component
public class JwtTokenUtil {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expireInSeconds}")
    private int expireInSeconds;

    public String generateToken(String username){
        Date dataCriacao = Date.from(LocalDateTime.now().toInstant(OffsetDateTime.now().getOffset()));
        Date dataExpiracao = Date.from(LocalDateTime.now().plusSeconds(expireInSeconds).toInstant(OffsetDateTime.now().getOffset()));

        return Jwts.builder()
                .setClaims(new HashMap<>())
                .setIssuedAt(dataCriacao)
                .setExpiration(dataExpiracao)
                .setSubject(username)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public String getUsernameFromToken(String token){
        Claims claims = Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }

}
