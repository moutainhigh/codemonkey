package com.cm.erp.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("ItemSupply")
public class ItemSupply extends ItemPlanning {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
}
