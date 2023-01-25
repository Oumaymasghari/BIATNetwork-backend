package tn.esprit.biat.Entity;

import java.io.Serializable;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.*;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Personne implements Serializable{
	
	private static final long serialVersionUID = 1L;
    
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;
	private String nom;
	private String prenom;
	private int age;
	private int cin;
	private String email;
	private Date dateNaissance;
	@Enumerated
	private Sexe sexe;
	private int telPersonnel;
	private int telPoste;
	private String agence ;
	private String password ;


	@ManyToMany(cascade = CascadeType.ALL)
	private List<Role> roles;

	@ManyToOne
	@JoinColumn(name = "departement_id")
	private Departement departement ;

	@ManyToMany(cascade = CascadeType.ALL)
	private Set<ActiviteAmicale> activiteAmicales;

	@OneToMany(cascade = CascadeType.ALL, mappedBy="personne")
	private Set<Reclamation> reclamations ;

	@OneToMany(cascade = CascadeType.ALL, mappedBy="personne1")
	private Set<Posts> posts ;
}
