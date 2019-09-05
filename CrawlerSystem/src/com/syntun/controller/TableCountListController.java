package com.syntun.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.syntun.controller.systemlog.SysLogger;
import com.syntun.datasource.DataSourceContextHolder;
import com.syntun.entity.TableCountList;
import com.syntun.etl.tools.SyntunDate;
import com.syntun.service.TableCountListService;
import com.syntun.util.GenericController;
import com.syntun.util.RemoteTxt;
import com.syntun.util.SyntunEmail;

import net.sf.json.JSONArray;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 */
@Controller
@RequestMapping(value = "/tableCountList")
public class TableCountListController {
    @Resource(name = "tableCountListService")
    TableCountListService tableCountListService;
    
    @RequestMapping(value = "/login")
    public ModelAndView login()
    {
        ModelAndView mv=new ModelAndView();
		DataSourceContextHolder.setDbType("ds_mop");  
        List<TableCountList> tableCountList = tableCountListService.login();
        mv.addObject("tableCountList",tableCountList);
        mv.setViewName("/login");
        return mv;
    }
    
    @RequestMapping(value = "/getAllList")
	public void getAllList(HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
    	String tableName = request.getParameter("tableName");
    	HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("tableName",tableName);

		DataSourceContextHolder.setDbType("ds_mop");  
		List<TableCountList> resultMap = tableCountListService.getAllList(params);
		
		JSONArray json = JSONArray.fromObject(resultMap);
		
    	response.getWriter().print(json);
		
	}
    
	@RequestMapping(value = "/getList")
	public void getList(HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		String dataBase = request.getParameter("dataBase");
		String tableName = request.getParameter("tableName");
		String getDate = request.getParameter("getDate");
		if(dataBase != null && dataBase.equals("")){
			dataBase = null;
		}
		if(tableName != null && tableName.equals("")){
			tableName = null;
		}
		if(getDate == null || getDate.equals("")){
			Date day = new Date();  
		    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
		    String dayDate=sdf.format(day);
		    getDate = SyntunDate.addDate(dayDate, -1);
		}
//    	int page = Integer.parseInt(request.getParameter("page"));
//    	int limit = Integer.parseInt(request.getParameter("limit"));
//    	int start = (page-1)*limit;
    	HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("page",null);
//		params.put("limit",limit);
		params.put("dataBase",dataBase);
		params.put("tableName",tableName);
		params.put("getDate",getDate);

		DataSourceContextHolder.setDbType("ds_mop");  
		int count = tableCountListService.getCount(params);
		List<TableCountList> result = tableCountListService.getList(params);
		
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
    @SysLogger(modelName = "添加记录", methodType = "addRecord")
	public void addRecord(HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		String dataBase = request.getParameter("dataBase");
		String tableName = request.getParameter("tableName");
		String countNum = request.getParameter("countNum");
		String getDate = request.getParameter("getDate");
		
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("dataBase",dataBase);
		params.put("tableName",tableName);
		params.put("countNum",countNum);
		params.put("getDate",getDate);

		DataSourceContextHolder.setDbType("ds_mop");  
		tableCountListService.addRecord(params);
		
	}
	
	@RequestMapping(value = "/delRecord")
    @SysLogger(modelName = "删除记录", methodType = "delRecord")
	public void delRecord(HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		
		String id = request.getParameter("id");
		
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("id",id);

		DataSourceContextHolder.setDbType("ds_mop");  
		tableCountListService.delRecord(params);
		
	}
	
	@RequestMapping(value = "/delAllRecord")
    @SysLogger(modelName = "批量删除", methodType = "delAllRecord")
	public void delAllRecord(HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		
		String items = request.getParameter("ids");
		
        List<String> delList = new ArrayList<String>();
        String[] strs = items.split(",");
        for (String str : strs) {
            delList.add(str);
        }

		DataSourceContextHolder.setDbType("ds_mop");  
		tableCountListService.delAllRecord(delList);
	}
	
	@RequestMapping(value = "/editRecord")
    @SysLogger(modelName = "修改记录", methodType = "editRecord")
	public void editRecord(HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		
		String id = request.getParameter("id");
		String dataBase = request.getParameter("dataBase");
		String tableName = request.getParameter("tableName");
		String countNum = request.getParameter("countNum");
		String getDate = request.getParameter("getDate");
		
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("id",id);
		params.put("dataBase",dataBase);
		params.put("tableName",tableName);
		params.put("countNum",countNum);
		params.put("getDate",getDate);

		DataSourceContextHolder.setDbType("ds_mop");  
		tableCountListService.editRecord(params);
	}
	
	@RequestMapping(value = "/sendEmail")
	public void sendEmail(HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		String email = request.getParameter("email");
		String dataBase = request.getParameter("dataBase");
		String tableName = request.getParameter("tableName");
		String getDate = request.getParameter("getDate");
		
		String content = getContent(dataBase, tableName, getDate);
		
		String[] emails = {email};
		List<Map<String, String>> resultList = new ArrayList<Map<String, String>>();
		HashMap<String, String> mapOut = new HashMap<String, String>();
		try {
			SyntunEmail.sendSimpleEmail(emails, "采集数据统计", content, "b", "html");
			mapOut.put("code", "0");
			mapOut.put("msg", "邮件发送成功");
		} catch (Exception e) {
			mapOut.put("code", "1");
			mapOut.put("msg", "邮件发送失败");
		}
		resultList.add(mapOut);
		JSONArray json = JSONArray.fromObject(resultList);
    	response.getWriter().print(json);
	}
	/**
	 * 查询表中的所有字段
	 * 
	 * @param dataBase	数据库名
	 * @param tableName	表名
	 * @param getDate	日期
	 * 
	 * @return
	 */
	public String getContent(String dataBase, String tableName, String getDate) {
		if(dataBase != null && dataBase.equals("")){
			dataBase = null;
		}
		if(tableName != null && tableName.equals("")){
			tableName = null;
		}
		String getDateStart = "'";
		List<String> list;
		String content = "<table border='1'><tr><th>数据库名</th><th>表名</th>";
		if(getDate!=null && getDate.equals("")){
			Date day = new Date();  
		    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
		    String dayDate=sdf.format(day);
			list = SyntunDate.getInterDays(SyntunDate.addDate(dayDate, -5), SyntunDate.addDate(dayDate, -1));
			for (String date : list) {
				getDateStart = getDateStart + date + "','";
				content = content + "<th>" + date + "</th>";
			}
		}else{
			list = SyntunDate.getInterDays(SyntunDate.addDate(getDate, -4), getDate);
			for (String date : list) {
				getDateStart = getDateStart + date + "','";
				content = content + "<th>" + date + "</th>";
			}
		}
		getDateStart = getDateStart + "'";
		content = content + "</tr>";
		
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("dataBase",dataBase);
		params.put("tableName",tableName);
		params.put("getDate",getDateStart);
		
		List<TableCountList> result = tableCountListService.getEmailList(params);
		HashMap<String, HashMap<String, Integer>> mapMap = new HashMap<String, HashMap<String, Integer>>();
		for (TableCountList tableData : result) {
			HashMap<String, Integer> map;
			String key = tableData.getDataBase()+"&"+tableData.getTableName();
			if(mapMap.containsKey(key)){
				map = mapMap.get(key);
			}else{
				map = new HashMap<String, Integer>();
			}
			map.put(tableData.getGetDate(), tableData.getCountNum());
			mapMap.put(key, map);
		}
		for (String key : mapMap.keySet()) { 
			content = content + "<tr><td>"+key.split("&")[0]+"</td><td>"+key.split("&")[1]+"</td>";
			HashMap<String, Integer> map = mapMap.get(key);
			for (String date : list) {
				int count = map.get(date)==null?0:map.get(date);
				content = content + "<td>" + count + "</td>";
			}
			content = content + "</tr>";
		} 
		content = content + "</table>";
		
		return content;
	}
	
	@RequestMapping(value = "/execute")
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		String getDate = request.getParameter("getDate");
		String str = "nohup /usr/bin/hadoop jar /home/renliping/tableCountList.jar " + getDate 
				+ " >> /home/renliping/tableCountList.log 2>&1 &";
		List<Map<String, String>> resultList = new ArrayList<Map<String, String>>();
		HashMap<String, String> mapOut = new HashMap<String, String>();
		try {
			String s = RemoteTxt.executeCommand(str,"192.168.0.124","renliping","renliping");
			mapOut.put("code", "0");
			mapOut.put("msg", s);
		} catch (Exception e) {
			mapOut.put("code", "1");
			mapOut.put("msg", "执行失败");
		}
		resultList.add(mapOut);
		JSONArray json = JSONArray.fromObject(resultList);
    	response.getWriter().print(json);
	}
}
