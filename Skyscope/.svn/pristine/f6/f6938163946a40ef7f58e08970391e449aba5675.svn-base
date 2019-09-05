package com.syntun.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sf.json.JSONObject;

public class GenericController {
	protected Logger logger = LoggerFactory.getLogger(BaseController.class);
	/**
	 * 返回成功的JSON字符串
	 */
	public final String SUCCESS = "{success:true}";
	/**
	 * 返回失败字符串
	 */
	public final String FAILURE = "{success:false}";

	/**
	 * 保存操作的信息
	 */
	//private MessageSourceAccessor messages;
	
	/**
	 * 
	 * @param 记录条数
	 * @param 查询结果
	 * @return
	 */
	public String getResultJson(int count, List<Object> result) {
		List<HashMap<String, String>> resultMap = new ArrayList<HashMap<String, String>>();
    	ResultJson resultJson = new ResultJson();
    	
    	for(int i=0, n=result.size(); i<n; i++){
    		Object bean = result.get(i);
    		HashMap<String, String> map = new HashMap<String, String>();
    		Field[] fields = bean.getClass().getDeclaredFields();  
            for (int j = 0, len = fields.length; j < len; j++) {  
                String varName = fields[j].getName();  
                //varName = varName.toLowerCase();				//将key置为小写，默认为对象的属性   
                boolean accessFlag = fields[j].isAccessible();  // 获取原来的访问控制权限  
                fields[j].setAccessible(true);  				// 修改访问控制权限  
                Object varValue;
				try {
					varValue = fields[j].get(bean);
					if (varValue != null)
	                    map.put(varName, varValue.toString());  
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}  				// 获取在对象f中属性fields[i]对应的对象中的变量  
                
                fields[j].setAccessible(accessFlag);  			// 恢复访问控制权限  
            }  
            resultMap.add(map);
        }
    	
    	resultJson.setCode(0);
    	resultJson.setMsg("");
    	resultJson.setCount(count);
    	resultJson.setData(resultMap);
		Object jsons = JSONObject.fromObject(resultJson);
    	
		return jsons.toString();
	}
}
