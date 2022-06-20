package com.demo.customerApp.transaction;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.demo.customerApp.account.Account;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table
public class Transaction {

	@Id
	@SequenceGenerator(name = "transaction_sequence", allocationSize = 1)
	@GeneratedValue(generator = "transaction_sequence", strategy = GenerationType.SEQUENCE)
	private Long id;

	private Double amount = 0.0;

	@JsonBackReference
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "account_id", nullable = false)
	private Account account;

	public Transaction() {
	}

	public Transaction(Long id, Double amount, Account account) {
		this.id = id;
		this.amount = amount;
		this.account = account;
	}

	public Transaction(Double amount, Account account) {
		this.amount = amount;
		this.account = account;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}
}
