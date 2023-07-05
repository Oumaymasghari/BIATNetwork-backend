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



    private int prix ;

    private String place ;
    @Enumerated(EnumType.STRING)
    private TypeVente typeVente ;
    private byte[] profilePic;
    private int contactNumber ;
    private boolean accepted=false;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private User user ;

    @OneToMany(cascade = CascadeType.ALL, mappedBy="vente")
    @JsonIgnoreProperties("vente")
    private Set<Picture> pictures ;

}
