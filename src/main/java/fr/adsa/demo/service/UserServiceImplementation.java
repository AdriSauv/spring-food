package fr.adsa.demo.service;

import fr.adsa.demo.config.JwtProvider;
import fr.adsa.demo.model.User;
import fr.adsa.demo.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImplementation implements UserService{
    private UserRepository userRepository;

    private JwtProvider jwtProvider;

    public UserServiceImplementation(UserRepository userRepository, JwtProvider jwtProvider) {
        this.userRepository = userRepository;
        this.jwtProvider = jwtProvider;
    }
    @Override
    public User findUserById(Long id) throws Exception {
        return userRepository.findById(id).orElseThrow(()->new Exception("User not found"));
    }

    @Override
    public User findUserByJwt(String jwt) throws Exception {
        String email = jwtProvider.getEmailFromToken(jwt);
        if(email==null){
            throw new Exception("Invalid token");
        }

        User user = userRepository.findByEmail(email);
        if(user==null){
            throw new Exception("User not found");
        }

        return user;
    }
}
