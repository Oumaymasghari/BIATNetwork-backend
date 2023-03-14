package tn.esprit.biat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import tn.esprit.biat.Entity.Personne;
import tn.esprit.biat.service.IpersonneService;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
//@RequestMapping("/personne")
@RestController
public class PersonneController {

    @Autowired
    IpersonneService personneService ;

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
    @PostMapping("/add-Personne")
    @ResponseBody
    // http://localhost:8089/add-Personne
    public Personne addPersonne(@RequestBody Personne p) {

        return personneService.addPersonne(p);


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
