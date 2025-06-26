package ait.cohort5860.post.dao;

import ait.cohort5860.post.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, String> {
}
