package com.example.demo.controllers;

import com.example.demo.models.Products;
import com.example.demo.repository.ProductRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/products/")
public class ProductsController {
    private final ProductRepository repositoryClass;

    @Autowired
    public ProductsController (ProductRepository repositoryClass) {
        this.repositoryClass = repositoryClass;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getProducts(Model model) {
        List<Products> products = repositoryClass.findAll();
        model.addAttribute("products", products);
        return "all-products";
    }

    @RequestMapping(value = "create-product", method = RequestMethod.GET)
    public String product(Products products) {
        return "create-product";
    }

    @RequestMapping(value = "create-product", method = RequestMethod.POST)
    public String postProduct(@Valid Products product, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "create-product";
        }
        repositoryClass.save(product);
        model.addAttribute("products", repositoryClass.findAll());
        return "all-products";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String getProduct(Model model, @PathVariable("id") int id) {
        Products product = repositoryClass.findById(id).orElseThrow(() -> new IllegalArgumentException("Данный продукт не существует! ->  " + id));
        model.addAttribute("product", product);
        return "show-product";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PATCH)
    public String putProduct(Products product, Model model) {
        repositoryClass.save(product);
        model.addAttribute("products", repositoryClass.findAll());
        return "all-products";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String putProduct(@PathVariable("id") int id, Model model) {
        repositoryClass.deleteById(id);
        model.addAttribute("products", repositoryClass.findAll());
        return "all-products";
    }
}
