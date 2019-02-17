package nl.terrax.tbspringbootdocker.controller.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public class ResponseUtil {
    public static <T> ResponseEntity<T> getResponseEntity(Optional<T> entity) {
        if (entity.isPresent()) {
            return new ResponseEntity<>(entity.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }
    }
}
