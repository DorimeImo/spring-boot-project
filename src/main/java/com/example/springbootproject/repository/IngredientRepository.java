package com.example.springbootproject.repository;

import com.example.springbootproject.model.product.Ingredient;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IngredientRepository extends CrudRepository<Ingredient, String> {

    List<Ingredient> findAll();

}
