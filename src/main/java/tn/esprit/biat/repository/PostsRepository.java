package tn.esprit.biat.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.biat.Entity.Posts;
@Repository
public interface PostsRepository extends JpaRepository<Posts, Long>{
}
