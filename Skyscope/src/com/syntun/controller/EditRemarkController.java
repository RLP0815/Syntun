package com.syntun.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.syntun.entity.EditRemark;
import com.syntun.service.EditRemarkService;
import com.syntun.util.GenericController;
import com.syntun.util.SyntunListener;

import net.sf.json.JSONArray;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 */
@Controller
@RequestMapping(value = "/editRemark")
public class EditRemarkController {
    @Resource(name = "editRemarkService")
    EditRemarkService editRemarkService;
    
    
    @RequestMapping(value = "/login")
    public ModelAndView login()
    {
        ModelAndView mv=new ModelAndView();
        List<EditRemark> editRemark = editRemarkService.login();
        mv.addObject("editRemark",editRemark);
        mv.setViewName("/login");
        return mv;
    }
    
    @RequestMapping(value = "/getAllList")
	public void getAllList(HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
    	String remarkId = request.getParameter("remarkId");
    	HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("remarkId",remarkId);

		List<EditRemark> resultMap = editRemarkService.getAllList(params);
		
		JSONArray json = JSONArray.fromObject(resultMap);
		
    	response.getWriter().print(json);
		
	}
    
	@RequestMapping(value = "/getList")
	public void getList(HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		String remarkId = request.getParameter("remarkId");
    	int page = Integer.parseInt(request.getParameter("page"));
    	int limit = Integer.parseInt(request.getParameter("limit"));
    	int start = (page-1)*limit;
    	HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("page",start);
		params.put("limit",limit);
		params.put("remarkId",remarkId);
		
		int count = editRemarkService.getCount(params);
		List<EditRemark> result = editRemarkService.getList(params);
		
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
		
		String remarkId = request.getParameter("remarkId");
		String remarkName = request.getParameter("remarkName");
		String serialNum = request.getParameter("serialNum");
		String remark = request.getParameter("remark");
		
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("remarkId",remarkId);
		params.put("remarkName",remarkName);
		params.put("serialNum",serialNum);
		params.put("remark",remark);
		
		editRemarkService.addRecord(params);
		
	}
	
	@RequestMapping(value = "/delRecord")
	public void delRecord(HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		
		String id = request.getParameter("id");
		
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("id",id);
		
		editRemarkService.delRecord(params);
		
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
		
		editRemarkService.delAllRecord(delList);
		
	}
	
	@RequestMapping(value = "/editRecord")
	public void editRecord(HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		
		String id = request.getParameter("id");
		String remarkId = request.getParameter("remarkId");
		String remarkName = request.getParameter("remarkName");
		String serialNum = request.getParameter("serialNum");
		String remark = request.getParameter("remark");
		
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("id",id);
		params.put("remarkId",remarkId);
		params.put("remarkName",remarkName);
		params.put("serialNum",serialNum);
		params.put("remark",remark);
		
		//System.out.println(params);
		editRemarkService.editRecord(params);
		
    	//response.getWriter().print();
	}
	
	@RequestMapping(value = "/execute")
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		String tableName = request.getParameter("tableName");
		if(tableName.equals("all")){
			tableName = "syntun_v2.BRAND_LIST,syntun_v2.CATEGORY_LIST,syntun_v2.PLATFORM_LIST,"
				+ "syntun_v2.PRODUCT_LIST,syntun_v2.PRODUCT_PLATFORM_LIST,syntun_v2.SHOP_LIST,syntun_v2.SHOP_LIST_YILI";
		}
		String email = request.getParameter("email");
		List<Map<String, String>> resultList = new ArrayList<Map<String, String>>();
		HashMap<String, String> mapOut = new HashMap<String, String>();
		try {
			SyntunListener syntunListener = new SyntunListener();
			syntunListener.Listener(tableName, email);
			mapOut.put("code", "0");
			mapOut.put("msg", "正在执行");
		} catch (Exception e) {
			mapOut.put("code", "1");
			mapOut.put("msg", "执行失败");
		}
		resultList.add(mapOut);
		JSONArray json = JSONArray.fromObject(resultList);
    	response.getWriter().print(json);
	}
}
