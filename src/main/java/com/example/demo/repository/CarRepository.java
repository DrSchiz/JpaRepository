package com.example.demo.repository;

import com.example.demo.models.Cars;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarRepository extends JpaRepository<Cars, Integer> {
    List<Cars> findByName(String name);
}
