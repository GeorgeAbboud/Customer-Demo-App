package com.demo.customerApp;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.demo.customerApp.account.Account;
import com.demo.customerApp.account.AccountService;
import com.demo.customerApp.customer.Customer;
import com.demo.customerApp.customer.CustomerRepository;
import com.demo.customerApp.customer.CustomerService;

@SpringBootApplication
public class CustomerAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerAppApplication.class, args);
	}

	@Bean
    public CommandLineRunner mappingDemo(CustomerService customerService
    		,
    		AccountService accountService
                                         ) {
        return args -> {

            // save new Customers
        	customerService.saveCustomer(new Customer("John", "Doe"));
        	customerService.saveCustomer(new Customer("Jane", "Doe"));
        	Customer cust = new Customer("Jack", "Nichol");
        	customerService.saveCustomer(cust);


        	accountService.saveAccount(new Account(0D, cust));
        	accountService.saveAccount(new Account(100D, cust));
        	accountService.saveAccount(new Account(0D, cust));
        };
	}
}
