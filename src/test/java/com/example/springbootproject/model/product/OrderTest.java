package com.example.springbootproject.model.product;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest {

    @Test
    void calculateTotalPrice() {
        Ingredient ingredient = new Ingredient("BBBB", "Description", Ingredient.Type.WRAP, 10);
        ArrayList<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(ingredient);
        ingredients.add(ingredient);
        ingredients.add(ingredient);
        ingredients.add(ingredient);
        ingredients.add(ingredient);

        Taco taco = new Taco();
        taco.setIngredients(ingredients);
        Order order= new Order();
        order.addTaco(taco);
        order.addTaco(taco);
        order.addTaco(taco);
        System.out.println(order.calculateTotalPrice());

    }
}