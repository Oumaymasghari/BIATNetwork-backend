package tn.esprit.biat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import tn.esprit.biat.Entity.Vente;

import java.util.List;

@Repository
public interface VenteRepository extends JpaRepository<Vente, Long> {
    @Query("SELECT v FROM Vente v WHERE v.accepted = true")
    List<Vente> findByAccepted();
}
