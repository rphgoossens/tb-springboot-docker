package nl.terrax.tbspringbootdocker.repository;

import nl.terrax.tbspringbootdocker.model.Brewery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BreweryRepository extends JpaRepository<Brewery, Long> {
}
