package com.demo.customerApp.customer;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.demo.customerApp.account.Account;
import com.demo.customerApp.account.AccountService;


@Service
public class CustomerService {

	private final CustomerRepository customerRepository;
	private final AccountService accountService;

	@Autowired
	public CustomerService(CustomerRepository customerRepository, AccountService accountService)
	{
		this.customerRepository = customerRepository;
		this.accountService = accountService;
	}

	public List<Customer> getAllCustomers()
	{
		return customerRepository.findAll();
	}
	
	public Optional<Customer> getCustomerById(Long id)
	{
		return customerRepository.findById(id);
	}


	public void saveCustomer(Customer customer)
	{
		customerRepository.save(customer);
	}

	public void deleteCustomer(Customer customer)
	{
		customerRepository.delete(customer);
	}

	public HashMap<String, Object> saveCustomerAccount(Long customerId, Double initialCredit)
	{
		HashMap<String, Object> returnMap = new HashMap<String, Object>();
		//
		Customer customer = this.getCustomerById(customerId).orElse(null);
		if(customer == null)
		{
			returnMap.put("success", false);
			returnMap.put("reason", "Customer does not exist");
			return returnMap;
		}

		Account account = new Account();
		account.setCustomer(customer);
		account.setInitialCredit(initialCredit);
		
		accountService.saveAccount(account);
		//
		returnMap.put("success", true);
		return returnMap;
	}
}
