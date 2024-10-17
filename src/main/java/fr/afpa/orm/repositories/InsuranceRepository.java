package fr.afpa.orm.repositories;

import org.springframework.data.repository.CrudRepository;
import fr.afpa.orm.entities.Insurance;

public interface InsuranceRepository extends CrudRepository<Insurance, Long> {
}
