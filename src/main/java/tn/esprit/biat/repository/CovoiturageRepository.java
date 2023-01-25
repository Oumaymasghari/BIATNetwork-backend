package tn.esprit.biat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.biat.Entity.Covoiturage;


@Repository
public interface CovoiturageRepository extends JpaRepository<Covoiturage, Long>  {
}
