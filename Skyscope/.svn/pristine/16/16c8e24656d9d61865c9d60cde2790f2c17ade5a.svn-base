package com.syntun.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.syntun.entity.PromotionRank;
import com.syntun.service.PromotionRankService;
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
@RequestMapping(value = "/promotionRank")
public class PromotionRankController {
    @Resource(name = "promotionRankService")
    PromotionRankService promotionRankService;
    
    
    @RequestMapping(value = "/login")
    public ModelAndView login()
    {
        ModelAndView mv=new ModelAndView();
        List<PromotionRank> promotionRank = promotionRankService.login();
        mv.addObject("promotionRank",promotionRank);
        mv.setViewName("/login");
        return mv;
    }
    
    @RequestMapping(value = "/getAllList")
	public void getAllList(HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
    	String newPromotionTypeName = request.getParameter("newPromotionTypeName");
    	int page = Integer.parseInt(request.getParameter("page"));
    	int limit = Integer.parseInt(request.getParameter("limit"));
    	int start = (page-1)*limit;
    	HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("page",start);
		params.put("limit",limit);
		params.put("newPromotionTypeName",newPromotionTypeName);

		int count = promotionRankService.getCount(params);
		List<PromotionRank> resultMap = promotionRankService.getList(params);
		
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
		String newPromotionTypeName = request.getParameter("newPromotionTypeName");
    	int page = Integer.parseInt(request.getParameter("page"));
    	int limit = Integer.parseInt(request.getParameter("limit"));
    	int start = (page-1)*limit;
    	HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("page",start);
		params.put("limit",limit);
		params.put("newPromotionTypeName",newPromotionTypeName);
		
		int count = promotionRankService.getCount(params);
		List<PromotionRank> result = promotionRankService.getList(params);
		
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
		
		String oldPromotionTypeName = request.getParameter("oldPromotionTypeName");
		String oldPromotionTypeInfo = request.getParameter("oldPromotionTypeInfo");
		String newPromotionTypeName = request.getParameter("newPromotionTypeName");
		
		
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("oldPromotionTypeName",oldPromotionTypeName);
		params.put("oldPromotionTypeInfo",oldPromotionTypeInfo);
		params.put("newPromotionTypeName",newPromotionTypeName);
		
		promotionRankService.addFiled(params);
		
	}
	
	@RequestMapping(value = "/delFiled")
	public void delFiled(HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		
		String id = request.getParameter("id");
		
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("id",id);
		
		promotionRankService.delFiled(params);
		
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
		
		promotionRankService.delAllFiled(delList);
		
	}
	
	@RequestMapping(value = "/editFiled")
	public void editFiled(HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		
		String id = request.getParameter("id");
		String oldPromotionTypeName = request.getParameter("oldPromotionTypeName");
		String oldPromotionTypeInfo = request.getParameter("oldPromotionTypeInfo");
		String newPromotionTypeName = request.getParameter("newPromotionTypeName");
		
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("id",id);
		params.put("oldPromotionTypeName",oldPromotionTypeName);
		params.put("oldPromotionTypeInfo",oldPromotionTypeInfo);
		params.put("newPromotionTypeName",newPromotionTypeName);
		
		//System.out.println(params);
		promotionRankService.editFiled(params);
		
    	//response.getWriter().print();
		
	}
   
}
