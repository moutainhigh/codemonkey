package com.cm.erp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cm.erp.domain.SalesOrder;
import com.cm.erp.service.SalesOrderService;
import com.codemonkey.web.controller.AbsListExtController;

@Controller
@RequestMapping("/ext/salesOrderList/**")
public class SalesOrderListController extends AbsListExtController<SalesOrder>{

	@Autowired private SalesOrderService salesOrderService;
	
	@Override
	protected SalesOrderService service() {
		return salesOrderService;
	}

}
