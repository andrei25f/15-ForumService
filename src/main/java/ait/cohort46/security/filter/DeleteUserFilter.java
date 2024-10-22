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
@RequiredArgsConstructor
@Order(30)
public class DeleteUserFilter implements Filter {
    private final UserRepository repository;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        try {
            if (checkEndpoint(request.getMethod(), request.getServletPath())) {
                String principal = request.getUserPrincipal().getName();
                String login = request.getServletPath().split("/")[3];
                User user = repository.findById(principal).get();
                if (!principal.equals(login) && !user.getRoles().contains(Role.ADMINISTRATOR)) {
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
        return HttpMethod.DELETE.matches(method) && servletPath.matches("/account/user/\\w+");
    }
}
