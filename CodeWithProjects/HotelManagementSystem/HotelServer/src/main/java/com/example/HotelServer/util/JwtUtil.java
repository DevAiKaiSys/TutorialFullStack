package com.example.HotelServer.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.Map;

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

    private Key getSigningKey() {
        /*HS512*/
        /*byte[] keyBytes = Decoders.BASE64.decode("173F980CDFC895ADB54268A9EDB02C7077F8CD0377609BFA8D1AD6858832F554DB91AF80D0AF266EF0C97C3FB019BE4993F37BAF94C3D3792C3C9E9C2DA1676B");*/
        /*HS256*/
        byte[] keyBytes = Decoders.BASE64.decode("B672F108A1C23FEF1C3D0ABF4930EB68333244C75B158784A6215F02A64CAC0C");
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
