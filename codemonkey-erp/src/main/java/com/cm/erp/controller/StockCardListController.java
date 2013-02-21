package com.cm.erp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cm.erp.domain.ItemStockCard;
import com.cm.erp.service.ItemStockCardService;
import com.codemonkey.web.controller.AbsListExtController;

@Controller
@RequestMapping("/ext/stockCardList/**")
public class StockCardListController extends AbsListExtController<ItemStockCard>{

	@Autowired private ItemStockCardService stockCardService;
	
	@Override
	protected ItemStockCardService service() {
		return stockCardService;
	}

}
