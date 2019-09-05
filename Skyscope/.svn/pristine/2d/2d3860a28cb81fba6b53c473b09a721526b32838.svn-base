package com.syntun.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.syntun.entity.PromotionSpecial;
import com.syntun.service.PromotionSpecialService;
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
@RequestMapping(value = "/promotionSpecial")
public class PromotionSpecialController {
    @Resource(name = "promotionSpecialService")
    PromotionSpecialService promotionSpecialService;
    
    
    @RequestMapping(value = "/login")
    public ModelAndView login()
    {
        ModelAndView mv=new ModelAndView();
        List<PromotionSpecial> promotionSpecial = promotionSpecialService.login();
        mv.addObject("promotionSpecial",promotionSpecial);
        mv.setViewName("/login");
        return mv;
    }
    
    @RequestMapping(value = "/getAllList")
	public void getAllList(HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
    	String promotionInfo = request.getParameter("promotionInfo");
    	int page = Integer.parseInt(request.getParameter("page"));
    	int limit = Integer.parseInt(request.getParameter("limit"));
    	int start = (page-1)*limit;
    	HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("page",start);
		params.put("limit",limit);
		params.put("promotionInfo",promotionInfo);

		int count = promotionSpecialService.getCount(params);
		List<PromotionSpecial> resultMap = promotionSpecialService.getList(params);
		
		JSONArray json = JSONArray.fromObject(resultMap);
		String column = new String();
		column = "{" +
				"\"" + "code" + "\""  + ":" + 0 + "," +
				"\"" + "msg" + "\"" + ":" + "\"" + "" + "\"," +
				"\"" + "count" + "\""  + ": " + count + "," +
				"\"" + "data" + "\""  + ":" + json +
				"}";
		
    	response.getWriter().print(column);
		
	}
    
	@RequestMapping(value = "/getList")
	public void getList(HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		String promotionInfo = request.getParameter("promotionInfo");
    	int page = Integer.parseInt(request.getParameter("page"));
    	int limit = Integer.parseInt(request.getParameter("limit"));
    	int start = (page-1)*limit;
    	HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("page",start);
		params.put("limit",limit);
		params.put("promotionInfo",promotionInfo);
		
		int count = promotionSpecialService.getCount(params);
		List<PromotionSpecial> result = promotionSpecialService.getList(params);
		
		GenericController gen = new GenericController();
		List<Object> res = new ArrayList<>();
        for(Object e : result){  
            Object obj = (Object)e;  
            res.add(obj);  
        }  
        
		String jsons = gen.getResultJson(count, res);
		response.getWriter().print(jsons.toString());
		
	}
	
	@RequestMapping(value = "/addFiled")
	public void addFiled(HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		String platformId = request.getParameter("platformId");
		String platformName = request.getParameter("platformName");
		String promotionInfo = request.getParameter("promotionInfo");
		String promotionFirst = request.getParameter("promotionFirst");
		String promotionSecond = request.getParameter("promotionSecond");
		
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("platformId",platformId);
		params.put("platformName",platformName);
		params.put("promotionInfo",promotionInfo);
		params.put("promotionFirst",promotionFirst);
		params.put("promotionSecond",promotionSecond);
		
		promotionSpecialService.addFiled(params);
		
	}
	
	@RequestMapping(value = "/delFiled")
	public void delFiled(HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		
		String id = request.getParameter("id");
		
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("id",id);
		
		promotionSpecialService.delFiled(params);
		
	}
	
	@RequestMapping(value = "/delAllFiled")
	public void delAllFiled(HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		
		String items = request.getParameter("ids");
		
        List<String> delList = new ArrayList<String>();
        String[] strs = items.split(",");
        for (String str : strs) {
            delList.add(str);
        }
		
		promotionSpecialService.delAllFiled(delList);
		
	}
	
	@RequestMapping(value = "/editFiled")
	public void editFiled(HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		
		String id = request.getParameter("id");
		String platformId = request.getParameter("platformId");
		String platformName = request.getParameter("platformName");
		String promotionInfo = request.getParameter("promotionInfo");
		String promotionFirst = request.getParameter("promotionFirst");
		String promotionSecond = request.getParameter("promotionSecond");
		
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("id",id);
		params.put("platformId",platformId);
		params.put("platformName",platformName);
		params.put("promotionInfo",promotionInfo);
		params.put("promotionFirst",promotionFirst);
		params.put("promotionSecond",promotionSecond);
		
		//System.out.println(params);
		promotionSpecialService.editFiled(params);
		
    	//response.getWriter().print();
		
	}
   
}
