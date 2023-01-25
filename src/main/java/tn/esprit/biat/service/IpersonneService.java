package tn.esprit.biat.service;

import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import tn.esprit.biat.Entity.Personne;

import java.util.List;


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
}
