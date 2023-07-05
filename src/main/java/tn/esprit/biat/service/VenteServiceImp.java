package tn.esprit.biat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.biat.Entity.Covoiturage;
import tn.esprit.biat.Entity.Vente;
import tn.esprit.biat.repository.VenteRepository;

import java.util.List;
@Service
public class VenteServiceImp implements  IventeService{

    @Autowired
    VenteRepository venteRepository;

    @Override
    public List<Vente> RetrieveAllVente() {
        List<Vente> vente =venteRepository.findAll();
        return vente;
    }

    @Override
    public Vente addVente(Vente v) {
        return venteRepository.save(v);
    }

    @Override
    public Vente retrieveVente(Long id) {
       Vente vente =venteRepository.findById(id).orElse(null);
        return vente;
    }
    @Override
    public Vente modifyVenteById(Long id) {
        Vente c = venteRepository.findById(id).orElse(null);
        return venteRepository.save(c);
    }
    @Override
    public Vente modifyVente(Vente c) {
        return venteRepository.save(c);
    }

    @Override
    public void deleteVente(Long id) {
        venteRepository.deleteById(id);
    }
}
