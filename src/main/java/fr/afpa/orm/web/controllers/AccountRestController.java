package fr.afpa.orm.web.controllers;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import fr.afpa.orm.dto.AccountDto;
import fr.afpa.orm.entities.Account;
import fr.afpa.orm.entities.Client;
import fr.afpa.orm.entities.User;
import fr.afpa.orm.repositories.AccountRepository;
import fr.afpa.orm.repositories.ClientRepository;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import jakarta.servlet.http.HttpServletResponse;

/**
 * AccountRestController gère les requêtes REST pour la gestion des comptes.
 */
@RestController
@RequestMapping("/api/accounts")
public class AccountRestController {

    private final AccountRepository accountRepository;
    private final ClientRepository clientRepository;

    @Autowired
    public AccountRestController(AccountRepository accountRepository, ClientRepository clientRepository) {
        this.accountRepository = accountRepository;
        this.clientRepository = clientRepository;
    }

   /**
     * Récupère tous les comptes et renvoie des DTOs.
     *
     * @return Liste de tous les comptes au format DTO.
     */
    @GetMapping
    public List<AccountDto> getAll() {
        return StreamSupport.stream(accountRepository.findAll().spliterator(), false)
                .map(account -> new AccountDto(
                    account.getId(), 
                    account.getBalance(), 
                    account.getCreationTime(), 
                    account.getClient() != null ? account.getClient().getId() : null 
                ))
                .collect(Collectors.toList());
    }

    /**
     * Récupère un compte spécifique par son identifiant.
     *
     * @param id Identifiant du compte.
     * @return Détails du compte si trouvé, ou un code 404 si non trouvé.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Account> getOne(@PathVariable long id) {
         

        Optional<Account> account = accountRepository.findById(id);
        return account.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Crée un nouveau compte et l'associe à un client si celui-ci est fourni.
     *
     * @param account Les informations du compte à créer.
     * @return Le compte créé avec un code HTTP 201.
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Account create(@RequestBody Account account) {
    
        if (account.getClient() != null && account.getClient().getId() != null) {
            Optional<Client> existingClient = clientRepository.findById(account.getClient().getId());
            if (existingClient.isPresent()) {
                account.setClient(existingClient.get()); 
            } else {
                throw new RuntimeException("Client not found");  
            }
        } else {
            throw new RuntimeException("Client ID must be provided");  
        }
      
        return accountRepository.save(account);
    }
    
    

    /**
     * Met à jour un compte existant avec de nouvelles informations.
     *
     * @param id      Identifiant du compte à mettre à jour.
     * @param account Informations à mettre à jour.
     * @return Le compte mis à jour, ou un code 404 si le compte n'existe pas.
     */
    @PutMapping("/{id}")
public ResponseEntity<Account> update(@PathVariable long id, @RequestBody Account account) {
    Optional<Account> existingAccount = accountRepository.findById(id);
    if (existingAccount.isPresent()) {
        Account currentAccount = existingAccount.get();
        
      
        account.setCreationTime(currentAccount.getCreationTime());
        
       
        if (account.getClient() != null && account.getClient().getId() != null) {
            Optional<Client> client = clientRepository.findById(account.getClient().getId());
            if (!client.isPresent()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Client not found");
            }
            account.setClient(client.get());
        }

        account.setId(id);

      
        Account updatedAccount = accountRepository.save(account);
        return ResponseEntity.ok(updatedAccount);
    } else {
        return ResponseEntity.notFound().build();
    }
}


   // Find account by email
//    @GetMapping("/email/{email}")
//    public ResponseEntity<Account> getAccountByEmail(@PathVariable String email) {
//        Optional<Account> account = accountRepository.findByEmail(email);
//        return account.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
//    }
    /**
     * Supprime un compte par son identifiant.
     *
     * @param id Identifiant du compte à supprimer.
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remove(@PathVariable long id) {
        if (accountRepository.existsById(id)) {
            accountRepository.deleteById(id);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Account not found");
        }
    }
}
