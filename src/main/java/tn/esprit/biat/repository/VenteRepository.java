package tn.esprit.biat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.biat.Entity.Vente;
@Repository
public interface VenteRepository extends JpaRepository<Vente, Long> {
}
