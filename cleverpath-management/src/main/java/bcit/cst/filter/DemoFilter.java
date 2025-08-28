package bcit.cst.filter;

import jakarta.servlet.*;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

/**
 * @author Zhimeng Zheng
 * @version 2025-08-27
 */
//@WebFilter(urlPatterns = "/*") // filter all requests
@Slf4j
public class DemoFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("DemoFilter init method");
    }

    // will be executed for every request
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("filtered a request");
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        log.info("DemoFilter destroy method");
    }
}
