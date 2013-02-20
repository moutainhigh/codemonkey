package com.cm.erp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.codemonkey.web.controller.AbsFormExtController;

import com.cm.erp.domain.SalesOrderLine;
import com.cm.erp.service.SalesOrderLineService;

@Controller
@RequestMapping("/ext/salesOrderLine/**")
public class SalesOrderLineFormController extends AbsFormExtController<SalesOrderLine>{

	@Autowired private SalesOrderLineService salesOrderLineService;
	
	@Override
	protected SalesOrderLineService service() {
		return salesOrderLineService;
	}

	@Override
	protected SalesOrderLine createEntity() {
		return new SalesOrderLine();
	}
}
