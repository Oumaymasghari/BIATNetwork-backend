package tn.esprit.biat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.biat.Entity.Departement;

@Repository
public interface DepartementRepository extends JpaRepository<Departement, Long> {
}
