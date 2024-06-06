package com.example.HotelServer.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtUtil {

    private String generateToken(Map<String, Object> extraClaims, UserDetails details) {
        return Jwts.builder()
                .claims(extraClaims)
                .subject(details.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24))
                .signWith(getSigningKey())
                .compact();
    }

    public String generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String userName = extractUserName(token);

        return userName.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                /*.setSigningKey(getSigningKey())*/
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public String extractUserName(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolvers) {
        final Claims claims = extractAllClaims(token);
        return claimsResolvers.apply(claims);
    }

    /*private Key getSigningKey() {*/
    private SecretKey getSigningKey() {
        /*HS512*/
        /*byte[] keyBytes = Decoders.BASE64.decode("173F980CDFC895ADB54268A9EDB02C7077F8CD0377609BFA8D1AD6858832F554DB91AF80D0AF266EF0C97C3FB019BE4993F37BAF94C3D3792C3C9E9C2DA1676B");*/
        /*HS256*/
        byte[] keyBytes = Decoders.BASE64.decode("B672F108A1C23FEF1C3D0ABF4930EB68333244C75B158784A6215F02A64CAC0C");
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
