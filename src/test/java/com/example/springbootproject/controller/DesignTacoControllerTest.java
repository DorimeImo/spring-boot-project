package com.example.springbootproject.controller;

import com.example.springbootproject.mapper.TacoMapper;
import com.example.springbootproject.model.product.Taco;
import com.example.springbootproject.model.user.User;
import com.example.springbootproject.repository.TacoRepository;
import com.example.springbootproject.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class DesignTacoControllerTest {

    @Autowired
    TacoRepository repositoryTaco;
    @Autowired
    UserRepository repositoryUser;
    @Autowired
    TacoMapper tacoMapper;

    @Test
    void showDesignForm() {
    }

    @Test
    void processDesign() {
    }

    @Test
    void confirmTacoDesign() {
        User user = repositoryUser.findByName("Aur");
        List <Taco> tacoList = tacoMapper.getArticle(275);
    }

    @Test
    void showListOfCustomTacos() {
    }
}