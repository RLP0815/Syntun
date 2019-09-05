package com.syntun.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.syntun.entity.PromotionReplace;
import com.syntun.service.PromotionReplaceService;
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
@RequestMapping(value = "/promotionReplace")
public class PromotionReplaceController {
    @Resource(name = "promotionReplaceService")
    PromotionReplaceService promotionReplaceService;
    
    
    @RequestMapping(value = "/login")
    public ModelAndView login()
    {
        ModelAndView mv=new ModelAndView();
        List<PromotionReplace> promotionReplace = promotionReplaceService.login();
        mv.addObject("promotionReplace",promotionReplace);
        mv.setViewName("/login");
        return mv;
    }
    
    @RequestMapping(value = "/getAllList")
	public void getAllList(HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
    	List<PromotionReplace> resultMap = promotionReplaceService.getAllList();
    	
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
		String replaceStr = request.getParameter("replaceStr");
    	int page = Integer.parseInt(request.getParameter("page"));
    	int limit = Integer.parseInt(request.getParameter("limit"));
    	int start = (page-1)*limit;
    	HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("page",start);
		params.put("limit",limit);
		params.put("replaceStr",replaceStr);
		
		int count = promotionReplaceService.getCount(params);
		List<PromotionReplace> result = promotionReplaceService.getList(params);
		
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
		
		String priority = request.getParameter("priority");
		String replaceStr = request.getParameter("replaceStr");
		String endStr = request.getParameter("endStr");
		
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("priority",priority);
		params.put("replaceStr",replaceStr);
		params.put("endStr",endStr);
		
		promotionReplaceService.addRecord(params);
		
	}
	
	@RequestMapping(value = "/delRecord")
	public void delRecord(HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		
		String id = request.getParameter("id");
		
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("id",id);
		
		promotionReplaceService.delRecord(params);
		
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
		
        promotionReplaceService.delAllRecord(delList);
		
	}
	
	@RequestMapping(value = "/editRecord")
	public void editRecord(HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		
		String id = request.getParameter("id");
		String priority = request.getParameter("priority");
		String replaceStr = request.getParameter("replaceStr");
		String endStr = request.getParameter("endStr");
		
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("id",id);
		params.put("priority",priority);
		params.put("replaceStr",replaceStr);
		params.put("endStr",endStr);
		
		promotionReplaceService.editRecord(params);
		
	}
   
}
