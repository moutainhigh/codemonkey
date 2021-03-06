package com.codemonkey.erp.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import com.codemonkey.utils.Calc;

@Entity
@DiscriminatorValue("PURCHASE_PAYMENT")
public class PurchasePaymentCurrencyTransaction extends CurrencyTransaction {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ManyToOne
	private Vendor vendor;
	
	@ManyToOne
	private PurchasePaymentLine ppLine;

	public PurchasePaymentCurrencyTransaction(PurchasePaymentLine ppLine) {
		super(ppLine);
		setDate(ppLine.getHeader().getPaymentDate());
		this.setPpLine(ppLine);
		this.vendor = ppLine.getHeader().getVendor();
		this.setAmountOnHand(Calc.neg(ppLine.getAmount()));
		this.setAmountOnPurchaseInvoice(ppLine.getAmount());
	}

	@Override
	public List<CurrencyPlanning> createPlanning() {
		List<CurrencyPlanning> plannings = new ArrayList<CurrencyPlanning>();
		plannings.add(createCurrencyInvoiceDemand());
		plannings.add(createCurrencyOnHandSupply());
		return plannings;
	}

	private CurrencyPlanning createCurrencyInvoiceDemand() {
		CurrencyPlanning plan = create(new CurrencyInvoiceDemand());
		plan.setAmount(Calc.neg(getAmount()));
		return plan;
	}

	private CurrencyPlanning createCurrencyOnHandSupply() {
		CurrencyPlanning plan = create(new CurrencyOnHandSupply());
		plan.setAmount(Calc.neg(getAmount()));
		return plan;
	}

	@Override
	public DocumentLine getDocLine() {
		return getPpLine();
	}

	public Vendor getVendor() {
		return vendor;
	}

	public void setVendor(Vendor vendor) {
		this.vendor = vendor;
	}

	public PurchasePaymentLine getPpLine() {
		return ppLine;
	}

	public void setPpLine(PurchasePaymentLine ppLine) {
		this.ppLine = ppLine;
	}

}
