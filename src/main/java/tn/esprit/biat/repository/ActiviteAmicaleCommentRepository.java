package tn.esprit.biat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.esprit.biat.Entity.AAcomment;
import tn.esprit.biat.Entity.ActiviteAmicale;
import tn.esprit.biat.Entity.Covoiturage;
import tn.esprit.biat.Entity.PostComment;

import java.util.List;
import java.util.Optional;

@Repository
public interface ActiviteAmicaleCommentRepository extends JpaRepository<AAcomment, Long> {
    List<AAcomment> findByActiviteAmicaleId(Long AAId);
    @Query("SELECT c FROM AAcomment c WHERE c.activiteAmicale = :activiteAmicale")
    List<AAcomment> findAAcommentByIdAA(@Param("activiteAmicale") Long activiteAmicale);





}
