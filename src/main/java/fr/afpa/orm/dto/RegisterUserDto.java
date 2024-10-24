package fr.afpa.orm.dto;

import fr.afpa.orm.entities.Role;


public record RegisterUserDto ( String email, String password, String fullName, Role role){
    
}
   
  