package com.example.travely.security.jwt;

import com.example.travely.exception.InvalidJwtTokenException;
import com.example.travely.exception.JwtTokenExpiredException;
import com.example.travely.security.auth.AuthDetailsService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@RequiredArgsConstructor
@Component
public class JwtTokenProvider {

    @Value("${jwt.secret-key}")
    private String secretKey;

    private final AuthDetailsService authDetailsService;

    public String resolveToken(HttpServletRequest request) {
        String bearer = request.getHeader("Authorization");
        return parseToken(bearer);
    }

    private String parseToken(String bearerToken) {
        if (bearerToken != null && bearerToken.startsWith("Bearer"))
            return bearerToken.replace("Bearer", "");
        return null;
    }

    public Authentication getAuthentication(String token) {
        String userEmail = getTokenSubject(token);
        UserDetails userDetails = authDetailsService.loadUserByUsername(userEmail);

        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    private String getTokenSubject(String token) {
        try {
            Claims claims = Jwts.parser().setSigningKey(secretKey)
                    .parseClaimsJws(token).getBody();
            return claims.getSubject();
        } catch (ExpiredJwtException e) {
            throw new JwtTokenExpiredException();
        } catch (Exception e) {
            throw new InvalidJwtTokenException();
        }

    }

    public String generateToken(String subject) {
        return Jwts.builder()
                .setIssuedAt(new Date())
                .setSubject(subject)
                .signWith(SignatureAlgorithm.HS512,secretKey)
                .compact();
    }
}
