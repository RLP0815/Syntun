package com.syntun.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.syntun.controller.systemlog.SysLogger;
import com.syntun.datasource.DataSourceContextHolder;
import com.syntun.entity.CrawlerCommentJD144Url302List;
import com.syntun.etl.tools.BaseDao;
import com.syntun.etl.tools.ConnectSql144;
import com.syntun.etl.tools.ConvertSql;
import com.syntun.etl.tools.InsertData144;
import com.syntun.service.CrawlerCommentJD144Url302ListService;
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
@RequestMapping(value = "/crawlerCommentJD144Url302List")
public class CrawlerCommentJD144Url302ListController {
    @Resource(name = "crawlerCommentJD144Url302ListService")
    CrawlerCommentJD144Url302ListService crawlerCommentJD144Url302ListService;
    
    
    @RequestMapping(value = "/login")
    public ModelAndView login()
    {
        ModelAndView mv=new ModelAndView();
        DataSourceContextHolder.setDbType("ds_144crawlerCommentJD");
        List<CrawlerCommentJD144Url302List> crawlerCommentJD144Url302List = crawlerCommentJD144Url302ListService.login();
        mv.addObject("crawlerCommentJD144Url302List",crawlerCommentJD144Url302List);
        mv.setViewName("/login");
        return mv;
    }
    
    @RequestMapping(value = "/getAllList")
	public void getAllList(HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
    	String urlGroup = request.getParameter("urlGroup");
    	HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("urlGroup",urlGroup);

        DataSourceContextHolder.setDbType("ds_144crawlerCommentJD");
		List<CrawlerCommentJD144Url302List> resultMap = crawlerCommentJD144Url302ListService.getAllList(params);
		
		JSONArray json = JSONArray.fromObject(resultMap);
		
    	response.getWriter().print(json);
		
	}
    
	@RequestMapping(value = "/getList")
	public void getList(HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		String urlGroup = request.getParameter("urlGroup");
    	int page = Integer.parseInt(request.getParameter("page"));
    	int limit = Integer.parseInt(request.getParameter("limit"));
    	int start = (page-1)*limit;
    	HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("page",start);
		params.put("limit",limit);
		params.put("urlGroup",urlGroup);

        DataSourceContextHolder.setDbType("ds_144crawlerCommentJD");
		int count = crawlerCommentJD144Url302ListService.getCount(params);
		List<CrawlerCommentJD144Url302List> result = crawlerCommentJD144Url302ListService.getList(params);
		
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
		String urlGroup = request.getParameter("urlGroup");
		String sortId = request.getParameter("sortId");
		
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("urlGroup",urlGroup);
		params.put("sortId",sortId);

        DataSourceContextHolder.setDbType("ds_144crawlerCommentJD");
		crawlerCommentJD144Url302ListService.addRecord(params);
		
	}
	
	@RequestMapping(value = "/delRecord")
    @SysLogger(modelName = "删除记录", methodType = "delRecord")
	public void delRecord(HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		
		String id = request.getParameter("id");
		
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("id",id);

        DataSourceContextHolder.setDbType("ds_144crawlerCommentJD");
		crawlerCommentJD144Url302ListService.delRecord(params);
		
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

        DataSourceContextHolder.setDbType("ds_144crawlerCommentJD");
		crawlerCommentJD144Url302ListService.delAllRecord(delList);
		
	}
	
	@RequestMapping(value = "/editRecord")
    @SysLogger(modelName = "修改记录", methodType = "editRecord")
	public void editRecord(HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		
		String id = request.getParameter("id");
		String urlGroup = request.getParameter("urlGroup");
		String sortId = request.getParameter("sortId");
		
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("id",id);
		params.put("urlGroup",urlGroup);
		params.put("sortId",sortId);

        DataSourceContextHolder.setDbType("ds_144crawlerCommentJD");
		crawlerCommentJD144Url302ListService.editRecord(params);
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
		String path = "/home/CrawlerCommentJDSystem/uplodExcel/crawlerCommentJD144Url302List";
		//String path = "D:\\upload";
	    File dir = new File(path,fileName);          
	    if(!dir.exists()){  
	        dir.mkdirs();  
	    }  
		file.transferTo(dir); 
		
		String filePath = file.toString();
		System.out.println(filePath);
		String excelPath = "/home/CrawlerCommentJDSystem/uplodExcel/crawlerCommentJD144Url302List/"+fileName;
		//String excelPath = "D:/upload/crawlerCommentJD144Url302List/"+fileName;
		//String excelPath = filePath.replace("\\", "/");
		
		List<String> dataTatal = new ArrayList<String>();
		List<String> fieldList = new ArrayList<>();
		List<HashMap<String, String>> excelData = new ArrayList<HashMap<String, String>>();
		
		POIReadExcelTool poi = new POIReadExcelTool();
		List<List<String>> list = poi.read(excelPath);
		// 读取表格字段列表
		Connection conn144 = ConnectSql144.getConn();
		List<String> fieldList144 = BaseDao.getFieldLowerCase("crawler_comment_Jd.url302list", conn144);
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
	            	for (int j = 0; j <= 2; j++) {
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
		    		String sql = ConvertSql.getSql("crawler_comment_Jd.url302list", fieldList, shopMap);
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
