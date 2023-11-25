package com.presentation.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

import java.sql.Date;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Projet {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Date dateDemarrage;
	private Date dateLiverison;
	private Date dateRuenion;
	private String description;
	private String methodologie;
	private String nom;
	private int nombreJourDeveloppement;
	private String nomClient;
	@ManyToOne
	private ChefProjet chefProjet;

}//end Projet