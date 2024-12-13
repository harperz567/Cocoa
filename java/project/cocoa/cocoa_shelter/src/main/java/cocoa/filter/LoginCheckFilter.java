package cocoa.filter;

import com.alibaba.fastjson.JSON;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.AntPathMatcher;
import cocoa.common.BaseContext;
import cocoa.common.R;


/**
 * LoginCheckFilter is a servlet filter for intercepting HTTP requests to check login status.
 * It determines whether a request needs authentication and ensures only authenticated users can access protected resources.
 */
@WebFilter(filterName = "loginCheckFilter", urlPatterns = "/*")
@Slf4j
public class LoginCheckFilter implements Filter {

    /**
     * URL matcher supporting Ant-style path matching (e.g., /**, /path/*).
     */
    public static final AntPathMatcher PATH_MATCHER = new AntPathMatcher();

    /**
     * Filters incoming requests to determine if authentication is required.
     *
     * @param servletRequest  the incoming request
     * @param servletResponse the response to the client
     * @param filterChain     the chain of filters to apply
     * @throws IOException      if an input/output error occurs
     * @throws ServletException if the request could not be handled
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
        FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        // 1. Get URI of the current request
        String requestURI = request.getRequestURI();
        log.info("Detect request: {}", requestURI);

        // URLs that do not require login checks
        String[] urls = new String[]{
            "/employee/login",
            "/employee/logout",
            "/backend/**",
            "/front/**",
            "/common/**",
            "/user/sendMsg",
            "/user/login"
        };

        // 2. Check if the current request needs to be processed
        boolean check = check(urls, requestURI);

        // 3. If it doesn't need processing, pass the request along the filter chain
        if (check) {
            log.info("This request({}) doesn't need processing", requestURI);
            filterChain.doFilter(request, response);
            return;
        }

        // 4-1. Check backend user login status, if logged in, proceed
        if (request.getSession().getAttribute("employee") != null) {
            log.info("User logged in, id: {}", request.getSession().getAttribute("employee"));

            Long empId = (Long) request.getSession().getAttribute("employee");
            BaseContext.setCurrentId(empId);

            filterChain.doFilter(request, response);
            return;
        }

        // 4-2. Check frontend client login status, if logged in, proceed
        if (request.getSession().getAttribute("user") != null) {
            log.info("User logged in, id: {}", request.getSession().getAttribute("user"));

            Long userId = (Long) request.getSession().getAttribute("user");
            BaseContext.setCurrentId(userId);

            filterChain.doFilter(request, response);
            return;
        }

        // 5. If not logged in, respond with an error to the client
        log.info("This request({}) needs processing", requestURI);
        log.info("User not logged in");
        response.getWriter().write(JSON.toJSONString(R.error("NOTLOGIN")));
    }

    /**
     * Checks if the request URI matches any of the given patterns.
     *
     * @param urls       an array of URL patterns to match
     * @param requestURI the URI of the incoming request
     * @return true if the request matches any pattern, false otherwise
     */
    public boolean check(String[] urls, String requestURI) {
        for (String url : urls) {
            boolean match = PATH_MATCHER.match(url, requestURI);
            if (match) {
                return true;
            }
        }
        return false;
    }
}
