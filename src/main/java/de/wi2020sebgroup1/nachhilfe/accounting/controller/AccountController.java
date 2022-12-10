package de.wi2020sebgroup1.nachhilfe.accounting.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.wi2020sebgroup1.nachhilfe.accounting.entities.Account;
import de.wi2020sebgroup1.nachhilfe.accounting.entities.AccountRepository;

@Controller
@RestController
@RequestMapping(value = "/account")
public class AccountController {
	
	@Autowired
	AccountRepository repo;
	
	@GetMapping(value = "/")
	public ResponseEntity<Iterable<Account>> getAll() {
		return new ResponseEntity<>(repo.findAll(),HttpStatus.OK);
	}
	
	@GetMapping("/{number}")
	public ResponseEntity<Object> getSpecific(@PathVariable String number){
		try {
			return new ResponseEntity<Object>(repo.findByNumber(number).get(), HttpStatus.OK);
		}
		catch(NoSuchElementException e) {
			return new ResponseEntity<Object>("Account "+number+" nicht gefunden.", HttpStatus.NOT_FOUND);
		}
	}
	
    @PostMapping(value = "/")
    public ResponseEntity<Account> save(@RequestBody Account a) {
    	Account account = new Account(a.getNumber());
    	return new ResponseEntity<>(repo.save(account), HttpStatus.CREATED);
    }
	
    @PutMapping(value = "/{creditNumber}/{debitNumber}/{amount}")
    public ResponseEntity<Object> transfer(@PathVariable String creditNumber, @PathVariable String debitNumber, @PathVariable double amount) {
    	
    	Account credit, debit;
    	
    	try {
			credit = repo.findByNumber(creditNumber).get();
		}
    	catch(NoSuchElementException e) {
			return new ResponseEntity<Object>("Account "+creditNumber+" nicht gefunden.", HttpStatus.NOT_FOUND);
		}
    	try {
    		debit = repo.findByNumber(debitNumber).get();
		}
    	catch(NoSuchElementException e) {
			return new ResponseEntity<Object>("Account "+debitNumber+" nicht gefunden.", HttpStatus.NOT_FOUND);
		}
    	
    	credit.credit(amount);
    	debit.debit(amount);
    	List<Account> accounts = new ArrayList<>();
    	accounts.add(credit);
    	accounts.add(debit);
    	
    	return new ResponseEntity<Object>(repo.saveAll(accounts), HttpStatus.OK);
    }
	
    @DeleteMapping(value = "/reset")
    public ResponseEntity<Object> delete() {
    	repo.deleteAll();
		return new ResponseEntity<Object>("Alle Einträge gelöscht", HttpStatus.OK);
    }

}
