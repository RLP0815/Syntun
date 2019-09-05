package com.syntun.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.syntun.entity.SkyGroups;
import com.syntun.service.SkyGroupsService;
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
@RequestMapping(value = "/skyGroups")
public class SkyGroupsController {
    @Resource(name = "skyGroupsService")
    SkyGroupsService skyGroupsService;
    

    @RequestMapping(value = "/getAllList")
	public void getAllList(HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
    	String groupName = request.getParameter("groupName");
    	int page = Integer.parseInt(request.getParameter("page"));
    	int limit = Integer.parseInt(request.getParameter("limit"));
    	int start = (page-1)*limit;
    	HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("page",start);
		params.put("limit",limit);
		params.put("groupName",groupName);

		int count = skyGroupsService.getCount(params);
		List<SkyGroups> resultMap = skyGroupsService.getList(params);
		
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
		String groupName = request.getParameter("groupName");
    	int page = Integer.parseInt(request.getParameter("page"));
    	int limit = Integer.parseInt(request.getParameter("limit"));
    	int start = (page-1)*limit;
    	HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("page",start);
		params.put("limit",limit);
		params.put("groupName",groupName);
		
		int count = skyGroupsService.getCount(params);
		List<SkyGroups> result = skyGroupsService.getList(params);
		
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
		
		String groupName = request.getParameter("groupName");
		String inspect = request.getParameter("inspect");
		
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("groupName",groupName);
		params.put("inspect",inspect);
		
		skyGroupsService.addRecord(params);
		
	}
	
	@RequestMapping(value = "/delRecord")
	public void delRecord(HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		
		String id = request.getParameter("id");
		
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("id",id);
		
		skyGroupsService.delRecord(params);
		skyGroupsService.delRecordUser(params);
		
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
		
		skyGroupsService.delAllRecord(delList);
		skyGroupsService.delAllRecordUser(delList);
		
	}
	
	@RequestMapping(value = "/editRecord")
	public void editRecord(HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		
		String id = request.getParameter("id");
		String groupName = request.getParameter("groupName");
		String inspect = request.getParameter("inspect");
		
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("id",id);
		params.put("groupName",groupName);
		params.put("inspect",inspect);
		
		skyGroupsService.editRecord(params);
	}
}
