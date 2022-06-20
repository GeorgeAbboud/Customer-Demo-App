package com.demo.customerApp.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.demo.customerApp.account.Account;

@Controller
public class CustomerViewController {

	@Autowired
	private final CustomerRestController customerRestController;


	public CustomerViewController(CustomerRestController customerRestController)
	{
		this.customerRestController = customerRestController;
	}

	@GetMapping(path = "/")
	public String viewHomePage(Model model)  {
		model.addAttribute("customersList", customerRestController.getCustomers());
        return "Customers";
    }

	@GetMapping(path = "/addAccount/{id}")
	public String loadAccountForm(@PathVariable(value = "id") Long id, Model model)  {

		Customer cust = customerRestController.getCustomerById(id).orElse(null);
		if(cust == null) return "redirect:/";
		
		Account account = new Account();
		account.setCustomer(cust);
		model.addAttribute("account", account);
		model.addAttribute("customerName", cust.getName() + " " + cust.getSurname());
        return "Account";
    }

	@PostMapping(path = "/saveCustomerAccount")
	public String saveCustomer(@ModelAttribute("account") Account account, Model model)  {
		Customer cust = customerRestController.getCustomerById(account.getCustomer().getId()).orElse(null);
		if(cust == null) return "redirect:/";
		
		customerRestController.saveAccount(account.getCustomer().getId(), account.getInitialCredit());
		//
		model.addAttribute("account", account);
		model.addAttribute("customerName", cust.getName() + " " + cust.getSurname());
		return "redirect:/";
	}
	
}
