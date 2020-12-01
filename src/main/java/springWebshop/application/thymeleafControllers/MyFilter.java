package springWebshop.application.thymeleafControllers;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

@Component
public class MyFilter implements Filter {

    @Override
    public void init(final FilterConfig filterConfig) throws ServletException {
        //do nothing
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
                       throws IOException, ServletException {
        if (request instanceof HttpServletRequest) {
            HttpServletRequest httpServletRequest = (HttpServletRequest) request;                
            httpServletRequest.getSession().setAttribute(
                                              "myFilter.LAST_URL",
                                               httpServletRequest .getRequestURI());
        } 

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }

}
