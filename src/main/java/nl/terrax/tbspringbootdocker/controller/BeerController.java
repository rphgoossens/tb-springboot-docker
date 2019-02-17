package nl.terrax.tbspringbootdocker.controller;

import nl.terrax.tbspringbootdocker.controller.error.TbBadRequestException;
import nl.terrax.tbspringbootdocker.controller.utils.ResponseUtil;
import nl.terrax.tbspringbootdocker.model.Beer;
import nl.terrax.tbspringbootdocker.repository.BeerRepository;
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
public class BeerController {

    private final Logger log = LoggerFactory.getLogger(BeerController.class);

    private final BeerRepository beerRepository;

    public BeerController(BeerRepository beerRepository) {
        this.beerRepository = beerRepository;
    }

    @PostMapping("/beers")
    public ResponseEntity<Beer> createBeer(@Valid @RequestBody Beer beer) throws URISyntaxException {
        log.debug("REST request to save Beer : {}", beer);
        if (beer.getId() != null) {
            throw new TbBadRequestException("A new beer cannot already have an ID", beer.toString());
        }
        Beer result = beerRepository.save(beer);
        return ResponseEntity.created(new URI("/api/beers/" + result.getId()))
                .body(result);
    }

    @PutMapping("/beers")
    public ResponseEntity<Beer> updateBeer(@Valid @RequestBody Beer beer) throws URISyntaxException {
        log.debug("REST request to update Beer : {}", beer);
        if (beer.getId() == null) {
            return createBeer(beer);
        }
        Beer result = beerRepository.save(beer);
        return ResponseEntity.ok()
                .body(result);
    }

    @GetMapping("/beers")
    public ResponseEntity<List<Beer>> getAllBeers() {
        log.debug("REST request to get a page of Beers");
        List<Beer> beers = beerRepository.findAll();
        return new ResponseEntity<>(beers, HttpStatus.OK);
    }

    @GetMapping("/beers/{id}")
      public ResponseEntity<Beer> getBeer(@PathVariable Long id) {
        log.debug("REST request to get Beer : {}", id);
        Optional<Beer> beer = beerRepository.findById(id);
        return ResponseUtil.getResponseEntity(beer);
    }

    @DeleteMapping("/beers/{id}")
    public ResponseEntity<Void> deleteBeer(@PathVariable Long id) {
        log.debug("REST request to delete Beer : {}", id);
        beerRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }


}
