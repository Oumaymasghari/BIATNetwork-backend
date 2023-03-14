package tn.esprit.biat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.biat.Entity.Personne;
import tn.esprit.biat.Entity.Posts;
import tn.esprit.biat.repository.PostsRepository;

import java.util.List;
@Service
public class PostServiceImp implements IpostService{

    @Autowired
    PostsRepository postsRepository ;


    @Override
    public List<Posts> RetrieveAllPosts() {
        List<Posts> posts =postsRepository.findAll();
        return  posts ;
    }

    @Override
    public Posts addPosts(Posts p) {
        System.out.println(p);
        return postsRepository.save(p);
    }

    @Override
    public Posts retrievePost(Long id) {
        Posts posts = postsRepository.findById(id).orElse(null);
        return posts;
    }

    @Override
    public Posts modifyPostse(Posts p) {

        return postsRepository.save(p);
    }

    @Override
    public void deletePosts(Long id) {
         postsRepository.deleteById(id);
    }
}
