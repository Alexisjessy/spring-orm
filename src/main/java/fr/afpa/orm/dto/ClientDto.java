package fr.afpa.orm.dto;

import java.time.LocalDate;
import java.util.UUID;


public record ClientDto (UUID id, String firstName,  String lastName,  String email, LocalDate birthday){
    
}
    
    