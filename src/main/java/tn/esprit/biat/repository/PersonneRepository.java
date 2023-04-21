package tn.esprit.biat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.esprit.biat.Entity.Covoiturage;
import tn.esprit.biat.Entity.Personne;
import tn.esprit.biat.Entity.User;

import java.util.Optional;

@Repository
public interface PersonneRepository extends JpaRepository<Personne, Long>{

    Personne findAppUserByEmail(String email) ;

    Optional<Personne> findByEmail(String email);
    Optional<Personne> findByUser(User user);
    @Query("SELECT p FROM Personne p WHERE p.user.id = :userId")
    Personne findPersonneByUserId(@Param("userId") Long userId);
    Optional<Personne> findByUserId(Long userId);
}
