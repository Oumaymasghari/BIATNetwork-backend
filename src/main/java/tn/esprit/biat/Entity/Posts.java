package tn.esprit.biat.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
//@Table(name = "biat_posts")

public class Posts implements Serializable  {
    private static final long serialVersionUID = 1L;


    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private String text ;
    private String files;

    @ManyToOne
    private User personne1 ;

    @OneToMany(cascade = CascadeType.ALL, mappedBy="posts_id")
    private Set<PostComment> postComments ;

}
