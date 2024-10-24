package fr.afpa.orm.web.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.afpa.orm.entities.User;
import fr.afpa.orm.services.UserService;
import java.util.Map.Entry;
import java.util.List;

@CrossOrigin
@RequestMapping("/users")
@RestController

public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    // @CrossOrigin(origins = "http://localhost:5173")
    @GetMapping("/me")
    public ResponseEntity<User> authenticatedUser() {
    
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = (User) authentication.getPrincipal();

        return ResponseEntity.ok(currentUser);
    }

    // @GetMapping
    // public ResponseEntity<List<User>> allUsers() {
    //     List <User> users = userService.allUsers();

    //     return ResponseEntity.ok(users);
    // }
}