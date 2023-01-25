package tn.esprit.biat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.biat.Entity.PostComment;

@Repository
public interface PostCommentRepository extends JpaRepository<PostComment, Long> {
}
