package fr.afpa.orm.repositories;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import fr.afpa.orm.entities.Insurance;

public interface InsuranceRepository extends CrudRepository<Insurance, Long> {
    Optional<Insurance> findByName(String name);
}