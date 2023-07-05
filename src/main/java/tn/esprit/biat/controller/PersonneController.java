package tn.esprit.biat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tn.esprit.biat.Entity.Personne;
import tn.esprit.biat.Entity.User;
import tn.esprit.biat.repository.PersonneRepository;
import tn.esprit.biat.repository.UserRepository;
import tn.esprit.biat.service.IpersonneService;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
//@RequestMapping("/personne")
@RestController
public class PersonneController {

    @Autowired
    IpersonneService personneService ;
    @Autowired
    PersonneRepository personneRepository ;
    @Autowired
    UserRepository userRepository;

    @PostMapping("/registration")

    public String createNewUser( @RequestBody Personne user) {
        String hello="";
        Personne userExists = personneService.findAppUserByEmail(user.getEmail());

        if (userExists != null) {

            hello="There is already a user registered with the email provided";

        } else {

            personneService.register(user);
            hello="OK";

        }

        return hello; }
    @PreAuthorize("hasRole('ROLE_PERSONNEL') or hasRole('ROLE_RH')")
    @PostMapping("/add-Personne/{userid}")
    @ResponseBody
    // http://localhost:8089/add-Personne
    public Personne addPersonne(@PathVariable Long userid,@RequestBody Personne p) {
        User user =userRepository.findById(userid).orElse(null);
        p.setUser(user);
        user.setProfilePic(p.getProfilePic());
        return personneService.addPersonne(p);


    }
    @PreAuthorize("hasRole('ROLE_PERSONNEL') or hasRole('ROLE_RH')")
    @PostMapping("/uploadProfilePic/{personneId}")
    public ResponseEntity<String> uploadProfilePic(@RequestParam("file") MultipartFile file, @PathVariable("personneId") Long personneId) {
        // validate the file

        // read the file content as bytes
        byte[] bytes;
        try {
            bytes = file.getBytes();
        } catch (IOException e) {
            // handle the exception
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

        // fetch the Personne entity from the database
        Optional<Personne> optionalPersonne = personneRepository.findById(personneId);
        if (!optionalPersonne.isPresent()) {
            return ResponseEntity.badRequest().body("Personne not found with ID: " + personneId);
        }
        Personne personne = optionalPersonne.get();

        // update the entity with the profile picture bytes
        personne.setProfilePic(bytes);
        personneRepository.save(personne);
        // return the URL of the uploaded profile picture
        String fileDownloadUri =  "blob:" + ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile/")
                .path(personneId.toString())
                .toUriString();
        // update the personne object with the new profile picture URL
        personne.setProfilePicUrl(fileDownloadUri);
        personneRepository.save(personne);

        // return a response
        return ResponseEntity.ok(fileDownloadUri);
    }
    @GetMapping("/getProfilePicUrl/{userId}")
    public ResponseEntity<String> getProfilePicUrl(@PathVariable Long userId) {
        Personne personne = personneRepository.findById(userId).orElse(null);
        if (personne == null || personne.getProfilePic() == null) {
            return ResponseEntity.notFound().build();
        }
        String profilePic = Base64.getEncoder().encodeToString(personne.getProfilePic());
        return ResponseEntity.ok("{\"data\":\"data:image/jpeg;base64," + profilePic + "\"}");
    }
    // http://localhost:8089/getPersonneByUser/{userid}
    @GetMapping("/getPersonneByUser/{userid}")
    @ResponseBody
    public Personne getPersonneByUser(@PathVariable Long userid){

        return personneService.getPersonneByUser(userid) ;
    }
   // http://localhost:8089/personne/{userid}
    @GetMapping("/personne/{userid}")
    @ResponseBody
    public Long getPersonneByUserId(@PathVariable Long userid) {
        // Query the database to get the corresponding personneid for the given userid
        Personne personne= personneRepository.findPersonneByUserId(userid);
        return personne.getId();
    }
    // http://localhost:8089/retrieve-all-Personne
    @GetMapping("/retrieve-all-Personne")
    @ResponseBody
    public List<Personne> RetrieveAllPersonne(){
        List<Personne> p =personneService.RetrieveAllPersonnes();
        return p ;
    }
    // http://localhost:8089/retrieve-personne/{personne-id}
    @GetMapping("/retrieve-personne/{personne-id}")
    @ResponseBody
    public Personne retrievpersonne(@PathVariable("personne-id") long id){
        return personneService.retrievePersonne(id);
    }

    //localhost:8089/modify-personne
    @PutMapping("/modify-personne")
    @ResponseBody
    public Personne modifypersonne (@RequestBody Personne p){


        return personneService.modifyPersonne(p);
    }

    //localhost:8089/delete-personne/{personne-id}
    @DeleteMapping("/delete-personne/{personne-id}")
    @ResponseBody
    void deletepersonne(@PathVariable("personne-id") long id){
        personneService.deletePersonne(id);
    }

}
