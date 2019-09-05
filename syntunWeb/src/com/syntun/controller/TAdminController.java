package com.syntun.controller;


import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.syntun.entity.TAdmin;
import com.syntun.service.TAdminService;
import com.syntun.util.MD5Util;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 
 */
@Controller
@RequestMapping(value = "/tadmin")
public class TAdminController {
    @Resource(name = "tAdminService")
    TAdminService tAdminService;
    
    
    @RequestMapping(value = "/login")
    public ModelAndView login()
    {
        ModelAndView mv=new ModelAndView();
        List<TAdmin>  tAdmin = tAdminService.login();
        mv.addObject("tAdmin",tAdmin);
        mv.setViewName("/login");
        return mv;
    }
    
	@RequestMapping(value = "/getLogin")
	public void getLogin(HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		
		String un = request.getParameter("userName");
		String pw = request.getParameter("password");
		String MD5pw = MD5Util.getMD5(pw);
		
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("un",un);
		params.put("pw",MD5pw);
		
		HashMap<String, String> resultMap = tAdminService.getLogin(params);
		
	    if(resultMap==null || resultMap.size()<1){
	    	resultMap = new HashMap<>();
	    	resultMap.put("state", "0");
	    	
	    }else{
	    	resultMap.put("state", "1");
	    	
	    	HttpSession session = request.getSession();
            //把用户数据保存在session域对象中
            session.setAttribute("nickName", resultMap.get("nickname"));
            session.setAttribute("email", resultMap.get("email"));
            session.setAttribute("phoneno", resultMap.get("phoneno"));
            session.setAttribute("userId", resultMap.get("user_id"));
            session.setAttribute("userName", resultMap.get("user_name"));
            //跳转到用户主页
            //response.sendRedirect(request.getContextPath()+"/index.jsp");
	    }
	    JSONObject json = JSONObject.fromObject(resultMap);
    	response.getWriter().print(json);
	}
    
    @RequestMapping(value = "/changePwd")
   	public void changePwd(HttpServletRequest request, HttpServletResponse response) throws Exception 
   	{
    	Md5PasswordEncoder md5 = new Md5PasswordEncoder();
    	
    	
    	String nickName = request.getParameter("nickName");
   		String oldP = request.getParameter("oldPwd");
   		String newP = request.getParameter("newPwd");
   		
   		String oldPwd = md5.encodePassword(oldP, nickName);
   		String newPwd = md5.encodePassword(newP, nickName);
   		
   		HashMap<String, Object> params = new HashMap<String, Object>();
   		params.put("nickName",nickName);
   		params.put("oldPwd",oldPwd);
   		params.put("newPwd",newPwd);
   		
   		tAdminService.changePwd(params);
   		
   	}
    
    @RequestMapping(value = "/getAllList")
	public void getAllList(HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
    	Md5PasswordEncoder md5 = new Md5PasswordEncoder();
    	
    	String nickName = request.getParameter("nickName");
   		String passWord = md5.encodePassword(request.getParameter("passWord"), nickName);
    	HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("nickName",nickName);
		params.put("passWord",passWord);

		List<TAdmin> resultMap = tAdminService.getAllList(params);
		
		JSONArray json = JSONArray.fromObject(resultMap);
    	response.getWriter().print(json);
		
	}
    
    @RequestMapping(value = "/addRecord")
	public void addRecord(HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		
		String remarkId = request.getParameter("remarkId");
		String remarkName = request.getParameter("remarkName");
		String serialNum = request.getParameter("serialNum");
		String remark = request.getParameter("remark");
		
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("remarkId",remarkId);
		params.put("remarkName",remarkName);
		params.put("serialNum",serialNum);
		params.put("remark",remark);
		
		tAdminService.addRecord(params);
		
	}
	
	@RequestMapping(value = "/delRecord")
	public void delRecord(HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		
		String id = request.getParameter("id");
		
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("id",id);
		
		tAdminService.delRecord(params);
		
	}
	
	@RequestMapping(value = "/delAllRecord")
	public void delAllRecord(HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		
		String items = request.getParameter("ids");
		
        List<String> delList = new ArrayList<String>();
        String[] strs = items.split(",");
        for (String str : strs) {
            delList.add(str);
        }
		
		tAdminService.delAllRecord(delList);
		
	}
	
	@RequestMapping(value = "/editRecord")
	public void editRecord(HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		
		String id = request.getParameter("id");
		String remarkId = request.getParameter("remarkId");
		String remarkName = request.getParameter("remarkName");
		String serialNum = request.getParameter("serialNum");
		String remark = request.getParameter("remark");
		
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("id",id);
		params.put("remarkId",remarkId);
		params.put("remarkName",remarkName);
		params.put("serialNum",serialNum);
		params.put("remark",remark);
		
		tAdminService.editRecord(params);
	}
}
