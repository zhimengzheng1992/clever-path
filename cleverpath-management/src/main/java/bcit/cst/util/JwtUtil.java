package bcit.cst.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;

public class JwtUtil {

    // 建议放在配置文件 application.yml 中，然后注入
    private static final String SECRET_KEY = "cleverpathSecretKeycleverpathSecretKey";
    // ⚠️ 长度至少 32 字节 (256 bit) 才能用于 HS256
    private static final long EXPIRATION = 1000 * 60 * 60 * 2; // 2 小时有效期

    // 使用 Key 对象代替 String
    private static final Key KEY = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());

    /**
     * 生成 JWT Token
     */
    public static String generateToken(Long id, String username) {
        return Jwts.builder()
                .setSubject(username)                 // Token 主题（用户名）
                .claim("id", id)                      // 自定义字段：id
                .setIssuedAt(new Date())              // 签发时间
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION)) // 过期时间
                .signWith(KEY, SignatureAlgorithm.HS256) // ✅ 新写法：Key + 算法
                .compact();
    }

    /**
     * 解析 Token
     */
    public static Claims parseToken(String token) {
        return Jwts.parserBuilder()       // ✅ parser() 已弃用，改用 parserBuilder()
                .setSigningKey(KEY)       // 使用 Key，而不是 String
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * 判断 Token 是否过期
     */
    public static boolean isTokenExpired(String token) {
        Date expiration = parseToken(token).getExpiration();
        return expiration.before(new Date());
    }
}
