package fr.adsa.demo.controller;

import fr.adsa.demo.model.User;
import fr.adsa.demo.repository.UserRepository;
import fr.adsa.demo.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping("/api/users/profile")
    public User findUserByJwt(@RequestHeader(name = "Authorization") String jwt) throws Exception {
        return userService.findUserByJwt(jwt);
    }


}
