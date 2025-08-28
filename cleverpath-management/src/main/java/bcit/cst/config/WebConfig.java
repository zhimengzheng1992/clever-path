package bcit.cst.config;

import bcit.cst.interceptor.TokenInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Zhimeng Zheng
 * @version 2025-08-27
 */
@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {

//    private final DemoInterceptor demoInterceptor;

    private final TokenInterceptor tokenInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(tokenInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns(
                        "/login",
                        "/swagger-ui/**",
                        "/v3/api-docs/**",
                        "/webjars/**",
                        "/openapi.json"
                );
    }
}
