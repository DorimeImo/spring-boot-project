package com.example.springbootproject;

import com.example.springbootproject.model.user.User;
import com.example.springbootproject.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class repostoriesTest {

    @Autowired
    UserRepository userRepository;


    @Test
    public void userExtractionTest(){
        User user = (User)userRepository.findByName("Aur");
        System.out.println(user!=null);
    }
}
