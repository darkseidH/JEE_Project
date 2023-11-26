package com.presentation.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.sql.Date;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Projet {
	private Long id;
	private Date dateDemarrage;
	private Date dateLiverison;
	private Date dateRuenion;
	private String description;
	private String methodologie;
	private String nom;
	private int nombreJourDeveloppement;
	private String nomClient;

}//end Projet