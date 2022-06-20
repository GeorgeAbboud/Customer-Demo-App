package com.demo.customerApp.customer;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.demo.customerApp.account.Account;
import com.fasterxml.jackson.annotation.JsonManagedReference;


@Entity
@Table
public class Customer {

	@Id
	@SequenceGenerator(name = "customer_sequence", allocationSize = 1)
	@GeneratedValue(generator = "customer_sequence", strategy = GenerationType.SEQUENCE)
	private Long id;

	private String name;
	private String surname;

	@Transient
	private Double balance;

	@JsonManagedReference
	@OneToMany(mappedBy = "customer", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private Set<Account> accountList;

	public Customer()
	{
	}
	public Customer(Long id, String name, String surname)
	{
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.accountList = new HashSet<Account>();
	}
	public Customer(String name, String surname)
	{
		this.name = name;
		this.surname = surname;
		this.accountList = new HashSet<Account>();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}
	public Set<Account> getAccountList() {
		return accountList;
	}
	public void setAccountList(Set<Account> accountList) {
		this.accountList = accountList;
	}

	public Double getBalance()
	{
		return accountList != null && accountList.size() > 0 ? accountList.stream().mapToDouble(Account::getBalance).sum() : 0D;
	}
}
