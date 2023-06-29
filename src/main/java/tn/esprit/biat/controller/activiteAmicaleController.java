package tn.esprit.biat.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.biat.Entity.*;
import tn.esprit.biat.repository.ActiviteAmicaleCommentRepository;
import tn.esprit.biat.repository.ActiviteAmicaleRepository;
import tn.esprit.biat.repository.UserRepository;
import tn.esprit.biat.service.EmailService;
import tn.esprit.biat.service.IActiviteAmicale;
import tn.esprit.biat.service.UserDetailsImpl;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/activiteAmicale")
@RestController
public class activiteAmicaleController {
    private static final Logger logger = LoggerFactory.getLogger(CovoiturageController.class);

    @Autowired
    IActiviteAmicale activiteAmicaleService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    private EmailService emailService;
    @Autowired
    ActiviteAmicaleRepository activiteAmicaleRepository;
    @Autowired
    ActiviteAmicaleCommentRepository activiteAmicaleCommentRepository;



    @PreAuthorize("hasRole('ROLE_PERSONNEL') or hasRole('ROLE_RH')")
    @GetMapping("/getAllActiviteAmicale")
    public List<ActiviteAmicale> getAllActiviteAmicale() {
        return activiteAmicaleService.RetrieveAllActiviteAmicale();
    }

    @PostMapping("/addActiviteAmicale")
    @PreAuthorize("hasRole('ROLE_PERSONNEL') or hasRole('ROLE_RH')")
    public ActiviteAmicale addActiviteAmicale(@RequestParam("file") MultipartFile[] files, @ModelAttribute ActiviteAmicale activiteAmicale, Authentication authentication) {
        try {
            Set<picturesAmicale> pictures = new HashSet<>();
            for (MultipartFile file : files) {
                picturesAmicale picture = new picturesAmicale();
                picture.setName(file.getOriginalFilename());
                picture.setData(file.getBytes());
                picture.setActiviteAmicale(activiteAmicale);
                pictures.add(picture);
            }

            activiteAmicale.setPicturesAmicales(pictures);

            // Retrieve the authenticated user's details
            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
            String username = userDetails.getUsername();

            // Retrieve the corresponding User entity from your repository or service layer
            User loggeduser = userRepository.getUserByUsername(username);

            // Set the user on the Vente entity
            activiteAmicale.setUser(loggeduser);
            ActiviteAmicale  savedactiviteAmicale = activiteAmicaleService.addActiviteAmicale(activiteAmicale);
            // send email lel les utilisateurs avec le rôle Personnel

            List<User> rhUsers = userRepository.findByRole(ERole.ROLE_PERSONNEL);
            System.out.println("email"+ userRepository.findByRole(ERole.ROLE_PERSONNEL));
            for (User user : rhUsers) {
                System.out.println("email"+user.getEmail());
                String to = user.getEmail();
                String subject = "Vente acceptée";
                String body = "Une nouvelle activite amicale a été ajoutée veuillez consulter le site web pour plus d'informations. " ;
                emailService.sendEmail(to, subject, body);
            }
            return savedactiviteAmicale ;
        } catch (IOException e) {
            throw new RuntimeException("Failed to upload picture(s)!");
        }
    }


    @PostMapping("/reactions/{activiteAmicaleid}")
    public ActiviteAmicale addReaction(@PathVariable("activiteAmicaleid") Long activiteAmicaleid, @RequestBody Reactions
            reaction, Authentication authentication) {

        ActiviteAmicale activiteAmicale = activiteAmicaleRepository.findAAById(activiteAmicaleid).get();
        String currentUserId = authentication.getName();
        //System.out.println("emoji = "+reaction.getEmoji()+"\n");
        //System.out.println("covoiturage.getLikeCount() = "+covoiturage.getLikeCount());
        logger.info("Request body: " + reaction.toString());
        switch (reaction.getEmoji()) {
            case "😀":
                activiteAmicale.setLikeCount(activiteAmicale.getLikeCount() + 1);

                break;
            case "😍":
                activiteAmicale.setLoveCount(activiteAmicale.getLoveCount() + 1);
                break;
            case "👍":
                activiteAmicale.setThumbsUpCount(activiteAmicale.getThumbsUpCount() + 1);
                break;
            case "👎":
                activiteAmicale.setThumbsDownCount(activiteAmicale.getThumbsDownCount() + 1);
                break;
            case "🎉":
                activiteAmicale.setCelebrationCount(activiteAmicale.getCelebrationCount() + 1);
                break;
            default:
                throw new IllegalArgumentException("Invalid emoji");
        }


        return activiteAmicaleRepository.save(activiteAmicale);
    }
    @PostMapping("/comments/{AAId}")
    public ResponseEntity<AAcomment> addComment(@PathVariable Long AAId, @RequestBody AAcomment comment) {
        ActiviteAmicale AA = activiteAmicaleRepository.findById(AAId).orElse(null);
        comment.setActiviteAmicale(AA);
        AAcomment savedComment = activiteAmicaleCommentRepository.save(comment);
        return ResponseEntity.ok(savedComment);
    }

}
