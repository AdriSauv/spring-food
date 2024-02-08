package fr.adsa.demo.controller;

import fr.adsa.demo.model.User;
import fr.adsa.demo.repository.UserRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/users")
    public User createUser(@RequestBody User user) {
        User isUserExist = userRepository.findByEmail(user.getEmail());
        if(isUserExist!=null){
            throw new RuntimeException("user already exist with email: "+user.getEmail());
        }

        User savedUser = userRepository.save(user);
        return savedUser;
    }
}
