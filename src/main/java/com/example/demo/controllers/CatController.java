package com.example.demo.controllers;

import com.example.demo.models.Cats;
import com.example.demo.models.Characters;
import com.example.demo.repository.CatRepository;
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
@RequestMapping(value = "/cats/")
public class CatController {
    private final CatRepository repositoryClass;

    @Autowired
    public CatController (CatRepository repositoryClass) {
        this.repositoryClass = repositoryClass;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getCats(Model model) {
        List<Cats> characters = repositoryClass.findAll();
        model.addAttribute("cats", characters);
        return "all-cats";
    }

    @RequestMapping(value = "create-cat", method = RequestMethod.GET)
    public String cat(Cats cats) {
        return "create-cat";
    }

    @RequestMapping(value = "create-cat", method = RequestMethod.POST)
    public String postCat(@Valid Cats cat, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "create-cat";
        }
        repositoryClass.save(cat);
        model.addAttribute("cats", repositoryClass.findAll());
        return "all-cats";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String getCat(Model model, @PathVariable("id") int id) {
        Cats cat = repositoryClass.findById(id).orElseThrow(() -> new IllegalArgumentException("Данный кот не существует! ->  " + id));
        model.addAttribute("cat", cat);
        return "show-cat";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PATCH)
    public String putCat(Cats cat, Model model) {
        repositoryClass.save(cat);
        model.addAttribute("cats", repositoryClass.findAll());
        return "all-cats";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String putCat(@PathVariable("id") int id, Model model) {
        repositoryClass.deleteById(id);
        model.addAttribute("cats", repositoryClass.findAll());
        return "all-cats";
    }
}
