package com.galaxy.bank.customer;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customers")
public class CustomerController {

	@Autowired
	private CustomerService service;
	
	@GetMapping("/findAll")
	public List<Customer> findAll(){
		return service.findAll();
	}
	
	@GetMapping("/findById/{id}")
	public Optional<Customer> buscarporid(@PathVariable Long id) {
		return service.findById(id);
	}
	
	public Customer guardar(Customer customer) {
		return service.save(customer);
	}
}
