package com.example.springbootproject.repository;

import com.example.springbootproject.model.Ingredient;
import org.springframework.data.repository.CrudRepository;


public interface IngredientRepository
        extends CrudRepository<Ingredient, String> {

}
