package ait.cohort46.security.filter;

import ait.cohort46.accounting.dao.UserRepository;
import ait.cohort46.accounting.model.Role;
import ait.cohort46.accounting.model.User;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Order(40)
public class UpdateByOwnerFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        try {
            if (checkEndpoint(request.getMethod(), request.getServletPath())) {
                String principal = request.getUserPrincipal().getName();
                String[] path = request.getServletPath().split("/");
                String login = path[path.length - 1];
                if (!principal.equals(login)) {
                    throw new RuntimeException();
                }
            }
        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
            return;
        }
        filterChain.doFilter(request, response);
    }


    private boolean checkEndpoint(String method, String servletPath) {
        return HttpMethod.PATCH.matches(method) && servletPath.matches("/account/user/\\w+")
                || HttpMethod.POST.matches(method) && servletPath.matches("/forum/post/\\w+")
                || HttpMethod.PATCH.matches(method) && servletPath.matches("/forum/post/\\w+/comment/\\w+");
    }
}
