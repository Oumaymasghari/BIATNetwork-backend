package tn.esprit.biat.service;
import java.util.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import tn.esprit.biat.Entity.Personne;
import tn.esprit.biat.Entity.Role;
import tn.esprit.biat.repository.PersonneRepository;
@EnableAutoConfiguration
@Service
public class PersonneServiceImp implements  IpersonneService{
    @Autowired
    PasswordEncoder passwordEncoder ;
    @Autowired
    PersonneRepository personneRepository ;

/*

    @Override
    public Personne register(Personne p) {
        String pwdCrypted  = passwordEncoder.encode(p.getPassword());
        p.setPassword(pwdCrypted);
        return personneRepository.save(p);
    }
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

//		 la premiere étape est de chercher si l'utilisateur existe dans la base ou non

        Personne appUser = personneRepository.findAppUserByEmail(email);

        List<GrantedAuthority> authorities = getAuthorities(appUser.getRoles());

        return new User(appUser.getEmail(),appUser.getPassword(),getAuthorities(appUser.getRoles()));





    }


    private List<GrantedAuthority> getAuthorities(List<Role> userRoles) {
        Set<GrantedAuthority> roles = new HashSet<GrantedAuthority>();

        for (Role role : userRoles) {

            roles.add(new SimpleGrantedAuthority(role.getNom()));

        }

        List<GrantedAuthority> grantedAuthorities = new ArrayList<>(roles);

        return grantedAuthorities;


    }

 */

    @Override
    public Personne register(Personne p) {
        return null;
    }

    @Override
    public List<Personne> RetrieveAllPersonnes() {
        List<Personne> personne =personneRepository.findAll();
        return personne;
    }

    @Override
    public Personne addPersonne(Personne p) {


        return personneRepository.save(p);
    }

    @Override
    public Personne retrievePersonne(Long id) {
        Personne personne = personneRepository.findById(id).orElse(null);
        return personne;
    }

    @Override
    public Personne modifyPersonne(Personne p) {

        return personneRepository.save(p);
    }

    @Override
    public void deletePersonne(Long id) {
        personneRepository.deleteById(id);
    }


    public Personne findAppUserByUserName(Long id) {
        Collection<Personne> personnes = personneRepository.findAll();
        for (Personne u : personnes) {
            if (u.getId().equals(id)) {
                return u;
            }
        }
        return null;
    }

    public Personne findAppUserByEmail(String email) {
        Collection<Personne> personnes = personneRepository.findAll();
        for (Personne u : personnes) {
            if (u.getEmail().equals(email)) {
                return u;
            }
        }
        return null;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}
