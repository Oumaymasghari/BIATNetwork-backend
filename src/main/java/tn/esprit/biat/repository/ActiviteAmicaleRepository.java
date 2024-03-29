package tn.esprit.biat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.esprit.biat.Entity.ActiviteAmicale;
import tn.esprit.biat.Entity.Cathegorie;
import tn.esprit.biat.Entity.Covoiturage;

import java.util.List;
import java.util.Optional;


@Repository
public interface ActiviteAmicaleRepository extends JpaRepository<ActiviteAmicale, Long>  {
    @Query("SELECT c FROM ActiviteAmicale c WHERE c.id = :AAId")
    Optional<ActiviteAmicale> findAAById(@Param("AAId") Long AAId);

    List<ActiviteAmicale> findByCathegorie(Cathegorie cathegorie);

}
