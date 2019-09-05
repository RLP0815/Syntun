package com.syntun.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.syntun.entity.ErrResult;
import com.syntun.service.ErrResultService;
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
@RequestMapping(value = "/errResult")
public class ErrResultController {
    @Resource(name = "errResultService")
    ErrResultService errResultService;
    
    
    @RequestMapping(value = "/login")
    public ModelAndView login()
    {
        ModelAndView mv=new ModelAndView();
        List<ErrResult> errResult = errResultService.login();
        mv.addObject("errResult",errResult);
        mv.setViewName("/login");
        return mv;
    }
    
    @RequestMapping(value = "/getAllList")
	public void getAllList(HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
    	String errTypeName = request.getParameter("errTypeName");
    	int page = Integer.parseInt(request.getParameter("page"));
    	int limit = Integer.parseInt(request.getParameter("limit"));
    	int start = (page-1)*limit;
    	HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("page",start);
		params.put("limit",limit);
		params.put("errTypeName",errTypeName);

		int count = errResultService.getCount(params);
		List<ErrResult> resultMap = errResultService.getList(params);
		
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
		String errTypeName = request.getParameter("errTypeName");
    	int page = Integer.parseInt(request.getParameter("page"));
    	int limit = Integer.parseInt(request.getParameter("limit"));
    	int start = (page-1)*limit;
    	HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("page",start);
		params.put("limit",limit);
		params.put("errTypeName",errTypeName);
		
		int count = errResultService.getCount(params);
		List<ErrResult> result = errResultService.getList(params);
		
		GenericController gen = new GenericController();
		List<Object> res = new ArrayList<>();
        for(Object e : result){  
        	//String getDate = e.getGetDate();
        	//SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");  
            //String formatDate = df.format(getDate); 
           // e.setGetDate(formatDate);
            
        	Object obj = (Object)e;  
            res.add(obj);  
        }  
        
		String jsons = gen.getResultJson(count, res);
		response.getWriter().print(jsons.toString());
		
	}
	
	@RequestMapping(value = "/delAllRecord")
	public void delAllFiled(HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
        
		errResultService.delAllRecord();
		
	}
}
