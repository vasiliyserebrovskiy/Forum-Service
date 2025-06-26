package ait.cohort5860.post.service;

import ait.cohort5860.post.dao.CommentRepository;
import ait.cohort5860.post.dao.PostRepository;
import ait.cohort5860.post.dao.TagRepository;
import ait.cohort5860.post.dto.AddCommentDto;
import ait.cohort5860.post.dto.AddPostDto;
import ait.cohort5860.post.dto.PostDto;
import ait.cohort5860.post.dto.exceptions.NotFoundException;
import ait.cohort5860.post.model.Comment;
import ait.cohort5860.post.model.Post;
import ait.cohort5860.post.model.Tag;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
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

    @Override
    @Transactional
    public PostDto addPost(String author, AddPostDto addPostDto) {
        Post post = new Post(addPostDto.getTitle(), addPostDto.getContent(), author);

        // Handle tags
        Set<String> tags = addPostDto.getTags();
        if (tags != null) {
            for (String tagName : tags) {
                Tag tag = tagRepository.findById(tagName).orElseGet(() -> tagRepository.save(new Tag(tagName)));
                post.addTag(tag);
            }
        }
        post = postRepository.save(post);
        return modelMapper.map(post, PostDto.class);
    }

    @Override
    public PostDto findPostById(Long id) {
        Post post = postRepository.findById(id).orElseThrow(NotFoundException::new);
        return modelMapper.map(post, PostDto.class);
    }

    @Override
    public void addLike(Long id) {
        Post post = postRepository.findById(id).orElseThrow(NotFoundException::new);
        post.addLike();
        postRepository.save(post);

    }

    @Override
    public List<PostDto> findPostsByAuthor(String author) {
        return postRepository.findPostsByAuthor(author).stream()
                .map(post -> modelMapper.map(post, PostDto.class))
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

        // When we delete post, we also must delete all comments if they exist!
        List<Comment> comments = commentRepository.findByPostId(post.getId());
        if (!comments.isEmpty()) {
            commentRepository.deleteAll(comments);
        }
        postRepository.delete(post);
        return modelMapper.map(post, PostDto.class);
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
