package tn.esprit.biat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.biat.Entity.Personne;
@Repository
public interface PersonneRepository extends JpaRepository<Personne, Long>{
    public Personne findAppUserByEmail(String email) ;

}
