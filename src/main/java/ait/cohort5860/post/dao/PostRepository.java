package ait.cohort5860.post.dao;

import ait.cohort5860.post.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findPostsByAuthor(String author);
    List<Post> findDistinctByTagsNameIn(Set<String> tags);

    //Stream<Post> findByAuthorIgnoreCase(String author); // lesson method

    @Query("SELECT DISTINCT p FROM Post p JOIN p.tags t WHERE LOWER(t.name) IN :names")
    List<Post> findDistinctByTagNamesIgnoreCase(@Param("names") Set<String> names);
    //Stream<Post> findDistinctByTagsNameIgnoreCase(List<String> names); // without query

    List<Post> findByDateCreatedBetween(LocalDateTime dateCreated, LocalDateTime dateCreated2);
}
