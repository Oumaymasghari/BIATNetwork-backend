package tn.esprit.biat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.biat.Entity.Personne;

import java.util.Optional;

@Repository
public interface PersonneRepository extends JpaRepository<Personne, Long>{

    Personne findAppUserByEmail(String email) ;

    Optional<Personne> findByEmail(String email);

}
