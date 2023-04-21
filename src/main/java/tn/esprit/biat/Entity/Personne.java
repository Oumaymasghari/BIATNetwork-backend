package tn.esprit.biat.Entity;

import java.util.*;

import javax.persistence.*;


import lombok.*;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "personne")
public class Personne {
	
	private static final long serialVersionUID = 1L;
    
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;
	@Lob
	private byte[] profilePic;
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
	private String profilePicUrl;



	@OneToOne
	private User user ;


}
