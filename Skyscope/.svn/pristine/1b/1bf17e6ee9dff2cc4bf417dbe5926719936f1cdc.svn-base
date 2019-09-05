package com.syntun.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.syntun.entity.ProductPlatformList;
import com.syntun.etl.tools.BaseDao;
import com.syntun.etl.tools.ConnectSql60;
import com.syntun.etl.tools.ConvertSql;
import com.syntun.etl.tools.InsertData60;
import com.syntun.service.ProductPlatformListService;
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
@RequestMapping(value = "/productPlatformList")
public class ProductPlatformListController {
    @Resource(name = "productPlatformListService")
    ProductPlatformListService productPlatformListService;
    
    
    @RequestMapping(value = "/login")
    public ModelAndView login()
    {
        ModelAndView mv=new ModelAndView();
        List<ProductPlatformList> productPlatformList = productPlatformListService.login();
        mv.addObject("productPlatformList",productPlatformList);
        mv.setViewName("/login");
        return mv;
    }
    
    @RequestMapping(value = "/getAllList")
	public void getAllList(HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
    	List<ProductPlatformList> resultMap = productPlatformListService.getAllList();
    	
		JSONArray json = JSONArray.fromObject(resultMap);
		String column = new String();
		column = "{" +
				"\"" + "code" + "\""  + ":" + 0 + "," +
				"\"" + "msg" + "\"" + ":" + "\"" + "" + "\"," +
				"\"" + "count" + "\""  + ": 12" + "," +
				"\"" + "data" + "\""  + ":" + json +
				"}";
		
    	response.getWriter().print(column);
	}
    /*
     * 按条件查询记录
     */
	@RequestMapping(value = "/getList")
	public void getList(HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		String uid = request.getParameter("uid");
    	int page = Integer.parseInt(request.getParameter("page"));
    	int limit = Integer.parseInt(request.getParameter("limit"));
    	int start = (page-1)*limit;
    	HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("page",start);
		params.put("limit",limit);
		params.put("uid",uid);
		
		int count = productPlatformListService.getCount(params);
		List<ProductPlatformList> result = productPlatformListService.getList(params);
		
		GenericController gen = new GenericController();
		List<Object> res = new ArrayList<>();
        for(Object e : result){  
            Object obj = (Object)e;  
            res.add(obj);  
        }  
        
		String jsons = gen.getResultJson(count, res);
		response.getWriter().print(jsons.toString());
		
	}
	/*
	 * 添加记录
	 */
	@RequestMapping(value = "/addRecord")
	public void addRecord(HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		String operationProductId = request.getParameter("operationProductId");
		String uid = request.getParameter("uid");
		String shopId = request.getParameter("shopId");
		String platformId = request.getParameter("platformId");
		String skuId = request.getParameter("skuId");
		
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("operationProductId",operationProductId);
		params.put("uid",uid);
		params.put("shopId",shopId);
		params.put("platformId",platformId);
		params.put("skuId",skuId);
		
		productPlatformListService.addRecord(params);
		
	}
	/*
	 * 编辑记录
	 */
	@RequestMapping(value = "/editRecord")
	public void editRecord(HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		
		String id = request.getParameter("id");
		String operationProductId = request.getParameter("operationProductId");
		String uid = request.getParameter("uid");
		String shopId = request.getParameter("shopId");
		String platformId = request.getParameter("platformId");
		String skuId = request.getParameter("skuId");
		
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("id",id);
		params.put("operationProductId",operationProductId);
		params.put("uid",uid);
		params.put("shopId",shopId);
		params.put("platformId",platformId);
		params.put("skuId",skuId);
		
		productPlatformListService.editRecord(params);
	}
	/*
	 * 删除记录
	 */
	@RequestMapping(value = "/delRecord")
	public void delRecord(HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		String id = request.getParameter("id");
		
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("id",id);
		
		productPlatformListService.delRecord(params);
		
	}
	/*
	 * 批量删除
	 */
	@RequestMapping(value = "/delAllRecord")
	public void delAllRecord(HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		String items = request.getParameter("ids");
        List<String> delList = new ArrayList<String>();
    	String[] strs = items.split(",");
        for (String str : strs) {
            delList.add(str);
        }
        productPlatformListService.delAllRecord(delList);
	}
	@RequestMapping(value = "/upload")
	@ResponseBody
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
		String path = "/home/Skyscope/uplodExcel/productPlatformList";
		//String path = "D:/upload/productPlatformList";
	    File dir = new File(path,fileName);          
	    if(!dir.exists()){  
	        dir.mkdirs();  
	    }  
		file.transferTo(dir); 
		
		String filePath = file.toString();
		System.out.println(filePath);
		String excelPath = "/home/Skyscope/uplodExcel/productPlatformList/"+fileName;
		//String excelPath = "D:/upload/productPlatformList/"+fileName;
		//String excelPath = filePath.replace("\\", "/");
		
		List<String> dataTatal = new ArrayList<String>();
		List<String> fieldList = new ArrayList<>();
		List<HashMap<String, String>> excelData = new ArrayList<HashMap<String, String>>();
		
		POIReadExcelTool poi = new POIReadExcelTool();
		List<List<String>> list = poi.read(excelPath);
		// 读取表格字段列表
		Connection conn60 = ConnectSql60.getConn();
		List<String> fieldList60 = BaseDao.getFieldLowerCase("skyscope_etl.product_platform_list", conn60);
		fieldList60.remove("id");
		ConnectSql60.push(conn60);
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
	            	if(fieldList.size() != fieldList60.size()){
	            		ifRun = false;
	            		break;
	            	}
	            	// 此列标题不包含在数据表字段，结束循环；
	            	for(String field : fieldList){
	            		if(!fieldList60.contains(field)){
	            			ifRun = false;
	            			break;
	            		}
	    	    	}
	            	if(!ifRun){
	            		break;
	            	}
	            }else{
	            	for (int j = 0; j < fieldList.size(); j++) {
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
		    		String sql = ConvertSql.getSql("skyscope_etl.product_platform_list", fieldList, shopMap);
		    		dataTatal.add(sql);
		    	}
				Thread t1 = new Thread(new InsertData60(dataTatal));
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
		return resObj;
	}
}
