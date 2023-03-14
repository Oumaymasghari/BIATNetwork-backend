package tn.esprit.biat.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Getter
@Setter
@Table
@NoArgsConstructor
@AllArgsConstructor


public class PostComment implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String comment_text ;
    private String comment_author;
    private Date comment_date ;

    @ManyToOne
    private Posts posts_id ;

    @ManyToOne
    private Covoiturage covoiturage;


}
