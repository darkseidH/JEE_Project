package com.buisness;

import com.presentation.model.Projet;

import java.sql.SQLException;
import java.util.List;

public interface I_Gestion_Projet {
    Projet addProjet(Projet projet);
    Projet updateProjet(Projet projet);
    boolean deleteProjet(Projet projet);
    List<Projet> findAllProjets() throws SQLException;
}
