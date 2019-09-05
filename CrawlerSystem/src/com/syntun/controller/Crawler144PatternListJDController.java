package com.syntun.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.syntun.controller.systemlog.SysLogger;
import com.syntun.datasource.DataSourceContextHolder;
import com.syntun.entity.Crawler144PatternListJD;
import com.syntun.etl.tools.BaseDao;
import com.syntun.etl.tools.ConnectSql144;
import com.syntun.etl.tools.ConvertSql;
import com.syntun.etl.tools.InsertData144;
import com.syntun.service.Crawler144PatternListJDService;
import com.syntun.util.GenericController;
import com.syntun.util.POIReadExcelTool;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * 
 */
@Controller
@RequestMapping(value = "/crawler144PatternListJD")
public class Crawler144PatternListJDController {
    @Resource(name = "crawler144PatternListJDService")
    Crawler144PatternListJDService crawler144PatternListJDService;
    
    
    @RequestMapping(value = "/login")
    public ModelAndView login()
    {
        ModelAndView mv=new ModelAndView();
        DataSourceContextHolder.setDbType("ds_144crawler");
        List<Crawler144PatternListJD> crawler144PatternListJD = crawler144PatternListJDService.login();
        mv.addObject("crawler144PatternListJD",crawler144PatternListJD);
        mv.setViewName("/login");
        return mv;
    }
    
    @RequestMapping(value = "/getAllList")
	public void getAllList(HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
    	String colName = request.getParameter("colName");
    	HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("colName",colName);

        DataSourceContextHolder.setDbType("ds_144crawler");
		List<Crawler144PatternListJD> resultMap = crawler144PatternListJDService.getAllList(params);
		
		JSONArray json = JSONArray.fromObject(resultMap);
		
    	response.getWriter().print(json);
		
	}
    
	@RequestMapping(value = "/getList")
	public void getList(HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		String colName = request.getParameter("colName");
    	int page = Integer.parseInt(request.getParameter("page"));
    	int limit = Integer.parseInt(request.getParameter("limit"));
    	int start = (page-1)*limit;
    	HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("page",start);
		params.put("limit",limit);
		params.put("colName",colName);

        DataSourceContextHolder.setDbType("ds_144crawler");
		int count = crawler144PatternListJDService.getCount(params);
		List<Crawler144PatternListJD> result = crawler144PatternListJDService.getList(params);
		
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
		Date day = new Date();    
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 

		String patternGroup = request.getParameter("patternGroup");
		String urlGroup = request.getParameter("urlGroup");
		String patternType = request.getParameter("patternType");
		String patternContent = request.getParameter("patternContent");
		String patternIndex = request.getParameter("patternIndex");
		String parentPatternId = request.getParameter("parentPatternId");
		String aboutPatternId = request.getParameter("aboutPatternId");
		String purlId = request.getParameter("purlId");
		String sortId = request.getParameter("sortId");
		String generateSortId = request.getParameter("generateSortId");
		String colName = request.getParameter("colName");
		String tableId = request.getParameter("tableId");
		String isSavePageData = request.getParameter("isSavePageData");
		String isGetUrl = request.getParameter("isGetUrl");
		String isDataParallel = request.getParameter("isDataParallel");
		String writeTime = df.format(day);
		String remark = request.getParameter("remark");
		
		HashMap<String, Object> paramses = new HashMap<String, Object>();
		paramses.put("patternGroup",patternGroup);
		paramses.put("urlGroup",urlGroup);
		paramses.put("patternType",patternType);
		paramses.put("patternContent",patternContent);
		paramses.put("patternIndex",patternIndex);
		paramses.put("parentPatternId",parentPatternId);
		paramses.put("aboutPatternId",aboutPatternId);
		paramses.put("purlId",purlId);
		paramses.put("sortId",sortId);
		paramses.put("generateSortId",generateSortId);
		paramses.put("colName",colName);
		paramses.put("tableId",tableId);
		paramses.put("colName",colName);
		paramses.put("isSavePageData",isSavePageData);
		paramses.put("isGetUrl",isGetUrl);
		paramses.put("isDataParallel",isDataParallel);
		paramses.put("writeTime",writeTime);
		paramses.put("remark",remark);

        DataSourceContextHolder.setDbType("ds_144crawler");
		crawler144PatternListJDService.addRecord(paramses);
		
	}
	
	@RequestMapping(value = "/delRecord")
    @SysLogger(modelName = "删除记录", methodType = "delRecord")
	public void delRecord(HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		
		String id = request.getParameter("id");
		
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("id",id);

        DataSourceContextHolder.setDbType("ds_144crawler");
		crawler144PatternListJDService.delRecord(params);
		
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

        DataSourceContextHolder.setDbType("ds_144crawler");
		crawler144PatternListJDService.delAllRecord(delList);
		
	}
	
	@RequestMapping(value = "/editRecord")
    @SysLogger(modelName = "修改记录", methodType = "editRecord")
	public void editRecord(HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		
		String id = request.getParameter("id");
		String patternGroup = request.getParameter("patternGroup");
		String urlGroup = request.getParameter("urlGroup");
		String patternType = request.getParameter("patternType");
		String patternContent = request.getParameter("patternContent");
		String patternIndex = request.getParameter("patternIndex");
		String parentPatternId = request.getParameter("parentPatternId");
		String aboutPatternId = request.getParameter("aboutPatternId");
		String purlId = request.getParameter("purlId");
		String sortId = request.getParameter("sortId");
		String generateSortId = request.getParameter("generateSortId");
		String colName = request.getParameter("colName");
		String tableId = request.getParameter("tableId");
		String isSavePageData = request.getParameter("isSavePageData");
		String isGetUrl = request.getParameter("isGetUrl");
		String isDataParallel = request.getParameter("isDataParallel");
		String writeTime = request.getParameter("writeTime");
		String remark = request.getParameter("remark");
		
		HashMap<String, Object> paramses = new HashMap<String, Object>();
		paramses.put("id",id);
		paramses.put("patternGroup",patternGroup);
		paramses.put("urlGroup",urlGroup);
		paramses.put("patternType",patternType);
		paramses.put("patternContent",patternContent);
		paramses.put("patternIndex",patternIndex);
		paramses.put("parentPatternId",parentPatternId);
		paramses.put("aboutPatternId",aboutPatternId);
		paramses.put("purlId",purlId);
		paramses.put("sortId",sortId);
		paramses.put("generateSortId",generateSortId);
		paramses.put("colName",colName);
		paramses.put("tableId",tableId);
		paramses.put("colName",colName);
		paramses.put("isSavePageData",isSavePageData);
		paramses.put("isGetUrl",isGetUrl);
		paramses.put("isDataParallel",isDataParallel);
		paramses.put("writeTime",writeTime);
		paramses.put("remark",remark);

        DataSourceContextHolder.setDbType("ds_144crawler");
		crawler144PatternListJDService.editRecord(paramses);
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
		String path = "/home/CrawlerSystem/uplodExcel/crawler144PatternListJD";
		//String path = "D:\\upload";
	    File dir = new File(path,fileName);          
	    if(!dir.exists()){  
	        dir.mkdirs();  
	    }  
		file.transferTo(dir); 
		
		String filePath = file.toString();
		System.out.println(filePath);
		String excelPath = "/home/CrawlerSystem/uplodExcel/crawler144PatternListJD/"+fileName;
		//String excelPath = "D:/upload/crawler144PatternListJD/"+fileName;
		//String excelPath = filePath.replace("\\", "/");
		
		List<String> dataTatal = new ArrayList<String>();
		List<String> fieldList = new ArrayList<>();
		List<HashMap<String, String>> excelData = new ArrayList<HashMap<String, String>>();
		
		POIReadExcelTool poi = new POIReadExcelTool();
		List<List<String>> list = poi.read(excelPath);
		// 读取表格字段列表
		Connection conn144 = ConnectSql144.getConn();
		List<String> fieldList144 = BaseDao.getFieldLowerCase("crawler.purl_list", conn144);
		ConnectSql144.push(conn144);
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
	            	if(fieldList.size() != fieldList144.size()){
	            		ifRun = false;
	            		break;
	            	}
	            	// 此列标题不包含在数据表字段，结束循环；
	            	for(String field : fieldList){
	            		if(!fieldList144.contains(field)){
	            			ifRun = false;
	            			break;
	            		}
	    	    	}
	            	if(!ifRun){
	            		break;
	            	}
	            }else{
	            	for (int j = 0; j <= 8; j++) {
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
		    		String sql = ConvertSql.getSql("crawler.purl_list", fieldList, shopMap);
		    		dataTatal.add(sql);
		    	}
				
				Thread t1 = new Thread(new InsertData144(dataTatal));
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
