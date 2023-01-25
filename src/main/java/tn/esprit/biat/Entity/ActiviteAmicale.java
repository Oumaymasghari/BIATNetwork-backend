package tn.esprit.biat.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
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
    private String nomCathegorie ;
    private String description ;
    private Cathegorie cathegorie ;

    @ManyToMany(mappedBy="activiteAmicales", cascade = CascadeType.ALL)
    private Set<Personne> personnes;
}
