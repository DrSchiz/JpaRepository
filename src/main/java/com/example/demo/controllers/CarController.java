package com.example.demo.controllers;

import com.example.demo.models.Cars;
import com.example.demo.models.Cats;
import com.example.demo.models.Characters;
import com.example.demo.repository.CarRepository;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping(value = "/cars/")
public class CarController {
    private final CarRepository repositoryClass;

    @Autowired
    public CarController (CarRepository repositoryClass) {
        this.repositoryClass = repositoryClass;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getCars(@RequestParam(name = "name", defaultValue = "") String name, Model model) {
        if (name.equals("")) {
            List<Cars> cars = repositoryClass.findAll();
            model.addAttribute("cars", cars);
        } else {
            List<Cars> cars = repositoryClass.findByName(name);
            model.addAttribute("cars", cars);
        }
        return "all-cars";
    }

    @RequestMapping(value="/", method = RequestMethod.POST)
    public String postCar(@RequestParam(name = "name", defaultValue = "") String name, RedirectAttributes redirectAttributes) {
        redirectAttributes.addAttribute("name", name);
        return "redirect:/cars/";
    }

    @RequestMapping(value = "create-car", method = RequestMethod.GET)
    public String car(Cars cars) {
        return "create-car";
    }

    @RequestMapping(value = "create-car", method = RequestMethod.POST)
    public String postCar(@Valid Cars car, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "create-car";
        }
        repositoryClass.save(car);
        model.addAttribute("cars", repositoryClass.findAll());
        return "all-cars";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String getCar(Model model, @PathVariable("id") int id) {
        Cars car = repositoryClass.findById(id).orElseThrow(() -> new IllegalArgumentException("Данная машина не существует! ->  " + id));
        model.addAttribute("car", car);
        return "show-car";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PATCH)
    public String putCar(Cars car, Model model) {
        repositoryClass.save(car);
        model.addAttribute("cars", repositoryClass.findAll());
        return "all-cars";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String putCar(@PathVariable("id") int id, Model model) {
        repositoryClass.deleteById(id);
        model.addAttribute("cars", repositoryClass.findAll());
        return "all-cars";
    }
}
