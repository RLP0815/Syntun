package com.syntun.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.syntun.controller.systemlog.OperatorLog;
import com.syntun.controller.systemlog.SysLogger;
import com.syntun.datasource.DataSourceContextHolder;
import com.syntun.entity.HistoryLog;
import com.syntun.entity.Permission;
import com.syntun.entity.Role;
import com.syntun.entity.RolePermission;
import com.syntun.entity.Server;
import com.syntun.entity.Servers;
import com.syntun.entity.TAdmin;
import com.syntun.entity.User;
import com.syntun.entity.UserRole;
import com.syntun.service.HistoryLogService;
import com.syntun.service.RoleService;
import com.syntun.service.TAdminService;
import com.syntun.service.UserRoleService;
import com.syntun.service.UserService;
import com.syntun.util.GenericController;
import com.syntun.util.MD5Util;
import com.syntun.util.Shell;
import com.syntun.util.UUIDGenerator;

import net.sf.json.JSONArray;

/**
 * 
 */
@Controller
@RequestMapping(value = "/user")
public class UserController {
    @Resource(name = "userService")
    UserService userService;
    
    @Resource(name = "roleService")
    RoleService roleService;
    
    @Resource(name = "userRoleService")
    UserRoleService userRoleService;
    
    @Resource(name = "tAdminService")
    TAdminService tAdminService;
    
    @Resource(name = "historyLogService")
    HistoryLogService historyLogService;

    @RequestMapping(value = "/list")
    public ModelAndView list()
    {
        ModelAndView mv=new ModelAndView();
        List<User>  userList=userService.getUser();
        mv.addObject("userList",userList);
        mv.setViewName("/show");
        return mv;
    }
    
    /**
     *查询权限列表
     */
    @ResponseBody
    @RequestMapping(value = "/getPermission", produces = "application/json;charset=UTF-8")
    public Map getPermission() {

        Map result = new HashMap<>();
        DataSourceContextHolder.setDbType("ds_mop");
        List<Permission> userList = userService.getPermission();
        result.put("userList", userList);
        return result;
    }
    /**
     *根据用户id查询用户权限
     */
    @ResponseBody
    @RequestMapping(value = "/getPermissionUseridInfo", produces = "application/json;charset=UTF-8")
    public Map getPermissionUseridInfo(HttpServletRequest request,String userId,String project) {

        Map result = new HashMap<>();
        result.put("userId",userId);
        result.put("project",project);
        DataSourceContextHolder.setDbType("ds_mop");
        List<Permission> userList = userService.getPermissionUseridInfo(result);
        result.clear();
        result.put("userList", userList);
        //创建session对象
        HttpSession session = request.getSession();
        //把权限数据保存在session域对象userList中
        JSONArray jsonArray = JSONArray.fromObject(userList);
        session.setAttribute("permissionInfo", jsonArray);
        return result;
    }
    /**
     *根据用户id查询用户菜单列表
     */
    @ResponseBody
    @RequestMapping(value = "/getPermissionUserid", produces = "application/json;charset=UTF-8")
    public Map getPermissionUserid(String userId) {

        Map result = new HashMap<>();
        DataSourceContextHolder.setDbType("ds_mop");
        List<Permission> userList = userService.getPermissionUserid(userId);
        result.put("userList", userList);
        
        return result;
    }
    
    /**
     *根据用户id查询用户url和按钮是否可见
     */
    @ResponseBody
    @RequestMapping(value = "/getPermissionUseridUrl", produces = "application/json;charset=UTF-8")
    public Map getPermissionUseridUrl(String userId) {

        Map result = new HashMap<>();
        DataSourceContextHolder.setDbType("ds_mop");
        List<Permission> userList = userService.getPermissionUseridUrl(userId);
        result.put("userList", userList);
        
        return result;
    }
    
    /**
     *根据roid和权限id，保存角色权限关系表数据
     */
    @ResponseBody
    @RequestMapping(value = "/roidPermissionAdd", produces = "application/json;charset=UTF-8")
    public Map roidPermissionAdd(HttpServletRequest request,String roid,@RequestParam(value = "permissionId[]") Integer[] permissionId) {

        Map result = new HashMap<>();
        if(StringUtils.isNotBlank(roid)){
        	DataSourceContextHolder.setDbType("ds_mop");
            int i = userService.deleteByRoidPermission(roid);
            if(permissionId.length>0){
                for(Integer p:permissionId){
                	RolePermission rolePermission = new RolePermission();
                	rolePermission.setId(UUIDGenerator.getUUIDoffSpace());
                	rolePermission.setSys_role_id(roid);
                	rolePermission.setSys_permission_id(p.toString());
                	userService.insertByRoidPermission(rolePermission);
                }
            }
            result.put("code", "200");
            result.put("msg", "成功!");
            
	        //根据用户id查询权限
            //创建session对象
            HttpSession session = request.getSession();
	        String userId = session.getAttribute("userid").toString();
	        if(StringUtils.isNotBlank(userId)){
		        Map map = new HashMap<>();
		        map.put("userid",userId);
		        map.put("project","ProData");//所属项目
		        List<Permission> userList = userService.getPermissionUseridInfo(map);
		        //把权限数据保存在session域对象userList中
		        JSONArray jsonArray = JSONArray.fromObject(userList);
		        session.setAttribute("menuList", userList);
		        session.setAttribute("permissionInfo", jsonArray);
	        }
        }else{
            result.put("code", "500");
            result.put("msg", "失败!");
        }
        
        return result;
    }
    
    /**
     *新增权限表
     */
    @ResponseBody
    @RequestMapping(value = "/permissionAdd", produces = "application/json;charset=UTF-8")
    public Map permissionAdd(String treeName,String treeType,String treeUrl,String treeImage,String pid,String treeSort,String treeProject) {

        Map result = new HashMap<>();
        if(StringUtils.isNotBlank(treeName)){
        	Permission p = new Permission();
        	p.setName(treeName);
        	p.setType(treeType);
        	p.setUrl(treeUrl);
        	p.setIcon(treeImage);
        	p.setpId(pid);
        	p.setAvailable('1');
        	p.setProject(treeProject);
        	p.setSortstring(treeSort);
        	DataSourceContextHolder.setDbType("ds_mop");
            int i = userService.permissionAdd(p);
            
            result.put("code", "200");
            result.put("msg", "成功!");
        }else{
            result.put("code", "500");
            result.put("msg", "失败!");
        }
        
        return result;
    }
    
    /**
     *修改权限表
     */
    @ResponseBody
    @RequestMapping(value = "/permissionEdit", produces = "application/json;charset=UTF-8")
    public Map permissionEdit(String treeName,String treeType,String treeUrl,String treeImage,String pid,String treeSort,String treeProject) {

        Map result = new HashMap<>();
        if(StringUtils.isNotBlank(treeName)){
        	Permission p = new Permission();
        	p.setName(treeName);
        	p.setType(treeType);
        	p.setUrl(treeUrl);
        	p.setIcon(treeImage);
        	p.setId(Integer.parseInt(pid));
        	p.setAvailable('1');
        	p.setProject(treeProject);
        	p.setSortstring(treeSort);
        	DataSourceContextHolder.setDbType("ds_mop");
            int i = userService.permissionEdit(p);
            
            result.put("code", "200");
            result.put("msg", "成功!");
        }else{
            result.put("code", "500");
            result.put("msg", "失败!");
        }
        
        return result;
    }
    /**
     *根据id查询权限表
     */
    @ResponseBody
    @RequestMapping(value = "/permissionFindById", produces = "application/json;charset=UTF-8")
    public Map permissionFindById(String treeid) {

        Map result = new HashMap<>();
        if(StringUtils.isNotBlank(treeid)){
        	DataSourceContextHolder.setDbType("ds_mop");
        	Permission permission = userService.permissionFindById(Integer.parseInt(treeid));
        	result.put("permission", permission);
            result.put("code", "200");
            result.put("msg", "成功!");
        }else{
            result.put("code", "500");
            result.put("msg", "失败!");
        }
        
        return result;
    }
    /**
     *根据父id子节点id删除权限表
     */
    @ResponseBody
    @RequestMapping(value = "/permissionDelByIdArrays", produces = "application/json;charset=UTF-8")
    public Map permissionDelByIdArrays(String treeid,@RequestParam(value = "childNodes[]") Integer[] childNodes) {

        Map result = new HashMap<>();
        if(StringUtils.isNotBlank(treeid)){
        	DataSourceContextHolder.setDbType("ds_mop");
        	userService.permissionDelById(Integer.parseInt(treeid));
        	if(childNodes.length>0){
        		for(Integer i:childNodes){
        			userService.permissionDelById(i);
        		}
        	}
            result.put("code", "200");
            result.put("msg", "成功!");
        }else{
            result.put("code", "500");
            result.put("msg", "失败!");
        }
        
        return result;
    }
    /**
     *根据id删除权限表
     */
    @ResponseBody
    @RequestMapping(value = "/permissionDelById", produces = "application/json;charset=UTF-8")
    public Map permissionDelById(String treeid) {

        Map result = new HashMap<>();
        if(StringUtils.isNotBlank(treeid)){
        	DataSourceContextHolder.setDbType("ds_mop");
        	userService.permissionDelById(Integer.parseInt(treeid));
            result.put("code", "200");
            result.put("msg", "成功!");
        }else{
            result.put("code", "500");
            result.put("msg", "失败!");
        }
        
        return result;
    }
    /**
     *根据roleid查询权限列表
     */
    @ResponseBody
    @RequestMapping(value = "/getPermissionRoleInfo", produces = "application/json;charset=UTF-8")
    public Map getPermissionRoleInfo(String roleId) {

        Map result = new HashMap<>();
        if(StringUtils.isNotBlank(roleId)){
        	DataSourceContextHolder.setDbType("ds_mop");
        	List<Permission> userList = userService.getPermissionRoleInfo(roleId);
            result.put("userList", userList);
            result.put("code", "200");
            result.put("msg", "成功!");
        }else{
            result.put("code", "500");
            result.put("msg", "失败!");
        }
        return result;
    }
    /**
     *查询角色列表
     */
    @ResponseBody
    @RequestMapping(value = "/getRoleInfo", produces = "application/json;charset=UTF-8")
    public Map getRoleInfo() {

        Map result = new HashMap<>();
        DataSourceContextHolder.setDbType("ds_mop");
    	List<Role> roleList = roleService.selectCateAccount();
    	if(roleList.size()>0){
            result.put("roleList", roleList);
            result.put("code", "200");
            result.put("msg", "成功!");
        }else{
            result.put("code", "500");
            result.put("msg", "失败!");
        }
        return result;
    }
    /**
     *增删改角色信息
     */
    @SysLogger( modelName = "用户登陆", methodType = "login")//aop注解
    @ResponseBody
    @RequestMapping(value = "/getEditRoleInfo", produces = "application/json;charset=UTF-8")
    public Map getEditRoleInfo(String id,String role,String descpt,String category,String flag) {

        Map result = new HashMap<>();
        Role r = new Role();
        int i = 0;
        DataSourceContextHolder.setDbType("ds_mop");
        if(flag.equals("add")){
        	r.setRole(role);
        	r.setDescpt(descpt);
        	r.setCategory(category);
        	i = roleService.insert(r);
        }else if(flag.equals("edit")){
        	r.setId(Integer.parseInt(id));
        	r.setRole(role);
        	r.setDescpt(descpt);
        	r.setCategory(category);
        	i = roleService.update(r);
        }else if(flag.equals("del")){
        	i = roleService.delete(Integer.parseInt(id));
        }
    	if(i>0){
            result.put("code", "200");
            result.put("msg", "成功!");
        }else{
            result.put("code", "500");
            result.put("msg", "失败!");
        }
        return result;
    }
    /**
     *根据userid查询所属角色
     */
    @ResponseBody
    @RequestMapping(value = "/queryUserRole", produces = "application/json;charset=UTF-8")
    public Map queryUserRole(String userid) {

        Map result = new HashMap<>();
        DataSourceContextHolder.setDbType("ds_mop");
    	List<UserRole> roleList = userRoleService.queryUserRole(Integer.parseInt(userid));
    	List<Role> roleInfo = new ArrayList<>();
    	if(roleList.size()>0){
    		for(UserRole u:roleList){
    			Role r =roleService.findone(u.getRoleid());
    			roleInfo.add(r);
    		}
            result.put("roleInfo", roleInfo);
            result.put("code", "200");
            result.put("msg", "成功!");
        }else{
            result.put("code", "500");
            result.put("msg", "失败!");
        }
        return result;
    }
    /**
     *修改用户信息和所属角色
     */
    @ResponseBody
    @RequestMapping(value = "/editUserRole", produces = "application/json;charset=UTF-8")
    public Map editUserRole(String userid,String name,String email,String phone,String roleid,String passwd,String username) {

        Map<String,String> result = new HashMap<>();
        //先修改用户表数据
        TAdmin t = new TAdmin();
        t.setId(Integer.parseInt(userid));
        t.setNickname(name);
        t.setEmail(email);
        t.setPhoneno(phone);
        t.setUserName(username);
        if(StringUtils.isNotBlank(passwd)){
        	t.setPasswd(MD5Util.getMD5Pwd(passwd,name));
        }
        DataSourceContextHolder.setDbType("ds_mop");
        int ta = tAdminService.update(t);
        //在删除用户和角色关系表数据  根据userid删除
        userRoleService.delUserid(Integer.parseInt(userid));
        //在保存用户和角色关系表数据
        UserRole userRole = new UserRole();
        userRole.setUserid(Integer.parseInt(userid));
        userRole.setRoleid(Integer.parseInt(roleid));
        int i = userRoleService.insert(userRole);
        if(i>0){
            result.put("code", "200");
            result.put("msg", "成功!");
        }else{
            result.put("code", "500");
            result.put("msg", "失败!");
        }
        return result;
    }
    /**
     * 用户新增
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "/addUserInfo", produces = "application/json;charset=UTF-8")
	public Map addUserInfo(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		String pw = request.getParameter("passwd");
		String nickname = request.getParameter("name");//用户名
		String userId = request.getParameter("userid");//用户ID  
		String userName = request.getParameter("username");//用户名称
		String email = request.getParameter("email");//邮箱
		String phone = request.getParameter("phone");//电话
		String roleid = request.getParameter("roleid");//角色id
		String MD5pw = MD5Util.getMD5Pwd(pw,nickname);
		
        Map<String,String> result = new HashMap<>();
		HashMap<String, Object> params = new HashMap<String, Object>();
		//先查询用户名是否存在
		params.put("un", nickname);
		DataSourceContextHolder.setDbType("ds_mop");
		Map m = tAdminService.getLogin(params);
		if(m!=null){
			String nicknames = m.get("nickname").toString();
            result.put("code", "201");
            result.put("msg", "["+nicknames+"]用户已经存在!");
		}else{
			params.put("nickname",nickname);
			params.put("userId",0);
			params.put("userName",userName);
			params.put("email",email);
			params.put("phoneno",phone);
			params.put("passwd",MD5pw);
			int i = tAdminService.addRecord(params);
	        if(i>0){
	        	String userid = params.get("id").toString();
	        	if(StringUtils.isNotBlank(roleid)){
	                //在保存用户和角色关系表数据
	                UserRole userRole = new UserRole();
	                userRole.setUserid(Integer.parseInt(userid));
	                userRole.setRoleid(Integer.parseInt(roleid));
	                userRoleService.insert(userRole);
	        	}
	            result.put("code", "200");
	            result.put("msg", "成功!");
	        }else{
	            result.put("code", "500");
	            result.put("msg", "失败!");
	        }
		}
		

        return result;
	}
    /**
     * 查询角色列表
     * @param request
     * @param response
     * @throws Exception
     */
	@RequestMapping(value = "/getRoleList")
	public void getRoleList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String cateName = request.getParameter("cateName");
    	int page = Integer.parseInt(request.getParameter("page"));
    	int limit = Integer.parseInt(request.getParameter("limit"));
    	int start = (page-1)*limit;
    	HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("page",start);
		params.put("limit",limit);
		params.put("cateName",cateName);
		DataSourceContextHolder.setDbType("ds_mop");
		int count = roleService.getCount(params);
		List<Role> result = roleService.getList(params);
		
		GenericController gen = new GenericController();
		List<Object> res = new ArrayList<>();
        for(Object e : result){  
            Object obj = (Object)e;  
            res.add(obj);  
        }  
        
		String jsons = gen.getResultJson(count, res);
		response.getWriter().print(jsons.toString());
		
	}
    /**
     * 查询用户列表
     * @param request
     * @param response
     * @throws Exception
     */
	@RequestMapping(value = "/getUserList")
	public void getUserList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String userName = request.getParameter("userName");
    	int page = Integer.parseInt(request.getParameter("page"));
    	int limit = Integer.parseInt(request.getParameter("limit"));
    	int start = (page-1)*limit;
    	HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("page",start);
		params.put("limit",limit);
		params.put("nickname",userName);
		DataSourceContextHolder.setDbType("ds_mop");
		int count = tAdminService.getCount(params);
		List<TAdmin> result = tAdminService.getList(params);

		GenericController gen = new GenericController();
		List<Object> res = new ArrayList<>();
        for(Object e : result){  
            Object obj = (Object)e;  
            res.add(obj);  
        }  
        
		String jsons = gen.getResultJson(count, res);
		response.getWriter().print(jsons.toString());
		
	}
	
    /**
     * 查询用户日志列表
     * @param request
     * @param response
     * @throws Exception
     */
	@RequestMapping(value = "/getUserLogList")
	public void getUserLogList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String tableName = request.getParameter("tableName");//表名
		String username = request.getParameter("username");//用户名
		String method = request.getParameter("method");//方法名
    	int page = Integer.parseInt(request.getParameter("page"));
    	int limit = Integer.parseInt(request.getParameter("limit"));
    	int start = (page-1)*limit;
    	HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("page",start);
		params.put("limit",limit);
		if(StringUtils.isNotBlank(tableName)){
			params.put("tableName","systemlog"+"_"+tableName);
		}else{
			params.put("tableName","systemlog");
		}
		params.put("username",username);
		params.put("method",method);
		DataSourceContextHolder.setDbType("ds_mop");
		int count = userService.getTotal(params);
		List<OperatorLog> result = userService.find(params);

		GenericController gen = new GenericController();
		List<Object> res = new ArrayList<>();
        for(Object e : result){  
            Object obj = (Object)e;  
            res.add(obj);  
        }  
        
		String jsons = gen.getResultJson(count, res);
		response.getWriter().print(jsons.toString());
	}
	
    /**
     * 查询历史用户日志列表
     * @param request
     * @param response
     * @throws Exception
     */
	@RequestMapping(value = "/getHistoryInfo")
	public void getHistoryInfo(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String type = request.getParameter("type");
		String searchName = request.getParameter("cateName");
    	int page = Integer.parseInt(request.getParameter("page"));
    	int limit = Integer.parseInt(request.getParameter("limit"));
    	int start = (page-1)*limit;
    	HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("page",start);
		params.put("limit",limit);
		params.put("type",type);
		params.put("name",searchName);
		DataSourceContextHolder.setDbType("ds_mop");
		int count = historyLogService.getTotal(params);
		List<HistoryLog> result = historyLogService.find(params);

		GenericController gen = new GenericController();
		List<Object> res = new ArrayList<>();
        for(Object e : result){  
            Object obj = (Object)e;  
            res.add(obj);  
        }  
        
		String jsons = gen.getResultJson(count, res);
		response.getWriter().print(jsons.toString());
	}
    /**
     *查询单个服务状态
     * @throws Exception 
     */
    @ResponseBody
    @RequestMapping(value = "/delHistoryInfo", produces = "application/json;charset=UTF-8")
    public Map delHistoryInfo(String id) {

        Map result = new HashMap<>();
        result.put("code", "500");
        result.put("msg", "失败!");
        long startTime = System.currentTimeMillis();
        DataSourceContextHolder.setDbType("ds_mop");
        if(StringUtils.isNotBlank(id)){
    		try {
    			historyLogService.delete(Integer.parseInt(id));
		        result.put("code", "200");
		        result.put("msg", "成功!");
    		} catch (Exception e) {
    			e.printStackTrace();
    		}
        }
        return result;
    }
    /**
     * 查询会议预定用户列表
     * @param request
     * @param response
     * @throws Exception
     */
	@RequestMapping(value = "/getRoomUserList")
	public void getRoomUserList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String userName = request.getParameter("userName");
		String roleid = request.getParameter("roleid");
    	int page = Integer.parseInt(request.getParameter("page"));
    	int limit = Integer.parseInt(request.getParameter("limit"));
    	int start = (page-1)*limit;
    	HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("page",start);
		params.put("limit",limit);
		params.put("nickname",userName);
		params.put("roleid",roleid);
		DataSourceContextHolder.setDbType("ds_mop");
		int count = tAdminService.getRoidCount(params);
		List<TAdmin> result = tAdminService.getRoidList(params);

		GenericController gen = new GenericController();
		List<Object> res = new ArrayList<>();
        for(Object e : result){  
            Object obj = (Object)e;  
            res.add(obj);  
        }  
        
		String jsons = gen.getResultJson(count, res);
		response.getWriter().print(jsons.toString());
		
	}
}
