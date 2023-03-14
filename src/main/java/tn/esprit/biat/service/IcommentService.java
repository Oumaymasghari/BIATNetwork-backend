package tn.esprit.biat.service;

import tn.esprit.biat.Entity.Covoiturage;
import tn.esprit.biat.Entity.PostComment;

import java.util.List;

public interface IcommentService {
    List<PostComment> RetrieveAllPostComment();
    PostComment addPostComment(PostComment p);
    PostComment retrievePostComment(Long id );
    PostComment modifyPostComment(PostComment p) ;
    void deletePostComment(Long id) ;
}
