package com.codemonkey.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.codemonkey.domain.IEntity;
import com.codemonkey.error.AuthError;
import com.codemonkey.error.BadObjVersionError;
import com.codemonkey.error.SessionTimeoutError;
import com.codemonkey.error.SysError;
import com.codemonkey.error.ValidationError;
import com.codemonkey.service.GenericService;
import com.codemonkey.utils.ClassHelper;
import com.codemonkey.utils.ExtConstant;
import com.codemonkey.utils.MMHelper;
import com.codemonkey.utils.SysUtils;
import com.codemonkey.web.converter.CustomConversionService;


public abstract class AbsExtController<T extends IEntity> implements SecurityController{
	
	private Class<?> type;
	
	private Logger log;
	
	@Autowired private CustomConversionService ccService;
	
	@Autowired private SysUtils sysUtils;
	
	protected abstract GenericService<T> service();
	
	AbsExtController(){
		this.type = ClassHelper.getSuperClassGenricType(getClass());
		log = SysUtils.getLog(getClass());
	}
	
	public void regResources() {
		
	}
	
	//----------------------
	// index
	//----------------------
	@RequestMapping("index")
    public String index(ModelMap modelMap , HttpSession session) {
		modelMap.addAttribute("modelFields", MMHelper.getModelFields(type));
    	modelMap.addAttribute("modelName", MMHelper.getModelName(type));
    	modelMap.addAttribute(ExtConstant.THEME, SysUtils.getCurrentTheme(session));
		modelMap.addAttribute(ExtConstant.PAGE_DATA, getPageData(session));
    	return ExtConstant.INDEX;
    }

	protected JSONObject getPageData(HttpSession session) {
		JSONObject pageData = new JSONObject();
		List<String> fieldNames = ClassHelper.getAllFieldNames(type);
		pageData.put(ExtConstant.THEME, SysUtils.getCurrentTheme(session));
		pageData.put("labels", labels(fieldNames));
		return pageData;
	}

	private JSONObject labels(List<String> fieldNames) {
		JSONObject labels = new JSONObject();
		if(CollectionUtils.isNotEmpty(fieldNames)){
			for(String fn : fieldNames){
				String key = StringUtils.uncapitalize(type.getSimpleName()) + "." + fn;
//				sysUtils.getAppSetting("setting.test");
				labels.put(key , sysUtils.msg(key));
			}
		}
		return labels;
	}
	
    protected String buildJson(List<T> list) {
    	JSONObject jo = buildResult(list);
		return jo.toString();
	}
    
    protected String buildJson(List<T> list , long total) {
    	JSONObject jo = buildResult(list);
    	jo.put(ExtConstant.TOTAL_COUNT, total);
		return jo.toString();
	}
    
	private JSONObject buildResult(List<T> list) {
		JSONObject jo = new JSONObject();
    	if(list != null){
    		JSONArray data = new JSONArray();
    		for(T t : list){
    			data.put(t.listJson());
    		}
    		jo.put(ExtConstant.SUCCESS, true);
			jo.put(ExtConstant.DATA, data);
    	}
		return jo;
	}
	
	@ExceptionHandler(SysError.class)
	@ResponseBody
	public String handleSysException(SysError ex, HttpServletRequest request) {
		return ex.json().toString();
	}
	
	@ExceptionHandler(AuthError.class)
	@ResponseBody
	public String handleAuthException(AuthError ex, HttpServletRequest request) {
		return ex.json().toString();
	}
	
	@ExceptionHandler(ValidationError.class)
	@ResponseBody
	public String handleValidationException(ValidationError ex, HttpServletRequest request) {
		return ex.json().toString();
	}
	
	@ExceptionHandler(BadObjVersionError.class)
	@ResponseBody
	public String handleBadVersionException(BadObjVersionError ex, HttpServletRequest request) {
		return ex.json().toString();
	}
	
	@ExceptionHandler(SessionTimeoutError.class)
	@ResponseBody
	public String handleSessionTimeout(SessionTimeoutError ex, HttpServletRequest request) {
		return ex.json().toString();
	}
    
	public Class<?> getType() {
		return type;
	}

	public void setType(Class<?> type) {
		this.type = type;
	}

	public Logger getLog() {
		return log;
	}

	public void setLog(Logger log) {
		this.log = log;
	}

	public SysUtils getSysUtils() {
		return sysUtils;
	}

	public void setSysUtils(SysUtils sysUtils) {
		this.sysUtils = sysUtils;
	}

	public CustomConversionService getCcService() {
		return ccService;
	}

	public void setCcService(CustomConversionService ccService) {
		this.ccService = ccService;
	}

}
