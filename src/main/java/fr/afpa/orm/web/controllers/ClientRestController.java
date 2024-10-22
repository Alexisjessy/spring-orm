package fr.afpa.orm.web.controllers;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import fr.afpa.orm.dto.AccountDto;
import fr.afpa.orm.dto.ClientDetailsDto;
import fr.afpa.orm.dto.ClientDto;
import fr.afpa.orm.dto.InsuranceDto;
import fr.afpa.orm.entities.Client;
import fr.afpa.orm.entities.Insurance;
import fr.afpa.orm.repositories.AccountRepository;
import fr.afpa.orm.repositories.ClientRepository;
import fr.afpa.orm.repositories.InsuranceRepository;
import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/api/clients")
public class ClientRestController {

    private final ClientRepository clientRepository;
    private final InsuranceRepository insuranceRepository;
    private final AccountRepository accountRepository;

    @Autowired
    public ClientRestController(ClientRepository clientRepository, AccountRepository accountRepository,
            InsuranceRepository insuranceRepository) {
        this.clientRepository = clientRepository;
        this.accountRepository = accountRepository;
        this.insuranceRepository = insuranceRepository;
    }

    @GetMapping("/{clientId}/details")
    @Transactional
    public ResponseEntity<ClientDetailsDto> getClientDetails(@PathVariable UUID clientId) {

        Optional<Client> clientOptional = clientRepository.findById(clientId);
        if (!clientOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        Client client = clientOptional.get();

        // Récupérer les comptes bancaires associés au client
        List<AccountDto> accounts = accountRepository.findByClientId(clientId)
                .stream()
                .map(account -> new AccountDto(account.getId(), account.getBalance(), account.getCreationTime(),
                        account.getClient().getId()))
                .collect(Collectors.toList());

        // Récupérer les assurances associées au client
        List<InsuranceDto> insurances = insuranceRepository.findByClientsId(clientId)
                .stream()
                .map(insurance -> new InsuranceDto(insurance.getId(), insurance.getName()))
                .collect(Collectors.toList());

        // Créer le DTO combiné
        ClientDetailsDto clientDetailsDto = new ClientDetailsDto(
                client.getId(),
                client.getFirstName(),
                client.getLastName(),
                client.getEmail(),
                client.getBirthday(),
                accounts,
                insurances);

        return ResponseEntity.ok(clientDetailsDto);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ClientDto> getAllClients() {
        return StreamSupport.stream(clientRepository.findAll().spliterator(), false)
                .map(client -> new ClientDto(client.getId(), client.getFirstName(), client.getLastName(),
                        client.getEmail(), client.getBirthday()))
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

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remove(@PathVariable UUID id) {
        if (clientRepository.existsById(id)) {
            clientRepository.deleteById(id);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Account not found");
        }
    }

}
