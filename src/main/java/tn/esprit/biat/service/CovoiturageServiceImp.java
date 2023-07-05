package tn.esprit.biat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import tn.esprit.biat.Entity.Covoiturage;
import tn.esprit.biat.Entity.Personne;
import tn.esprit.biat.Entity.User;
import tn.esprit.biat.repository.CovoiturageRepository;
import tn.esprit.biat.repository.UserRepository;

import java.util.List;
@Service
public class CovoiturageServiceImp implements ICovoiturageService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    CovoiturageRepository covoiturageRepository ;
    @Autowired
    UserDetailsServiceImpl userDetailsServiceImpl ;
    @Autowired
    userService userService ;


    @Override
    public List<Covoiturage> RetrieveAllCovoiturage() {
        List<Covoiturage> cov =covoiturageRepository.findAll();
        return cov;
    }

    @Override
    public Covoiturage addCovoiturage(Covoiturage c) {

        // Get the current user from the security context
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        UserDetails userDetails = userDetailsServiceImpl.loadUserByUsername(username);
        User user = userService.getUserByUsername(userDetails.getUsername());

        // Set the user entity
        c.setUser(user);
        return covoiturageRepository.save(c);
    }

    @Override
    public Covoiturage retrieveCovoiturage(Long id) {
        Covoiturage cov = covoiturageRepository.findById(id).orElse(null);
        return cov;
    }

    @Override
    public Covoiturage modifyCovoiturage(Covoiturage c) {
        return covoiturageRepository.save(c);
    }
    @Override
    public Covoiturage modifyCovoiturageById(Long id) {
        Covoiturage c = covoiturageRepository.findById(id).orElse(null);
        return covoiturageRepository.save(c);
    }
    @Override
    public void deleteCovoiturage(Long id) {
        covoiturageRepository.deleteById(id);
    }
}
