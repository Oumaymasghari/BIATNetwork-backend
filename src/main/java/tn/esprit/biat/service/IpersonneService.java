package tn.esprit.biat.service;

import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import tn.esprit.biat.Entity.Personne;
import tn.esprit.biat.Entity.User;

import java.util.List;
import java.util.Optional;


@Component
public interface IpersonneService extends UserDetailsService {

    Personne register(Personne p);
    List<Personne> RetrieveAllPersonnes();
    Personne addPersonne(Personne p);
    Personne retrievePersonne(Long id );
    Personne modifyPersonne(Personne p) ;
    void deletePersonne(Long id) ;
    Personne findAppUserByUserName(Long id);
    Personne findAppUserByEmail(String email) ;
    Personne getPersonneByUser(Long userid) ;
    public Optional<Personne> findByUserId(Long userId);
}
