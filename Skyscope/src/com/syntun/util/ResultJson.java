package com.syntun.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.sf.json.JSONObject;

public class ResultJson {

    private int code;

    private String msg;

    private int count;

    private List<HashMap<String, String>> data;

    

    public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}

	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}

	public List<HashMap<String, String>> getData() {
		return data;
	}
	public void setData(List<HashMap<String, String>> data) {
		this.data = data;
	}
	
	
	public static void main(String[] args) {
		ResultJson testJson =new ResultJson();

        testJson.setCode(0);
        testJson.setMsg("");
        testJson.setCount(12);

        HashMap<String,String> da1 = new HashMap<String, String>();
        da1.put("a", "1");
        da1.put("b", "2");
        da1.put("c", "3");
        
        HashMap<String,String> da2 = new HashMap<String, String>();
        da2.put("a", "4");
        da2.put("b", "5");
        da2.put("c", "6");
        
        List<HashMap<String, String>> ld = new ArrayList<HashMap<String, String>>();
        ld.add(da1);
        ld.add(da2);
        
        testJson.setData(ld);

        Object json= JSONObject.fromObject(testJson);
        System.out.println(json.toString());
    }

}
