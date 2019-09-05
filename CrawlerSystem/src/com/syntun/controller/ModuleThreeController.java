package com.syntun.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.syntun.controller.systemlog.SysLogger;
import com.syntun.datasource.DataSourceContextHolder;
import com.syntun.entity.ModuleThree;
import com.syntun.service.ModuleThreeService;
import com.syntun.util.DatabaseUtil;
import com.syntun.util.GenericController;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import net.sf.json.JSONArray;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 
 */
@Controller
@RequestMapping(value = "/moduleThree")
public class ModuleThreeController {
    @Resource(name = "moduleThreeService")
    ModuleThreeService moduleThreeService;
    
    @RequestMapping(value = "/login")
    public ModelAndView login()
    {
        ModelAndView mv=new ModelAndView();
		DataSourceContextHolder.setDbType("ds_mop");  
        List<ModuleThree> moduleThree = moduleThreeService.login();
        mv.addObject("moduleThree",moduleThree);
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
		List<ModuleThree> resultMap = moduleThreeService.getAllList(params);
		
		JSONArray json = JSONArray.fromObject(resultMap);
		
    	response.getWriter().print(json);
		
	}
    
	@RequestMapping(value = "/getList")
	public void getList(HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		String method = request.getParameter("method");
		if(method!=null && method.equals(""))
			method = null;
		String userName = request.getSession().getAttribute("userName").toString();
		if(userName.equals("管理员")){
			userName = null;
		}
		String database = request.getParameter("database");
		if(database!=null && database.equals(""))
			database = null;
		String tableName = request.getParameter("tableName");
		if(tableName!=null && tableName.equals(""))
			tableName = null;
		String columnB1 = request.getParameter("columnB1");
		if(columnB1!=null && columnB1.equals(""))
			columnB1 = null;
		String listVal = request.getParameter("listVal");
		if(listVal!=null && listVal.equals(""))
			listVal = null;
		String column1 = request.getParameter("column1");
		if(column1!=null && column1.equals(""))
			column1 = null;
    	int page = Integer.parseInt(request.getParameter("page"));
    	int limit = Integer.parseInt(request.getParameter("limit"));
    	int start = (page-1)*limit;
    	HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("page",start);
		params.put("limit",limit);
		params.put("database",database);
		params.put("tableName",tableName);
		params.put("columnB1",columnB1);
		params.put("listVal",listVal);
		params.put("column1",column1);
		params.put("userName",userName);
		params.put("method",method);

		DataSourceContextHolder.setDbType("ds_mop");  
		int count = moduleThreeService.getCount(params);
		List<ModuleThree> result = moduleThreeService.getList(params);
		
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
		String ipPort = request.getParameter("ipPort");
		String dataBase = request.getParameter("dataBase");
		String tableName = request.getParameter("tableName");
		String column1 = request.getParameter("column1");
		String listKey = request.getParameter("listKey");
		String listVal = request.getParameter("listVal");
		String connectKey1 = request.getParameter("connectKey1");
		String connectVal1 = request.getParameter("connectVal1");
		String connectKey2 = request.getParameter("connectKey2");
		String connectVal2 = request.getParameter("connectVal2");
		
		String userName = request.getSession().getAttribute("userName").toString();
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date d = new Date();
		String addTime = sf.format(d);
		
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("ipPort",ipPort);
		params.put("dataBase",dataBase);
		params.put("tableName",tableName);
		params.put("column1",column1);
		params.put("columnB1",column1);
		params.put("listKey",listKey);
		params.put("listVal",listVal);
		params.put("connectKey1",connectKey1);
		params.put("connectBKey1",connectKey1);
		params.put("connectVal1",connectVal1);
		params.put("connectKey2",connectKey2);
		params.put("connectBKey2",connectKey2);
		params.put("connectVal2",connectVal2);
		params.put("userName",userName);
		params.put("addTime",addTime);

		DataSourceContextHolder.setDbType("ds_mop");  
		moduleThreeService.addRecord(params);
		
	}
	
	@RequestMapping(value = "/delRecord")
    @SysLogger(modelName = "删除记录", methodType = "delRecord")
	public void delRecord(HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		
		String id = request.getParameter("id");
		
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("id",id);

		DataSourceContextHolder.setDbType("ds_mop");  
		moduleThreeService.delRecord(params);
		
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
		moduleThreeService.delAllRecord(delList);
		
	}
	
	@RequestMapping(value = "/editRecord")
    @SysLogger(modelName = "修改记录", methodType = "editRecord")
	public void editRecord(HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		
		String id = request.getParameter("id");
		String ipPort = request.getParameter("ipPort");
		String dataBase = request.getParameter("dataBase");
		String tableName = request.getParameter("tableName");
		String column1 = request.getParameter("column1");
		String columnB1 = request.getParameter("columnB1");
		String listKey = request.getParameter("listKey");
		String listVal = request.getParameter("listVal");
		String connectKey1 = request.getParameter("connectKey1");
		String connectBKey1 = request.getParameter("connectBKey1");
		String connectVal1 = request.getParameter("connectVal1");
		String connectKey2 = request.getParameter("connectKey2");
		String connectBKey2 = request.getParameter("connectBKey2");
		String connectVal2 = request.getParameter("connectVal2");
		
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("id",id);
		params.put("ipPort",ipPort);
		params.put("dataBase",dataBase);
		params.put("tableName",tableName);
		params.put("column1",column1);
		params.put("columnB1",columnB1);
		params.put("listKey",listKey);
		params.put("listVal",listVal);
		params.put("connectKey1",connectKey1);
		params.put("connectBKey1",connectBKey1);
		params.put("connectVal1",connectVal1);
		params.put("connectKey2",connectKey2);
		params.put("connectBKey2",connectBKey2);
		params.put("connectVal2",connectVal2);

		DataSourceContextHolder.setDbType("ds_mop");  
		moduleThreeService.editRecord(params);
	}
	
	@RequestMapping(value = "/getDataBase")
	public void getDataBase(HttpServletRequest request, HttpServletResponse response) throws Exception 
	{

        DataSourceContextHolder.setDbType("ds_mop");  
		List<Map<String, String>> resultList = moduleThreeService.getDataBase();
		
		JSONArray json = JSONArray.fromObject(resultList);
    	response.getWriter().print(json);
	}
	
	@RequestMapping(value = "/getTableName")
	public void getTableName(HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		DataSourceContextHolder.setDbType("ds_mop");  
		List<Map<String, String>> resultList = moduleThreeService.getTableName();
		
		JSONArray json = JSONArray.fromObject(resultList);
    	response.getWriter().print(json);
	}
	
	/**
     * 校对数据
     * @throws Exception 
     */
    @ResponseBody
    @RequestMapping(value = "/getCheckResult", produces = "application/json;charset=UTF-8")
    @SysLogger(modelName = "QC三校对数据", methodType = "getCheckResult")
    public Map getCheckResult(String checkMethod, String database, String tableName, String column1, String columnB1,
    		String listVal, String connectKey1, String connectBKey1, String connectVal1, String connectKey2, String connectBKey2, String connectVal2,
    		String method, String ed) {

    	 Map result = new HashMap<>();
         result.put("code", "500");
         result.put("msg", "失败!");
 		try {
 		    HashMap<String, Object> params = new HashMap<String, Object>();
 			HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
			String userName = request.getSession().getAttribute("userName").toString();
 			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
 			Date d = new Date();
 			String addTime = sf.format(d);
 			params.put("ipPort","192.168.0.15:1433");
 			params.put("dataBase",database);
 			params.put("tableName",tableName);
 			params.put("column1",column1);
 			params.put("columnB1",columnB1);
 			params.put("listKey","品类");
 			params.put("listVal",listVal);
 			params.put("connectKey1",connectKey1);
 			params.put("connectBKey1",connectBKey1);
 			params.put("connectVal1",connectVal1);
 			params.put("connectKey2",connectKey2);
 			params.put("connectBKey2",connectBKey2);
 			params.put("connectVal2",connectVal2);
 			params.put("userName",userName);
 			params.put("addTime",addTime);
 			params.put("method",method);
 			DataSourceContextHolder.setDbType("ds_mop");  
 			if(ed.equals("F")){
 	 			moduleThreeService.addRecord(params);
 			}
 			
 			String url = "jdbc:sqlserver://192.168.0.15:1433;DatabaseName="+database;
 			Set<String> st = DatabaseUtil.getCheckResultThree(checkMethod,url,tableName,column1,columnB1,
 		    		listVal,connectKey1,connectBKey1,connectVal1,connectKey2,connectBKey2,connectVal2);
 			String lie = " 产品ID：product_id";
			if(columnB1.toLowerCase().indexOf("shop") != -1){
				lie = " 店铺ID：shop_id";
			}
			String outText = "数据库："+database+" 表："+tableName+lie+" 被校对列："+columnB1;
 			String out = "数据库："+database+" 表："+tableName+lie+" 被校对列："+columnB1+"\n";

 			//FileOutputStream os = new FileOutputStream("D:/out.xls");
			FileOutputStream os = new FileOutputStream("/home/CrawlerSystem/excel/out.xls");
			// 创建工作薄
			WritableWorkbook workbook = Workbook.createWorkbook(os);
			// 创建新的一页
			WritableSheet sheet = workbook.createSheet("Sheet", 0);
 			int n = 0;
			sheet.addCell(new Label(0, n, outText)); 
			n++;
			
 			for (String s : st) {  
 				if(s.split("@@").length>1){
 	 				out += "\t" + s.split("@@")[0] + "\t\t\t" + s.split("@@")[1] + "\n";
					 
 	 				sheet.addCell(new Label(0, n, s.split("@@")[0])); 
 	 				sheet.addCell(new Label(1, n, s.split("@@")[1])); 
 	 				n++;
 				}else{
 					out += "\t" + s + "\n";
					 
 					sheet.addCell(new Label(1, n, s)); 
 					n++;
 				}
 			}  
 			
 			result.put("out", out);
 		    result.put("code", "200");
 		    result.put("msg", "成功!");
 		   
 		    //BaseController.outPutTxt(out);
 		    
 		    //开始执行写入操作
            workbook.write();
            //关闭流
            workbook.close();
 			
 		} catch (Exception e) {
 			e.printStackTrace();
 		}
 		return result;
    }
    /**
     * 批量校对数据
     * @throws Exception 
     */
    @ResponseBody
    @RequestMapping(value = "/getCheckResults", produces = "application/json;charset=UTF-8")
    @SysLogger(modelName = "QC三批量校对数据", methodType = "getCheckResults")
    public Map getCheckResults(String checkMethod, String database, String tableName, String column1, String columnB1,
    		String listVal, String connectKey1, String connectBKey1, String connectVal1, String connectKey2, String connectBKey2, String connectVal2) {
    	
    	 Map result = new HashMap<>();
         result.put("code", "500");
         result.put("msg", "失败!");
         String out = "";
 		try {
 			//FileOutputStream os = new FileOutputStream("D:/out.xls");
 			FileOutputStream os = new FileOutputStream("/home/CrawlerSystem/excel/out.xls");
			// 创建工作薄
			WritableWorkbook workbook = Workbook.createWorkbook(os);
			// 创建新的一页
			WritableSheet sheet = workbook.createSheet("Sheet", 0);
 			int n = 0;
 			
 			for(int i=0; i<database.split(",").length; i++){
 				String url = "jdbc:sqlserver://192.168.0.15:1433;DatabaseName="+database.split(",")[i];
 				String k1 = "";
 				String bk1 = "";
 				String v1 = "";
 				if(!connectKey1.split(",")[i].equals("O")){
 					k1 = connectKey1.split(",")[i];
 	 				bk1 = connectBKey1.split(",")[i];
 	 				v1 = connectVal1.split(",")[i];
 				}
 				String k2 = "";
 				String bk2 = "";
 				String v2 = "";
 				if(!connectKey2.split(",")[i].equals("O")){
 					k2 = connectKey2.split(",")[i];
 	 				bk2 = connectBKey2.split(",")[i];
 	 				v2 = connectVal2.split(",")[i];
 				}
 				
 				Set<String> st = DatabaseUtil.getCheckResultThree(checkMethod,url,tableName.split(",")[i],column1.split(",")[i],columnB1.split(",")[i],
 	 		    		listVal.split(",")[i],k1,bk1,v1,k2,bk2,v2);
 				String outText = i+1 + "、  数据库："+database.split(",")[i]+" 表："+tableName.split(",")[i]+" 产品ID：product_id 被校对列："+columnB1.split(",")[i];
 				out += i+1 + "、  数据库："+database.split(",")[i]+" 表："+tableName.split(",")[i]+" 产品ID：product_id 被校对列："+columnB1.split(",")[i]+"\n";
 				
 				sheet.addCell(new Label(0, n, outText)); 
 				n++;
 	 			
 	 			for (String s : st) {  
 	 				if(s.split("@@").length>1){
 	 	 				out += "\t" + s.split("@@")[0] + "\t\t\t" + s.split("@@")[1] + "\n";

 	 	 				sheet.addCell(new Label(0, n, s.split("@@")[0])); 
 	 	 				sheet.addCell(new Label(1, n, s.split("@@")[1])); 
 	 	 				n++;
 	 				}else{
 	 					out += "\t" + s + "\n";

 	 	 				sheet.addCell(new Label(1, n, s)); 
 	 	 				n++;
 	 				}
 	 			}  
 	 			out += "\n";
 			}
 			
 			result.put("out", out);
 		    result.put("code", "200");
 		    result.put("msg", "成功!");
 		   
 		    //BaseController.outPutTxt(out);
 		    
 		    //开始执行写入操作
            workbook.write();
            //关闭流
            workbook.close();
 			
 		} catch (Exception e) {
 			e.printStackTrace();
 		}
 		return result;
    }
    
    /**
     * 获取非法字符
     * @throws Exception 
     */
    @ResponseBody
    @RequestMapping(value = "/getIllegalChar", produces = "application/json;charset=UTF-8")
    public Map getIllegalChar() {
    	
    	 Map result = new HashMap<>();
         result.put("code", "500");
         result.put("msg", "失败!");
         String out = "";
 		try {
 			DataSourceContextHolder.setDbType("ds_mop");  
 			List<ModuleThree> resultList = moduleThreeService.getIllegalChar();
 			for(ModuleThree mt : resultList) {
 				out += mt.getColumn1();
 			}
 			result.put("out", out);
 		    result.put("code", "200");
 		    result.put("msg", "成功!");
 		} catch (Exception e) {
 			e.printStackTrace();
 		}
 		return result;
    }
    
    /**
     * 保存非法字符
     * @throws Exception 
     */
    @ResponseBody
    @RequestMapping(value = "/editIllegalChar", produces = "application/json;charset=UTF-8")
    public Map editIllegalChar(String illegalChar) {
    	
    	 Map result = new HashMap<>();
         result.put("code", "500");
         result.put("msg", "失败!");
         String out = "";
 		try {
 			
 			HashMap<String, Object> params = new HashMap<String, Object>();
 			params.put("id",1);
 			params.put("illegalChar",illegalChar);

 			DataSourceContextHolder.setDbType("ds_mop");  
 			moduleThreeService.editIllegalChar(params);
 			
 		    result.put("code", "200");
 		    result.put("msg", "成功!");
 		} catch (Exception e) {
 			e.printStackTrace();
 		}
 		return result;
    }
    
    /**
     * 查询匹配条件
     * @throws Exception 
     */
    @ResponseBody
    @RequestMapping(value = "/getListVal", produces = "application/json;charset=UTF-8")
    public Map getListVal(String tableName, String column) {

        Map result = new HashMap<>();
        result.put("code", "500");
        result.put("msg", "失败!");
		try {
			String URL = "jdbc:sqlserver://192.168.0.15:1433;DatabaseName=info";
			List<String> list = DatabaseUtil.getListVal(URL, tableName, column);
			if(list.size()>0){
				result.put("list", list);
		        result.put("code", "200");
		        result.put("msg", "成功!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
        return result;
    }
    /**
     * 查询匹配条件,参数表列名对应内容  对应列
     * @throws Exception 
     */
    @ResponseBody
    @RequestMapping(value = "/getListVal2", produces = "application/json;charset=UTF-8")
    public Map getListVal2(String tableName, String column, String listVal) {

        Map result = new HashMap<>();
        result.put("code", "500");
        result.put("msg", "失败!");
		try {
			String URL = "jdbc:sqlserver://192.168.0.15:1433;DatabaseName=info";
			List<String> list = DatabaseUtil.getListVal2(URL, tableName, column, listVal);
			if(list.size()>0){
				result.put("list", list);
		        result.put("code", "200");
		        result.put("msg", "成功!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
        return result;
    }
    /**
     * 查询匹配条件
     * @throws Exception 
     */
    @ResponseBody
    @RequestMapping(value = "/getConnectVal", produces = "application/json;charset=UTF-8")
    public Map getConnectVal(String tableName, String column) {

        Map result = new HashMap<>();
        result.put("code", "500");
        result.put("msg", "失败!");
		try {
			String URL = "jdbc:sqlserver://192.168.0.15:1433;DatabaseName=info";
			List<String> list = DatabaseUtil.getConnectVal(URL, tableName, column);
			if(list.size()>0){
				result.put("list", list);
		        result.put("code", "200");
		        result.put("msg", "成功!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
        return result;
    }
}
