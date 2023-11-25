package com.presentation.model;

import jakarta.persistence.Entity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Director extends User{
    public Director() {
        super();
    }
    public Director(long id, String first_name, String last_name, String email, String password, String role) {
        super(id, first_name, last_name, email, password, "director");
    }

}
