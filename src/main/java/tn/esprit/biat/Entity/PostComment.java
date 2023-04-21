package tn.esprit.biat.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
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
    @Temporal(TemporalType.TIMESTAMP)
    private Date comment_date ;

    @ManyToOne(fetch = FetchType.LAZY)
    private Posts posts_id ;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private Covoiturage covoiturage;


}
