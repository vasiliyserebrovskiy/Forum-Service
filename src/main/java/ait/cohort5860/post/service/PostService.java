package ait.cohort5860.post.service;

import ait.cohort5860.post.dto.*;
import ait.cohort5860.post.model.FileEntity;
import org.apache.commons.io.monitor.FileEntry;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public interface PostService {

    PostDto addPost(String author, AddPostDto addPostDto);

    PostDto findPostById(Long id);

    void addLike(Long id);

    List<PostDto> findPostsByAuthor(String author);

    PostDto addComment(Long id, String author, AddCommentDto addCommentDto);

    PostDto deletePost(Long id);

    List<PostDto> findPostsByTags(Set<String> tags);

    List<PostDto> findPostsByPeriod(LocalDate from, LocalDate to);

    PostDto updatePost(Long id, AddPostDto addPostDto);

    FileResponseDto storeFile(Long postId, MultipartFile file);

    FileEntity getFile(Long fileId);

    //FileResponseDto deleteFile(Long postId, Long fileId);

    //List<FileDto> getPostFiles(Long postId);

}
