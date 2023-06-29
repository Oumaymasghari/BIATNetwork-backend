package tn.esprit.biat.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class ActiviteAmicale implements Serializable  {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description ;
    private Cathegorie cathegorie ;
    @Temporal(TemporalType.TIMESTAMP)
    private Date datePost ;
    private String enseigne ;
    private Integer  nbMots;
    // Reaction properties
    private Integer  likeCount = 0 ;
    private Integer loveCount = 0;
    private Integer thumbsUpCount  = 0;
    private Integer thumbsDownCount = 0 ;
    private Integer celebrationCount = 0 ;



    @ManyToOne(cascade = CascadeType.PERSIST)
    private User user;
    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL, mappedBy="activiteAmicale")
    List<AAcomment> AAcomment ;
    @OneToMany(cascade = CascadeType.ALL, mappedBy="activiteAmicale")
    @JsonIgnoreProperties("activiteAmicale")
    private Set<picturesAmicale> picturesAmicales ;
}
