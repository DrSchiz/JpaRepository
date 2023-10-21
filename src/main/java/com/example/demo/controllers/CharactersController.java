package com.example.demo.controllers;

import com.example.demo.models.Characters;
import com.example.demo.repository.CharacterRepository;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/characters/")
public class CharactersController {
    private final CharacterRepository repositoryClass;

    @Autowired
    public CharactersController (CharacterRepository repositoryClass) {
        this.repositoryClass = repositoryClass;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getCharacters(Model model) {
        List<Characters> characters = repositoryClass.findAll();
        model.addAttribute("characters", characters);
        return "all-characters";
    }

    @RequestMapping(value = "create-character", method = RequestMethod.GET)
    public String character(Characters characters) {
        return "create-character";
    }

    @RequestMapping(value = "create-character", method = RequestMethod.POST)
    public String postCharacter(@Valid Characters character, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "create-character";
        }
        repositoryClass.save(character);
        model.addAttribute("characters", repositoryClass.findAll());
        return "all-characters";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String getCharacter(Model model, @PathVariable("id") int id) {
        Characters character = repositoryClass.findById(id).orElseThrow(() -> new IllegalArgumentException("Данный персонаж не существует! ->  " + id));
        model.addAttribute("character", character);
        return "show-character";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PATCH)
    public String putCharacter(Characters character, Model model) {
        repositoryClass.save(character);
        model.addAttribute("characters", repositoryClass.findAll());
        return "all-characters";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String putCharacters(@PathVariable("id") int id, Model model) {
        repositoryClass.deleteById(id);
        model.addAttribute("characters", repositoryClass.findAll());
        return "all-characters";
    }
}
