package com.syntun.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.syntun.entity.PromoCompare;
import com.syntun.service.PromoCompareService;
import com.syntun.util.GenericController;

import net.sf.json.JSONArray;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 
 */
@Controller
@RequestMapping(value = "/promoCompare")
public class PromoCompareController {
    @Resource(name = "promoCompareService")
    PromoCompareService promoCompareService;
    
    
    @RequestMapping(value = "/login")
    public ModelAndView login()
    {
        ModelAndView mv=new ModelAndView();
        List<PromoCompare> promoCompare = promoCompareService.login();
        mv.addObject("promoCompare",promoCompare);
        mv.setViewName("/login");
        return mv;
    }
    
    @RequestMapping(value = "/getAllList")
	public void getAllList(HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
    	String promotionTypeName = request.getParameter("promotionTypeName");
    	int page = Integer.parseInt(request.getParameter("page"));
    	int limit = Integer.parseInt(request.getParameter("limit"));
    	int start = (page-1)*limit;
    	HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("page",start);
		params.put("limit",limit);
		params.put("promotionTypeName",promotionTypeName);
		
		int count = promoCompareService.getCount(params);
		List<PromoCompare> resultMap = promoCompareService.getList(params);
    	
		/*
	    if(resultMap==null || resultMap.size()<1){
	    	resultMap = new HashMap<>();
	    	resultMap.put("state", 0);
	    	
	    }else{
	    	resultMap.put("state", 1);
	    }
	    */
		JSONArray json = JSONArray.fromObject(resultMap);
		String column = new String();
		column = "{" +
				"\"" + "code" + "\""  + ":" + 0 + "," +
				"\"" + "msg" + "\"" + ":" + "\"" + "" + "\"," +
				"\"" + "count" + "\""  + ":" + count + "," +
				"\"" + "data" + "\""  + ":" + json +
				"}";
		
    	response.getWriter().print(column);
		
	}
    
	@RequestMapping(value = "/getList")
	public void getList(HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		String promotionTypeName = request.getParameter("promotionTypeName");
    	int page = Integer.parseInt(request.getParameter("page"));
    	int limit = Integer.parseInt(request.getParameter("limit"));
    	int start = (page-1)*limit;
    	HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("page",start);
		params.put("limit",limit);
		params.put("promotionTypeName",promotionTypeName);
		
		int count = promoCompareService.getCount(params);
		List<PromoCompare> result = promoCompareService.getList(params);
		
		GenericController gen = new GenericController();
		List<Object> res = new ArrayList<>();
        for(Object e : result){  
            Object obj = (Object)e;  
            res.add(obj);  
        }  
        
		String jsons = gen.getResultJson(count, res);
		response.getWriter().print(jsons.toString());
		
	}
	
	@RequestMapping(value = "/addRecord")
	public void addRecord(HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		String promotionType = request.getParameter("promotionType");
		int platformId = Integer.parseInt(request.getParameter("platformId"));
		String platformName = request.getParameter("platformName");
		String promotionTypeName = request.getParameter("promotionTypeName");
		String promotionStandard = request.getParameter("promotionStandard");
		int promotionId = Integer.parseInt(request.getParameter("promotionId"));
		
		PromoCompare promoCompare = new PromoCompare();
		promoCompare.setPromotionType(promotionType);
		promoCompare.setPlatformId(platformId);
		promoCompare.setPlatformName(platformName);
		promoCompare.setPromotionTypeName(promotionTypeName);
		promoCompare.setPromotionStandard(promotionStandard);
		promoCompare.setPromotionId(promotionId);
		
		promoCompareService.insert(promoCompare);
		
	}
	
	@RequestMapping(value = "/delRecord")
	public void delRecord(HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		
		String id = request.getParameter("id");
		
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("id",id);
		
		promoCompareService.delRecord(params);
		
	}
	
	@RequestMapping(value = "/delAllRecord")
	public void delAllRecord(HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		
		String items = request.getParameter("ids");
		
		System.out.println(items);
		
        List<String> delList = new ArrayList<String>();
        String[] strs = items.split(",");
        for (String str : strs) {
            delList.add(str);
        }
		
        promoCompareService.delAllRecord(delList);
		
	}
	
	@RequestMapping(value = "/editRecord")
	public void editRecord(HttpServletRequest request, HttpServletResponse response) throws Exception 
	{

		String promotionType = request.getParameter("promotionType");
		String id = request.getParameter("id");
		String platformId = request.getParameter("platformId");
		String platformName = request.getParameter("platformName");
		String promotionTypeName = request.getParameter("promotionTypeName");
		String promotionStandard = request.getParameter("promotionStandard");
		String promotionId = request.getParameter("promotionId");
		
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("id",id);
		params.put("promotionType",promotionType);
		params.put("platformId",platformId);
		params.put("platformName",platformName);
		params.put("promotionTypeName",promotionTypeName);
		params.put("promotionStandard",promotionStandard);
		params.put("promotionId",promotionId);
		
		promoCompareService.editRecord(params);
		
	}
   
}
