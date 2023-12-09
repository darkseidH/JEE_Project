package com.presentation.model;


import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Notification {
    private Long id;
    private String contenu;
    private Long projetId;
    private Long userId;
}
