package ait.cohort46.post.model;

import ait.cohort46.post.dto.CommentDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Singular;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Getter
@Document(collection = "posts")
public class Post {
    @Id
    private String id;
    private String title;
    private String content;
    @Setter
    private String author;
    private LocalDateTime dateCreated;
    private Set<String> tags;
    private Integer likes;
    private List<CommentDto> comments;

    public Post() {
        id = "generator";
        dateCreated = LocalDateTime.now();
    }
}
