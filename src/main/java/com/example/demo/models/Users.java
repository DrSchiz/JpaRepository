package com.example.demo.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "users")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int Id;
    @NotBlank(message = "Поле Имя не должно быть пустым!")
    private String Name;
    @NotBlank(message = "Поле Фамилия не должно быть пустым!")
    private String LastName;
    @NotBlank(message = "Поле Почта не должно быть пустым!")
    @Email(message = "Неверный формат почты!")
    private String EMail;

    public Users() {}

    public Users(int id, String name, String lastName, String EMail) {
        Id = id;
        Name = name;
        LastName = lastName;
        this.EMail = EMail;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getEMail() {
        return EMail;
    }

    public void setEMail(String EMail) {
        this.EMail = EMail;
    }
}
