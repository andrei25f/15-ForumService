package ait.cohort46.accounting.dao;

import ait.cohort46.accounting.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
    boolean existsUserByLoginIgnoreCase(String login);
}
