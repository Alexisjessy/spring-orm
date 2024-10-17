package fr.afpa.orm.web.controllers;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import fr.afpa.orm.entities.Client;
import fr.afpa.orm.entities.Insurance;
import fr.afpa.orm.repositories.ClientRepository;
import fr.afpa.orm.repositories.InsuranceRepository;

@RestController
@RequestMapping("/api/clients")
public class ClientRestController {

    private final ClientRepository clientRepository;
    private final InsuranceRepository insuranceRepository;

    @Autowired
    public ClientRestController(ClientRepository clientRepository, InsuranceRepository insuranceRepository) {
        this.clientRepository = clientRepository;
        this.insuranceRepository = insuranceRepository;
    }

    // Endpoint GET pour récupérer la liste des clients
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Client> getAllClients() {
       
        return StreamSupport.stream(clientRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }
      // Endpoint GET pour récupérer les assurances d'un client spécifique
    @GetMapping("/{id}/insurances")
    public ResponseEntity<Set<Insurance>> getClientInsurances(@PathVariable UUID id) {
        return clientRepository.findById(id)
                .map(client -> ResponseEntity.ok(client.getInsurances()))
                .orElse(ResponseEntity.notFound().build());
    }
    @PostMapping("/{id}/insurances")
    public ResponseEntity<Client> addInsuranceToClient(@PathVariable UUID id, @RequestBody Insurance insurance) {
        return clientRepository.findById(id).map(client -> {
          
            Insurance savedInsurance = insuranceRepository.save(insurance);
            client.getInsurances().add(savedInsurance); 
            clientRepository.save(client); 
            return ResponseEntity.ok(client); 
        }).orElse(ResponseEntity.notFound().build());
    }
     // Endpoint POST pour ajouter un nouveau client
     @PostMapping
     public ResponseEntity<Client> createClient(@RequestBody Client client) {
       
         if (client.getFirstName() == null || client.getLastName() == null) {
             return ResponseEntity.badRequest().build(); 
         }
         Client savedClient = clientRepository.save(client);
         return ResponseEntity.status(HttpStatus.CREATED).body(savedClient);
     }
     
}

