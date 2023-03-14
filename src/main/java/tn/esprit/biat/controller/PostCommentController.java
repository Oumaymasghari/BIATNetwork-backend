package tn.esprit.biat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import tn.esprit.biat.Entity.Covoiturage;
import tn.esprit.biat.Entity.PostComment;
import tn.esprit.biat.repository.PostCommentRepository;
import tn.esprit.biat.service.IcommentService;
import tn.esprit.biat.service.IpostService;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/PostComment")
@RestController
public class PostCommentController {
    @Autowired
    IcommentService commentService;

    @PostMapping("/add-PostComment")
    @PreAuthorize("hasRole('ROLE_PERSONNEL') or hasRole('ROLE_RH')")
    // http://localhost:8089/PostComment/add-PostComment
    public PostComment addPostComment(@RequestBody PostComment p) {

        return commentService.addPostComment(p);


    }

    // http://localhost:8089/PostComment/retrieve-all-PostComment
    @GetMapping("/retrieve-all-PostComment")
    @ResponseBody
    public List<PostComment> RetrieveAllPostComment(){
        List<PostComment> C =commentService.RetrieveAllPostComment();
        return C ;
    }
    // http://localhost:8089/PostComment/retrieve-PostComment/{PostComment-id}
    @GetMapping("/retrieve-PostComment/{PostComment-id}")
    @ResponseBody
    public PostComment retrievPostComment(@PathVariable("PostComment-id") long id){
        return commentService.retrievePostComment(id);
    }

    //localhost:8089/PostComment/modify-PostComment
    @PutMapping("/modify-PostComment")
    @ResponseBody
    public PostComment modifyCovoiturage (@RequestBody PostComment C){


        return commentService.modifyPostComment(C);
    }

    //localhost:8089/PostComment/delete-PostComment/{PostComment-id}
    @DeleteMapping("/delete-PostComment/{PostComment-id}")
    @ResponseBody
    void deletePostComment(@PathVariable("PostComment-id") long id){

        commentService.deletePostComment(id);
    }
}
