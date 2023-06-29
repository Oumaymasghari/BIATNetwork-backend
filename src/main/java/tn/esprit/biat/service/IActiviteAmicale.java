package tn.esprit.biat.service;

import tn.esprit.biat.Entity.ActiviteAmicale;
import tn.esprit.biat.Entity.Vente;

import java.util.List;

public interface IActiviteAmicale {
    List<ActiviteAmicale> RetrieveAllActiviteAmicale();
    ActiviteAmicale addActiviteAmicale(ActiviteAmicale v);
    ActiviteAmicale retrieveActiviteAmicale(Long id );
    ActiviteAmicale modifyActiviteAmicale(ActiviteAmicale c) ;
    void deleteActiviteAmicale(Long id) ;
}
