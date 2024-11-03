package com.example.mozispring.Service;

import com.example.mozispring.Model.MyAppUser;
import com.example.mozispring.Model.MyAppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

@Service
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
    @Autowired
    private MyAppUserRepository userRepository;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public void encodePasswords() {
        List<MyAppUser> users = userRepository.findAll();
        logger.info("Talált felhasználók: " + users);

        for (MyAppUser user : users) {
            if (user.getPassword() != null && !user.getPassword().startsWith("$2a$")) {
                String encodedPassword = passwordEncoder.encode(user.getPassword());
                user.setPassword(encodedPassword);
                logger.info("Titkosított jelszó felhasználónak: " + user.getUsername());
            } else if (user.getPassword() == null) {
                logger.warn("A felhasználó {} jelszava null!", user.getUsername());
            } else {
                logger.info("Felhasználó {} jelszava már titkosítva van.", user.getUsername());
            }
        }

        userRepository.saveAll(users);
    }
}
