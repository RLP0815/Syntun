package com.syntun.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.syntun.entity.PatternList;
import com.syntun.service.PatternListService;
import com.syntun.util.GenericController;

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
@RequestMapping(value = "/patternList")
public class PatternListController {
    @Resource(name = "patternListService")
    PatternListService patternListService;
    
    @RequestMapping(value = "/login")
    public ModelAndView login()
    {
        ModelAndView mv=new ModelAndView();
        List<PatternList> patternList = patternListService.login();
        mv.addObject("patternList",patternList);
        mv.setViewName("/login");
        return mv;
    }
    
    @RequestMapping(value = "/getAllList")
	public void getAllList(HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
    	String patternStr = request.getParameter("patternStr");
    	int page = Integer.parseInt(request.getParameter("page"));
    	int limit = Integer.parseInt(request.getParameter("limit"));
    	int start = (page-1)*limit;
    	HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("page",start);
		params.put("limit",limit);
		params.put("patternStr",patternStr);
		
		int count = patternListService.getCount(params);
		List<PatternList> result = patternListService.getList(params);
		
		GenericController gen = new GenericController();
		List<Object> res = new ArrayList<>();
        for(Object e : result){  
            Object obj = (Object)e;  
            res.add(obj);  
        }  
        
		String jsons = gen.getResultJson(count, res);
		response.getWriter().print(jsons.toString());
	}
    
	@RequestMapping(value = "/getList")
	public void getList(HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		String patternStr = request.getParameter("patternStr");
    	int page = Integer.parseInt(request.getParameter("page"));
    	int limit = Integer.parseInt(request.getParameter("limit"));
    	int start = (page-1)*limit;
    	HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("page",start);
		params.put("limit",limit);
		params.put("patternStr",patternStr);
		
		int count = patternListService.getCount(params);
		List<PatternList> result = patternListService.getList(params);
		
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
		
		int priority = Integer.parseInt(request.getParameter("priority"));
		int patternType = Integer.parseInt(request.getParameter("patternType"));
		String patternStr = request.getParameter("patternStr");
		int manGroup = Integer.parseInt(request.getParameter("manGroup"));
		int jieGroup = Integer.parseInt(request.getParameter("jieGroup"));
		int isDazhe = Integer.parseInt(request.getParameter("isDazhe"));
		int isMeiman = Integer.parseInt(request.getParameter("isMeiman"));
		
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("priority",priority);
		params.put("patternType",patternType);
		params.put("patternStr",patternStr);
		params.put("manGroup",manGroup);
		params.put("jieGroup",jieGroup);
		params.put("isDazhe",isDazhe);
		params.put("isMeiman",isMeiman);
		
		patternListService.addRecord(params);
		
	}
	
	@RequestMapping(value = "/delRecord")
	public void delRecord(HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		
		String id = request.getParameter("id");
		
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("id",id);
		
		patternListService.delRecord(params);
		
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
		
        patternListService.delAllRecord(delList);
		
	}
	
	@RequestMapping(value = "/editRecord")
	public void editRecord(HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		
		int id = Integer.parseInt(request.getParameter("id"));
		int priority = Integer.parseInt(request.getParameter("priority"));
		int patternType = Integer.parseInt(request.getParameter("patternType"));
		String patternStr = request.getParameter("patternStr");
		int manGroup = Integer.parseInt(request.getParameter("manGroup"));
		int jieGroup = Integer.parseInt(request.getParameter("jieGroup"));
		int isDazhe = Integer.parseInt(request.getParameter("isDazhe"));
		int isMeiman = Integer.parseInt(request.getParameter("isMeiman"));
		
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("id",id);
		params.put("priority",priority);
		params.put("patternType",patternType);
		params.put("patternStr",patternStr);
		params.put("manGroup",manGroup);
		params.put("jieGroup",jieGroup);
		params.put("isDazhe",isDazhe);
		params.put("isMeiman",isMeiman);
		
		patternListService.editRecord(params);
		
	}
   
}
