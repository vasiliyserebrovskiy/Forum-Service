package ait.cohort5860.post.model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

/**
 * @author Vasilii Serebrovskii
 * @version 1.0 (26.06.2025)
 */
@NoArgsConstructor
@Getter
@EqualsAndHashCode(of = {"id"})
@Entity
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // DB will generate the id
    @Column(name = "id")
    private long id;
    @Column(name = "username")
    private String username;
    @Column(name = "message", columnDefinition = "TEXT")
    private String message;
    @Column(name = "date_created")
    private LocalDateTime dateCreated = LocalDateTime.now();
    @Column(name = "likes")
    private int likes;
    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    //using this constructor, we will have post_id in DB equals null.
    public Comment(String user, String message) {
        this.username = user;
        this.message = message;
    }
    // We need to create a relation between comment and post.
    public Comment(String user, String message, Post post) {
        this.username = user;
        this.message = message;
        this.post = post;
    }

    public void addLike() {
        likes++;
    }
}
