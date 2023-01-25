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

@DiscriminatorValue("2")
public class Vente implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String article ;
    private String dateDisponibilite ;
    private int prix ;
    private String place ;
    @Enumerated(EnumType.STRING)
    private TypeVente typeVente ;
}
