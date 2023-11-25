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
}
