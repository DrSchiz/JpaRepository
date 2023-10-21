package com.example.demo.repository;

import com.example.demo.models.Cats;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CatRepository extends JpaRepository<Cats, Integer> {
}
