package ait.cohort5860.post.model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Vasilii Serebrovskii
 * @version 1.0 (04.07.2025)
 */
@Getter
@EqualsAndHashCode(of = {"id"})
@Entity
@Table(name = "files")
public class FileEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Setter
    @Column(name = "file_name")
    private String fileName;
    @Setter
    @Column(name = "content_type")
    private String contentType;
    @Setter
    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;
    @Setter
    @Lob
    @Column(name="file_data", nullable = false)
    private byte[] data;
}
