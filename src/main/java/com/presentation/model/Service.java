package com.presentation.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Service {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	private Long id;
	private String description;
	private int duree;

	@ManyToOne
	public Projet projet;
	@ManyToOne
	public Developer developer;

}//end Service