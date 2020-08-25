package com.example.springbootproject.controller;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.example.springbootproject.model.Ingredient;
import com.example.springbootproject.model.Ingredient.Type;
import com.example.springbootproject.model.Taco;
import com.example.springbootproject.repository.TacoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Controller
@RequestMapping("/design")
public class DesignTacoController {

    @Autowired
    TacoRepository repository;

    @GetMapping
    public String showDesignForm(Model model) {

        List<Ingredient> ingredients= populateTheList();

        Type[] types = Ingredient.Type.values();
        for (Type type : types) {
            model.addAttribute(type.toString().toLowerCase(),
                    filterByType(ingredients, type));
        }

        model.addAttribute("taco", new Taco());
        return "design";
    }

    @PostMapping
    public String processDesign(@Valid Taco taco, Errors errors, Model model) {
        if (errors.hasErrors()) {
            List<Ingredient> ingredients= populateTheList();

            Type[] types = Ingredient.Type.values();
            for (Type type : types) {
                model.addAttribute(type.toString().toLowerCase(),
                        filterByType(ingredients, type));
            }

            model.addAttribute("taco", taco);
            return "design";
        }

  // Save the taco design...
        repository.save(taco);
  // We'll do this in chapter 3
        log.info("Processing design: " + taco);

        return "redirect:/orders/current";
    }

    private List<Ingredient> filterByType(List<Ingredient> ingredients, Type type) {
        return ingredients
                .stream()
                .filter(x -> x.getType().equals(type))
                .collect(Collectors.toList());
    }

    private List<Ingredient> populateTheList() {
        List<Ingredient> ingredients = Arrays.asList(
                new Ingredient("FLTO", "Flour Tortilla", Ingredient.Type.WRAP),
                new Ingredient("COTO", "Corn Tortilla", Ingredient.Type.WRAP),
                new Ingredient("GRBF", "Ground Beef", Ingredient.Type.PROTEIN),
                new Ingredient("CARN", "Carnitas", Ingredient.Type.PROTEIN),
                new Ingredient("TMTO", "Diced Tomatoes", Ingredient.Type.VEGGIES),
                new Ingredient("LETC", "Lettuce", Ingredient.Type.VEGGIES),
                new Ingredient("CHED", "Cheddar", Ingredient.Type.CHEESE),
                new Ingredient("JACK", "Monterrey Jack", Ingredient.Type.CHEESE),
                new Ingredient("SLSA", "Salsa", Ingredient.Type.SAUCE),
                new Ingredient("SRCR", "Sour Cream", Ingredient.Type.SAUCE)
        );
        return ingredients;
    }
}