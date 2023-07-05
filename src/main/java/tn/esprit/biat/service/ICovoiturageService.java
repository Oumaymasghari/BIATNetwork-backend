package tn.esprit.biat.service;

import tn.esprit.biat.Entity.Covoiturage;
import tn.esprit.biat.Entity.Personne;

import java.util.List;

public interface ICovoiturageService {
    List<Covoiturage> RetrieveAllCovoiturage();
    Covoiturage addCovoiturage(Covoiturage c);
    Covoiturage retrieveCovoiturage(Long id );
    Covoiturage modifyCovoiturage(Covoiturage c) ;
    void deleteCovoiturage(Long id) ;
    public Covoiturage modifyCovoiturageById(Long id);
}
