package bcit.cst;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.Test;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;

/**
 * @author Zhimeng Zheng
 * @version 2025-08-27
 */
public class JwtTest {
    @Test
    public void testGenerateToken() {
        final HashMap<String, Object> dataMap = new HashMap<>();
        dataMap.put("id", 1);
        dataMap.put("username", "admin");

        SecretKey key = Keys.hmacShaKeyFor("my-secret-key-which-is-long-enough!!".getBytes());

        String jwt = Jwts.builder()
                .signWith(key)
                .addClaims(dataMap) //add
                .setExpiration(new Date(System.currentTimeMillis() + 3600 * 1000)) // 1 hour;
                .compact();
        System.out.println(jwt);
    }

    @Test
    public void testParseToken() {
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJpZCI6MSwidXNlcm5hbWUiOiJhZG1pbiIsImV4cCI6MTc1NjMyMzE2MX0.8-5nTFOo2HuIEKchIFsBVhu6B3hDeUAudf-cfyfQzt4";
        SecretKey key = Keys.hmacShaKeyFor("my-secret-key-which-is-long-enough!!".getBytes());
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
        System.out.println(claims);
    }


}
