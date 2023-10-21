package com.example.demo.repository;

import com.example.demo.models.Products;
import com.example.demo.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Products, Integer> {
}
