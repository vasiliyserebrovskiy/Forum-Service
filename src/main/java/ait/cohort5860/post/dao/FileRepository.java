package ait.cohort5860.post.dao;

import ait.cohort5860.post.model.FileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Vasilii Serebrovskii
 * @version 1.0 (04.07.2025)
 */
public interface FileRepository extends JpaRepository<FileEntity, Long> {
    List<FileEntity> findAllByPostId(Long id);
}
