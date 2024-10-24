package fr.afpa.orm.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import fr.afpa.orm.dto.LoginUserDto;
import fr.afpa.orm.dto.RegisterUserDto;
import fr.afpa.orm.entities.Role;
import fr.afpa.orm.entities.User;
import fr.afpa.orm.repositories.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

 

    public User signup(RegisterUserDto input) {
       
        var user = User.builder()
                .fullName(input.fullName())
                .email(input.email())
                .password(passwordEncoder.encode(input.password())) 
                .role(Role.USER)
                .build();
        return userRepository.save(user);
    }

    public User authenticate(LoginUserDto input) {
        
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getEmail(),
                        input.getPassword()
                )
        );

       
        return userRepository.findByEmail(input.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + input.getEmail()));
    }
}
