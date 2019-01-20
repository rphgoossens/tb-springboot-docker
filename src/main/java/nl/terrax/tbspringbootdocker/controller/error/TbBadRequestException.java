package nl.terrax.tbspringbootdocker.controller.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.net.URISyntaxException;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class TbBadRequestException extends URISyntaxException {


    public TbBadRequestException(String input, String reason) {
        super(input, reason);
    }
}
