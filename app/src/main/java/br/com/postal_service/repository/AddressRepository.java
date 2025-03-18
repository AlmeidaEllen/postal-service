package br.com.postal_service.repository;

import br.com.postal_service.model.Address;
import org.springframework.data.repository.CrudRepository;

public interface AddressRepository extends CrudRepository<Address, String> {
    
}
