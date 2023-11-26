package com.presentation.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Set;


@Setter
@Getter
public class ChefProjet extends User {

	public ChefProjet(long id, String first_name, String last_name, String email, String password) {
		super(id, first_name, last_name, email, password, "ChefProjet");
	}

	public ChefProjet() {
	}

}