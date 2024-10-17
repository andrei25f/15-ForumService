package ait.cohort46.post.controller;

import ait.cohort46.post.dto.NewCommentDto;
import ait.cohort46.post.dto.NewPostDto;
import ait.cohort46.post.dto.PostDto;
import ait.cohort46.post.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@RestController
public class PostController {
    @Autowired
    private PostService postService;

    @PostMapping("/forum/post/{user}")
    public PostDto addNewPost(@PathVariable String user, @RequestBody NewPostDto newPostDto) {
        return postService.addNewPost(user, newPostDto);
    }

    @GetMapping("/forum/post/{postId}")
    public PostDto findPostById(@PathVariable String postId) {
        return postService.findPostById(postId);
    }

    @PatchMapping("/forum/post/{postId}/like")
    public void addLike(@PathVariable String postId) {
        postService.addLike(postId);
    }

    @GetMapping("/forum/posts/author/{user}")
    public Iterable<PostDto> findPostsByAuthor(@PathVariable String user) {
        return postService.findPostsByAuthor(user);
    }

    @PatchMapping("/forum/post/{postId}/comment/{commenter}")
    public PostDto addComment(@PathVariable String postId, @PathVariable String commenter, @RequestBody NewCommentDto newCommentDto) {
        return postService.addComment(postId, commenter, newCommentDto);
    }

    @GetMapping("/forum/post/{postId}")
    public PostDto removePost(@PathVariable String postId) {
        return postService.removePost(postId);
    }

    @GetMapping("/forum/posts/tags")
    public Iterable<PostDto> findPostsByTags(@RequestParam List<String> values) {
        return postService.findPostsByTags(values);
    }

    @GetMapping("/forum/posts/period")
    public Iterable<PostDto> findPostsByPeriod(@RequestParam LocalDate dateFrom, @RequestParam LocalDate dateTo) {
        return postService.findPostsByPeriod(dateFrom, dateTo);
    }
}
