package com.syntun.controller.serverclassify;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.syntun.datasource.DataSourceContextHolder;
import com.syntun.entity.server.ServerClassify;
import com.syntun.service.serviceClassify.ServerClassifyService;
import com.syntun.util.GenericController;

/**
 * 平台分类
 */
@Controller
@RequestMapping(value = "/serverClassify")
public class ServerClassifyController {
	
    @Resource(name = "serverClassifyService")
    ServerClassifyService serverClassifyService;
	
    @ResponseBody
    @RequestMapping(value = "/addServerClassify", produces = "application/json;charset=UTF-8")
	public Map addServerClassify(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String name = request.getParameter("serverClassifyName");

		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("code", "500");
		params.put("msg", "失败!");
		if(StringUtils.isNotBlank(name)){
			DataSourceContextHolder.setDbType("ds_mop");
			ServerClassify s = new ServerClassify();
			s.setName(name);
			int i = serverClassifyService.insert(s);
			if(i>0){
				params.put("code", "200");
				params.put("msg", "成功!");
			}
		}
	    return params;
	}
    /**
     * 根据id初始化数据
     */
    @RequestMapping(value = "/getAll", produces = "application/json;charset=UTF-8")
	public void getAll(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
    	int page = Integer.parseInt(request.getParameter("page"));
    	int limit = Integer.parseInt(request.getParameter("limit"));
    	int start = (page-1)*limit;
    	
		HashMap params = new HashMap<String, Object>();
		params.put("code", "500");
		params.put("msg", "失败!");
		params.put("page",start);
		params.put("limit",limit);
		DataSourceContextHolder.setDbType("ds_mop");
		
		int count = serverClassifyService.getTotal(params);
		List<ServerClassify> result = serverClassifyService.find(params);

		GenericController gen = new GenericController();
		List<Object> res = new ArrayList<>();
        for(Object e : result){  
            Object obj = (Object)e;  
            res.add(obj);  
        }  
        
		String jsons = gen.getResultJson(count, res);
		response.getWriter().print(jsons.toString());
	}
    @ResponseBody
    @RequestMapping(value = "/getAllList", produces = "application/json;charset=UTF-8")
	public Map getAllList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("code", "500");
		params.put("msg", "失败!");
		List<ServerClassify> serverClassifyList = serverClassifyService.find(params);
		if(!serverClassifyList.isEmpty()){
			params.put("code", "200");
			params.put("msg", "成功!");
			params.put("serverClassifyList", serverClassifyList);
		}
	    return params;
	}
    @ResponseBody
    @RequestMapping(value = "/delServerClassify", produces = "application/json;charset=UTF-8")
	public Map delServerClassify(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String id = request.getParameter("id");

		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("code", "500");
		params.put("msg", "失败!");
		if(StringUtils.isNotBlank(id)){
			DataSourceContextHolder.setDbType("ds_mop");
			int i = serverClassifyService.delete(Integer.parseInt(id));
			if(i>0){
				params.put("code", "200");
				params.put("msg", "成功!");
			}
		}
	    return params;
	}
}
   
