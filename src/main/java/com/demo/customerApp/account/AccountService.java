package com.demo.customerApp.account;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.customerApp.transaction.Transaction;
import com.demo.customerApp.transaction.TransactionService;

@Service
public class AccountService {

	private final AccountRepository accountRepository;
	private final TransactionService transactionService;

	@Autowired
	public AccountService(AccountRepository accountRepository, TransactionService transactionService)
	{
		this.accountRepository = accountRepository;
		this.transactionService = transactionService;
	}

	public List<Account> getAllAccounts()
	{
		return accountRepository.findAll();
	}


	public void saveAccount(Account account)
	{
		accountRepository.save(account);

		if(account.getInitialCredit() != null && account.getInitialCredit() > 0)
		{
			Transaction transaction = new Transaction();
			transaction.setAccount(account);
			transaction.setAmount(account.getInitialCredit());
			transactionService.saveTransaction(transaction);
		}
	}

	public void deleteAccount(Account account)
	{
		accountRepository.delete(account);
	}
}
