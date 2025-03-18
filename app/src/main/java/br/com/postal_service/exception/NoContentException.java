package br.com.postal_service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(value=HttpStatus.NO_CONTENT)
public class NoContentException extends RuntimeException{
    
}
