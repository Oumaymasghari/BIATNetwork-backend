package tn.esprit.biat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.esprit.biat.Entity.Covoiturage;

import java.util.Optional;


@Repository
public interface CovoiturageRepository extends JpaRepository<Covoiturage, Long>  {
    @Query("SELECT c FROM Covoiturage c WHERE c.id = :covId")
    Optional<Covoiturage> findCovoiturageById(@Param("covId") Long covId);

}
