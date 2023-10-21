package com.example.demo.models;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.Range;

@Entity
@Table(name = "products")
public class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int Id;
    @NotBlank(message = "Поле Название не должно быть пустым!")
    private String Name;
    @NotBlank(message = "Поле Описание не должно быть пустым!")
    private String Description;

    public Products() {}

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

    public int getPrice() {
        return Price;
    }

    public void setPrice(int price) {
        Price = price;
    }

    public Products(int id, String name, String description, int price) {
        Id = id;
        Name = name;
        Description = description;
        Price = price;
    }

    @Range(min=0, message = "Цена не может быть меньше 1!")
    private int Price;
}
