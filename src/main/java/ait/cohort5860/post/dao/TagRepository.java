package ait.cohort5860.post.dao;

import ait.cohort5860.post.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TagRepository extends JpaRepository<Tag, String> {
    Optional<Tag> findByNameIgnoreCase(String tagName);
}
