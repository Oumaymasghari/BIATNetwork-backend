package tn.esprit.biat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.biat.Entity.ActiviteAmicale;


@Repository
public interface ActiviteAmicaleRepository extends JpaRepository<ActiviteAmicale, Long>  {
}
