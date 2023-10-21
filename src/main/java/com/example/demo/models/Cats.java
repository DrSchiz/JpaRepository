package com.example.demo.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "cats")
public class Cats {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int Id;
    @NotBlank(message = "Поле Имя не должно быть пустым!")
    private String Name;
    @NotBlank(message = "Поле Порода не должно быть пустым!")
    private String Breed;
    @NotBlank(message = "Поле Цвет не должно быть пустым!")
    private String Color;
    public Cats() {}

    public Cats(int id, String name, String breed, String color) {
        Id = id;
        Name = name;
        Breed = breed;
        Color = color;
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

    public String getBreed() {
        return Breed;
    }

    public void setBreed(String breed) {
        Breed = breed;
    }

    public String getColor() {
        return Color;
    }

    public void setColor(String color) {
        Color = color;
    }
}
