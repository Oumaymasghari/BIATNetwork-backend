package tn.esprit.biat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.biat.Entity.Covoiturage;
import tn.esprit.biat.Entity.PostComment;
import tn.esprit.biat.Entity.Posts;
import tn.esprit.biat.repository.CovoiturageRepository;
import tn.esprit.biat.repository.PostCommentRepository;


import java.time.LocalDateTime;
import java.util.List;
@Service
public class commentServiceImp implements IcommentService {
    @Autowired
    PostCommentRepository postCommentRepository;
    @Autowired
    CovoiturageRepository covoiturageRepository;


    @Override
    public List<PostComment> RetrieveAllPostComment() {
        List<PostComment> post =postCommentRepository.findAll();
        return post;
    }

    @Override
    public PostComment addPostComment(Long covoiturageId, String content) {
        Covoiturage covoiturage = new Covoiturage();
        covoiturage.setId(covoiturageId);

        PostComment comment = new PostComment();
        comment.setComment_text(content);
        comment.setCovoiturage(covoiturage);


        return postCommentRepository.save(comment);
    }

    @Override
    public List<PostComment> getCommentsByCovoiturageId(Long covoiturageId) {
        Covoiturage cov=covoiturageRepository.findById(covoiturageId).orElse(null);
        return cov.getPostComments();
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
