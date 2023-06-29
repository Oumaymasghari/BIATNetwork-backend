package tn.esprit.biat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.biat.Entity.ActiviteAmicale;
import tn.esprit.biat.Entity.Covoiturage;
import tn.esprit.biat.Entity.PostComment;
import tn.esprit.biat.repository.ActiviteAmicaleRepository;

import java.util.List;
@Service
public class activiteAmicaleImp implements IActiviteAmicale{

    @Autowired
    ActiviteAmicaleRepository activiteAmicaleRepository;


    @Override
    public List<ActiviteAmicale> RetrieveAllActiviteAmicale() {
        List<ActiviteAmicale> ActiviteAmicale =activiteAmicaleRepository.findAll();

        return ActiviteAmicale;
    }

    @Override
    public ActiviteAmicale addActiviteAmicale(ActiviteAmicale v) {
        return activiteAmicaleRepository.save(v);
    }

    @Override
    public ActiviteAmicale retrieveActiviteAmicale(Long id) {
        ActiviteAmicale activiteAmicale = activiteAmicaleRepository.findById(id).orElse(null);
        return activiteAmicale;
    }

    @Override
    public ActiviteAmicale modifyActiviteAmicale(ActiviteAmicale c) {
        return null;
    }

    @Override
    public void deleteActiviteAmicale(Long id) {

    }
}
