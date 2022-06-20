package com.demo.customerApp.transaction;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class TransactionService {

	private final TransactionRepository transactionRepository;
	
	@Autowired
	public TransactionService(TransactionRepository transactionRepository)
	{
		this.transactionRepository = transactionRepository;
	}

	public List<Transaction> getAllTransactions()
	{
		return transactionRepository.findAll();
	}


	public void saveTransaction(Transaction transaction)
	{
		transactionRepository.save(transaction);
	}

	public void deleteTransaction(Transaction transaction)
	{
		transactionRepository.delete(transaction);
	}

}
