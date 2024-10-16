package fr.afpa.orm.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import fr.afpa.orm.entities.Client;
 import java.util.UUID;
@Repository
public interface ClientRepository extends CrudRepository<Client, UUID> {
}

