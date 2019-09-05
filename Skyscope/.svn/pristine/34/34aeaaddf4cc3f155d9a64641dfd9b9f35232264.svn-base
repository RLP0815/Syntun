package com.syntun.security;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import com.syntun.service.TAdminService;
import com.syntun.util.SpringContext;


public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler{  
	
  @Override  
  public void onAuthenticationSuccess(HttpServletRequest request,  
          HttpServletResponse response, Authentication authentication) throws IOException,  
          ServletException {  
	  HttpSession session = request.getSession();
	  
	  if(authentication!=null&&authentication.getPrincipal() != null){
		  UserDetails userDetails = (UserDetails)authentication.getPrincipal(); 
	      //输出登录提示信息  
	      System.out.println("管理员 " + userDetails.getUsername() + " 登录"); 
	      session.setAttribute("authorities", authentication.getAuthorities());
	      	ApplicationContext appCtx = SpringContext.getApplicationContext();
	      	TAdminService tAdminService = (TAdminService)appCtx.getBean(TAdminService.class);
	      
	      	HashMap<String, Object> params = new HashMap<String, Object>();
			params.put("un",userDetails.getUsername());
			
			Map resultMap = tAdminService.getLogin(params);
			if(resultMap != null || resultMap.size()>0){
				session.setAttribute("nickName", resultMap.get("nickname"));
		        session.setAttribute("email", resultMap.get("email"));
		        session.setAttribute("phoneno", resultMap.get("phoneno"));
		        session.setAttribute("userId", resultMap.get("user_id"));
		        session.setAttribute("userName", resultMap.get("user_name"));
		    }
	      super.onAuthenticationSuccess(request, response, authentication);  
	  }
      
  }  
    
}  
