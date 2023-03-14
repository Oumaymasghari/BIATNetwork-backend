package tn.esprit.biat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import tn.esprit.biat.Entity.Covoiturage;
import tn.esprit.biat.Entity.Reactions;
import tn.esprit.biat.repository.CovoiturageRepository;
import tn.esprit.biat.service.ICovoiturageService;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/Covoiturage")
@RestController
public class CovoiturageController {

    @Autowired
    ICovoiturageService covoiturageService ;
    @Autowired
    CovoiturageRepository covoiturageRepository ;

    @PostMapping("/add-Covoiturage")
    @PreAuthorize("hasRole('ROLE_PERSONNEL') or hasRole('ROLE_RH')")
    // http://localhost:8089/Covoiturage/add-Covoiturage
    public Covoiturage addCovoiturage(@RequestBody Covoiturage C) {

        return covoiturageService.addCovoiturage(C);


    }

    // http://localhost:8089/Covoiturage/retrieve-all-Covoiturage
    @GetMapping("/retrieve-all-Covoiturage")
    @ResponseBody
    public List<Covoiturage> RetrieveAllCovoiturage(){
        List<Covoiturage> C =covoiturageService.RetrieveAllCovoiturage();
        return C ;
    }
    // http://localhost:8089/Covoiturage/retrieve-Covoiturage/{Covoiturage-id}
    @GetMapping("/retrieve-Covoiturage/{Covoiturage-id}")
    @ResponseBody
    public Covoiturage retrievCovoiturage(@PathVariable("Covoiturage-id") long id){
        return covoiturageService.retrieveCovoiturage(id);
    }

    //localhost:8089/Covoiturage/modify-Covoiturage
    @PutMapping("/modify-Covoiturage")
    @ResponseBody
    public Covoiturage modifyCovoiturage (@RequestBody Covoiturage C){


        return covoiturageService.modifyCovoiturage(C);
    }

    //localhost:8089/Covoiturage/delete-Covoiturage/{Covoiturage-id}
    @DeleteMapping("/delete-Covoiturage/{Covoiturage-id}")
    @ResponseBody
    void deleteCovoiturage(@PathVariable("Covoiturage-id") long id){

        covoiturageService.deleteCovoiturage(id);
    }
    @PostMapping("/reactions/{covId}")
    public Covoiturage addReaction(@PathVariable("covId") long covId, @RequestBody Reactions
            reaction) {
        Covoiturage covoiturage = covoiturageRepository.findById(covId).orElse(null);

        switch (reaction.getEmoji()) {
            case "😀":
                covoiturage.setLikeCount(covoiturage.getLikeCount() + 1);
                break;
            case "😍":
                covoiturage.setLoveCount(covoiturage.getLoveCount() + 1);
                break;
            case "👍":
                covoiturage.setThumbsUpCount(covoiturage.getThumbsUpCount() + 1);
                break;
            case "👎":
                covoiturage.setThumbsDownCount(covoiturage.getThumbsDownCount() + 1);
                break;
            case "🎉":
                covoiturage.setCelebrationCount(covoiturage.getCelebrationCount() + 1);
                break;
            default:
                throw new IllegalArgumentException("Invalid emoji");
        }


        return covoiturageRepository.save(covoiturage);
    }


}
