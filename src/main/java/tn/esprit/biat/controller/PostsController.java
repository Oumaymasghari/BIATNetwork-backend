package tn.esprit.biat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import tn.esprit.biat.Entity.Posts;
import tn.esprit.biat.service.IpostService;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/Posts")
@RestController
public class PostsController {

    @Autowired
    IpostService postService ;

    @PostMapping("/add-Posts")
    //@PreAuthorize("hasRole('PERSONNEL') or hasRole('RH')")
    // http://localhost:8089/Posts/add-Posts
    public Posts addPosts(@RequestBody Posts p) {

        return postService.addPosts(p);


    }

    // http://localhost:8089/Posts/retrieve-all-Posts
    @GetMapping("/retrieve-all-Posts")
    @ResponseBody
    public List<Posts> RetrieveAllPosts(){
        List<Posts> p =postService.RetrieveAllPosts();
        return p;
    }
    // http://localhost:8089/Posts/retrieve-Posts/{Posts-id}
    @GetMapping("/retrieve-Posts/{Posts-id}")
    @ResponseBody
    public Posts retrievPosts(@PathVariable("Posts-id") long id){
        return postService.retrievePost(id);
    }

    //localhost:8089/Posts/modify-Posts
    @PutMapping("/modify-Covoiturage")
    @ResponseBody
    public Posts modifyPosts (@RequestBody Posts p){


        return postService.modifyPostse(p);
    }

    //localhost:8089/Posts/delete-Posts/{Posts-id}
    @DeleteMapping("/delete-Posts/{Posts-id}")
    @ResponseBody
    void deletePosts(@PathVariable("Posts-id") long id){

        postService.deletePosts(id);
    }



}
