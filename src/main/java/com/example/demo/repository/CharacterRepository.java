package com.example.demo.repository;

import com.example.demo.models.Characters;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CharacterRepository extends JpaRepository<Characters, Integer> {
}
