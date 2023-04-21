package tn.esprit.biat.service;

import tn.esprit.biat.Entity.Covoiturage;
import tn.esprit.biat.Entity.PostComment;

import java.util.List;

public interface IcommentService {
    List<PostComment> RetrieveAllPostComment();
    PostComment addPostComment(Long covoiturageId, String content);
    List<PostComment> getCommentsByCovoiturageId(Long covoiturageId);
    PostComment modifyPostComment(PostComment p) ;
    void deletePostComment(Long id) ;
}
