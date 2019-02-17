package nl.terrax.tbspringbootdocker.controller;

import nl.terrax.tbspringbootdocker.controller.error.TbBadRequestException;
import nl.terrax.tbspringbootdocker.controller.utils.ResponseUtil;
import nl.terrax.tbspringbootdocker.model.Brewery;
import nl.terrax.tbspringbootdocker.repository.BreweryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class BreweryController {

    private final Logger log = LoggerFactory.getLogger(BreweryController.class);

    private final BreweryRepository breweryRepository;

    public BreweryController(BreweryRepository breweryRepository) {
        this.breweryRepository = breweryRepository;
    }

    @PostMapping("/breweries")
    public ResponseEntity<Brewery> createBrewery(@Valid @RequestBody Brewery brewery) throws URISyntaxException {
        log.debug("REST request to save Brewery : {}", brewery);

        if (brewery.getId() != null) {
            throw new TbBadRequestException("A new brewery cannot already have an ID", brewery.toString());
        }

        Brewery result = breweryRepository.save(brewery);
        return ResponseEntity.created(new URI("/api/breweries/" + result.getId()))
                .body(result);
    }

    @PutMapping("/breweries")
    public ResponseEntity<Brewery> updateBrewery(@Valid @RequestBody Brewery brewery) throws URISyntaxException {
        log.debug("REST request to update Brewery : {}", brewery);

        if (brewery.getId() == null) {
            return createBrewery(brewery);
        }

        Brewery result = breweryRepository.save(brewery);
        return ResponseEntity.ok()
                .body(result);
    }

    @GetMapping("/breweries")
    public ResponseEntity<List<Brewery>> getAllBreweries() {
        log.debug("REST request to get a page of Breweries");
        List<Brewery> breweries = breweryRepository.findAll();
        return new ResponseEntity<>(breweries, HttpStatus.OK);
    }

    @GetMapping("/breweries/{id}")
    public ResponseEntity<Brewery> getBrewery(@PathVariable Long id) {
        log.debug("REST request to get Brewery : {}", id);
        Optional<Brewery> brewery = breweryRepository.findById(id);
        return ResponseUtil.getResponseEntity(brewery);
    }

    @DeleteMapping("/breweries/{id}")
    public ResponseEntity<Void> deleteBrewery(@PathVariable Long id) {
        log.debug("REST request to delete Brewery : {}", id);
        breweryRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
