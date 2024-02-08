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
        User savedUser = userRepository.save(user);
        return savedUser;
    }

    public User findByEmail(String email) throws Exception {
        User user = userRepository.findByEmail(email);
        if(user!=null){
            throw new Exception("user not found with email: "+email);
        }
        return user;
    }
}