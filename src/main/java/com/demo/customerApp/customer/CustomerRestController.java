package com.demo.customerApp.customer;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "customer")
public class CustomerRestController {

	private final CustomerService customerService;

	@Autowired
	public CustomerRestController(CustomerService customerService)
	{
		this.customerService = customerService;
	}

	@RequestMapping(path = "getAll")
	public List<Customer> getCustomers()
	{
		return customerService.getAllCustomers();
	}

	public Optional<Customer> getCustomerById(Long id)
	{
		return customerService.getCustomerById(id);
	}

	@PostMapping(path = "saveAccount")
	public HashMap<String, Object> saveAccount(@RequestParam(name = "customerID") Long customerId, @RequestParam(name = "initialCredit") Double initialCredit)
	{
		return customerService.saveCustomerAccount(customerId, initialCredit);
	}

}
