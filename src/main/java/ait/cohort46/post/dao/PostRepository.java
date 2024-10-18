package ait.cohort46.post.dao;

import ait.cohort46.post.model.Post;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public interface PostRepository extends MongoRepository<Post, String> {
    Stream<Post> findByAuthorIgnoreCase(String author);

    Stream<Post> findByTagsInIgnoreCase(List<String> tags);

    Stream<Post> findByDateCreatedBetween(LocalDate dateFrom, LocalDate dateTo);
}
