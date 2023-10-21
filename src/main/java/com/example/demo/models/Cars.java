package com.example.demo.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

@Entity
@Table(name = "cars")
public class Cars {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int Id;
    @NotBlank(message = "Поле Имя не должно быть пустым!")
    private String name;
    @NotBlank(message = "Поле Модель не должно быть пустым!")
    private String Model;
    @Range(min=0, message = "Цена не может быть меньше 0!")
    private int Price;
    public Cars() {}

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModel() {
        return Model;
    }

    public void setModel(String model) {
        Model = model;
    }

    public int getPrice() {
        return Price;
    }

    public void setPrice(int price) {
        Price = price;
    }

    public Cars(int id, String name, String model, int price) {
        Id = id;
        this.name = name;
        Model = model;
        Price = price;
    }
}
