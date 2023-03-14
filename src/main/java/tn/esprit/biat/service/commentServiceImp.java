package tn.esprit.biat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.biat.Entity.Covoiturage;
import tn.esprit.biat.Entity.PostComment;
import tn.esprit.biat.Entity.Posts;
import tn.esprit.biat.repository.PostCommentRepository;

import java.util.List;
@Service
public class commentServiceImp implements IcommentService {
    @Autowired
    PostCommentRepository postCommentRepository;


    @Override
    public List<PostComment> RetrieveAllPostComment() {
        List<PostComment> post =postCommentRepository.findAll();
        return post;
    }

    @Override
    public PostComment addPostComment(PostComment p) {
        return postCommentRepository.save(p);
    }

    @Override
    public PostComment retrievePostComment(Long id) {
        PostComment post = postCommentRepository.findById(id).orElse(null);
        return post;
    }

    @Override
    public PostComment modifyPostComment(PostComment p) {
        return postCommentRepository.save(p);
    }

    @Override
    public void deletePostComment(Long id) {
        postCommentRepository.deleteById(id);
    }
}
