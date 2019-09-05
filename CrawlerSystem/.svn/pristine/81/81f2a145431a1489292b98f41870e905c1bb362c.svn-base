package com.syntun.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.syntun.datasource.DataSourceContextHolder;
import com.syntun.entity.ApiDoc;
import com.syntun.entity.ApiName;
import com.syntun.entity.ApiProduct;
import com.syntun.service.ApiDocService;
import com.syntun.service.ApiNameService;
import com.syntun.service.ApiProductService;

import net.sf.json.JSONArray;

/**
 * api
 */
@Controller
@RequestMapping(value = "/apiManage")
public class ApiController {
	
    @Resource(name = "apiNameService")
    ApiNameService apiNameService;
    
    @Resource(name = "apiProductService")
    ApiProductService apiProductService;
    
    @Resource(name = "apiDocService")
    ApiDocService apiDocService;
	
    @ResponseBody
    @RequestMapping(value = "/apiGenerate", produces = "application/json;charset=UTF-8")
	public Map apiGenerate(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		
		String apiName1 = request.getParameter("apiName");
		String apiAddress = request.getParameter("apiAddress");
		String backFormat = request.getParameter("backFormat");
		String reqMode = request.getParameter("reqMode");
		String apiRemarks = request.getParameter("apiRemarks");
		
		String productArray = request.getParameter("productArray");//产品介绍
		String paramArray = request.getParameter("paramArray");//请求参数
		String backParamArray = request.getParameter("backParamArray");//返回参数
		
		JSONArray jsonArray = JSONArray.fromObject(productArray);//把String转换为json 
		List<ApiProduct> apiProductList = JSONArray.toList(jsonArray,ApiProduct.class);
		
		JSONArray jsonArray1 = JSONArray.fromObject(paramArray);//把String转换为json 
		List<ApiDoc> apiDocList = JSONArray.toList(jsonArray1,ApiDoc.class);
		
		JSONArray jsonArray2 = JSONArray.fromObject(backParamArray);//把String转换为json 
		List<ApiDoc> backDocList = JSONArray.toList(jsonArray2,ApiDoc.class);
		
		HashMap<String, Object> params = new HashMap<String, Object>();

		params.put("code", "500");
		params.put("msg", "失败!");
		
		DataSourceContextHolder.setDbType("ds_mop");
		ApiName apiName = new ApiName();
		apiName.setApiname(apiName1);
		apiName.setApiaddress(apiAddress);
		apiName.setBackformat(backFormat);
		apiName.setReqmode(reqMode);
		apiName.setApiremarks(apiRemarks);
		apiNameService.insert(apiName);
		
		if(!apiProductList.isEmpty()){
			for(ApiProduct a:apiProductList){
				a.setPid(apiName.getId());
				apiProductService.insert(a);
			}
		}
		if(!apiDocList.isEmpty()){
			for(ApiDoc a:apiDocList){
				a.setPid(apiName.getId());
				a.setFlag("0");//请求参数
				apiDocService.insert(a);
			}
		}
		if(!backDocList.isEmpty()){
			for(ApiDoc a:backDocList){
				a.setPid(apiName.getId());
				a.setFlag("1");//返回参数
				apiDocService.insert(a);
			}
		}
		if(apiName!=null){
			params.put("code", "200");
			params.put("msg", "成功!");
			params.put("id", apiName.getId());
		}
	    //ModelAndView mv = new ModelAndView("redirect:/index.jsp");
	    return params;
	}
    /**
     * 根据id初始化数据
     */
    @ResponseBody
    @RequestMapping(value = "/getInitData", produces = "application/json;charset=UTF-8")
	public Map getInitData(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String id = request.getParameter("id");
		
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("code", "500");
		params.put("msg", "失败!");
		
		if(StringUtils.isNotBlank(id)){
			DataSourceContextHolder.setDbType("ds_mop");
			ApiName apiName = apiNameService.findone(Integer.parseInt(id));
			params.put("pid", apiName.getId());
			List<ApiProduct> productList = apiProductService.findInfo(params);
			List<ApiDoc> docList = apiDocService.findInfo(params);
			if(!docList.isEmpty()){
				StringBuilder sb = new StringBuilder();
				sb.append(apiName.getApiaddress());
				sb.append("?");
				for(int i=0;i<docList.size();i++){
					//0是请求参数
					if(docList.get(i).getFlag().equals("0")){
						String name = docList.get(i).getName();
						String datavalue = docList.get(i).getDatavalue();
						if(i==0){
							sb.append(name+"="+datavalue);
						}else{
							sb.append("&"+name+"="+datavalue);
						}
					}
				}
				apiName.setReqdemo(sb.toString());
			}
			
			params.put("apiName", apiName);
			params.put("productList", productList);
			params.put("docList", docList);
			params.put("code", "200");	
			params.put("msg", "成功!");
		}
	    return params;
	}
    /**
     * 根据id初始化数据
     */
    @ResponseBody
    @RequestMapping(value = "/getInitProductData", produces = "application/json;charset=UTF-8")
	public Map getInitProductData(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("code", "500");
		params.put("msg", "失败!");
		
		DataSourceContextHolder.setDbType("ds_mop");
		List<ApiName> apiNameList = apiNameService.findInfo(params);
		if(!apiNameList.isEmpty()){
			params.put("apiNameList", apiNameList);
			params.put("code", "200");	
			params.put("msg", "成功!");
		}
	    return params;
	}
}
   
