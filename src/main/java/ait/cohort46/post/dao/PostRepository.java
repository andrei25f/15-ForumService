package ait.cohort46.post.dao;

import ait.cohort46.post.model.Post;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.stream.Stream;

public interface PostRepository extends MongoRepository<Post, String> {
}
