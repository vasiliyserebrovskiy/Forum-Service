package ait.cohort5860.post.service;

import ait.cohort5860.post.dao.CommentRepository;
import ait.cohort5860.post.dao.FileRepository;
import ait.cohort5860.post.dao.PostRepository;
import ait.cohort5860.post.dao.TagRepository;
import ait.cohort5860.post.dto.*;
import ait.cohort5860.post.dto.exceptions.NotFoundException;
import ait.cohort5860.post.model.Comment;
import ait.cohort5860.post.model.FileEntity;
import ait.cohort5860.post.model.Post;
import ait.cohort5860.post.model.Tag;
import ait.cohort5860.post.service.logging.PostLogger;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.monitor.FileEntry;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;


/**
 * @author Vasilii Serebrovskii
 * @version 1.0 (26.06.2025)
 */
@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final TagRepository tagRepository;
    private final CommentRepository commentRepository;
    private final ModelMapper modelMapper;
    private final FileRepository fileRepository;

    @Override
    @Transactional
    public PostDto addPost(String author, AddPostDto newPostDto) {
        Post post = new Post(newPostDto.getTitle(), newPostDto.getContent(), author);

        // Handle tags
        return getPostDto(newPostDto, post);
    }

    @Override
    public PostDto findPostById(Long id) {
        Post post = postRepository.findById(id).orElseThrow(NotFoundException::new);
        return modelMapper.map(post, PostDto.class);
    }

    @Override
    @Transactional
    @PostLogger
    public void addLike(Long id) {
        Post post = postRepository.findById(id).orElseThrow(NotFoundException::new);
        post.addLike();
    }

    @Override
    @Transactional(readOnly = true)
    public List<PostDto> findPostsByAuthor(String author) {
        return postRepository.findByAuthorIgnoreCase(author)
                .map(p -> modelMapper.map(p, PostDto.class))
                .toList();
    }

    @Override
    @Transactional
    public PostDto addComment(Long id, String author, AddCommentDto addCommentDto) {
        Post post = postRepository.findById(id).orElseThrow(NotFoundException::new);
        Comment comment = new Comment(author, addCommentDto.getMessage(), post);
        commentRepository.save(comment);
        post.addComment(comment);
        postRepository.save(post);
        return modelMapper.map(post, PostDto.class);
    }

    @Override
    @Transactional
    public PostDto deletePost(Long id) {
        Post post = postRepository.findById(id).orElseThrow(NotFoundException::new);
        postRepository.delete(post);
        return modelMapper.map(post, PostDto.class);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PostDto> findPostsByTags(Set<String> tags) {
        return postRepository.findDistinctByTagsNameInIgnoreCase(tags)
                .map(p -> modelMapper.map(p, PostDto.class))
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<PostDto> findPostsByPeriod(LocalDate dateFrom, LocalDate dateTo) {
        LocalDateTime from = dateFrom.atStartOfDay();
        LocalDateTime to = dateTo.atTime(LocalTime.MAX);
        return postRepository.findByDateCreatedBetween(from, to)
                .map(p -> modelMapper.map(p, PostDto.class))
                .toList();
    }

    @Override
    @Transactional
    @PostLogger
    public PostDto updatePost(Long id, AddPostDto newPostDto) {
        Post post = postRepository.findById(id).orElseThrow(NotFoundException::new);
        String content = newPostDto.getContent();
        if (content != null) {
            post.setContent(content);
        }
        String title = newPostDto.getTitle();
        if (title != null) {
            post.setTitle(title);
        }
        return getPostDto(newPostDto, post);
    }

    private PostDto getPostDto(AddPostDto newPostDto, Post post) {
        Set<String> tags = newPostDto.getTags();
        if (tags != null) {
            for (String tagName : tags) {
                Tag tag = tagRepository.findById(tagName)
                        .orElseGet(() -> tagRepository.save(new Tag(tagName)));
                post.addTag(tag);
            }
        }
        post = postRepository.save(post);
        return modelMapper.map(post, PostDto.class);
    }

    // Files methods
    @Override
    @Transactional
    public FileResponseDto storeFile(Long postId, MultipartFile multipartFile) {
        try {
            Post post = postRepository.findById(postId).orElseThrow(NotFoundException::new);
            FileEntity file = new FileEntity();
            file.setFileName(multipartFile.getOriginalFilename());
            file.setContentType(multipartFile.getContentType());
            file.setData(multipartFile.getBytes());
            file.setPost(post);

            FileEntity saved = fileRepository.save(file);

            FileResponseDto dto = new FileResponseDto();
            dto.setFilename(saved.getFileName());
            dto.setDownloadUrl("/files/download/" + saved.getId());

            return dto;
        } catch (IOException e) {
            throw new RuntimeException("Не удалось сохранить файл", e);
        }
    }

    @Override
    public FileEntity getFile(Long fileId) {
        return fileRepository.findById(fileId)
                .orElseThrow(NotFoundException::new);
    }

    @Override
    @Transactional(readOnly = true)
    public List<FileDto> getPostFiles(Long postId) {
        System.out.println("BEFORE FINDING IN REPOSITORY: " + postId + "");
        List<FileEntity> files = fileRepository.findAllByPostId(postId);
        return files.stream()
                .map(file -> modelMapper.map(file, FileDto.class)).toList();
    }
    @Override
    public FileResponseDto deleteFile(Long fileId) {
        FileEntity file = fileRepository.findById(fileId)
                .orElseThrow(NotFoundException::new);
        fileRepository.delete(file);
        FileResponseDto dto = new FileResponseDto();
        dto.setFilename(file.getFileName());
        dto.setDownloadUrl("/files/download/" + file.getId());
        return dto;
    }

}
