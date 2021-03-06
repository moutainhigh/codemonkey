package com.codemonkey.erp.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;

import com.codemonkey.domain.AbsEE;

@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
public class CurrencyPlanning extends AbsEE {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Enumerated(EnumType.STRING)
	private Currency currency;
	
	private Double amount;
	
	private Date date;
	
	@ManyToOne
	private CurrencyTransaction currencyTransaction;
	
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Currency getCurrency() {
		return currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public CurrencyTransaction getCurrencyTransaction() {
		return currencyTransaction;
	}

	public void setCurrencyTransaction(CurrencyTransaction currencyTransaction) {
		this.currencyTransaction = currencyTransaction;
	}

}
