package fr.adsa.demo.controller;

import fr.adsa.demo.config.JwtProvider;
import fr.adsa.demo.model.User;
import fr.adsa.demo.repository.UserRepository;
import fr.adsa.demo.response.AuthResponse;
import fr.adsa.demo.service.CustomUserDetailsService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Security;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private UserRepository userRepository;
    private CustomUserDetailsService customUserDetails;

    private JwtProvider jwtProvider;

    private PasswordEncoder passwordEncoder;

    public AuthController(UserRepository userRepository, CustomUserDetailsService customUserDetails, JwtProvider jwtProvider, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.customUserDetails = customUserDetails;
        this.jwtProvider = jwtProvider;
        this.passwordEncoder = passwordEncoder;
    }

    //register method
    @PostMapping("/signup")
    public AuthResponse createUser(@RequestBody User user) throws Exception {
        String email = user.getEmail();
        String password = user.getPassword();
        String fullName = user.getFullName();

        User isExistEmail = userRepository.findByEmail(email);
        if(isExistEmail != null){
            throw new Exception("Email already exists");
        }

        User createdUser = new User();
        createdUser.setEmail(email);
        createdUser.setPassword(passwordEncoder.encode(password));
        createdUser.setFullName(fullName);
        User savedUser = userRepository.save(createdUser);

        Authentication authentication = new UsernamePasswordAuthenticationToken(email, password);

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtProvider.generateToken(authentication);

        return new AuthResponse(token, "User registered successfully");
    }
}
