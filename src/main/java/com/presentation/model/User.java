package com.presentation.model;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {
    private long id;
    private String first_name;
    private String last_name;
    private String email;
    private String password;
    private String role;
}
