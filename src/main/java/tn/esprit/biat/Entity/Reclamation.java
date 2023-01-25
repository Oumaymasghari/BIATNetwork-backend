package tn.esprit.biat.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Reclamation implements Serializable  {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String author ;
    private String description ;
    private String type ;
    private String etat ;
    @Enumerated(EnumType.STRING)
    private Anonymat anonymat ;
    @ManyToOne
    private Personne personne ;

}
