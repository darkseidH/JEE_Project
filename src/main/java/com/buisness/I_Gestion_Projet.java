package com.buisness;

import com.presentation.model.Projet;

import java.sql.SQLException;
import java.util.List;

public interface I_Gestion_Projet {
    public Projet addProjet(Projet projet);
    public Projet updateProjet(Projet projet);
    public boolean deleteProjet(Projet projet);
    public List<Projet> findAllProjets() throws SQLException;

    List<Projet> findAllProjetsByEmailChef(String email);
}
