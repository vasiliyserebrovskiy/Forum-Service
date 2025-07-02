package ait.cohort5860.security;

import ait.cohort5860.post.dao.PostRepository;
import ait.cohort5860.post.model.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author Vasilii Serebrovskii
 * @version 1.0 (02.07.2025)
 */
@Component
@RequiredArgsConstructor
public class CustomWebSecurity {

    private final PostRepository postRepository;

    public boolean checkPostAuthor(String postId, String username) {
        try {
            Long id = Long.parseLong(postId);
            //TODO: create DB method
            Post post = postRepository.findById(id).orElse(null); // can write new method for DB requset which return true or false
            return post != null && post.getAuthor().equalsIgnoreCase(username);
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
