package com.example.demo.controllers;

import com.example.demo.models.Users;
import com.example.demo.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/users/")
public class UsersController {
    private final UserRepository repositoryClass;

    @Autowired
    public UsersController (UserRepository repositoryClass) {
        this.repositoryClass = repositoryClass;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getUsers(Model model) {
        List<Users> users = repositoryClass.findAll();
        model.addAttribute("users", users);
        return "all-users";
    }

    @RequestMapping(value = "create-user", method = RequestMethod.GET)
    public String user(Users user) {
        return "create-user";
    }

    @RequestMapping(value = "create-user", method = RequestMethod.POST)
    public String postUser(@Valid Users user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "create-user";
        }
        repositoryClass.save(user);
        model.addAttribute("users", repositoryClass.findAll());
        return "all-users";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String getUser(Model model, @PathVariable("id") int id) {
        Users user = repositoryClass.findById(id).orElseThrow(() -> new IllegalArgumentException("Данный пользователь не существует! ->  " + id));
        model.addAttribute("user", user);
        return "show-user";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PATCH)
    public String putUser(Users user, Model model) {
        repositoryClass.save(user);
        model.addAttribute("users", repositoryClass.findAll());
        return "all-users";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String putUser(@PathVariable("id") int id, Model model) {
        repositoryClass.deleteById(id);
        model.addAttribute("users", repositoryClass.findAll());
        return "all-users";
    }
}
