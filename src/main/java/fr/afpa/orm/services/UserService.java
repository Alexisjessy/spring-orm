package fr.afpa.orm.services;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import java.util.List;

import fr.afpa.orm.entities.User;
import fr.afpa.orm.repositories.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> allUsers() {
        List<User> users = new ArrayList<>();

        userRepository.findAll().forEach(users::add);

        return users;
    }
}