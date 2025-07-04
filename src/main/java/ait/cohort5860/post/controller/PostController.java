package ait.cohort5860.post.controller;

import ait.cohort5860.post.dto.*;
import ait.cohort5860.post.model.FileEntity;
import ait.cohort5860.post.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.time.LocalDateTime;
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

    @PostMapping("/post/{author}")
    @ResponseStatus(HttpStatus.CREATED)
    public PostDto addPost(@PathVariable String author, @RequestBody @Valid AddPostDto addPostDto) {
        return postService.addPost(author, addPostDto);
    }

    @GetMapping("/post/{id}")
    public PostDto findPostById(@PathVariable Long id) {
        return postService.findPostById(id);
    }

    @PatchMapping("/post/{id}/like")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void addLike(@PathVariable Long id) {
        postService.addLike(id);
    }

    @GetMapping("/posts/author/{author}")
    public List<PostDto> findPostsByAuthor(@PathVariable String author) {
        return postService.findPostsByAuthor(author);
    }

    @PatchMapping("/post/{id}/comment/{author}")
    public PostDto addComment(@PathVariable Long id, @PathVariable String author, @RequestBody @Valid AddCommentDto addCommentDto) {
        return postService.addComment(id, author, addCommentDto);
    }

    @DeleteMapping("/post/{id}")
    public PostDto deletePost(@PathVariable Long id) {
        return postService.deletePost(id);
    }

    @GetMapping("/posts/tags")
    public List<PostDto> findPostsByTags(@RequestParam("values") Set<String> tags) {
        return postService.findPostsByTags(tags);
    }

    @GetMapping("/posts/period")
    public List<PostDto> findPostsByPeriod(@RequestParam("dateFrom") LocalDate from, @RequestParam("dateTo") LocalDate  to) {
        return postService.findPostsByPeriod(from, to);
    }

    @PatchMapping("/post/{id}")
    public PostDto updatePost(@PathVariable Long id, @RequestBody AddPostDto addPostDto) {
        return postService.updatePost(id, addPostDto);
    }

    // Files endpoints
    @PostMapping("/post/{postId}/upload")
    public ResponseEntity<FileResponseDto> uploadFile(@PathVariable Long postId, @RequestParam("file") MultipartFile file) {
        System.out.println("We are here!");
        System.out.println("File: " + file.getOriginalFilename());
        System.out.println("File Size: " + file.getSize());
        FileResponseDto dto = postService.storeFile(postId, file);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/post/download/{fileId}")
    public ResponseEntity<byte[]> downloadFile(@PathVariable Long fileId) {
        FileEntity file = postService.getFile(fileId);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFileName() + "\"")
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(file.getData());
    }
}
