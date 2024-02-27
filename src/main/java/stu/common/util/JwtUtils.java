//package stu.common.util;
//
//
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.security.Keys;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Component;
//
//import java.security.Key;
//import java.util.Date;
//
//@Component
//public class JwtUtils {
//
//    @Value("${jwt.secret}")
//    private String jwtSecret;
//
//    @Value("${jwt.expirationMs}")
//    private int jwtExpirationMs;
//
//    public String generateJwtToken(String username) {
//        Date expiryDate = new Date(new Date().getTime() + jwtExpirationMs);
//        Key key = Keys.hmacShaKeyFor(jwtSecret.getBytes());
//        return Jwts.builder()
//        		.subject(username)
//        		.issuedAt(new Date())
//        		.expiration(expiryDate)
//        		.signWith(key)
//        		.compact();
//    }
//
//    public String getUsernameFromJwtToken(String token) {
//        Claims claims = Jwts.parser()
//                .setSigningKey(jwtSecret.getBytes())
//                .parseClaimsJws(token)
//                .getBody();
//        return claims.getSubject();
//    }
//
//    public boolean validateJwtToken(String authToken) {
//        try {
//            Jwts.parserBuilder().setSigningKey(jwtSecret.getBytes()).build().parseClaimsJws(authToken);
//            return true;
//        } catch (Exception e) {
//            return false;
//        }
//    }
//}
//
