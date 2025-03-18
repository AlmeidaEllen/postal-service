package br.com.postal_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import br.com.postal_service.exception.NotReadyException;
import br.com.postal_service.service.PostalService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import br.com.postal_service.model.Address;


@RestController
public class PostalController {
    @Autowired
    private PostalService service;

    @GetMapping("status")
    public String get() {
        return "Postal Service is " + service.getStatus();
    }

    @GetMapping("zip/{zipcode}")
	public Address getByZipcode(
			@PathVariable("zipcode") String zipcode) throws NotReadyException {
		return this.service.getByZipcode(zipcode);
	}

}
