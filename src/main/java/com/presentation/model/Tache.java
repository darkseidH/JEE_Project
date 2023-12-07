package com.presentation.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Tache {
	private Long id;
	private int avancement;
	private String description;
	private Long service_id;

}//end Tache