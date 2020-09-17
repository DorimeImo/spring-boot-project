package com.example.springbootproject.controller;


import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import com.example.springbootproject.model.product.Ingredient;
import com.example.springbootproject.model.product.Ingredient.Type;
import com.example.springbootproject.model.product.Order;
import com.example.springbootproject.model.product.Taco;
import com.example.springbootproject.model.user.User;
import com.example.springbootproject.repository.IngredientRepository;
import com.example.springbootproject.repository.TacoRepository;
import com.example.springbootproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.ModelAndView;


@Slf4j
@Controller
@RequestMapping("/design")
public class DesignTacoController {

    @Autowired
    IngredientRepository repository;

    @Autowired
    TacoRepository repositoryTaco;

    @Autowired
    UserRepository repositoryUser;

    @GetMapping
    public String showDesignForm(Model model) {

        List<Ingredient> ingredients= repository.findAll();

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
            List<Ingredient> ingredients = repository.findAll();

            Type[] types = Ingredient.Type.values();
            for (Type type : types) {
                model.addAttribute(type.toString().toLowerCase(),
                        filterByType(ingredients, type));
            }

            model.addAttribute("taco", taco);
            return "design";
        }
        log.info("Processing design: " + taco);

        return "confirmTacoDesign";
    }

    @RequestMapping(value = "/confirm-taco", method = RequestMethod.POST)
    public String confirmTacoDesign(@Valid Taco taco, Principal principal, Model model){
        log.info("inside confirmTacoDesign method");
        User user = (User)repositoryUser.findByName(principal.getName());
        if(user!=null)
            taco.setCreator(user);
        log.info("User found");
        taco.getPrice();
        repositoryTaco.save(taco);
        log.info("taco saved");
        List <Taco> customTacoList= repositoryTaco.getAllByCreator(user);
        model.addAttribute("customTacos", customTacoList);
        return "listOfCustomTacos";
    }

    @RequestMapping(value = "/list-custom-tacos", method = RequestMethod.GET)
    public String showListOfCustomTacos(Model model, Principal principal){
        User user = (User)repositoryUser.findByName(principal.getName());
        List <Taco> customTacoList= repositoryTaco.getAllByCreator(user);
        model.addAttribute("customTacos",customTacoList);
        return "listOfCustomTacos";
    }

    @RequestMapping(value = "/add-taco-to-order", method = RequestMethod.POST)
    public ModelAndView  addTacoToOrder(@RequestParam (value = "id") String id, Model model, HttpSession session){

        log.info("I am in addTacoMethod");

        if(session.getAttribute("chosenTacos")==null){
            log.info("the chosenTacos equals to null");
            Order order = new Order();
            order.addTaco(repositoryTaco.findById(Long.valueOf(id)).get());
            session.setAttribute("chosenTacos", order);
        }
        else{
            Order order =(Order)session.getAttribute("chosenTacos");
            order.addTaco(repositoryTaco.findById(Long.valueOf(id)).get());
            session.setAttribute("chosenTacos", order);
        }
        return new ModelAndView("redirect:/design/list-custom-tacos");
    }

    private List<Ingredient> filterByType(List<Ingredient> ingredients, Type type) {
        return ingredients
                .stream()
                .filter(x -> x.getType().equals(type))
                .collect(Collectors.toList());
    }

}