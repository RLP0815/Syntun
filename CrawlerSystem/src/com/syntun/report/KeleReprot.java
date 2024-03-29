package com.syntun.report;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.syntun.entity.CateGory;

/**
 * Created by wangdong on 2019/3/12.
 */
public class KeleReprot {
	
	private final static Logger LOGGER = LoggerFactory.getLogger(KeleReprot.class);

    private static String DRIVER = "com.microsoft.jdbc.sqlserver.SQLServerDriver";
    private static String URL = "jdbc:sqlserver://192.168.0.16:1433;DatabaseName=skyscope";
    private static String USERNAME = "liang";
    private static String PASSWORD = "liangjianqing";
    private static final String SQL = "SELECT * FROM ";// 数据库操作
    
/*    public DatabaseUtil(String DRIVER,String URL,String USERNAME,String PASSWORD){
        this.DRIVER = DRIVER;
        this.URL = URL;
        this.USERNAME = USERNAME;
        this.PASSWORD = PASSWORD;
    }*/

/*    static {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            LOGGER.error("can not load jdbc driver", e);
        }
    }*/

    /**
     * 获取数据库连接
     *
     * @return
     */
    public static Connection getConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            LOGGER.error("get connection failure", e);
        }
        return conn;
    }

    /**
     * 关闭数据库连接
     * @param conn
     */
    public static void closeConnection(Connection conn) {
        if(conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                LOGGER.error("close connection failure", e);
            }
        }
    }


    
    public static void getKeleList(String date,HttpServletRequest request, HttpServletResponse response) throws Exception {
    	
    	List<CateGory> skuInfo = getSkuInfo(date);//第一个sheet页面数据
    	
    	//京东
    	List<CateGory> jdlist = new ArrayList<CateGory>(); 
    	List<CateGory> jdzs = getfenzi(date,"碳酸饮料","jd");
    	jdlist.addAll(jdzs);
        //碳酸饮料新增四个   ===开始
        //1：Coke TM 2：Pepsi TM 3：Coca Cola Total 4：Pepsi Total；
        String CokeTM = getCateGory("Coke TM");
        String PepsiTM = getCateGory("Pepsi TM");
        String CocaColaTotal = getCateGory("Coca Cola Total");
        String PepsiTotal = getCateGory("Pepsi Total");
        List<CateGory> getwinfo = getwinfo(date,CokeTM,"jd");
        List<CateGory> getwinfo2 = getwinfo(date,PepsiTM,"jd");
        List<CateGory> getwinfo3 = getwinfo(date,CocaColaTotal,"jd");
        List<CateGory> getwinfo4 = getwinfo(date,PepsiTotal,"jd");
    	CateGory cg =new CateGory();  
    	cg.setCategory("碳酸饮料");
    	cg.setBrand("Coke TM");
    	cg.setWsum(getwinfo.get(0).getWsum());
    	jdlist.add(cg);
        
    	CateGory cg2 =new CateGory();  
    	cg2.setCategory("碳酸饮料");
        cg2.setBrand("Pepsi TM");
        cg2.setWsum(getwinfo2.get(0).getWsum());
        jdlist.add(cg2);
        
    	CateGory cg3 =new CateGory();  
    	cg3.setCategory("碳酸饮料");
        cg3.setBrand("Coca Cola Total");
        cg3.setWsum(getwinfo3.get(0).getWsum());
        jdlist.add(cg3);
        
    	CateGory cg4 =new CateGory();  
    	cg4.setCategory("碳酸饮料");
        cg4.setBrand("Pepsi Total");
        cg4.setWsum(getwinfo4.get(0).getWsum());
        jdlist.add(cg4);
        
    	List<CateGory> jdgw = getfenzi(date,"果味饮料","jd");
    	jdlist.addAll(jdgw);
    	List<CateGory> jdbz = getfenzi(date,"包装饮用水","jd");
    	jdlist.addAll(jdbz);
    	List<CateGory> jdchunYuemfz = getchunYuem(date,"jd");//纯悦分子
    	jdlist.addAll(jdchunYuemfz);
    	
        Map wxinfo = getwxinfo(date,"jd");//jd分母
        double bao = (double) wxinfo.get("bao");
        double guo = (double) wxinfo.get("guo");
        double suan = (double) wxinfo.get("suan");
        
        List<CateGory> jdchunYueMufm = getchunYueMu("jd");//纯悦分母
        String wsum3 = jdchunYueMufm.get(0).getWsum();
        double w5 = Double.parseDouble(wsum3);
        
        //碳酸饮料新增四个周分母
        List<CateGory> getwinfo5 = getwinfo(date,"jd");
        String wsum2 = getwinfo5.get(0).getWsum();
        double w4 = Double.parseDouble(wsum2);
        
        if(jdlist.size()>0){
        	for(CateGory c:jdlist){
        	    if(c.getCategory().equals("纯悦")){
            		double wsum = Double.parseDouble(c.getWsum())/w5;
            		c.setWsums(wsum+"");
        		}else if(c.getBrand().equals("Coke TM") || c.getBrand().equals("Pepsi TM") || c.getBrand().equals("Coca Cola Total") || c.getBrand().equals("Pepsi Total")){
            		double wsum = Double.parseDouble(c.getWsum())/w4;
            		c.setWsums(wsum+"");
        		}else{
            		if(c.getCategory().equals("包装饮用水")){
                		double wsum = Double.parseDouble(c.getWsum())/bao;
                		c.setWsums(wsum+"");
            		}else if(c.getCategory().equals("果味饮料")){
                		double wsum = Double.parseDouble(c.getWsum())/guo;
                		c.setWsums(wsum+"");
            		}else if(c.getCategory().equals("碳酸饮料")){
                		double wsum = Double.parseDouble(c.getWsum())/suan;
                		c.setWsums(wsum+"");
            		}
        		}
        	}
        }
        
        //天猫
    	List<CateGory> tmlist = new ArrayList<CateGory>(); 
    	List<CateGory> tmzs = getfenzi(date,"碳酸饮料","tm");
    	tmlist.addAll(tmzs);
        //碳酸饮料新增四个   ===开始
        //1：Coke TM 2：Pepsi TM 3：Coca Cola Total 4：Pepsi Total；
        List<CateGory> getwinfotm1 = getwinfo(date,CokeTM,"tm");
        List<CateGory> getwinfotm2 = getwinfo(date,PepsiTM,"tm");
        List<CateGory> getwinfotm3 = getwinfo(date,CocaColaTotal,"tm");
        List<CateGory> getwinfotm4= getwinfo(date,PepsiTotal,"tm");
    	CateGory cgtm =new CateGory();  
    	cgtm.setCategory("碳酸饮料");
    	cgtm.setBrand("Coke TM");
    	cgtm.setWsum(getwinfotm1.get(0).getWsum());
    	tmlist.add(cgtm);
        
    	CateGory cgtm2 =new CateGory();  
    	cgtm2.setCategory("碳酸饮料");
    	cgtm2.setBrand("Pepsi TM");
    	cgtm2.setWsum(getwinfotm2.get(0).getWsum());
        tmlist.add(cgtm2);
        
    	CateGory cgtm3 =new CateGory();  
    	cgtm3.setCategory("碳酸饮料");
    	cgtm3.setBrand("Coca Cola Total");
    	cgtm3.setWsum(getwinfotm3.get(0).getWsum());
        tmlist.add(cgtm3);
        
    	CateGory cgtm4 =new CateGory();  
    	cgtm4.setCategory("碳酸饮料");
    	cgtm4.setBrand("Pepsi Total");
    	cgtm4.setWsum(getwinfotm4.get(0).getWsum());
        tmlist.add(cgtm4);
        
    	List<CateGory> tmgw = getfenzi(date,"果味饮料","tm");
    	tmlist.addAll(tmgw);
    	List<CateGory> tmbz = getfenzi(date,"包装饮用水","tm");
    	tmlist.addAll(tmbz);
    	List<CateGory> tmchunYuemfz = getchunYuem(date,"tm");//纯悦分子
    	tmlist.addAll(tmchunYuemfz);
    	
        Map wxinfotm = getwxinfo(date,"tm");//jd分母
        double tmbao = (double) wxinfotm.get("bao");
        double tmguo = (double) wxinfotm.get("guo");
        double tmsuan = (double) wxinfotm.get("suan");
        
        List<CateGory> tmchunYueMufm = getchunYueMu("tm");//纯悦分母
        String wsumtm = tmchunYueMufm.get(0).getWsum();
        double tm = Double.parseDouble(wsumtm);
        
        //碳酸饮料新增四个周分母
        List<CateGory> getwinfotm5 = getwinfo(date,"tm");
        String wsumtm2 = getwinfotm5.get(0).getWsum();
        double wtm4 = Double.parseDouble(wsumtm2);
        
        if(tmlist.size()>0){
        	for(CateGory c:tmlist){
        	    if(c.getCategory().equals("纯悦")){
            		double wsum = Double.parseDouble(c.getWsum())/tm;
            		c.setWsums(wsum+"");
        		}else if(c.getBrand().equals("Coke TM") || c.getBrand().equals("Pepsi TM") || c.getBrand().equals("Coca Cola Total") || c.getBrand().equals("Pepsi Total")){
            		double wsum = Double.parseDouble(c.getWsum())/wtm4;
            		c.setWsums(wsum+"");
        		}else{
            		if(c.getCategory().equals("包装饮用水")){
                		double wsum = Double.parseDouble(c.getWsum())/tmbao;
                		c.setWsums(wsum+"");
            		}else if(c.getCategory().equals("果味饮料")){
                		double wsum = Double.parseDouble(c.getWsum())/tmguo;
                		c.setWsums(wsum+"");
            		}else if(c.getCategory().equals("碳酸饮料")){
                		double wsum = Double.parseDouble(c.getWsum())/tmsuan;
                		c.setWsums(wsum+"");
            		}
        		}
        	}
        }
        
        
        //京东和天猫
    	List<CateGory> tm_jdlist = new ArrayList<CateGory>(); 
    	List<CateGory> tm_jdzs = getfenzi(date,"碳酸饮料","");
    	tm_jdlist.addAll(tm_jdzs);
        //碳酸饮料新增四个   ===开始
        //1：Coke TM 2：Pepsi TM 3：Coca Cola Total 4：Pepsi Total；
        List<CateGory> getwinfotmjd1 = getwinfo(date,CokeTM,"");
        List<CateGory> getwinfotmjd2 = getwinfo(date,PepsiTM,"");
        List<CateGory> getwinfotmjd3 = getwinfo(date,CocaColaTotal,"");
        List<CateGory> getwinfotmjd4 = getwinfo(date,PepsiTotal,"");
    	CateGory cgtmjd1 =new CateGory();  
    	cgtmjd1.setCategory("碳酸饮料");
    	cgtmjd1.setBrand("Coke TM");
    	cgtmjd1.setWsum(getwinfotmjd1.get(0).getWsum());
    	tm_jdlist.add(cgtmjd1);
        
    	CateGory cgtmjd2 =new CateGory();  
    	cgtmjd2.setCategory("碳酸饮料");
    	cgtmjd2.setBrand("Pepsi TM");
    	cgtmjd2.setWsum(getwinfotmjd2.get(0).getWsum());
    	tm_jdlist.add(cgtmjd2);
        
    	CateGory cgtmjd3 =new CateGory();  
    	cgtmjd3.setCategory("碳酸饮料");
    	cgtmjd3.setBrand("Coca Cola Total");
    	cgtmjd3.setWsum(getwinfotmjd3.get(0).getWsum());
    	tm_jdlist.add(cgtmjd3);
        
    	CateGory cgtmjd4 =new CateGory();  
    	cgtmjd4.setCategory("碳酸饮料");
    	cgtmjd4.setBrand("Pepsi Total");
    	cgtmjd4.setWsum(getwinfotmjd4.get(0).getWsum());
    	tm_jdlist.add(cgtmjd4);
    	
    	List<CateGory> tm_jdgw = getfenzi(date,"果味饮料","");
    	tm_jdlist.addAll(tm_jdgw);
    	List<CateGory> tm_jdbz = getfenzi(date,"包装饮用水","");
    	tm_jdlist.addAll(tm_jdbz);
    	List<CateGory> tm_jdchunYuemfz = getchunYuem(date,"");//纯悦分子
    	tm_jdlist.addAll(tm_jdchunYuemfz);
    	
        Map wxinfotm_jd = getwxinfo(date,"");//jd分母
        double tm_jdbao = (double) wxinfotm_jd.get("bao");
        double tm_jdguo = (double) wxinfotm_jd.get("guo");
        double tm_jdsuan = (double) wxinfotm_jd.get("suan");
        
        List<CateGory> tm_jdchunYueMufm = getchunYueMu("");//纯悦分母
        String wsumtm_jd = tm_jdchunYueMufm.get(0).getWsum();
        double tm_jd = Double.parseDouble(wsumtm_jd);
        
        //碳酸饮料新增四个周分母
        List<CateGory> getwinfotmjd = getwinfo(date,"");
        String wsumtmjd = getwinfotmjd.get(0).getWsum();
        double wtmjd = Double.parseDouble(wsumtmjd);
        
        if(tm_jdlist.size()>0){
        	for(CateGory c:tm_jdlist){
        	    if(c.getCategory().equals("纯悦")){
            		double wsum = Double.parseDouble(c.getWsum())/tm_jd;
            		c.setWsums(wsum+"");
        		}else if(c.getBrand().equals("Coke TM") || c.getBrand().equals("Pepsi TM") || c.getBrand().equals("Coca Cola Total") || c.getBrand().equals("Pepsi Total")){
            		double wsum = Double.parseDouble(c.getWsum())/wtmjd;
            		c.setWsums(wsum+"");
        		}else{
            		if(c.getCategory().equals("包装饮用水")){
                		double wsum = Double.parseDouble(c.getWsum())/tm_jdbao;
                		c.setWsums(wsum+"");
            		}else if(c.getCategory().equals("果味饮料")){
                		double wsum = Double.parseDouble(c.getWsum())/tm_jdguo;
                		c.setWsums(wsum+"");
            		}else if(c.getCategory().equals("碳酸饮料")){
                		double wsum = Double.parseDouble(c.getWsum())/tm_jdsuan;
                		c.setWsums(wsum+"");
            		}
        		}
        	}
        }
        ExcelUtil.exportUserExcel(jdlist, tmlist, tm_jdlist, skuInfo,request,response,date);
        System.out.println("完成");
    	
    }
    /**
     * 获取最后4个分类
     * @return
     */
    public static String getCateGory(String type) {
    	List<String> list=new ArrayList<String>(); 
        //与数据库的连接
        Connection conn = getConnection();
        String sql = "select En_brand from cola_copy_new where type = '"+type+"'";
        try {  
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs=pst.executeQuery();      
            while(rs.next()){  
                list.add(rs.getString("En_brand"));  
            }  
           } catch (Exception e) {  
            e.printStackTrace();  
        }finally {
        	closeConnection(conn);
		}
    	StringBuilder sb = new StringBuilder();
    	for(int i=0;i<list.size();i++){
    		if(i==0){
    			sb.append("'");
    			sb.append(list.get(i));
    			sb.append("'");
    		}else{
    			sb.append(",");
    			sb.append("'");
    			sb.append(list.get(i));
    			sb.append("'");
    		}
    	}
        return sb.toString();
    }
    /**
     * 最后4个，获取一周分子总数
     * @return
     */
    public static List<CateGory> getwinfo(String date,String brand,String ecType) {
    	List<CateGory> list=new ArrayList<CateGory>(); 
        //与数据库的连接
        Connection conn = getConnection();
        String sql = "select sum(SALES_PRICE) SALES_PRICE  from cloa_chushu where date='"+date+"' and CATEGORY='碳酸饮料' and  En_brand in ("+brand+")";
        //jd或天猫 分类
        if(StringUtils.isNotBlank(ecType)){
        	sql = "select sum(SALES_PRICE) SALES_PRICE  from cloa_chushu where date='"+date+"' and ec='"+ecType+"' and CATEGORY='碳酸饮料' and  En_brand in ("+brand+")";
        }
        try {  
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs=pst.executeQuery();      
            while(rs.next()){  
            	CateGory c =new CateGory();  
                c.setWsum(rs.getString("SALES_PRICE"));
                list.add(c);  
            }  
           } catch (Exception e) {  
            e.printStackTrace();  
        }finally {
        	closeConnection(conn);
		}
        return list;
    }
    /**
     * 最后4个，获取一周分母总数
     * @return
     */
    public static List<CateGory> getwinfo(String date,String ecType) {
    	List<CateGory> list=new ArrayList<CateGory>(); 
        //与数据库的连接
        Connection conn = getConnection();
        String sql = "select sum(SALES_PRICE) SALES_PRICE  from cloa_chushu where date='"+date+"' and CATEGORY='碳酸饮料'";
        if(StringUtils.isNotBlank(ecType)){
        	sql = "select sum(SALES_PRICE) SALES_PRICE  from cloa_chushu where date='"+date+"' and ec='"+ecType+"' and CATEGORY='碳酸饮料'";
        }
        try {  
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs=pst.executeQuery();      
            while(rs.next()){  
            	CateGory c =new CateGory();  
                c.setWsum(rs.getString("SALES_PRICE"));
                list.add(c);  
            }  
           } catch (Exception e) {  
            e.printStackTrace();  
        }finally {
        	closeConnection(conn);
		}
        return list;
    }
    public static void getKeleList(String date) throws Exception {
    	
    	List<CateGory> skuInfo = getSkuInfo(date);//第一个sheet页面数据
    	
    	//京东
    	List<CateGory> jdlist = new ArrayList<CateGory>(); 
    	List<CateGory> jdzs = getfenzi(date,"碳酸饮料","jd");
    	jdlist.addAll(jdzs);
        //碳酸饮料新增四个   ===开始
        //1：Coke TM 2：Pepsi TM 3：Coca Cola Total 4：Pepsi Total；
        String CokeTM = getCateGory("Coke TM");
        String PepsiTM = getCateGory("Pepsi TM");
        String CocaColaTotal = getCateGory("Coca Cola Total");
        String PepsiTotal = getCateGory("Pepsi Total");
        List<CateGory> getwinfo = getwinfo(date,CokeTM,"jd");
        List<CateGory> getwinfo2 = getwinfo(date,PepsiTM,"jd");
        List<CateGory> getwinfo3 = getwinfo(date,CocaColaTotal,"jd");
        List<CateGory> getwinfo4 = getwinfo(date,PepsiTotal,"jd");
    	CateGory cg =new CateGory();  
    	cg.setCategory("碳酸饮料");
    	cg.setBrand("Coke TM");
    	cg.setWsum(getwinfo.get(0).getWsum());
    	jdlist.add(cg);
        
    	CateGory cg2 =new CateGory();  
    	cg2.setCategory("碳酸饮料");
        cg2.setBrand("Pepsi TM");
        cg2.setWsum(getwinfo2.get(0).getWsum());
        jdlist.add(cg2);
        
    	CateGory cg3 =new CateGory();  
    	cg3.setCategory("碳酸饮料");
        cg3.setBrand("Coca Cola Total");
        cg3.setWsum(getwinfo3.get(0).getWsum());
        jdlist.add(cg3);
        
    	CateGory cg4 =new CateGory();  
    	cg4.setCategory("碳酸饮料");
        cg4.setBrand("Pepsi Total");
        cg4.setWsum(getwinfo4.get(0).getWsum());
        jdlist.add(cg4);
        
    	List<CateGory> jdgw = getfenzi(date,"果味饮料","jd");
    	jdlist.addAll(jdgw);
    	List<CateGory> jdbz = getfenzi(date,"包装饮用水","jd");
    	jdlist.addAll(jdbz);
    	List<CateGory> jdchunYuemfz = getchunYuem(date,"jd");//纯悦分子
    	jdlist.addAll(jdchunYuemfz);
    	
        Map wxinfo = getwxinfo(date,"jd");//jd分母
        double bao = (double) wxinfo.get("bao");
        double guo = (double) wxinfo.get("guo");
        double suan = (double) wxinfo.get("suan");
        
        List<CateGory> jdchunYueMufm = getchunYueMu("jd");//纯悦分母
        String wsum3 = jdchunYueMufm.get(0).getWsum();
        double w5 = Double.parseDouble(wsum3);
        
        //碳酸饮料新增四个周分母
        List<CateGory> getwinfo5 = getwinfo(date,"jd");
        String wsum2 = getwinfo5.get(0).getWsum();
        double w4 = Double.parseDouble(wsum2);
        
        if(jdlist.size()>0){
        	for(CateGory c:jdlist){
        	    if(c.getCategory().equals("纯悦")){
            		double wsum = Double.parseDouble(c.getWsum())/w5;
            		c.setWsums(wsum+"");
        		}else if(c.getBrand().equals("Coke TM") || c.getBrand().equals("Pepsi TM") || c.getBrand().equals("Coca Cola Total") || c.getBrand().equals("Pepsi Total")){
            		double wsum = Double.parseDouble(c.getWsum())/w4;
            		c.setWsums(wsum+"");
        		}else{
            		if(c.getCategory().equals("包装饮用水")){
                		double wsum = Double.parseDouble(c.getWsum())/bao;
                		c.setWsums(wsum+"");
            		}else if(c.getCategory().equals("果味饮料")){
                		double wsum = Double.parseDouble(c.getWsum())/guo;
                		c.setWsums(wsum+"");
            		}else if(c.getCategory().equals("碳酸饮料")){
                		double wsum = Double.parseDouble(c.getWsum())/suan;
                		c.setWsums(wsum+"");
            		}
        		}
        	}
        }
        
        //天猫
    	List<CateGory> tmlist = new ArrayList<CateGory>(); 
    	List<CateGory> tmzs = getfenzi(date,"碳酸饮料","tm");
    	tmlist.addAll(tmzs);
        //碳酸饮料新增四个   ===开始
        //1：Coke TM 2：Pepsi TM 3：Coca Cola Total 4：Pepsi Total；
        List<CateGory> getwinfotm1 = getwinfo(date,CokeTM,"tm");
        List<CateGory> getwinfotm2 = getwinfo(date,PepsiTM,"tm");
        List<CateGory> getwinfotm3 = getwinfo(date,CocaColaTotal,"tm");
        List<CateGory> getwinfotm4= getwinfo(date,PepsiTotal,"tm");
    	CateGory cgtm =new CateGory();  
    	cgtm.setCategory("碳酸饮料");
    	cgtm.setBrand("Coke TM");
    	cgtm.setWsum(getwinfotm1.get(0).getWsum());
    	tmlist.add(cgtm);
        
    	CateGory cgtm2 =new CateGory();  
    	cgtm2.setCategory("碳酸饮料");
    	cgtm2.setBrand("Pepsi TM");
    	cgtm2.setWsum(getwinfotm2.get(0).getWsum());
        tmlist.add(cgtm2);
        
    	CateGory cgtm3 =new CateGory();  
    	cgtm3.setCategory("碳酸饮料");
    	cgtm3.setBrand("Coca Cola Total");
    	cgtm3.setWsum(getwinfotm3.get(0).getWsum());
        tmlist.add(cgtm3);
        
    	CateGory cgtm4 =new CateGory();  
    	cgtm4.setCategory("碳酸饮料");
    	cgtm4.setBrand("Pepsi Total");
    	cgtm4.setWsum(getwinfotm4.get(0).getWsum());
        tmlist.add(cgtm4);
        
    	List<CateGory> tmgw = getfenzi(date,"果味饮料","tm");
    	tmlist.addAll(tmgw);
    	List<CateGory> tmbz = getfenzi(date,"包装饮用水","tm");
    	tmlist.addAll(tmbz);
    	List<CateGory> tmchunYuemfz = getchunYuem(date,"tm");//纯悦分子
    	tmlist.addAll(tmchunYuemfz);
    	
        Map wxinfotm = getwxinfo(date,"tm");//jd分母
        double tmbao = (double) wxinfotm.get("bao");
        double tmguo = (double) wxinfotm.get("guo");
        double tmsuan = (double) wxinfotm.get("suan");
        
        List<CateGory> tmchunYueMufm = getchunYueMu("tm");//纯悦分母
        String wsumtm = tmchunYueMufm.get(0).getWsum();
        double tm = Double.parseDouble(wsumtm);
        
        //碳酸饮料新增四个周分母
        List<CateGory> getwinfotm5 = getwinfo(date,"tm");
        String wsumtm2 = getwinfotm5.get(0).getWsum();
        double wtm4 = Double.parseDouble(wsumtm2);
        
        if(tmlist.size()>0){
        	for(CateGory c:tmlist){
        	    if(c.getCategory().equals("纯悦")){
            		double wsum = Double.parseDouble(c.getWsum())/tm;
            		c.setWsums(wsum+"");
        		}else if(c.getBrand().equals("Coke TM") || c.getBrand().equals("Pepsi TM") || c.getBrand().equals("Coca Cola Total") || c.getBrand().equals("Pepsi Total")){
            		double wsum = Double.parseDouble(c.getWsum())/wtm4;
            		c.setWsums(wsum+"");
        		}else{
            		if(c.getCategory().equals("包装饮用水")){
                		double wsum = Double.parseDouble(c.getWsum())/tmbao;
                		c.setWsums(wsum+"");
            		}else if(c.getCategory().equals("果味饮料")){
                		double wsum = Double.parseDouble(c.getWsum())/tmguo;
                		c.setWsums(wsum+"");
            		}else if(c.getCategory().equals("碳酸饮料")){
                		double wsum = Double.parseDouble(c.getWsum())/tmsuan;
                		c.setWsums(wsum+"");
            		}
        		}
        	}
        }
        
        
        //京东和天猫
    	List<CateGory> tm_jdlist = new ArrayList<CateGory>(); 
    	List<CateGory> tm_jdzs = getfenzi(date,"碳酸饮料","");
    	tm_jdlist.addAll(tm_jdzs);
        //碳酸饮料新增四个   ===开始
        //1：Coke TM 2：Pepsi TM 3：Coca Cola Total 4：Pepsi Total；
        List<CateGory> getwinfotmjd1 = getwinfo(date,CokeTM,"");
        List<CateGory> getwinfotmjd2 = getwinfo(date,PepsiTM,"");
        List<CateGory> getwinfotmjd3 = getwinfo(date,CocaColaTotal,"");
        List<CateGory> getwinfotmjd4 = getwinfo(date,PepsiTotal,"");
    	CateGory cgtmjd1 =new CateGory();  
    	cgtmjd1.setCategory("碳酸饮料");
    	cgtmjd1.setBrand("Coke TM");
    	cgtmjd1.setWsum(getwinfotmjd1.get(0).getWsum());
    	tm_jdlist.add(cgtmjd1);
        
    	CateGory cgtmjd2 =new CateGory();  
    	cgtmjd2.setCategory("碳酸饮料");
    	cgtmjd2.setBrand("Pepsi TM");
    	cgtmjd2.setWsum(getwinfotmjd2.get(0).getWsum());
    	tm_jdlist.add(cgtmjd2);
        
    	CateGory cgtmjd3 =new CateGory();  
    	cgtmjd3.setCategory("碳酸饮料");
    	cgtmjd3.setBrand("Coca Cola Total");
    	cgtmjd3.setWsum(getwinfotmjd3.get(0).getWsum());
    	tm_jdlist.add(cgtmjd3);
        
    	CateGory cgtmjd4 =new CateGory();  
    	cgtmjd4.setCategory("碳酸饮料");
    	cgtmjd4.setBrand("Pepsi Total");
    	cgtmjd4.setWsum(getwinfotmjd4.get(0).getWsum());
    	tm_jdlist.add(cgtmjd4);
    	
    	List<CateGory> tm_jdgw = getfenzi(date,"果味饮料","");
    	tm_jdlist.addAll(tm_jdgw);
    	List<CateGory> tm_jdbz = getfenzi(date,"包装饮用水","");
    	tm_jdlist.addAll(tm_jdbz);
    	List<CateGory> tm_jdchunYuemfz = getchunYuem(date,"");//纯悦分子
    	tm_jdlist.addAll(tm_jdchunYuemfz);
    	
        Map wxinfotm_jd = getwxinfo(date,"");//jd分母
        double tm_jdbao = (double) wxinfotm_jd.get("bao");
        double tm_jdguo = (double) wxinfotm_jd.get("guo");
        double tm_jdsuan = (double) wxinfotm_jd.get("suan");
        
        List<CateGory> tm_jdchunYueMufm = getchunYueMu("");//纯悦分母
        String wsumtm_jd = tm_jdchunYueMufm.get(0).getWsum();
        double tm_jd = Double.parseDouble(wsumtm_jd);
        
        //碳酸饮料新增四个周分母
        List<CateGory> getwinfotmjd = getwinfo(date,"");
        String wsumtmjd = getwinfotmjd.get(0).getWsum();
        double wtmjd = Double.parseDouble(wsumtmjd);
        
        if(tm_jdlist.size()>0){
        	for(CateGory c:tm_jdlist){
        	    if(c.getCategory().equals("纯悦")){
            		double wsum = Double.parseDouble(c.getWsum())/tm_jd;
            		c.setWsums(wsum+"");
        		}else if(c.getBrand().equals("Coke TM") || c.getBrand().equals("Pepsi TM") || c.getBrand().equals("Coca Cola Total") || c.getBrand().equals("Pepsi Total")){
            		double wsum = Double.parseDouble(c.getWsum())/wtmjd;
            		c.setWsums(wsum+"");
        		}else{
            		if(c.getCategory().equals("包装饮用水")){
                		double wsum = Double.parseDouble(c.getWsum())/tm_jdbao;
                		c.setWsums(wsum+"");
            		}else if(c.getCategory().equals("果味饮料")){
                		double wsum = Double.parseDouble(c.getWsum())/tm_jdguo;
                		c.setWsums(wsum+"");
            		}else if(c.getCategory().equals("碳酸饮料")){
                		double wsum = Double.parseDouble(c.getWsum())/tm_jdsuan;
                		c.setWsums(wsum+"");
            		}
        		}
        	}
        }
        ExcelUtil.exportUserExcel(jdlist, tmlist, tm_jdlist, skuInfo);
        System.out.println("完成");
    	
    }
    /**
     * ChunYue 分母
     * @return
     */
    public static List<CateGory> getchunYueMu(String ecType) {
    	List<CateGory> list=new ArrayList<CateGory>(); 
        //与数据库的连接
        Connection conn = getConnection();
        String sql ="select sum(SALES_PRICE) SALES_PRICE from cloa_chushu where CATEGORY='包装饮用水'";
        //jd或天猫 分类
        if(StringUtils.isNotBlank(ecType)){
        	sql = "select sum(SALES_PRICE) SALES_PRICE from cloa_chushu where ec='"+ecType+"' and CATEGORY='包装饮用水'";
        }
        try {  
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs=pst.executeQuery();      
            while(rs.next()){  
            	CateGory c =new CateGory();  
                c.setWsum(rs.getString("SALES_PRICE"));
                list.add(c);  
            }  
           } catch (Exception e) {  
            e.printStackTrace();  
        }finally {
        	closeConnection(conn);
		}
        return list;
    }
    /**
     * ChunYue分子
     * @return
     */
    public static List<CateGory> getchunYuem(String date,String ecType) {
    	List<CateGory> list=new ArrayList<CateGory>(); 
        //与数据库的连接
        Connection conn = getConnection();
        String sql ="select sum(SALES_PRICE) SALES_PRICE from cloa_chushu where En_brand = 'Chun Yue' and date='"+date+"'";
        //jd或天猫 分类
        if(StringUtils.isNotBlank(ecType)){
        	sql = "select sum(SALES_PRICE) SALES_PRICE from cloa_chushu where En_brand = 'Chun Yue' and ec='"+ecType+"' and date='"+date+"'";
        }
        try {  
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs=pst.executeQuery();      
            while(rs.next()){  
            	CateGory c =new CateGory();  
            	c.setBrand("Chun Yue");
            	c.setCategory("纯悦");
            	if(StringUtils.isNotBlank(rs.getString("SALES_PRICE"))){
            		c.setWsum(rs.getString("SALES_PRICE"));
            	}else{
            		c.setWsum("0");
            	}
                list.add(c);  
            }  
           } catch (Exception e) {  
            e.printStackTrace();  
        }finally {
        	closeConnection(conn);
		}
        return list;
    }
    //分子
    public static List<CateGory> getfenzi(String date,String cateGory,String ec){
        //与数据库的连接
        Connection conn = getConnection();
        List<CateGory> list=new ArrayList<CateGory>(); 
        String sqlsuan = "select top 10 CATEGORY, En_brand,sum(SALES_PRICE) SALES_PRICE  from cloa_chushu where date='"+date+"' and CATEGORY='"+cateGory+"' GROUP BY CATEGORY, En_brand ORDER BY CATEGORY,SALES_PRICE DESC";
        if(StringUtils.isNotBlank(ec)){
        	sqlsuan = "select top 10 CATEGORY, En_brand,sum(SALES_PRICE) SALES_PRICE  from cloa_chushu where date='"+date+"' and ec='"+ec+"' and CATEGORY='"+cateGory+"' GROUP BY CATEGORY, En_brand ORDER BY CATEGORY,SALES_PRICE DESC";
        }
        try {  
            PreparedStatement pst=conn.prepareStatement(sqlsuan);  
            ResultSet rs=pst.executeQuery();      
            while(rs.next()){  
            	CateGory c =new CateGory();  
                c.setCategory(rs.getString("CATEGORY"));
                c.setBrand(rs.getString("En_brand"));
                c.setWsum(rs.getString("SALES_PRICE"));
                list.add(c);  
            }  
           } catch (Exception e) {  
            e.printStackTrace();  
        }finally {
        	closeConnection(conn);
		}
        return list;
    }
    /**
     * 第二个sheet页分母
     * @return
     */
    public static Map getwxinfo(String date,String ecType) {
    	List<CateGory> list=new ArrayList<CateGory>(); 
        //与数据库的连接
        Connection conn = getConnection();
        String sql = "select CATEGORY, sum(SALES_PRICE) SALES_PRICE from cloa_chushu where date='"+date+"' GROUP BY  CATEGORY";
        if(StringUtils.isNotBlank(ecType)){
        	sql = "select CATEGORY, sum(SALES_PRICE) SALES_PRICE from cloa_chushu where date='"+date+"' and ec='"+ecType+"' GROUP BY  CATEGORY";
        }
        try {  
            PreparedStatement pst=conn.prepareStatement(sql);  
            ResultSet rs=pst.executeQuery();      
            while(rs.next()){  
            	CateGory c =new CateGory();  
                c.setCategory(rs.getString("CATEGORY"));
                c.setWsum(rs.getString("SALES_PRICE"));
                list.add(c);  
            }  
           } catch (Exception e) {  
            e.printStackTrace();  
        }finally {
        	closeConnection(conn);
		}
        double bao = 0;
        double guo = 0;
        double suan = 0;
        if(list.size()>0){
        	for(CateGory c:list){
        		if(StringUtils.isNotBlank(c.getCategory())){
            		if(c.getCategory().equals("包装饮用水")){
            			bao += Double.parseDouble(c.getWsum());
            		}else if(c.getCategory().equals("果味饮料")){
            			guo += Double.parseDouble(c.getWsum());
            		}else if(c.getCategory().equals("碳酸饮料")){
            			suan += Double.parseDouble(c.getWsum());
            		}
        		}
        	}
        }
        Map<String,Double> m = new HashMap();
        //System.out.println(bao+","+guo+","+suan);
        m.put("bao", bao);
        m.put("guo", guo);
        m.put("suan", suan);
        return m;
    }
    //第一个sheet页面赋值功能
    public static List<CateGory> getSkuInfo(String date) throws Exception{
  
    	//获取中英文对照表信息
    	List<CateGory> skuList = getSkudzb();
    	if(skuList.size()>0){
    		for(CateGory c:skuList){
    			//获取中文类型
    			String brand = c.getBrand();
    			//周
    			c.setWsum(getSkuwm("jd",brand,date));
    			c.setWsums(getSkuwm("tm",brand,date));
    		}
    	}else{
    		System.out.println("对照表没数据");
    	}
    	
    	CateGory cg = new CateGory();
    	cg.setCategory("Top SKU Total");
    	cg.setBrand("Top SKU Total");
    	double a = 0;
    	double b = 0;
		for(CateGory c:skuList){
			a += Double.parseDouble(c.getWsum());
			b += Double.parseDouble(c.getWsums());
		}
		cg.setWsum(a+"");
		cg.setWsums(b+"");
		skuList.add(cg);
    	return skuList;
    }
    /**
     * sku 中英文对照表。
     */
    public static List<CateGory> getSkudzb() {
    	List<CateGory> list=new ArrayList<CateGory>(); 
        //与数据库的连接
        Connection conn = getConnection();
        String sql = "select * from Cola_Sku_En_Ch";
        try {  
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs=pst.executeQuery();      
            while(rs.next()){  
            	CateGory c =new CateGory();  
                c.setCategory(rs.getString("中文sku"));
                c.setBrand(rs.getString("英文sku"));
                list.add(c);  
            }  
           } catch (Exception e) {  
            e.printStackTrace();  
        }finally {
        	closeConnection(conn);
		}
        return list;
    }
    /**
     * sku 周和月 天猫和京东数据
     */
    public static String getSkuwm(String ecType,String sku,String date) {
    	List<CateGory> list=new ArrayList<CateGory>(); 
        //与数据库的连接
        Connection conn = getConnection();
        String sql = "select sum(sales) sales from COLA_SKU_SALES_chushu where ec='"+ecType+"' and sku='"+sku+"' and date='"+date+"'";
        try {  
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs=pst.executeQuery();      
            while(rs.next()){  
            	CateGory c =new CateGory();  
                c.setWsum(rs.getString("sales"));
                list.add(c);  
            }  
           } catch (Exception e) {  
            e.printStackTrace();  
        }finally {
        	closeConnection(conn);
		}
		String wsum = list.get(0).getWsum();
		if(StringUtils.isBlank(wsum)){
			wsum="0";
		}
        return wsum;
    }
    public static void main(String[] args) throws Exception {
    	getKeleList("2019-06-09");//生成excel
    }
}
