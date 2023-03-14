package tn.esprit.biat.Entity;

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


public class Covoiturage  implements Serializable {
    private static final long serialVersionUID = 1L;
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String heureDepart ;
    private String depart ;
    private String arrivee ;
    private Date datedepart ;
    private int nbPersonne ;
    private int prix ;
    private String contactNumber ;
    private Date datePost ;

    // Reaction properties
    private Integer  likeCount ;
    private Integer loveCount;
    private Integer thumbsUpCount ;
    private Integer thumbsDownCount ;
    private Integer celebrationCount ;
    @ManyToOne(cascade = CascadeType.PERSIST)
    private User user ;
    @OneToMany(mappedBy = "cov", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Reactions> reactions = new ArrayList<>();
    @OneToMany(cascade = CascadeType.ALL, mappedBy="covoiturage")
    private Set<PostComment> postComments ;

}
