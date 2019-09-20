package com.rarait.education.login.auth;

import com.rarait.education.login.resource.AuthResponse;
import com.rarait.education.shared.AppProperties;
import com.rarait.framework.security.impl.DefaultUserDetails;
import com.rarait.framework.shared.DateUtil;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.stream.Collectors;

/**
 * @author Prajin Maharjan
 * @see <code>https://grokonez.com/spring-framework/spring-boot/spring-security-jwt-authentication-restapis-springboot-spring-mvc-spring-security-spring-jpa-mysql</code>
 * @since 1.0
 */
@Slf4j
@Component
public class JwtProvider {

    @Value(AppProperties.SECRET)
    private String secret;

    @Value(AppProperties.TOKEN_EXPIRATION)
    private int expiration;

    public AuthResponse getAuthToken(Authentication authentication) {
        DefaultUserDetails principal = (DefaultUserDetails) authentication.getPrincipal();
        return AuthResponse.builder()
                .token(Jwts.builder()
                        .setSubject(principal.getUsername())
                        .setIssuedAt(new Date())
                        .setExpiration(DateUtil.addMinuteOnCurrent(expiration))
                        .signWith(SignatureAlgorithm.HS512, secret)
                        .compact())
                .username(principal.getUsername())
                .role(principal.getAuthorities().stream()
                        .map(n -> n.toString())
                        .collect(Collectors.joining(",")))
                .build();
    }

    public String getUsernameFromAuthToken(String authToken) {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(authToken)
                .getBody().getSubject();
    }

    public boolean validateAuthToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException | MalformedJwtException |
                UnsupportedJwtException | IllegalArgumentException exe) {
            log.error("Invalid auth token;  Message: {} ", exe.getMessage());
        } catch (ExpiredJwtException exe) {
            log.error("Expired JWT token -> Message: {}", exe.getMessage());
        }
        return false;
    }

    public void logout(String authToken) {
        Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(authToken)
                .getBody()
                .setExpiration(new Date());
    }

//    public void printPdf(String authToken){
//        Jwts.parser()
//                .setSigningKey(secret)
//                .parseClaimsJws(authToken)
//                .getBody()
//                .getSubject();
//    }
}
