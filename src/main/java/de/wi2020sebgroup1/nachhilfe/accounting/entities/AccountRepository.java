package de.wi2020sebgroup1.nachhilfe.accounting.entities;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends CrudRepository<Account, String> {
	
	Optional<Account> findByNumber(String number);
	
}
