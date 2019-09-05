package com.syntun.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.syntun.entity.PlatformList;
import com.syntun.service.PlatformListService;
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
@RequestMapping(value = "/platformList")
public class PlatformListController {
    @Resource(name = "platformListService")
    PlatformListService platformListService;
    
    
    @RequestMapping(value = "/login")
    public ModelAndView login()
    {
        ModelAndView mv=new ModelAndView();
        List<PlatformList> platformList = platformListService.login();
        mv.addObject("platformList",platformList);
        mv.setViewName("/login");
        return mv;
    }
    
    @RequestMapping(value = "/getAllList")
	public void getAllList(HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
    	List<PlatformList> resultMap = platformListService.getAllList();
    	
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
    /*
     * 按条件查询记录
     */
	@RequestMapping(value = "/getList")
	public void getList(HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		String platformName = request.getParameter("platformName");
    	int page = Integer.parseInt(request.getParameter("page"));
    	int limit = Integer.parseInt(request.getParameter("limit"));
    	int start = (page-1)*limit;
    	HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("page",start);
		params.put("limit",limit);
		params.put("platformName",platformName);
		
		int count = platformListService.getCount(params);
		List<PlatformList> result = platformListService.getList(params);
		
		GenericController gen = new GenericController();
		List<Object> res = new ArrayList<>();
        for(Object e : result){  
            Object obj = (Object)e;  
            res.add(obj);  
        }  
        
		String jsons = gen.getResultJson(count, res);
		response.getWriter().print(jsons.toString());
		
	}
	/*
	 * 添加记录
	 */
	@RequestMapping(value = "/addRecord")
	public void addRecord(HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		String platformId = request.getParameter("platformId");
		String platformName = request.getParameter("platformName");
		//String platformTableName = request.getParameter("platformTableName");
		String platformTableName = "";
		
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("platformId",platformId);
		params.put("platformName",platformName);
		params.put("platformTableName",platformTableName);
		
		platformListService.addRecord(params);
		
	}
	/*
	 * 删除记录
	 */
	@RequestMapping(value = "/delRecord")
	public void delRecord(HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		String id = request.getParameter("id");
		
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("id",id);
		
		platformListService.delRecord(params);
		
	}
	/*
	 * 批量删除
	 */
	@RequestMapping(value = "/delAllRecord")
	public void delAllRecord(HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		String items = request.getParameter("ids");
		
        List<String> delList = new ArrayList<String>();
        String[] strs = items.split(",");
        for (String str : strs) {
            delList.add(str);
        }
		
        platformListService.delAllRecord(delList);
	}
	/*
	 * 编辑记录
	 */
	@RequestMapping(value = "/editRecord")
	public void editRecord(HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		
		String id = request.getParameter("id");
		String platformId = request.getParameter("platformId");
		String platformName = request.getParameter("platformName");
		//String platformTableName = request.getParameter("platformTableName");
		String platformTableName = "";
		
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("id",id);
		params.put("platformId",platformId);
		params.put("platformName",platformName);
		params.put("platformTableName",platformTableName);
		
		platformListService.editRecord(params);
	}
   
}
