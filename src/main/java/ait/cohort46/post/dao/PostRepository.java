package ait.cohort46.post.dao;

import ait.cohort46.post.model.Post;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.stream.Stream;

public interface PostRepository extends CrudRepository<Post, Long> {
    Optional<Post> findById(String id);
}
