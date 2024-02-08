package fr.adsa.demo.service;

import fr.adsa.demo.model.User;

public interface UserService {
    public User findUserById(Long id) throws Exception;
}
