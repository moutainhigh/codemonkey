package com.codemonkey.erp.controller;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.codemonkey.erp.domain.Document;
import com.codemonkey.web.controller.AbsFormExtController;

@Controller
public abstract class DocumentFormController<T extends Document> extends AbsFormExtController<T>{


	abstract void processPost(T t);
	
	//----------------------
    // post
    //----------------------
    @RequestMapping("post")
    @ResponseBody
    public String post(@RequestBody String body) {
    	
    	JSONObject params = parseJson(body);
		
		T t = service().doSave(params , getCcService());
		
		processPost(t);
    	
    	return result(t);
    }

}