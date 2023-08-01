package com.example.test2.config;


import com.example.test2.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class UserCommandLineRunner implements CommandLineRunner {

    @Autowired
    private UserService service;


    @Override
    public void run(String... args) throws Exception {
//        UserIn bob = new UserIn();
//        bob.setFirstName("bob");
//        bob.setLastName("boby");
//        bob.setPassword("123");
//        bob.setAge(25);
//        bob.setEmail("bob@gmail.com");
//
//        UserIn tom = new UserIn();
//        tom.setFirstName("tom");
//        tom.setLastName("tomy");
//        tom.setPassword("123");
//        tom.setAge(26);
//        tom.setEmail("tom@gmail.com");
//
//        UserIn rum = new UserIn();
//        rum.setFirstName("rum");
//        rum.setLastName("rumy");
//        rum.setPassword("123");
//        rum.setAge(28);
//        rum.setEmail("rum@gmail.com");
//
//        service.create(bob);
//        service.create(tom);
//        service.create(rum);

    }
}
