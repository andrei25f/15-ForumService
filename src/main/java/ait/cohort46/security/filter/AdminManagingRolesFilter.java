package ait.cohort46.security.filter;

import ait.cohort46.accounting.dao.UserRepository;
import ait.cohort46.accounting.model.Role;
import ait.cohort46.security.model.User;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Order(20)
public class AdminManagingRolesFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        if (checkEndpoint(request.getServletPath())) {
            User user = (User) request.getUserPrincipal();
            if (!user.getRoles().contains(Role.ADMINISTRATOR.name())) {
                response.sendError(HttpServletResponse.SC_FORBIDDEN);
            }
        }
        filterChain.doFilter(request, response);
    }

    private boolean checkEndpoint(String servletPath) {
        return servletPath.matches("/account/user/\\w+/role/\\w+");
    }
}
