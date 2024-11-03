package com.example.mozispring;

import com.example.mozispring.Model.MyAppUserRepository;
import com.example.mozispring.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class StartupRunner implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(StartupRunner.class);

    @Autowired
    private MyAppUserRepository userRepository;

    @Autowired
    private UserService userService;

    @Override
    public void run(String... args) throws Exception {
        if (userRepository.count() == 0) {
            logger.info("Nincsenek felhasználók az adatbázisban");
        } else {
            userRepository.findAll().forEach(user -> logger.info("Felhasználó az adatbázisban: " + user.getUsername()));
        }
        userService.encodePasswords();
    }
}
