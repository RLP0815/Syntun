package com.syntun.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.syntun.entity.SkyUsers;
import com.syntun.inspect.SkyscopeInspect;
import com.syntun.inspect.SkyscopeInspectUser;
import com.syntun.service.SkyUsersService;
import com.syntun.util.GenericController;

import net.sf.json.JSONArray;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * 
 */
@Controller
@RequestMapping(value = "/skyUsers")
public class SkyUsersController {
    @Resource(name = "skyUsersService")
    SkyUsersService skyUsersService;
    

    @RequestMapping(value = "/getAllList")
	public void getAllList(HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
    	String userName = request.getParameter("userName");
    	int page = Integer.parseInt(request.getParameter("page"));
    	int limit = Integer.parseInt(request.getParameter("limit"));
    	int start = (page-1)*limit;
    	HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("page",start);
		params.put("limit",limit);
		params.put("userName",userName);

		int count = skyUsersService.getCount(params);
		List<SkyUsers> resultMap = skyUsersService.getList(params);
		
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
		String userName = request.getParameter("userName");
    	int page = Integer.parseInt(request.getParameter("page"));
    	int limit = Integer.parseInt(request.getParameter("limit"));
    	int start = (page-1)*limit;
    	HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("page",start);
		params.put("limit",limit);
		params.put("userName",userName);
		
		int count = skyUsersService.getCount(params);
		List<SkyUsers> result = skyUsersService.getList(params);
		
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
		
		String userName = request.getParameter("userName");
		String userId = request.getParameter("userId");
		String groupName = request.getParameter("groupName");
		String groupId = request.getParameter("groupId");
		
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("userName",userName);
		params.put("userId",userId);
		params.put("groupName",groupName);
		params.put("groupId",groupId);
		
		skyUsersService.addRecord(params);
		
	}
    
    @RequestMapping(value = "/editRecord")
	public void editRecord(HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		
		String id = request.getParameter("id");
		String userName = request.getParameter("userName");
		String userId = request.getParameter("userId");
		String groupName = request.getParameter("groupName");
		String groupId = request.getParameter("groupId");
		
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("id",id);
		params.put("userName",userName);
		params.put("userId",userId);
		params.put("groupName",groupName);
		params.put("groupId",groupId);
		
		skyUsersService.editRecord(params);
	}
    
	@RequestMapping(value = "/delRecord")
	public void delRecord(HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		
		String id = request.getParameter("id");
		
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("id",id);
		
		skyUsersService.delRecord(params);
		
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
		
		skyUsersService.delAllRecord(delList);
		
	}
	
	@RequestMapping(value = "/exeRecord")
	public void exeRecord(HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		String exeDate = request.getParameter("exeDate");
		String userId = request.getParameter("userId");
		String userName = request.getParameter("userName");
		String groupId = request.getParameter("groupId");
		String email = request.getParameter("email");
		
		String dateStr = exeDate.split(" ")[0];
		String hour = exeDate.split(" ")[1].split(":")[0];
		String min = exeDate.split(" ")[1].split(":")[1];
		if(min.equals("00")){
    		min = "15";
    	}
    	if(min.equals("30")){
    		min = "45";
    	}
		//System.out.println(exeDate + "-->" + dateStr + "-->" + hour + "-->" + min + "-->" + userId + "-->" + groupId);
		//System.out.println(email);
		SkyscopeInspectUser.getResult(dateStr, hour, min, groupId, userId, userName, email);
		
	}
	 
	@RequestMapping(value = "/exeAllRecord")
	public void exeAllRecord(HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		String exeDate = request.getParameter("exeDate");
		String email = request.getParameter("email");
		
		String dateStr = exeDate.split(" ")[0];
		String hour = exeDate.split(" ")[1].split(":")[0];
		//String min = Integer.parseInt(exeDate.split(" ")[1].split(":")[1]) + "";
		String min = exeDate.split(" ")[1].split(":")[1];
		Date day1 = new Date(); 
		if(min.equals("00")){
    		min = "15";
    	}
    	if(min.equals("30")){
    		min = "45";
    	}
		//System.out.println(exeDate + "-->" + dateStr + "-->" + hour + "-->" + min + "-->" + userId + "-->" + groupId);
    	HashMap<String, List<HashMap<String, String>>> priceDataAll = 
				new HashMap<String, List<HashMap<String, String>>>();
		HashMap<String, List<HashMap<String, String>>> promotionDataAll = 
				new HashMap<String, List<HashMap<String, String>>>();
		SkyscopeInspect.getResult(dateStr, hour, min, day1, email, priceDataAll, promotionDataAll);
    	//SkyscopeInspect.getResult(dateStr, hour, min, day1, email);
		//HalfPriceResult.HalfResult(dateStr, hour, min, email);
		
	}
	
	@RequestMapping(value = "/getResult")
	public void getResult(HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		String dateStr = "2018-06-28";
		String hour = "18";
		String min = "45";
		Date day1 = new Date(); 
		String email = "";
		HashMap<String, List<HashMap<String, String>>> priceDataAll = 
				new HashMap<String, List<HashMap<String, String>>>();
		HashMap<String, List<HashMap<String, String>>> promotionDataAll = 
				new HashMap<String, List<HashMap<String, String>>>();
		SkyscopeInspect.getResult(dateStr, hour, min, day1, email, priceDataAll, promotionDataAll);
	}
	
	//全球购用户管理，查询
	@RequestMapping(value = "/getHkList")
	public void getHkList(HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		String userName = request.getParameter("userName");
    	int page = Integer.parseInt(request.getParameter("page"));
    	int limit = Integer.parseInt(request.getParameter("limit"));
    	int start = (page-1)*limit;
    	HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("page",start);
		params.put("limit",limit);
		params.put("userName",userName);
		
		int count = skyUsersService.getHkCount(params);
		List<SkyUsers> result = skyUsersService.getHkList(params);
		
		GenericController gen = new GenericController();
		List<Object> res = new ArrayList<>();
        for(Object e : result){  
            Object obj = (Object)e;  
            res.add(obj);  
        }  
        
		String jsons = gen.getResultJson(count, res);
		response.getWriter().print(jsons.toString());
		
	}
	//全球购用户管理，添加
    @RequestMapping(value = "/addHkRecord")
	public void addHkRecord(HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		
		String userName = request.getParameter("userName");
		String userId = request.getParameter("userId");
		
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("userName",userName);
		params.put("userId",userId);
		
		skyUsersService.addHkRecord(params);
		
	}
	//全球购用户管理，删除
	@RequestMapping(value = "/delHkRecord")
	public void delHkRecord(HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		
		String id = request.getParameter("id");
		
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("id",id);
		
		skyUsersService.delHkRecord(params);
		
	}
	//全球购用户管理，批量删除
	@RequestMapping(value = "/delAllHkRecord")
	public void delAllHkRecord(HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		
		String items = request.getParameter("ids");
		
        List<String> delList = new ArrayList<String>();
        String[] strs = items.split(",");
        for (String str : strs) {
            delList.add(str);
        }
		
		skyUsersService.delAllHkRecord(delList);
		
	}
	//全球购用户管理，用户选择
	@RequestMapping(value = "/getNoHkList")
	public void getNoHkList(HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		int page = Integer.parseInt(request.getParameter("page"));
    	int limit = Integer.parseInt(request.getParameter("limit"));
    	int start = (page-1)*limit;
    	HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("page",start);
		params.put("limit",limit);
		
		int count = skyUsersService.getNoHkCount(params);
		List<SkyUsers> result = skyUsersService.getNoHkList(params);
		
		GenericController gen = new GenericController();
		List<Object> res = new ArrayList<>();
        for(Object e : result){  
            Object obj = (Object)e;  
            res.add(obj);  
        }  
        
		String jsons = gen.getResultJson(count, res);
		response.getWriter().print(jsons.toString());
		
	}
}
