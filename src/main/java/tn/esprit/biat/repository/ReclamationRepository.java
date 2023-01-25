package tn.esprit.biat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.biat.Entity.Posts;
import tn.esprit.biat.Entity.Reclamation;
@Repository
public interface ReclamationRepository extends JpaRepository<Reclamation, Long> {
}
