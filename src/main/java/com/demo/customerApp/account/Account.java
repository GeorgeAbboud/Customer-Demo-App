package com.demo.customerApp.account;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.aspectj.apache.bcel.generic.IINC;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.data.jpa.repository.Query;

import com.demo.customerApp.customer.Customer;
import com.demo.customerApp.transaction.Transaction;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table
public class Account {

	@Id
	@SequenceGenerator(name = "account_sequence", allocationSize = 1)
	@GeneratedValue(generator = "account_sequence", strategy = GenerationType.SEQUENCE)
	private Long id;

//	private Integer acccountNumber;

	@Transient
	private Double initialCredit;
	@Transient
	private Double balance;

	@JsonBackReference
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "customer_id", nullable = false)
	private Customer customer = new Customer();

	@JsonManagedReference
	@OneToMany(mappedBy = "account", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Transaction> transactionList;

	public Account()
	{
	}

	public Account(Long id, Double initialCredit, Customer customer)
	{
		this.id = id;
		this.initialCredit = initialCredit;
		this.customer = customer;
	}
	public Account(Double initialCredit, Customer customer)
	{
		this.initialCredit = initialCredit;
		this.customer = customer;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getInitialCredit() {
		return initialCredit;
	}

	public void setInitialCredit(Double initialCredit) {
		this.initialCredit = initialCredit;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Set<Transaction> getTransactionList() {
		return transactionList;
	}

	public void setTransactionList(Set<Transaction> transactionList) {
		this.transactionList = transactionList;
	}

	public Double getBalance()
	{
		return transactionList != null && transactionList.size() > 0 ? transactionList.stream().mapToDouble(Transaction::getAmount).sum() : 0D;
	}


}
