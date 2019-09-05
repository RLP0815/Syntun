package com.syntun.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.syntun.controller.systemlog.SysLogger;
import com.syntun.datasource.DataSourceContextHolder;
import com.syntun.entity.DataTableFiled;
import com.syntun.etl.tools.BaseDao;
import com.syntun.etl.tools.ConnectSql132;
import com.syntun.etl.tools.ConnectSqlUtil;
import com.syntun.etl.tools.ConvertSql;
import com.syntun.etl.tools.InsertData132;
import com.syntun.service.DataTableFiledService;
import com.syntun.util.GenericController;
import com.syntun.util.POIReadExcelTool;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * 
 */
@Controller
@RequestMapping(value = "/dataTableFiled")
public class DataTableFiledController {
    @Resource(name = "dataTableFiledService")
    DataTableFiledService dataTableFiledService;
    
    
    @RequestMapping(value = "/login")
    public ModelAndView login()
    {
        ModelAndView mv=new ModelAndView();
		DataSourceContextHolder.setDbType("ds_mop");  
        List<DataTableFiled> dataTableFiled = dataTableFiledService.login();
        mv.addObject("dataTableFiled",dataTableFiled);
        mv.setViewName("/login");
        return mv;
    }
    
    @RequestMapping(value = "/getAllList")
	public void getAllList(HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
    	String filedName = request.getParameter("filedName");
    	HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("filedName",filedName);

		DataSourceContextHolder.setDbType("ds_mop");  
		List<DataTableFiled> resultMap = dataTableFiledService.getAllList(params);
		
		JSONArray json = JSONArray.fromObject(resultMap);
		
    	response.getWriter().print(json);
		
	}
    
	@RequestMapping(value = "/getList")
	public void getList(HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		String filedName = request.getParameter("filedName");
    	int page = Integer.parseInt(request.getParameter("page"));
    	int limit = Integer.parseInt(request.getParameter("limit"));
    	int start = (page-1)*limit;
    	HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("page",start);
		params.put("limit",limit);
		params.put("filedName",filedName);

		DataSourceContextHolder.setDbType("ds_mop");  
		int count = dataTableFiledService.getCount(params);
		List<DataTableFiled> result = dataTableFiledService.getList(params);
		
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
		String filedName = request.getParameter("filedName");
		String tableId = request.getParameter("tableId");
		String filedDataType = request.getParameter("filedDataType");
		String defaultValue = request.getParameter("defaultValue");
		String filedDataFun = request.getParameter("filedDataFun");
		
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("filedName",filedName);
		params.put("tableId",tableId);
		params.put("filedDataType",filedDataType);
		params.put("defaultValue",defaultValue);
		params.put("filedDataFun",filedDataFun);

		DataSourceContextHolder.setDbType("ds_mop");  
		dataTableFiledService.addRecord(params);
		
	}
	
	@RequestMapping(value = "/delRecord")
    @SysLogger(modelName = "删除记录", methodType = "delRecord")
	public void delRecord(HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		
		String filedId = request.getParameter("filedId");
		
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("filedId",filedId);

		DataSourceContextHolder.setDbType("ds_mop");  
		dataTableFiledService.delRecord(params);
		
	}
	
	@RequestMapping(value = "/delAllRecord")
    @SysLogger(modelName = "批量删除", methodType = "delAllRecord")
	public void delAllRecord(HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		
		String items = request.getParameter("filedIds");
		
        List<String> delList = new ArrayList<String>();
        String[] strs = items.split(",");
        for (String str : strs) {
            delList.add(str);
        }

		DataSourceContextHolder.setDbType("ds_mop");  
		dataTableFiledService.delAllRecord(delList);
		
	}
	
	@RequestMapping(value = "/editRecord")
    @SysLogger(modelName = "修改记录", methodType = "editRecord")
	public void editRecord(HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		
		String filedId = request.getParameter("filedId");
		String filedName = request.getParameter("filedName");
		String tableId = request.getParameter("tableId");
		String filedDataType = request.getParameter("filedDataType");
		String defaultValue = request.getParameter("defaultValue");
		String filedDataFun = request.getParameter("filedDataFun");
		
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("filedId",filedId);
		params.put("filedName",filedName);
		params.put("tableId",tableId);
		params.put("filedDataType",filedDataType);
		params.put("defaultValue",defaultValue);
		params.put("filedDataFun",filedDataFun);

		DataSourceContextHolder.setDbType("ds_mop");  
		dataTableFiledService.editRecord(params);
	}
	
	@RequestMapping(value = "/addTable",produces = "text/plain;charset=utf-8")
    //@SysLogger(modelName = "创建表格", methodType = "addTable")
	public void addTable(HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		String dataIP = request.getParameter("dataIP");
		String databaseName = request.getParameter("databaseName");
		String tableName = request.getParameter("tableName");
		String items = request.getParameter("columns");

		JSONObject resObj = new JSONObject();
		Connection conn = ConnectSqlUtil.getConn(dataIP,databaseName);
		Statement stmt = conn.createStatement();
		
		String sql = "CREATE TABLE " + tableName + "(";
		String sqlKey = " PRIMARY KEY (";
		String sqlEnd = " ) ENGINE=MyISAM AUTO_INCREMENT=62 DEFAULT CHARSET=utf8;";
		
		try {
			//System.out.println(items);
			JSONArray jsonArray = JSONArray.fromObject(items);//把String转换为json 
			//System.out.println(jsonArray.size());
			for(int i=0;i<jsonArray.size();i++){
				JSONObject job = jsonArray.getJSONObject(i); // 遍历 jsonarray 数组，把每一个对象转成 json 对象
				if(job.get("column").equals("")){
					//continue;
					break;
				}
				// 字段名 数据类型（长度）
				if(job.get("dataType").equals("date")||job.get("dataType").equals("datetime")||job.get("dataType").equals("timestamp")||job.get("dataType").equals("text")){
					sql = sql+job.get("column")+" "+job.get("dataType");
				}else{
					sql = sql+job.get("column")+" "+job.get("dataType")+"("+job.get("length")+")";
				}
				// 是否主键
				if(job.get("primaryKey").equals("T")){
					if(sqlKey.equals(" PRIMARY KEY (")){
						sqlKey = sqlKey + job.get("column");
					}else if(job.get("primaryKey").equals("F")){
						sqlKey = sqlKey +","+ job.get("column");
					}
				}
				// 是否非空
				if(job.get("notNull").equals("T")){
					sql = sql+" NOT NULL";
				}else if(job.get("notNull").equals("F")){
					sql = sql+" DEFAULT NULL";
				}
				// 是否自增
				if(job.get("autoIncrement").equals("T")){
					sql = sql+" AUTO_INCREMENT";
				}else if(job.get("autoIncrement").equals("F")){
					
				}
				// 默认值
				if(!job.get("default").equals("")){
					if(job.get("dataType").equals("timestamp")){
						sql = sql + " DEFAULT "+job.get("default");
					}else{
						sql = sql + " DEFAULT '"+job.get("default")+"'";
					}
				}
				// 注释
				sql = sql + " COMMENT '"+job.get("comment")+"',";
			}
		
			sqlKey = sqlKey + ")";
			sql = sql + sqlKey + sqlEnd;
			System.out.println(sql);
		
			if(0 == stmt.executeUpdate(sql)){
				resObj.put("code", 0);
				resObj.put("msg", "成功创建表"+tableName);
			}else{
				resObj.put("code", 1);
				resObj.put("msg", "创建失败"+tableName);
			}
		} catch (Exception e) {
			//System.out.println(e.getMessage());
			resObj.put("code", 2);
			resObj.put("msg", "创建失败："+e.getMessage());
		}

		stmt.close();
		ConnectSqlUtil.push(conn);
		ConnectSqlUtil.closeConnection(conn);
		
		PrintWriter out=response.getWriter();
		out.println(resObj);
		out.flush();
		out.close();
		
	}
	
	@RequestMapping(value = "/upload")
	@ResponseBody
    @SysLogger(modelName = "批量插入", methodType = "upload")
    public JSONObject upload(MultipartFile file, HttpServletRequest servletRequest) throws IOException { 
		JSONObject resObj = new JSONObject();
		// request.getSession().getServletContext().getRealPath("/"); //服务器地址 
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss"); 
		Date day = new Date(); 
		String dateDay = df.format(day);

		String fileName = file.getOriginalFilename();  
		String fileName0 = fileName.substring(0, fileName.indexOf("."));  
		String fileName1 = fileName.substring(fileName.indexOf("."));  
		fileName = fileName0 + "-" + dateDay + fileName1;
		String path = "/home/CrawlerSystem/uplodExcel/dataTableFiled";
		//String path = "D:\\upload";
	    File dir = new File(path,fileName);          
	    if(!dir.exists()){  
	        dir.mkdirs();  
	    }  
		file.transferTo(dir); 
		
		String filePath = file.toString();
		System.out.println(filePath);
		String excelPath = "/home/CrawlerSystem/uplodExcel/dataTableFiled/"+fileName;
		//String excelPath = "D:/upload/dataTableFiled/"+fileName;
		//String excelPath = filePath.replace("\\", "/");
		
		List<String> dataTatal = new ArrayList<String>();
		List<String> fieldList = new ArrayList<>();
		List<HashMap<String, String>> excelData = new ArrayList<HashMap<String, String>>();
		
		POIReadExcelTool poi = new POIReadExcelTool();
		List<List<String>> list = poi.read(excelPath);
		// 读取表格字段列表
		Connection conn132 = ConnectSql132.getConn();
		List<String> fieldList132 = BaseDao.getFieldLowerCase("xitong.data_table_filed_list", conn132);
		ConnectSql132.push(conn132);
		//excel数据条数
		int ex = 0;
    	boolean ifRun = true;
	    if (list != null) {
	    	ex = list.size() - 1;
	    	for (int i = 0; i < list.size(); i++) {
	    		HashMap<String, String> dateMap = new HashMap<String, String>();
	            //System.out.print("第" + (i) + "行");
	            List<String> cellList = list.get(i);
	            //System.out.print(" 本行" + cellList.size() + "列");	           
	            if(i==0){
	            	// 遍历第一行转换为小写
	            	for(String field : list.get(i)){
	            		fieldList.add(field.toLowerCase());
	    	    	}
	            	// 表格列和数据库列个数不相等直接退出
	            	if(fieldList.size() != fieldList132.size()){
	            		ifRun = false;
	            		break;
	            	}
	            	// 此列标题不包含在数据表字段，结束循环；
	            	for(String field : fieldList){
	            		if(!fieldList132.contains(field)){
	            			ifRun = false;
	            			break;
	            		}
	    	    	}
	            	if(!ifRun){
	            		break;
	            	}
	            }else{
	            	for (int j = 0; j <= 5; j++) {
	            		if(cellList.get(j).indexOf(".") != -1){
	            			dateMap.put(fieldList.get(j),cellList.get(j).substring(0, cellList.get(j).indexOf(".")));
	            		}else{
	            			dateMap.put(fieldList.get(j),cellList.get(j));
	            		}
	 	        	}
	            	excelData.add(dateMap);
	            }
	        }
	    	//System.out.println(excelData);
	    	if(excelData.size()!=0){
		    	for(HashMap<String, String> shopMap : excelData){
		    		String sql = ConvertSql.getSql("xitong.data_table_filed_list", fieldList, shopMap);
		    		dataTatal.add(sql);
		    	}
				Thread t1 = new Thread(new InsertData132(dataTatal));
				t1.start();
				boolean isAlice = true;
				while(isAlice){
					if(!t1.isAlive()){
						isAlice = false;
						resObj.put("code", 0);
						resObj.put("msg", "上传Excel表格中数据："+ex+"条");
						resObj.put("data", "");
					}
				 }
	    	}else if(!ifRun){
	    		resObj.put("code", 1);
				resObj.put("msg", "上传Excel表格字段格式错误");
				resObj.put("data", "");
	    	}else{
	    		resObj.put("code", 0);
				resObj.put("msg", "上传Excel表格中数据："+ex+"条");
				resObj.put("data", "");
	    	}
	    }else{
			resObj.put("code", 0);
			resObj.put("msg", "上传Excel表格中数据："+ex+"条");
			resObj.put("data", "");
	    }
//	    String st = delExcel(excelPath);
//	    if(st.equals("YES")){
//	    	String fileNameBak = file.getOriginalFilename() + "." + dateDay;    
//	 	    File dirBak = new File(path,fileNameBak);          
//	 	    if(!dirBak.exists()){  
//	 	    	dirBak.mkdirs();  
//	 	    }  
//	 		//file.transferTo(dirBak); 
//	    }
		return resObj;
	}
	
	public String delExcel(String filePath){
		File file = new File(filePath);
		// 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
		if (file.exists()) {
			if (file.delete()) {
				System.out.println("删除成功！");
				return "YES";
			} else {
				System.out.println("删除失败！");
				return "NO";
			}
		} else {
			System.out.println("删除单个文件失败：不存在！");
			return "NOerr";
		}
	}
}
