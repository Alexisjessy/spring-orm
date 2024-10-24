package fr.afpa.orm.repositories;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import fr.afpa.orm.entities.Insurance;

public interface InsuranceRepository extends CrudRepository<Insurance, Long> {
    Optional<Insurance> findByName(String name);
<<<<<<< HEAD
}
=======
}
>>>>>>> 0f9a292d2045b83a746bf1b2bda385d1385bf3c8
