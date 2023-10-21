package com.example.demo.models;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

@Entity
@Table(name = "characters")
public class Characters {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int Id;

    public Characters() {}
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

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Characters(int id, String name, String description, int age) {
        Id = id;
        Name = name;
        Description = description;
        this.age = age;
    }

    @NotBlank(message = "Поле Имя не должно быть пустым!")
    private String Name;
    @NotBlank(message = "Поле Описание не должно быть пустым!")
    private String Description;
    @Range(min=0, message = "Цена не может быть меньше 0!")
    private int age;
}
