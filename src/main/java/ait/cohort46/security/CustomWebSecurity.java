package ait.cohort46.security;

import ait.cohort46.accounting.dao.UserRepository;
import ait.cohort46.accounting.model.User;
import ait.cohort46.post.dao.PostRepository;
import ait.cohort46.post.model.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class CustomWebSecurity {
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    public boolean checkPostAuthor(String postId, String username) {
        Post post = postRepository.findById(postId).orElse(null);
        return post != null && post.getAuthor().equals(username);
    }

    public boolean checkPasswordDate(String username) {
        User user = userRepository.findById(username).orElse(null);
        return user != null && user.getPasswordDate().plusDays(60).isAfter(LocalDate.now());
    }
}
