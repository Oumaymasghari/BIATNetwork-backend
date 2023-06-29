package tn.esprit.biat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.biat.Entity.AAcomment;
import tn.esprit.biat.Entity.ActiviteAmicale;
import tn.esprit.biat.Entity.Cathegorie;
import tn.esprit.biat.Entity.PostComment;
import tn.esprit.biat.repository.ActiviteAmicaleCommentRepository;
import tn.esprit.biat.repository.ActiviteAmicaleRepository;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/AAComment")
@RestController
public class AACommentController {

    @Autowired
    ActiviteAmicaleCommentRepository activiteAmicaleCommentRepository;
    @Autowired
    ActiviteAmicaleRepository activiteAmicaleRepository;


    @GetMapping("/retrieve-AAComment/{AA-id}")
    @ResponseBody
    public ResponseEntity<List<AAcomment>> getcommentbyid(@PathVariable("AA-id") long id){

        List<AAcomment> AAcomment  = activiteAmicaleCommentRepository.findByActiviteAmicaleId(id);

        return  new ResponseEntity<>(AAcomment, HttpStatus.OK);
    }
  /*  @GetMapping("/getFilterWordCount/{idAA}")
    public int getFilterWordCount(@PathVariable Long idAA) {
        List<String> filterWords = Arrays.asList("j'aime", "j'adore", "excellent", "excellente");
        List<AAcomment> comments = activiteAmicaleCommentRepository.findByActiviteAmicaleId(idAA);
        Integer totalCount = 0;

        for (AAcomment comment : comments) {
            String commentText = comment.getComment_text().toLowerCase();
            int count = 0;

            for (String word : filterWords) {
                Pattern pattern = Pattern.compile("\\b" + Pattern.quote(word) + "\\b", Pattern.CASE_INSENSITIVE);
                Matcher matcher = pattern.matcher(commentText);

                while (matcher.find()) {
                    count++;
                }
            }

            totalCount += count;
        }
        ActiviteAmicale aa= activiteAmicaleRepository.findById(idAA).orElse(null);
        aa.setNbMots(totalCount);
        activiteAmicaleRepository.save(aa);
        return totalCount;

    } */
    @GetMapping("/getFilterWordCount")
    public Map<Long, Integer> getFilterWordCount() {
        List<String> filterWords = Arrays.asList("j'aime", "j'adore", "excellent", "excellente");
        List<AAcomment> comments = activiteAmicaleCommentRepository.findAll();
        Map<Long, Integer> totalCountMap = new HashMap<>();

        for (AAcomment comment : comments) {
            Long idAA = comment.getActiviteAmicale().getId();
            String commentText = comment.getComment_text().toLowerCase();
            int count = 0;

            for (String word : filterWords) {
                Pattern pattern = Pattern.compile("\\b" + Pattern.quote(word) + "\\b", Pattern.CASE_INSENSITIVE);
                Matcher matcher = pattern.matcher(commentText);

                while (matcher.find()) {
                    count++;
                }
            }

            Integer totalCount = totalCountMap.getOrDefault(idAA, 0);
            totalCount += count;
            totalCountMap.put(idAA, totalCount);
        }

        for (Map.Entry<Long, Integer> entry : totalCountMap.entrySet()) {
            Long idAA = entry.getKey();
            Integer totalCount = entry.getValue();

            ActiviteAmicale aa = activiteAmicaleRepository.findById(idAA).orElse(null);
            if (aa != null) {
                aa.setNbMots(totalCount);
                activiteAmicaleRepository.save(aa);
            }
        }

        return totalCountMap;
    }
    @GetMapping("/getActiviteAmicalesByCategory/{cathegorie}")
    public List<ActiviteAmicale> getActiviteAmicalesByCategory(@PathVariable Cathegorie cathegorie) {
        return activiteAmicaleRepository.findByCathegorie(cathegorie);
    }
}
