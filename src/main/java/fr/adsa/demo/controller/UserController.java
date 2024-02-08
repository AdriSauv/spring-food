package fr.adsa.demo.controller;

import fr.adsa.demo.model.User;
import fr.adsa.demo.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @DeleteMapping("/users/{id}")
    public String deleteUser(@PathVariable Long id){
        userRepository.deleteById(id);
        return "User deleted successfully";
    }

    @GetMapping("/users/all")
    public List<User> getUsers(){
        return userRepository.findAll();
    }
}
