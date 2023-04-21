package tn.esprit.biat.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table
public class Vente implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String article ;

    private Date dateDisponibilite ;

    private int prix ;

    private String place ;
    @Enumerated(EnumType.STRING)
    private TypeVente typeVente ;

    private int contactNumber ;
    private boolean accepted=false;

    @ManyToOne
    private User personne3 ;

    @OneToMany(cascade = CascadeType.ALL, mappedBy="vente")
    @JsonIgnoreProperties("vente")
    private Set<Picture> pictures ;

}
