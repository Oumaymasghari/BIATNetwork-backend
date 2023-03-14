package tn.esprit.biat.service;

import tn.esprit.biat.Entity.Covoiturage;
import tn.esprit.biat.Entity.Posts;

import java.util.List;

public interface IpostService {
    List<Posts> RetrieveAllPosts();
    Posts addPosts(Posts p);
    Posts retrievePost(Long id );
    Posts modifyPostse(Posts p) ;
    void deletePosts(Long id) ;
}
