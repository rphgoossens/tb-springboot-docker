package nl.terrax.tbspringbootdocker.repository;


import nl.terrax.tbspringbootdocker.model.Beer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BeerRepository extends JpaRepository<Beer, Long> {

}

