package com.presentation.model;


import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class ChefProjet extends User {

    public ChefProjet() {
        super();
    }

    public ChefProjet(long id, String first_name, String last_name, String email, String password, boolean is_active) {
        super(id, first_name, last_name, email, password, "chef", is_active);
    }


}