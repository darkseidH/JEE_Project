package com.presentation.model;

import lombok.*;

import java.sql.Date;


@Setter
@Getter
@ToString
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
	private Long chefProjet_id;

}//end Projet