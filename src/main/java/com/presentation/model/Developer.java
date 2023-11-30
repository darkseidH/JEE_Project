package com.presentation.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Developer extends User {

	public Developer() {
		super();
	}
	public Developer(long id, String first_name, String last_name, String email, String password,boolean is_active) {
		super(id, first_name, last_name, email, password, "developer",is_active);
	}
}