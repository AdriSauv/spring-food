package fr.adsa.demo.service;

import fr.adsa.demo.model.User;
import fr.adsa.demo.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImplementation implements UserService{
    private UserRepository userRepository;

    public UserServiceImplementation(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public User findUserById(Long id) throws Exception {
        return userRepository.findById(id).orElseThrow(()->new Exception("User not found"));
    }
}
