package tn.esprit.biat.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Role  implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nom;

    @JsonIgnore
    @ManyToMany(cascade = CascadeType.ALL, mappedBy="roles")
    private List<Personne> Personnes;

    @Override
    public String toString() {
        return "Role [id=" + id + ", nom=" + nom + "]";
    }




}
