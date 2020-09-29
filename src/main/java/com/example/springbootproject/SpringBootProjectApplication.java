package com.example.springbootproject;


import com.example.springbootproject.jms.JmsOrder;
import com.example.springbootproject.jms.JmsServerAgent;
import com.example.springbootproject.model.product.Ingredient;
import com.example.springbootproject.model.product.Taco;
import com.example.springbootproject.model.user.Address;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;

@SpringBootApplication
@MapperScan("com.example.springbootproject")
public class SpringBootProjectApplication implements ApplicationRunner {

	@Autowired
	JmsServerAgent sender;

	@Override
	public void run(ApplicationArguments applicationArguments) throws Exception {
		JmsOrder order = new JmsOrder();

		Ingredient ingredient = new Ingredient("BBBB", "Description", Ingredient.Type.WRAP, 10);
		ArrayList<Ingredient> ingredients = new ArrayList<>();
		ingredients.add(ingredient);
		ingredients.add(ingredient);
		ingredients.add(ingredient);
		ingredients.add(ingredient);
		ingredients.add(ingredient);
		Taco taco1 = new Taco();
		taco1.setIngredients(ingredients);
		Taco taco2= new Taco();
		taco2.setIngredients(ingredients);
		ArrayList <Taco> tacos= new ArrayList<>();
		tacos.add(taco1);
		tacos.add(taco2);

		order.setTacos(tacos);

		Address address = new Address();
		address.setCity("Mun");
		address.setStreet("Stein");
		address.setState("Aust");
		address.setZip("213");

		order.setAddress(address);

		sender.send(order);
	}



	public static void main(String[] args) {
		SpringApplication.run(SpringBootProjectApplication.class, args);
	}

}
