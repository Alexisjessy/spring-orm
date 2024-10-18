package fr.afpa.orm.repositories;
import java.util.List;
import java.util.UUID;


import org.springframework.data.repository.CrudRepository;
import fr.afpa.orm.entities.Insurance;

public interface InsuranceRepository extends CrudRepository<Insurance, Long> {
    List<Insurance> findByClientsId(UUID clientId);
}
