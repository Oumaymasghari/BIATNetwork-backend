package tn.esprit.biat.service;

import tn.esprit.biat.Entity.Covoiturage;
import tn.esprit.biat.Entity.Vente;

import java.util.List;

public interface IventeService {
    List<Vente> RetrieveAllVente();
    Vente addVente(Vente v);
    Vente retrieveVente(Long id );
    Vente modifyVente(Vente c) ;
    void deleteVente(Long id) ;
    public Vente modifyVenteById(Long id);
}
