package cat.itacademy.s05.t02.n01.S05T02N01Mascota.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class TokenJwt {

    private final Key key = Keys.hmacShaKeyFor("01234567890123456789012345678901234".getBytes());


    public String generateToken(Long userId, String userName, String role){
        String token = Jwts
                .builder()
                .setSubject(userName)
                .claim("id", userId)
                .claim("role", role)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+ 3600000))
                .signWith(key)
                .compact();
        return token;
    }

    public String extractUser(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public String getUsernameFromToken(String token) {
        return getAllClaimsFromToken(token).getSubject();
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
