package fr.afpa.orm.repositories;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import fr.afpa.orm.entities.Account;

/**
 * TODO implémenter un "repository" (similaire à un DAO) permettant d'interagir avec les données de la BDD
 * Tutoriel -> https://www.geeksforgeeks.org/spring-boot-crudrepository-with-example/
 */
@Repository
public interface AccountRepository extends CrudRepository<Account, Long> {

    // Optional<Account> findByUsername(String username);

    // Optional<Account> findByEmail(String email);

    // Optional<Account> findByUsernameAndEmail(String username, String email);
     
 
   }

