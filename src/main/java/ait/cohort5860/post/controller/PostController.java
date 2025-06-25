package ait.cohort5860.post.controller;

import ait.cohort5860.post.dto.AddCommentDto;
import ait.cohort5860.post.dto.AddPostDto;
import ait.cohort5860.post.dto.CommentDto;
import ait.cohort5860.post.dto.PostDto;
import ait.cohort5860.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

/**
 * @author Vasilii Serebrovskii
 * @version 1.0 (25.06.2025)
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/forum")
public class PostController {

    private final PostService postService;

    @Override
    @ResponseStatus(HttpStatus.CREATED)
    public PostDto addPost(String author, AddPostDto addPostDto) {
        return null;
    }

    @Override
    public PostDto findPostById(Long id) {
        return null;
    }

    @Override
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void addLike(Long id) {

    }

    @Override
    public List<PostDto> findPostsByAuthor(String author) {
        return List.of();
    }

    @Override
    public PostDto addComment(Long id, String author, AddCommentDto addCommentDto) {
        return null;
    }

    @Override
    public PostDto deletePost(Long id) {
        return null;
    }

    @Override
    public List<PostDto> findPostsByTags(Set<String> tags) {
        return List.of();
    }

    @Override
    public List<PostDto> findPostsByPeriod(LocalDate from, LocalDate to) {
        return List.of();
    }

    @Override
    public PostDto updatePost(Long id, AddPostDto addPostDto) {
        return null;
    }
}
