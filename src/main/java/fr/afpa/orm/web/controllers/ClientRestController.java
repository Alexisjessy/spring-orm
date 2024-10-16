package fr.afpa.orm.web.controllers;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import fr.afpa.orm.entities.Client;
import fr.afpa.orm.repositories.ClientRepository;

@RestController
@RequestMapping("/api/clients")
public class ClientRestController {

    private final ClientRepository clientRepository;

    @Autowired
    public ClientRestController(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    // Endpoint GET pour récupérer la liste des clients
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Client> getAllClients() {
        // Convertir l'Iterable en une List de clients
        return StreamSupport.stream(clientRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }
}
