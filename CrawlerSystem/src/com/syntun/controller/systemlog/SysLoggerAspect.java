package com.syntun.controller.systemlog;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.syntun.datasource.DataSourceContextHolder;
import com.syntun.service.UserService;
import com.syntun.util.WebUtil;


@Aspect
@Component
public class SysLoggerAspect {

	
@Resource
private UserService userService;
@Resource
HttpServletRequest request;
@Resource
HttpSession  httpSession;

private static Logger logger = Logger.getLogger(SysLoggerAspect.class);
private static SimpleDateFormat sdf1=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


@Pointcut("@annotation(com.syntun.controller.systemlog.SysLogger)") //切点
public void controllerAspect(){}


@Pointcut("execution(* com.syntun.controller..*.*(..))") //切点
public void webExceptionLog(){}

/**
 * 返回后通知
 * @param joinPoint
 * @param rvt
 */
@AfterReturning(pointcut="controllerAspect()",returning="rvt")
public void doAfterReturn(JoinPoint joinPoint,Object rvt){
    handleLog(joinPoint, rvt,null);
}

public void handleLog(JoinPoint joinPoint,Object rvt,Exception e){
	try{
        String ip = "";
        Map<String,String> params = new HashMap<String,String>();
		OperatorLog operatorLog = new OperatorLog();
		SysLogger logger = giveController(joinPoint);
		String logName = logger.modelName();//方法中文名称
/*		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request= attributes.getRequest();*/
		String method = request.getMethod();//get请求或post请求
		//判断是否是post方法，如果是，则记录到日志表中
		// if("POST".equals(method)){
		String beanName = joinPoint.getSignature().getDeclaringTypeName(); //方法所在的类名
		String methodName = joinPoint.getSignature().getName();//方法名
		String uri = request.getRequestURI(); 
		String url = request.getRequestURL().toString(); 
        if(joinPoint.getArgs().length > 0){
            params = getParams(request);//请求参数
            ip = WebUtil.getIpAddress(request);//请求用户ip
        }
		String uid = request.getSession().getAttribute("userid").toString(); 
		String uname = request.getSession().getAttribute("nickName").toString(); 
		operatorLog.setUserid(uid);//用户id
		operatorLog.setUsername(uname);//用户名称
		operatorLog.setMethod(logName);//方法名
		operatorLog.setBeanname(beanName);//方法所在的类名
		operatorLog.setIntf(uri);//操作接口名
		operatorLog.setUrl(url);//操作接口地址
		operatorLog.setRequestip(ip);//操作ip
		operatorLog.setRequestparam(methodName);//操作接口参数
		//保存数据库
		DataSourceContextHolder.setDbType("ds_mop"); 
		userService.addSystemLog(operatorLog);
		} catch (Exception ee) {
			ee.printStackTrace();
		}
}

/**
 * 获得注解
 * @param joinPoint
 * @throws Exception
 */
private static SysLogger giveController(JoinPoint joinPoint) throws Exception
{
    Signature signature = joinPoint.getSignature();
    MethodSignature methodSignature = (MethodSignature) signature;
    Method method = methodSignature.getMethod();

    if (method != null)
    {
        return method.getAnnotation(SysLogger.class);
    }
    return null;
}




/** 
 * 异常通知 用于拦截异常日志 
 * 
 * @param joinPoint 
 * @param e 
 */
@AfterThrowing(pointcut = "webExceptionLog()", throwing = "e")
public void doAfterThrowing(JoinPoint joinPoint, Throwable e) {
	HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
	HttpSession session = request.getSession();
	//获取请求ip  
	String ip = request.getRemoteAddr();
	//获取用户请求方法的参数并序列化为JSON格式字符串
	try{
		String method = request.getMethod();
		String param = request.getParameterMap().toString();
		System.out.println(param);
		String beanName = joinPoint.getSignature().getDeclaringTypeName(); //方法所在的类名
		String methodName = joinPoint.getSignature().getName()+"-"+method;//方法名
		String uri = request.getRequestURI(); //接口名
		String url = request.getRequestURL().toString(); //url
		OperatorLog operatorLog = new OperatorLog();
/*		operatorLog.setExceptionName(e.getClass().getName());
		operatorLog.setExceptionMsg(e.getMessage());
		operatorLog.setMethod(methodName);
		operatorLog.setUrl(url);
		operatorLog.setIntf(uri);
		operatorLog.setRequestParam(param);
		operatorLog.setBeanName(beanName);
		long beginTime = System.currentTimeMillis();
		Date date = new Date(beginTime);
		operatorLog.setRequestTime(date);
		operatorLog.setRequestIp(ip);*/
		
		//保存数据库
		//roleService.insert(operatorLog);
	}catch (Exception ex){
	//记录本地异常日志  
		e.printStackTrace();
	}
}




/*@AfterReturning(returning="result",pointcut = "webRequestLog()")
public void doAfterReturning(Object result){
}
*/


/**
 * 获取参数集
 * @param request
 * @return params
 */
public Map<String,String> getParams(HttpServletRequest request)
{
    Map<String,String> params=new HashMap<String,String>();
    Map properties;
    Map.Entry entry;
    String name;
    String value;
    properties = request.getParameterMap();
    Iterator iterator = properties.entrySet().iterator();
    while (iterator.hasNext()) {
        entry = (Map.Entry) iterator.next();
        name = (String) entry.getKey();
        Object valueObj = entry.getValue();
        if(valueObj ==null){
            value="";
        }else if(valueObj instanceof String[]){
            String[] values = (String[])valueObj;
            value="";
            for(int i=0;i<values.length;i++){
                value += values[i] + ",";
            }
            value = value.substring(0, value.length()-1);
        }else{
            value = valueObj.toString();
        }
        if(!value.isEmpty())
        {
            if(name.indexOf("[]")>=0)
            {
                name=name.replace("[]","");
            }
            else if(name.equals("limit"))
            {
                name="pageSize";
            }
            params.put(name, value);
        }
    }
    String  url  =  "http://"  +  request.getServerName()  +  ":"  +  request.getServerPort()  +  request.getContextPath()+request.getServletPath();
    if(request.getQueryString()!=null)
    {
        url+="?"+request.getQueryString();
    }
    System.err.println("URL："+url);
    System.err.println("params:"+params);
    /* for(Map.Entry<String, String> obj : params.entrySet())
    {
        System.err.println("Key="+obj.getKey()+",Value="+obj.getValue());
    }*/
    return params;
	}
}
