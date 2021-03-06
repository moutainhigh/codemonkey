package com.codemonkey.erp.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import com.codemonkey.utils.Calc;

@Entity
@DiscriminatorValue("PURCHASE_RECEIPT")
public class PurchaseReceiptItemTransaction extends ItemTransaction {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ManyToOne
	private Vendor vendor;
	
	@ManyToOne
	private PurchaseReceiptLine rpLine;
	
	PurchaseReceiptItemTransaction(){}
	
	public PurchaseReceiptItemTransaction(PurchaseReceiptLine rpLine) {
		super(rpLine);
		this.vendor = rpLine.getPurchaseOrderLine().getHeader().getVendor();
		this.setRpLine(rpLine);
		this.setQtyOnHand(rpLine.getQty());
		this.setQtyOnPurchaseOrder(Calc.neg(rpLine.getQty()));
	}

	@Override
	public List<ItemPlanning> createPlanning() {
		List<ItemPlanning> plannings = new ArrayList<ItemPlanning>();
		plannings.add(createItemOrderSupply());
		plannings.add(createItemOnhandSupply());
		return plannings;
	}
	
	private ItemPlanning createItemOnhandSupply() {
		ItemPlanning supply = create(new ItemOnhandSupply());
		supply.setQty(getQty());
		return supply;
	}

	private ItemPlanning createItemOrderSupply() {
		ItemPlanning demand = create(new ItemOrderSupply());
		demand.setQty(Calc.neg(getQty()));
		return demand;
	}


	@Override
	public DocumentLine getDocLine() {
		return getRpLine();
	}
	
	public Vendor getVendor() {
		return vendor;
	}

	public void setVendor(Vendor vendor) {
		this.vendor = vendor;
	}

	public PurchaseReceiptLine getRpLine() {
		return rpLine;
	}

	public void setRpLine(PurchaseReceiptLine rpLine) {
		this.rpLine = rpLine;
	}

}
