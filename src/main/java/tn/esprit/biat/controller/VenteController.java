package tn.esprit.biat.controller;

import ch.qos.logback.core.CoreConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.biat.Entity.*;
import tn.esprit.biat.repository.UserRepository;
import tn.esprit.biat.repository.VenteRepository;
import tn.esprit.biat.request.CreditRequest;
import tn.esprit.biat.service.EmailService;
import tn.esprit.biat.service.IventeService;
import tn.esprit.biat.service.UserDetailsImpl;

import java.awt.*;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/Vente")
@RestController
public class VenteController {

    @Autowired
    IventeService venteService;
    @Autowired
    VenteRepository venteRepository;

    @Autowired
    UserRepository userRepository;
    @Autowired
    private EmailService emailService;
    @Autowired
    AuthenticationManager authenticationManager;


    @PreAuthorize("hasRole('ROLE_PERSONNEL') or hasRole('ROLE_RH')")
    @GetMapping("/getAllVentes")
    public List<Vente> getAllVentes() {
        return venteService.RetrieveAllVente();
    }

    @PostMapping("/addVente")
    @PreAuthorize("hasRole('ROLE_PERSONNEL') or hasRole('ROLE_RH')")
    public Vente addVente(@RequestParam("file") MultipartFile[] files, @ModelAttribute Vente vente, Authentication authentication) {
        try {
            Set<Picture> pictures = new HashSet<>();
            for (MultipartFile file : files) {
                Picture picture = new Picture();
                picture.setName(file.getOriginalFilename());
                picture.setData(file.getBytes());
                picture.setVente(vente);
                pictures.add(picture);
            }

            vente.setPictures(pictures);

            // Retrieve the authenticated user's details
            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
            String username = userDetails.getUsername();

            // Retrieve the corresponding User entity from your repository or service layer
            User loggeduser = userRepository.getUserByUsername(username);

            // Set the user on the Vente entity
            vente.setUser(loggeduser);
            Vente savedVente = venteService.addVente(vente);
            // send email lel les utilisateurs avec le rôle RH

            List<User> rhUsers = userRepository.findByRole(ERole.ROLE_RH);
            System.out.println("email"+ userRepository.findByRole(ERole.ROLE_RH));
            for (User user : rhUsers) {
                System.out.println("email"+user.getEmail());
                String to = user.getEmail();
                String subject = "Nouvelle vente ajoutée";
                String body = "Une nouvelle vente a été ajoutée. ID de vente : " + savedVente.getId();
                emailService.sendEmail(to, subject, body);
            }
            return savedVente;
        } catch (IOException e) {
            throw new RuntimeException("Failed to upload picture(s)!");
        }
    }
    @PostMapping("/credit/{idvente}")
    public String calculerCapaciteCredit(@RequestBody CreditRequest request, @PathVariable Long idvente) {
        double salaireMensuel = request.getSalaireMensuel();
        double mensualitesCredit = request.getMensualitesCredit();
        // Calcul de la capacité de remboursement de l'emprunteur
        double capaciteRemboursement = (salaireMensuel * 0.4)- mensualitesCredit;

        Vente vente = venteRepository.findById(idvente).orElse(null);
        // Calcul de la capacité d'emprunt de l'emprunteur
        double montantEmpreinter = vente.getPrix();
        double remboursementCredit = (montantEmpreinter * (0.1 / 14)) / (1 - Math.pow(1 - (0.1 / 14), 210));
        if (capaciteRemboursement < remboursementCredit) {
            return "Credit en depassement de la capacite d'endettement";
        } else {
            return "Credit dans la capacite d'endettement";
        }
    }

    @PostMapping("/accept/{id}")
    public Vente acceptVente(@PathVariable Long id) {

        Vente vente = venteRepository.getById(id);
        vente.setAccepted(true);
        User user = vente.getUser();

        // Send an email to the user
        String to = user.getEmail();
        String subject = "Vente acceptée";
        String body = "votre vente avec ID " + vente.getId() + " a ete acceptée.";
        emailService.sendEmail(to, subject, body);

        return venteService.addVente(vente);
    }
    @GetMapping("/ventes/accepted")
    public List<Vente> getAcceptedVentes() {
        return venteRepository.findByAccepted();
    }
    @Transactional
    @DeleteMapping ("/ventes/delete")
    public void deleteaccepted() {
         venteRepository.deleteAccepted();
    }
    @GetMapping("/ventes/{id}")
    public void deleteVentes(@PathVariable Long id) {
         venteRepository.deleteById(id);
    }
}
