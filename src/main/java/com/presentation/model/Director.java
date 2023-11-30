package com.presentation.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Director extends User {
    public Director() {
        super();
    }

    public Director(long id, String first_name, String last_name, String email, String password, String role, boolean is_active) {
        super(id, first_name, last_name, email, password, "director", is_active);
    }

}
