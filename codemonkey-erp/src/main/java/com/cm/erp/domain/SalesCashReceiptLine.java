package com.cm.erp.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import com.codemonkey.utils.Calc;

@Entity
public class SalesCashReceiptLine extends DocumentLineAdapter {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ManyToOne 
	private SalesCashReceipt salesCashReceipt;
	
	private Double price;

	private Double taxRate;
	
	@Override
	public SalesCashReceipt getHeader() {
		return getSalesCashReceipt();
	}
	
	public List<Transaction> createCurrencyTransactions() {
		List<Transaction> trans = new ArrayList<Transaction>();
		trans.add(new SalesCashReceiptCurrencyTransaction(this));
		return trans;
	}
	
	public Double getAmount() {
		return Calc.mul(getPrice() , getQty());
	}
	
	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getTaxRate() {
		return taxRate;
	}

	public void setTaxRate(Double taxRate) {
		this.taxRate = taxRate;
	}

	public SalesCashReceipt getSalesCashReceipt() {
		return salesCashReceipt;
	}

	public void setSalesCashReceipt(SalesCashReceipt salesCashReceipt) {
		this.salesCashReceipt = salesCashReceipt;
	}

}
