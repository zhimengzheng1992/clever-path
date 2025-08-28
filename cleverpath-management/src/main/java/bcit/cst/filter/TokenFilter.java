package bcit.cst.filter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @author Zhimeng Zheng
 * @version 2025-08-27
 */
@Slf4j
//@WebFilter(urlPatterns = "/*") // 过滤所有请求
public class TokenFilter implements Filter {

    // 定义你的 secret key（实际项目里建议放到配置文件）
    private static final String SECRET = "cleverpathSecretKeycleverpathSecretKey";
    private static final SecretKey KEY = Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8));

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        //1. get the request path
        String path = request.getRequestURI();

        //2. Check whether it is a login request. If the path contains /login, it indicates a login operation and the request is allowed to proceed.
        if (path.contains("/login")) {
            log.info("Login request, no need to check token.");
            filterChain.doFilter(request, response);
            return;
        }

        //3. get the token from the request header
        String token = request.getHeader("Authorization");
        if (token == null || token.isEmpty()) {
            token = request.getHeader("token"); // fallback
        }

        //4. Check if the token is valid. If the token is invalid, return an error response(status code 401) indicating that authentication is required.
        if (token == null || token.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // 401
            response.getWriter().write("Missing token, authentication required");
            return;
        }

        try {
            // 解析并验证 Token（如果过期/签名错误会抛异常）
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(KEY)
                    .build()
                    .parseClaimsJws(token.replace("Bearer ", "")) // 兼容 "Bearer xxx"
                    .getBody();

            // 你可以从 claims 里拿到用户信息
            Long id = claims.get("id", Long.class);
            String username = claims.getSubject();

            // 5. If the token is valid, allow the request to proceed.
            log.info("Token is valid, proceeding with the request.");
            filterChain.doFilter(request, response);

        } catch (Exception e) {
            // Token 解析失败
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // 401
            response.getWriter().write("Invalid or expired token");
        }
    }
}
