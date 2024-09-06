package com.vakya.bakendprojet.models;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class User extends BaseModel{
    private String username;
    private String email;
    private String password;

}
