package com.syntun.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.syntun.entity.PriceCompute;
import com.syntun.service.PriceComputeService;
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
@RequestMapping(value = "/priceCompute")
public class PriceComputeController {
    @Resource(name = "priceComputeService")
    PriceComputeService priceComputeService;
    
    
    @RequestMapping(value = "/login")
    public ModelAndView login()
    {
        ModelAndView mv=new ModelAndView();
        List<PriceCompute> priceCompute = priceComputeService.login();
        mv.addObject("priceCompute",priceCompute);
        mv.setViewName("/login");
        return mv;
    }
    
    @RequestMapping(value = "/getAllList")
	public void getAllList(HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
    	List<PriceCompute> resultMap = priceComputeService.getAllList();
    	
		JSONArray json = JSONArray.fromObject(resultMap);
		String column = new String();
		column = "{" +
				"\"" + "code" + "\""  + ":" + 0 + "," +
				"\"" + "msg" + "\"" + ":" + "\"" + "" + "\"," +
				"\"" + "count" + "\""  + ": 12" + "," +
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
		
		int count = priceComputeService.getCount(params);
		List<PriceCompute> result = priceComputeService.getList(params);
		
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
		
		String platformId = request.getParameter("platformId");
		String promotionTypeName = request.getParameter("promotionTypeName");
		String promotionPrice = request.getParameter("promotionPrice");
		String price = request.getParameter("price");
		
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("platformId",platformId);
		params.put("promotionTypeName",promotionTypeName);
		params.put("promotionPrice",promotionPrice);
		params.put("price",price);
		
		priceComputeService.addRecord(params);
		
	}
	
	@RequestMapping(value = "/delRecord")
	public void delRecord(HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		
		String id = request.getParameter("id");
		
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("id",id);
		
		priceComputeService.delRecord(params);
		
	}
	
	@RequestMapping(value = "/delAllRecord")
	public void delAllRecord(HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		
		String items = request.getParameter("ids");
		
        List<String> delList = new ArrayList<String>();
        String[] strs = items.split(",");
        for (String str : strs) {
            delList.add(str);
        }
		
        priceComputeService.delAllRecord(delList);
		
	}
	
	@RequestMapping(value = "/editRecord")
	public void editRecord(HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		
		String id = request.getParameter("id");
		String platformId = request.getParameter("platformId");
		String promotionTypeName = request.getParameter("promotionTypeName");
		String promotionPrice = request.getParameter("promotionPrice");
		String price = request.getParameter("price");
		
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("id",id);
		params.put("platformId",platformId);
		params.put("promotionTypeName",promotionTypeName);
		params.put("promotionPrice",promotionPrice);
		params.put("price",price);
		
		priceComputeService.editRecord(params);
		
	}
   
}
