package com.syntun.util;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

public class UrlParamUtil {

	/**
	 * 获取指定url中的域名
	 * @param url
	 * @return
	 */
	public static String getParamByUrlYm(String str) {
    	URL url;
    	String domain = "";
		try {
			url = new URL(str);
			domain = url.getHost();

		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return domain;
	}
	public static String getPlatformId(String url){
		
		String flag = "";
		String paramByUrlYm = getParamByUrlYm(url);
		if(paramByUrlYm.contains("jd")){
			flag = "1";
		}else if(paramByUrlYm.contains("suning")){
			flag = "3";
		}else if(paramByUrlYm.contains("guomei")){
			flag = "4";
		}else if(paramByUrlYm.contains("tmall")){
			flag = "5";
		}else if(paramByUrlYm.contains("dangdang")){//当当
			flag = "6";
		}else if(paramByUrlYm.contains("yhd")){//一号店
			flag = "7";
		}else if(paramByUrlYm.contains("amazon")){//亚马逊
			flag = "10";
		}else if(paramByUrlYm.contains("womai")){//我买网
			flag = "13";
		}else if(paramByUrlYm.contains("mia")){//蜜芽
			flag = "33";
		}else if(paramByUrlYm.contains("360haoyao")){//360医药
			flag = "44";
		}else if(paramByUrlYm.contains("vip")){//唯品会
			flag = "45";
		}else if(paramByUrlYm.contains("tuhu")){//途虎
			flag = "46";
		}else if(paramByUrlYm.contains("taobao")){//淘宝
			flag = "47";
		}else if(paramByUrlYm.contains("qccr")){//汽车超人
			flag = "48";
		}else if(paramByUrlYm.contains("kaola")){//考拉
			flag = "49";
		}else if(paramByUrlYm.contains("pinduoduo")){//拼多多
			flag = "50";
		}
		return flag;
	}
	/**
	 * 获取指定url中的某个参数
	 * @param url
	 * @param name
	 * @return
	 */
	public static String getParamByUrl(String url, String name) {
	    url += "&";
	    String pattern = "(\\?|&){1}#{0,1}" + name + "=[a-zA-Z0-9]*(&{1})";

	    Pattern r = Pattern.compile(pattern);

	    Matcher m = r.matcher(url);
	    if (m.find( )) {
	        //System.out.println(m.group(0));
	        return m.group(0).split("=")[1].replace("&", "");
	    } else {
	        return null;
	    }
	}

    public static void main(String[] args) throws IOException  {
    	
    	//苏宁:前面是shop_code,product_id
    	String suning = "https://product.suning.com/0000000000/102370744.html?safp=d488778a.13701.productWrap.43&safc=prd.3.ssdsn_pic06-1_jz";
    	//product_id,sku
    	String guomei = "https://item.gome.com.cn/9140043448-1130043036.html?intcmp=list-9000000700-1_1_1";
    	//天猫
    	String str = "https://detail.tmall.hk/hk/item.htm?id=534827183734&cat_id=2&is_b=1";
    	//京东和当当
    	str = "https://item.jd.com/1126199.html";
    	URL url;
    	String product_id = "";
    	String sku_id = "";
    	String shop_id = "";
		try {
			url = new URL(str);
			//String domain = url.getHost();
			//System.out.println(url.getFile());
			String paramByUrl = getParamByUrl(str,"id");
			//参数上有id
			if(StringUtils.isNotBlank(paramByUrl)){
				System.out.println(paramByUrl);
			}else{
				String path = url.getPath();
				String[] split = path.split("\\.");
				String[] params = split[0].split("/");
				//属于国美类型
				if(path.contains("-")){
					String[] string = params[1].split("-");
					product_id = string[0];
					sku_id = string[1];
				}else{
					//苏宁类型
					if(params.length==3){
						shop_id = params[1];
						product_id = params[2];
					}else {
						//京东和当当类型
						product_id = params[1];
					}

				}
				System.out.println(product_id);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
    

}