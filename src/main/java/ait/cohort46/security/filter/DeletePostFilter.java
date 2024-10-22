package ait.cohort46.security.filter;

import ait.cohort46.accounting.model.Role;
import ait.cohort46.post.dao.PostRepository;
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
@RequiredArgsConstructor
@Order(60)
public class DeletePostFilter implements Filter {
    private final PostRepository postRepository;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        try {
            if (checkEndpoint(request.getMethod(), request.getServletPath())) {
                User user = (User) request.getUserPrincipal();
                String[] path = request.getServletPath().split("/");
                String id = path[path.length - 1];
                if (!postRepository.findById(id).get().getAuthor().equals(user.getName())
                        && !user.getRoles().contains(Role.MODERATOR.name())) {
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
        return HttpMethod.DELETE.matches(method) && servletPath.matches("/forum/post/\\w+");
    }
}
