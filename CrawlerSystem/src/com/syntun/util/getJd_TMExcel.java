package com.syntun.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
public class getJd_TMExcel {
    private final static Logger LOGGER = LoggerFactory.getLogger(getJd_TMExcel.class);

    private static String DRIVER = "com.microsoft.jdbc.sqlserver.SQLServerDriver";
    private static String URL = "jdbc:sqlserver://192.168.0.16:1433;DatabaseName=skyscope";
    private static String USERNAME = "liang";
    private static String PASSWORD = "liangjianqing";
    private static final String SQL = "SELECT * FROM ";// 数据库操作
    
    static List<String> list = new ArrayList<>();
    static{
    	list.add("1,2018-12-31,2019-01-06,1");
    	list.add("1,2019-01-07,2019-01-13,2");
    	list.add("1,2019-01-14,2019-01-20,3");
    	list.add("1,2019-01-21,2019-01-27,4");
    	
    	list.add("2,2019-01-28,2019-02-03,1");
    	list.add("2,2019-02-04,2019-02-10,2");
    	list.add("2,2019-02-11,2019-02-17,3");
    	list.add("2,2019-02-18,2019-02-24,4");
    	
    	list.add("3,2019-02-25,2019-03-03,1");
    	list.add("3,2019-03-04,2019-03-10,2");
    	list.add("3,2019-03-11,2019-03-17,3");
    	list.add("3,2019-03-18,2019-03-24,4");
    	list.add("3,2019-03-25,2019-03-31,5");
    	
    	list.add("4,2019-04-01,2019-04-07,1");
    	list.add("4,2019-04-08,2019-04-14,2");
    	list.add("4,2019-04-15,2019-04-21,3");
    	list.add("4,2019-04-22,2019-04-28,4");
    	
    	list.add("5,2019-04-29,2019-05-05,1");
    	list.add("5,2019-05-06,2019-05-12,2");
    	list.add("5,2019-05-13,2019-05-19,3");
    	list.add("5,2019-05-20,2019-05-26,4");
    	
    	list.add("6,2019-05-27,2019-06-02,1");
    	list.add("6,2019-06-03,2019-06-09,2");
    	list.add("6,2019-06-10,2019-06-16,3");
    	list.add("6,2019-06-17,2019-06-23,4");
    	list.add("6,2019-06-24,2019-06-30,5");
    	
    	list.add("7,2019-07-01,2019-07-07,1");
    	list.add("7,2019-07-08,2019-07-14,2");
    	list.add("7,2019-07-15,2019-07-21,3");
    	list.add("7,2019-07-22,2019-07-28,4");
    	
    	list.add("8,2019-07-29,2019-08-04,1");
    	list.add("8,2019-08-05,2019-08-11,2");
    	list.add("8,2019-08-12,2019-08-18,3");
    	list.add("8,2019-08-19,2019-08-25,4");
    	
    	list.add("9,2019-08-26,2019-09-01,1");
    	list.add("9,2019-09-02,2019-09-08,2");
    	list.add("9,2019-09-09,2019-09-15,3");
    	list.add("9,2019-09-16,2019-09-22,4");
    	list.add("9,2019-09-23,2019-09-29,5");
    	
    	list.add("10,2019-09-30,2019-10-06,1");
    	list.add("10,2019-10-07,2019-10-13,2");
    	list.add("10,2019-10-14,2019-10-20,3");
    	list.add("10,2019-10-21,2019-10-27,4");
    	
    	list.add("11,2019-10-28,2019-11-03,1");
    	list.add("11,2019-11-04,2019-11-10,2");
    	list.add("11,2019-11-11,2019-11-17,3");
    	list.add("11,2019-11-18,2019-11-24,4");
    	
    	list.add("12,2019-11-25,2019-12-01,1");
    	list.add("12,2019-12-02,2019-12-08,2");
    	list.add("12,2019-12-09,2019-12-15,3");
    	list.add("12,2019-12-16,2019-12-22,4");
    	list.add("12,2019-12-23,2019-12-29,5");
    	
    	list.add("1,2019-12-30,2020-01-05,1");
    }

    
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
    /**
     * 获取周，总价格信息。
     * @return
     */
    public static Map getwxinfo(String date,String ecType) {
    	List<CateGory> list=new ArrayList<CateGory>(); 
        //与数据库的连接
        Connection conn = getConnection();
        String sql = "select CATEGORY, sum(SALES_PRICE) SALES_PRICE from cloa_chushu where week='"+date+"' GROUP BY  CATEGORY";
        if(StringUtils.isNotBlank(ecType)){
        	sql = "select CATEGORY, sum(SALES_PRICE) SALES_PRICE from cloa_chushu where week='"+date+"' and ec='"+ecType+"' GROUP BY  CATEGORY";
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
    /**
     * 获取月，总价格信息。
     * @return
     */
    public static Map getwxinfos(String date,String ecType) {
    	List<CateGory> list=new ArrayList<CateGory>(); 
        //与数据库的连接
        Connection conn = getConnection();
        String sql = "select CATEGORY, sum(SALES_PRICE) SALES_PRICE from cloa_chushu where week in ("+date+") GROUP BY  CATEGORY";
        if(StringUtils.isNotBlank(ecType)){
        	sql = "select CATEGORY, sum(SALES_PRICE) SALES_PRICE from cloa_chushu where ec='"+ecType+"' and week in ("+date+") GROUP BY  CATEGORY";
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
    /**
     * 获取年，总价格信息。
     * @return
     */
    public static Map getYearinfo(String date,String ecType) {
    	List<CateGory> list=new ArrayList<CateGory>(); 
        //与数据库的连接
        Connection conn = getConnection();
        String sql = "select CATEGORY, sum(SALES_PRICE) SALES_PRICE from cloa_chushu where date BETWEEN '2019-01-01' AND '"+date+"' GROUP BY  CATEGORY";
        if(StringUtils.isNotBlank(ecType)){
        	sql = "select CATEGORY, sum(SALES_PRICE) SALES_PRICE from cloa_chushu where ec='"+ecType+"' and date BETWEEN '2019-01-01' AND '"+date+"' GROUP BY  CATEGORY";
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
        Map<String,Double> m = new HashMap<String,Double>();
        //System.out.println(bao+","+guo+","+suan);
        m.put("bao", bao);
        m.put("guo", guo);
        m.put("suan", suan);
        return m;
    }
    /**
     * 获取top10品牌价格
     * @return
     * @throws Exception 
     */
    public static void getColumnComments(String date,HttpServletRequest request, HttpServletResponse response) throws Exception {
    	
    	long startTime = System.currentTimeMillis();
    	
    	String weekDay = getWeekDay(date);
    	List<CateGory> list=new ArrayList<CateGory>(); 
        //与数据库的连接
        Connection conn = getConnection();

        //1碳酸饮料
        String sqlsuan = "select  top 10 CATEGORY, En_brand,sum(SALES_PRICE) SALES_PRICE  from cloa_chushu where week='"+weekDay+"' and CATEGORY='碳酸饮料'   GROUP BY  CATEGORY, En_brand  ORDER BY CATEGORY,SALES_PRICE DESC";
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
        }
        //碳酸饮料新增四个   ===开始
        //1：Coke TM 2：Pepsi TM 3：Coca Cola Total 4：Pepsi Total；
        String CokeTM = getCateGory("Coke TM");
        String PepsiTM = getCateGory("Pepsi TM");
/*        String CocaColaTotal = getCateGory("Coca Cola Total");
        String PepsiTotal = getCateGory("Pepsi Total");*/
        String CocaColaTotal = "可口可乐";
        String PepsiTotal = "百事可乐";
        List<CateGory> getwinfo = getwinfo(weekDay,CokeTM,"");
        List<CateGory> getwinfo2 = getwinfo(weekDay,PepsiTM,"");
        List<CateGory> getwinfo3 = getwinfo(weekDay,CocaColaTotal,"");
        List<CateGory> getwinfo4 = getwinfo(weekDay,PepsiTotal,"");
    	CateGory cg =new CateGory();  
    	cg.setCategory("碳酸饮料");
    	cg.setBrand("Coke TM");
    	cg.setWsum(getwinfo.get(0).getWsum());
        list.add(cg);
        
    	CateGory cg2 =new CateGory();  
    	cg2.setCategory("碳酸饮料");
        cg2.setBrand("Pepsi TM");
        cg2.setWsum(getwinfo2.get(0).getWsum());
        list.add(cg2);
        
    	CateGory cg3 =new CateGory();  
    	cg3.setCategory("碳酸饮料");
        cg3.setBrand("Coca Cola Total");
        cg3.setWsum(getwinfo3.get(0).getWsum());
        list.add(cg3);
        
    	CateGory cg4 =new CateGory();  
    	cg4.setCategory("碳酸饮料");
        cg4.setBrand("Pepsi Total");
        cg4.setWsum(getwinfo4.get(0).getWsum());
        list.add(cg4);
        //碳酸饮料新增四个   =分子==结束
        
        //1果味饮料
        String sqlguo = "select  top 10 CATEGORY, En_brand,sum(SALES_PRICE) SALES_PRICE  from cloa_chushu where week='"+weekDay+"' and CATEGORY='果味饮料'   GROUP BY  CATEGORY, En_brand  ORDER BY CATEGORY,SALES_PRICE DESC";
        try {  
            PreparedStatement pst=conn.prepareStatement(sqlguo);  
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
        }
        //1包装饮用水
        String sqlbao = "select top 10 CATEGORY,En_brand,sum(SALES_PRICE) SALES_PRICE  from cloa_chushu where week='"+weekDay+"' and CATEGORY='包装饮用水' GROUP BY  CATEGORY, En_brand  ORDER BY CATEGORY,SALES_PRICE DESC";
        try {  
            PreparedStatement pst=conn.prepareStatement(sqlbao);  
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
        
        //Chun Yue 分子
        List<CateGory> getchunYuem = getchunYuem("'"+weekDay+"'","");
    	CateGory cg5 =new CateGory();  
    	cg5.setCategory("包装饮用水");
        cg5.setBrand("Chun Yue");
        cg5.setWsum(getchunYuem.get(0).getWsum());
        list.add(cg5);
        
        
        //获取周分母
        Map wxinfo = getwxinfo(weekDay,"");
        double bao = (double) wxinfo.get("bao");
        double guo = (double) wxinfo.get("guo");
        double suan = (double) wxinfo.get("suan");
        //碳酸饮料新增四个周分母
        List<CateGory> getwinfo5 = getwinfo(weekDay,"");
        String wsum2 = getwinfo5.get(0).getWsum();
        double w4 = Double.parseDouble(wsum2);
        //Chun Yue 分母
        List<CateGory> getchunYueMu = getchunYueMu("");
        String wsum3 = getchunYueMu.get(0).getWsum();
        double w5 = Double.parseDouble(wsum3);
        if(list.size()>0){
        	for(CateGory c:list){
        		if(c.getBrand().equals("Coke TM") || c.getBrand().equals("Pepsi TM") || c.getBrand().equals("Coca Cola Total") || c.getBrand().equals("Pepsi Total")){
            		double wsum = Double.parseDouble(c.getWsum())/w4;
            		c.setWsums(wsum+"");
        		}else if(c.getBrand().equals("Chun Yue")){
            		double wsum = Double.parseDouble(c.getWsum())/w5;
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
        //生成月总数==============================
    	List<String> week = getWeek(date);
    	StringBuilder sb = new StringBuilder();
    	for(int i=0;i<week.size();i++){
    		if(i==0){
    			sb.append("'");
    			sb.append(week.get(i));
    			sb.append("'");
    		}else{
    			sb.append(",");
    			sb.append("'");
    			sb.append(week.get(i));
    			sb.append("'");
    		}
    	}
    	Map getwxinfos = getwxinfos(sb.toString(),"");//月分母
        double mbao = (double) getwxinfos.get("bao");
        double mguo = (double) getwxinfos.get("guo");
        double msuan = (double) getwxinfos.get("suan");
        
        List<CateGory> getwinfo6 = getminfo(sb.toString(),"");//碳酸饮料新增四个月分母
        String msum = getwinfo6.get(0).getWsum();
        double m4 = Double.parseDouble(msum);
        if(list.size()>0){
        	for(CateGory c:list){
        		if(c.getBrand().equals("Coke TM")){
            		//获取月分子
        			List<CateGory> getmzinfo = getmzinfo(sb.toString(),CokeTM,"");
             		double d = Double.parseDouble(getmzinfo.get(0).getWsum());
            		c.setMsum(d+"");
            		c.setMsums(d/m4+"");
        		}else if(c.getBrand().equals("Pepsi TM")){
            		//获取月分子
        			List<CateGory> getmzinfo = getmzinfo(sb.toString(),PepsiTM,"");
             		double d = Double.parseDouble(getmzinfo.get(0).getWsum());
            		c.setMsum(d+"");
            		c.setMsums(d/m4+"");
        		}else if(c.getBrand().equals("Coca Cola Total")){
            		//获取月分子
        			List<CateGory> getmzinfo = getmzinfo(sb.toString(),CocaColaTotal,"");
             		double d = Double.parseDouble(getmzinfo.get(0).getWsum());
            		c.setMsum(d+"");
            		c.setMsums(d/m4+"");
        		}else if(c.getBrand().equals("Pepsi Total")){
            		//获取月分子
        			List<CateGory> getmzinfo = getmzinfo(sb.toString(),PepsiTotal,"");
             		double d = Double.parseDouble(getmzinfo.get(0).getWsum());
            		c.setMsum(d+"");
            		c.setMsums(d/m4+"");
        		}else if(c.getBrand().equals("Chun Yue")){
        			List<CateGory> getchunYuem2 = getchunYuem(sb.toString(),"");
             		double d = Double.parseDouble(getchunYuem2.get(0).getWsum());
            		c.setMsum(d+"");
            		c.setMsums(d/w5+"");
        		}else{
            		List<CateGory> getmxinfo = getmxinfo(sb.toString(),c.getCategory(),c.getBrand(),"");//月分子
            		double d = Double.parseDouble(getmxinfo.get(0).getWsum());
            		c.setMsum(d+"");
            		if(c.getCategory().equals("包装饮用水")){
            			c.setMsums(d/mbao+"");
            		}else if(c.getCategory().equals("果味饮料")){
            			c.setMsums(d/mguo+"");
            		}else if(c.getCategory().equals("碳酸饮料")){
            			c.setMsums(d/msuan+"");
            		}
        		}
        	}
        }
        //算年
        //String yesrday = getYesrday(date);//根据用户日期算年，取上周最后一天日期。之前版本
        String yesrday = getYesrday1(date);//根据用户日期算年，取本周最后一天。
        Map yearinfo = getYearinfo(yesrday,"");//年分母
        double ybao = (double) yearinfo.get("bao");
        double yguo = (double) yearinfo.get("guo");
        double ysuan = (double) yearinfo.get("suan");
        //碳酸饮料新增四个年分母
        List<CateGory> yinfo = getYinfo(yesrday,"");//4个年分母
        String ysum = yinfo.get(0).getWsum();
        double ysums = Double.parseDouble(ysum);
        if(list.size()>0){
        	for(CateGory c:list){
        		if(c.getBrand().equals("Coke TM")){
            		//获取年分子
        			List<CateGory> getmzinfo = getYzinfo(yesrday,CokeTM,"");
             		double d = Double.parseDouble(getmzinfo.get(0).getWsum());
            		c.setYsum(d+"");
            		c.setYsums(d/ysums+"");
        		}else if(c.getBrand().equals("Pepsi TM")){
            		//获取年分子
        			List<CateGory> getmzinfo = getYzinfo(yesrday,PepsiTM,"");
             		double d = Double.parseDouble(getmzinfo.get(0).getWsum());
            		c.setYsum(d+"");
            		c.setYsums(d/ysums+"");
        		}else if(c.getBrand().equals("Coca Cola Total")){
            		//获取年分子
        			List<CateGory> getmzinfo = getYzinfo(yesrday,CocaColaTotal,"");
             		double d = Double.parseDouble(getmzinfo.get(0).getWsum());
            		c.setYsum(d+"");
            		c.setYsums(d/ysums+"");
        		}else if(c.getBrand().equals("Pepsi Total")){
            		//获取年分子
        			List<CateGory> getmzinfo = getYzinfo(yesrday,PepsiTotal,"");
             		double d = Double.parseDouble(getmzinfo.get(0).getWsum());
            		c.setYsum(d+"");
            		c.setYsums(d/ysums+"");
        		}else if(c.getBrand().equals("Chun Yue")){
        			List<CateGory> getchunYuem2 = getchunYueyzi(yesrday,"");//年分子
             		double d = Double.parseDouble(getchunYuem2.get(0).getWsum());
            		c.setYsum(d+"");
            		c.setYsums(d/w5+"");
        		}else{
            		List<CateGory> getmxinfo = getyerarinfo(yesrday,c.getCategory(),c.getBrand(),"");//年分子
            		double d = Double.parseDouble(getmxinfo.get(0).getWsum());
            		c.setYsum(d+"");
            		if(c.getCategory().equals("包装饮用水")){
            			c.setYsums(d/ybao+"");
            		}else if(c.getCategory().equals("果味饮料")){
            			c.setYsums(d/yguo+"");
            		}else if(c.getCategory().equals("碳酸饮料")){
            			c.setYsums(d/ysuan+"");
            		}
        		}
        	}
        }
        
        //生成excel  list集合：jd和天猫的数据
        List<CateGory> jdInfo = getJD_tmInfo(date,"jd");
        List<CateGory> tmInfo = getJD_tmInfo(date,"tm");
        List<CateGory> skuInfo = getSkuInfo(date);//第一个sheet页面数据
        String excelName = date+"-"+yesrday;
        JD_TXExcelUtil.exportUserExcel(jdInfo,tmInfo,list,skuInfo,request,response,excelName);
        long endTime = System.currentTimeMillis();
        System.out.println("四个生成完成耗时:"+ (endTime-startTime)+"ms");
    }
    /**
     * ChunYue 周和月分子
     * @return
     */
    public static List<CateGory> getchunYuem(String date,String ecType) {
    	List<CateGory> list=new ArrayList<CateGory>(); 
        //与数据库的连接
        Connection conn = getConnection();
        String sql ="select sum(SALES_PRICE) SALES_PRICE from cloa_chushu where En_brand = 'Chun Yue' and week in ("+date+")";
        //jd或天猫 分类
        if(StringUtils.isNotBlank(ecType)){
        	sql = "select sum(SALES_PRICE) SALES_PRICE from cloa_chushu where En_brand = 'Chun Yue' and ec='"+ecType+"' and week in ("+date+")";
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
     * ChunYue 年分子
     * @return
     */
    public static List<CateGory> getchunYueyzi(String date,String ecType) {
    	List<CateGory> list=new ArrayList<CateGory>();
        //与数据库的连接
        Connection conn = getConnection();
        String sql ="select sum(SALES_PRICE) SALES_PRICE from cloa_chushu where En_brand = 'Chun Yue' and date BETWEEN '2019-01-01' AND '"+date+"'";
        //jd或天猫 分类
        if(StringUtils.isNotBlank(ecType)){
        	sql = "select sum(SALES_PRICE) SALES_PRICE from cloa_chushu where En_brand = 'Chun Yue' and ec='"+ecType+"' and date BETWEEN '2019-01-01' AND '"+date+"'";
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
     * ChunYue 分母都有一样
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
     * 最后4个，获取一周分子总数
     * @return
     */
    public static List<CateGory> getwinfo(String date,String brand,String ecType) {
    	List<CateGory> list=new ArrayList<CateGory>(); 
        //与数据库的连接
        Connection conn = getConnection();
        String sql = "";
        if(brand.equals("可口可乐") || brand.equals("百事可乐")){
        	sql = "select sum(SALES_PRICE) SALES_PRICE  from cloa_chushu where week='"+date+"' and CATEGORY='碳酸饮料' and  BRAND ='"+brand+"'";
        }else{
        	sql = "select sum(SALES_PRICE) SALES_PRICE  from cloa_chushu where week='"+date+"' and CATEGORY='碳酸饮料' and  En_brand in ("+brand+")";
        }
        
        //jd或天猫 分类
        if(StringUtils.isNotBlank(ecType)){
        	if(brand.equals("可口可乐") || brand.equals("百事可乐")){
        		sql = "select sum(SALES_PRICE) SALES_PRICE  from cloa_chushu where week='"+date+"' and ec='"+ecType+"' and CATEGORY='碳酸饮料' and  BRAND ='"+brand+"'";
        	}else{
        		sql = "select sum(SALES_PRICE) SALES_PRICE  from cloa_chushu where week='"+date+"' and ec='"+ecType+"' and CATEGORY='碳酸饮料' and  En_brand in ("+brand+")";
        	}
        	
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
        String sql = "select sum(SALES_PRICE) SALES_PRICE  from cloa_chushu where week='"+date+"' and CATEGORY='碳酸饮料'";
        if(StringUtils.isNotBlank(ecType)){
        	sql = "select sum(SALES_PRICE) SALES_PRICE  from cloa_chushu where week='"+date+"' and ec='"+ecType+"' and CATEGORY='碳酸饮料'";
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
     * 最后4个，获取月分子总数
     * @return
     */
    public static List<CateGory> getmzinfo(String date,String brand,String ecType) {
    	List<CateGory> list=new ArrayList<CateGory>(); 
        //与数据库的连接
        Connection conn = getConnection();
        String sql = "";
        if(brand.equals("可口可乐") || brand.equals("百事可乐")){
        	sql = "select sum(SALES_PRICE) SALES_PRICE  from cloa_chushu where week in ("+date+") and CATEGORY='碳酸饮料' and  BRAND ='"+brand+"'";
        }else{
        	sql = "select sum(SALES_PRICE) SALES_PRICE  from cloa_chushu where week in ("+date+") and CATEGORY='碳酸饮料' and  En_brand in ("+brand+")";
        }
        
        if(StringUtils.isNotBlank(ecType)){
        	if(brand.equals("可口可乐") || brand.equals("百事可乐")){
        		sql = "select sum(SALES_PRICE) SALES_PRICE  from cloa_chushu where CATEGORY='碳酸饮料' and ec='"+ecType+"' and BRAND ='"+brand+"' and week in ("+date+")";
        	}else{
        		sql = "select sum(SALES_PRICE) SALES_PRICE  from cloa_chushu where CATEGORY='碳酸饮料' and ec='"+ecType+"' and En_brand in ("+brand+") and week in ("+date+")";
        	}
        	
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
     * 最后4个，获取月分母总数
     * @return
     */
    public static List<CateGory> getminfo(String date,String ecType) {
    	List<CateGory> list=new ArrayList<CateGory>(); 
        //与数据库的连接
        Connection conn = getConnection();
        String sql = "select sum(SALES_PRICE) SALES_PRICE  from cloa_chushu where week in ("+date+") and CATEGORY='碳酸饮料'";
        if(StringUtils.isNotBlank(ecType)){
        	sql = "select sum(SALES_PRICE) SALES_PRICE  from cloa_chushu where ec='"+ecType+"' and week in ("+date+") and CATEGORY='碳酸饮料'";
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
     * 最后4个，获取年分子总数
     * @return
     */
    public static List<CateGory> getYzinfo(String date,String brand,String ecType) {
    	List<CateGory> list=new ArrayList<CateGory>(); 
        //与数据库的连接
        Connection conn = getConnection();
        String sql = "";
        if(brand.equals("可口可乐") || brand.equals("百事可乐")){
        	sql = "select sum(SALES_PRICE) SALES_PRICE  from cloa_chushu where date BETWEEN '2019-01-01' AND '"+date+"' and CATEGORY='碳酸饮料' and  BRAND ='"+brand+"'";
        }else{
        	sql = "select sum(SALES_PRICE) SALES_PRICE  from cloa_chushu where date BETWEEN '2019-01-01' AND '"+date+"' and CATEGORY='碳酸饮料' and  En_brand in ("+brand+")";
        }
        
        if(StringUtils.isNotBlank(ecType)){
        	if(brand.equals("可口可乐") || brand.equals("百事可乐")){
        		sql = "select sum(SALES_PRICE) SALES_PRICE  from cloa_chushu where date BETWEEN '2019-01-01' AND '"+date+"' and CATEGORY='碳酸饮料' and ec='"+ecType+"' and BRAND ='"+brand+"'";
        	}else{
        		sql = "select sum(SALES_PRICE) SALES_PRICE  from cloa_chushu where date BETWEEN '2019-01-01' AND '"+date+"' and CATEGORY='碳酸饮料' and ec='"+ecType+"' and En_brand in ("+brand+")";
        	}
        	
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
     * 最后4个，获取年分母总数
     * @return
     */
    public static List<CateGory> getYinfo(String date,String ecType) {
    	List<CateGory> list=new ArrayList<CateGory>(); 
        //与数据库的连接
        Connection conn = getConnection();
        String sql = "select sum(SALES_PRICE) SALES_PRICE  from cloa_chushu where date BETWEEN '2019-01-01' AND '"+date+"' and CATEGORY='碳酸饮料'";
        if(StringUtils.isNotBlank(ecType)){
        	sql = "select sum(SALES_PRICE) SALES_PRICE  from cloa_chushu where CATEGORY='碳酸饮料' and ec='"+ecType+"' and date BETWEEN '2019-01-01' AND '"+date+"' ";
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
     * 获取周，总价格信息。
     * @return
     */
    public static List<CateGory> getmxinfo(String date,String category,String brand,String ecType) {
    	List<CateGory> list=new ArrayList<CateGory>(); 
        //与数据库的连接
        Connection conn = getConnection();
        String sql = "select  top 10 CATEGORY, En_brand,sum(SALES_PRICE) SALES_PRICE  from cloa_chushu where CATEGORY='"+category+"' and En_brand = ?  and week in ("+date+")  GROUP BY  CATEGORY, En_brand  ORDER BY CATEGORY,SALES_PRICE DESC";
        if(StringUtils.isNotBlank(ecType)){
        	sql = "select  top 10 CATEGORY, En_brand,sum(SALES_PRICE) SALES_PRICE  from cloa_chushu where CATEGORY='"+category+"' and En_brand = ? and ec='"+ecType+"' and week in ("+date+")  GROUP BY  CATEGORY, En_brand  ORDER BY CATEGORY,SALES_PRICE DESC";
        }
        try {  
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, brand);
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
     * 获取年单个产品，总价格信息。
     * @return
     */
    public static List<CateGory> getyerarinfo(String date,String category,String brand,String ecType) {
    	List<CateGory> list=new ArrayList<CateGory>(); 
        //与数据库的连接
        Connection conn = getConnection();
        String sql = "select top 10 CATEGORY, En_brand,sum(SALES_PRICE) SALES_PRICE from cloa_chushu where CATEGORY='"+category+"' and En_brand =? and date BETWEEN '2019-01-01' AND '"+date+"'  GROUP BY  CATEGORY, En_brand  ORDER BY CATEGORY,SALES_PRICE DESC";
        if(StringUtils.isNotBlank(ecType)){
        	sql = "select top 10 CATEGORY, En_brand,sum(SALES_PRICE) SALES_PRICE from cloa_chushu where CATEGORY='"+category+"' and ec='"+ecType+"' and En_brand =? and date BETWEEN '2019-01-01' AND '"+date+"'  GROUP BY  CATEGORY, En_brand  ORDER BY CATEGORY,SALES_PRICE DESC";
        }
        try {  
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, brand);
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
    //根据用户指定日期，找周
    public static String getWeekDay(String str) throws Exception{

    	String week = "";
    	for(String l:list){
    		String[] split = l.split(",");
    		if(hourMinuteBetween(str,split[1],split[2])){
    			week = l;
    			break;
    		}
    	}
    	String[] split2 = week.split(",");

    	return split2[1];
    }
    //根据指定日期，找月周
    public static List<String> getWeek(String str) throws Exception{

    	String week = "";
    	for(String l:list){
    		String[] split = l.split(",");
    		if(hourMinuteBetween(str,split[1],split[2])){
    			week = l;
    			break;
    		}
    	}
    	String[] split2 = week.split(",");
    	List<String> ls = new ArrayList<>();
    	for(String l:list){
    		String[] split = l.split(",");
    		if(split2[0].equals(split[0])){
    			int i1 = Integer.parseInt(split[3]);
    			int i2 = Integer.parseInt(split2[3]);
    			if(i1<=i2){
    				ls.add(split[1]);
    			}
    		}
    	}
    	return ls;
    }
    
    //根据传的日期算出年 上一周最后一条日期
    public static String getYesrday(String str) throws Exception{

    	String week = "";
    	for(String l:list){
    		String[] split = l.split(",");
    		if(hourMinuteBetween(str,split[1],split[2])){
    			week = l;
    			break;
    		}
    	}
    	//计算出来的周
    	String[] split2 = week.split(",");
    	String ls = "";
    	for(String l:list){
    		String[] split = l.split(",");
    		//==1，要取上个月的最后一条
    		if(split2[3].equals("1")){
    			int parseInt = Integer.parseInt(split2[0]);
    			String weeks = "";
    			//取5
    			if(parseInt%3==0){
    				weeks = "5";
    			}else{
    				weeks = "4";
    			}
    			String s = parseInt-1+"";
    			if(s.equals(split[0])){
    				if(weeks.equals(split[3])){
    					ls = split[2];
    				}
    			}
    		}else{
        		if(split2[0].equals(split[0])){
        			int parseInt = Integer.parseInt(split2[3]);
        			String s = parseInt-1+"";
    				if(s.equals(split[3])){
    					ls = split[2];
    				}
        		}
    		}

    	}
    	return ls;
    }
    //根据传的日期算出年  本周周最后一天日期
    public static String getYesrday1(String str) throws Exception{

    	String week = "";
    	for(String l:list){
    		String[] split = l.split(",");
    		if(hourMinuteBetween(str,split[1],split[2])){
    			week = l;
    			break;
    		}
    	}
    	//计算出来的周
    	String[] split2 = week.split(",");

    	return split2[2];
    }

	public static boolean hourMinuteBetween(String nowDate, String startDate, String endDate) throws Exception{
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		
		Date now = format.parse(nowDate);
		Date start = format.parse(startDate);
		Date end = format.parse(endDate);
		
		long nowTime = now.getTime();
		long startTime = start.getTime();
		long endTime = end.getTime();
		
		return nowTime >= startTime && nowTime <= endTime;
	}
	
	
    /**
     * 获取JD和天猫数据
     * @return
     * @throws Exception 
     */
    public static List<CateGory> getJD_tmInfo(String date,String ecType) throws Exception {
    	
    	String weekDay = getWeekDay(date);
    	List<CateGory> list=new ArrayList<CateGory>(); 
        //与数据库的连接
        Connection conn = getConnection();

        //1碳酸饮料
        String sqlsuan = "select top 10 CATEGORY, En_brand,sum(SALES_PRICE) SALES_PRICE  from cloa_chushu where week='"+weekDay+"' and ec='"+ecType+"' and CATEGORY='碳酸饮料' GROUP BY CATEGORY, En_brand ORDER BY CATEGORY,SALES_PRICE DESC";
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
        }
        //碳酸饮料新增四个   ===开始
        //1：Coke TM 2：Pepsi TM 3：Coca Cola Total 4：Pepsi Total；
        String CokeTM = getCateGory("Coke TM");
        String PepsiTM = getCateGory("Pepsi TM");
/*        String CocaColaTotal = getCateGory("Coca Cola Total");
        String PepsiTotal = getCateGory("Pepsi Total");*/
        String CocaColaTotal = "可口可乐";
        String PepsiTotal = "百事可乐";
        
        List<CateGory> getwinfo = getwinfo(weekDay,CokeTM,ecType);//查询分子
        List<CateGory> getwinfo2 = getwinfo(weekDay,PepsiTM,ecType);//查询分子
        List<CateGory> getwinfo3 = getwinfo(weekDay,CocaColaTotal,ecType);//查询分子
        List<CateGory> getwinfo4 = getwinfo(weekDay,PepsiTotal,ecType);//查询分子
    	CateGory cg =new CateGory();  
    	cg.setCategory("碳酸饮料");
    	cg.setBrand("Coke TM");
    	cg.setWsum(getwinfo.get(0).getWsum());
        list.add(cg);
        
    	CateGory cg2 =new CateGory();  
    	cg2.setCategory("碳酸饮料");
        cg2.setBrand("Pepsi TM");
        cg2.setWsum(getwinfo2.get(0).getWsum());
        list.add(cg2);
        
    	CateGory cg3 =new CateGory();  
    	cg3.setCategory("碳酸饮料");
        cg3.setBrand("Coca Cola Total");
        cg3.setWsum(getwinfo3.get(0).getWsum());
        list.add(cg3);
        
    	CateGory cg4 =new CateGory();  
    	cg4.setCategory("碳酸饮料");
        cg4.setBrand("Pepsi Total");
        cg4.setWsum(getwinfo4.get(0).getWsum());
        list.add(cg4);
        //碳酸饮料新增四个   =分子==结束
        
        //1果味饮料
        String sqlguo = "select top 10 CATEGORY,En_brand,sum(SALES_PRICE) SALES_PRICE from cloa_chushu where week='"+weekDay+"' and ec='"+ecType+"' and CATEGORY='果味饮料' GROUP BY  CATEGORY, En_brand  ORDER BY CATEGORY,SALES_PRICE DESC";
        try {  
            PreparedStatement pst=conn.prepareStatement(sqlguo);  
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
        }
        //1包装饮用水
        String sqlbao = "select top 10 CATEGORY,En_brand,sum(SALES_PRICE) SALES_PRICE  from cloa_chushu where week='"+weekDay+"' and ec='"+ecType+"' and CATEGORY='包装饮用水' GROUP BY  CATEGORY, En_brand  ORDER BY CATEGORY,SALES_PRICE DESC";
        try {  
            PreparedStatement pst=conn.prepareStatement(sqlbao);  
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
        
        //Chun Yue 分子
        List<CateGory> getchunYuem = getchunYuem("'"+weekDay+"'",ecType);
    	CateGory cg5 =new CateGory();  
    	cg5.setCategory("包装饮用水");
        cg5.setBrand("Chun Yue");
        cg5.setWsum(getchunYuem.get(0).getWsum());
        list.add(cg5);
        
        Map wxinfo = getwxinfo(weekDay,ecType);//获取周分母
        double bao = (double) wxinfo.get("bao");
        double guo = (double) wxinfo.get("guo");
        double suan = (double) wxinfo.get("suan");
        
        //碳酸饮料新增四个周分母
        List<CateGory> getwinfo5 = getwinfo(weekDay,ecType);
        String wsum2 = getwinfo5.get(0).getWsum();
        double w4 = Double.parseDouble(wsum2);
        
        //Chun Yue 分母
        List<CateGory> getchunYueMu = getchunYueMu(ecType);
        String wsum3 = getchunYueMu.get(0).getWsum();
        double w5 = Double.parseDouble(wsum3);
        //本周分母赋值
        if(list.size()>0){
        	for(CateGory c:list){
        		if(c.getBrand().equals("Coke TM") || c.getBrand().equals("Pepsi TM") || c.getBrand().equals("Coca Cola Total") || c.getBrand().equals("Pepsi Total")){
            		double wsum = Double.parseDouble(c.getWsum())/w4;
            		c.setWsums(wsum+"");
        		}else if(c.getBrand().equals("Chun Yue")){
        			if(StringUtils.isNotBlank(c.getWsum())){
                		double wsum = Double.parseDouble(c.getWsum())/w5;
                		c.setWsums(wsum+"");
        			}else{
        				c.setWsum("0");
        				c.setWsums("0");
        			}
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
        //生成月总数==============================
    	List<String> week = getWeek(date);
    	StringBuilder sb = new StringBuilder();
    	for(int i=0;i<week.size();i++){
    		if(i==0){
    			sb.append("'");
    			sb.append(week.get(i));
    			sb.append("'");
    		}else{
    			sb.append(",");
    			sb.append("'");
    			sb.append(week.get(i));
    			sb.append("'");
    		}
    	}
    	Map getwxinfos = getwxinfos(sb.toString(),ecType);//月分母
        double mbao = (double) getwxinfos.get("bao");
        double mguo = (double) getwxinfos.get("guo");
        double msuan = (double) getwxinfos.get("suan");
        //碳酸饮料新增四个月分母
        List<CateGory> getwinfo6 = getminfo(sb.toString(),ecType);//4个月分母
        String msum = getwinfo6.get(0).getWsum();
        double m4 = Double.parseDouble(msum);
        if(list.size()>0){
        	for(CateGory c:list){
        		if(c.getBrand().equals("Coke TM")){
            		//获取月分子
        			List<CateGory> getmzinfo = getmzinfo(sb.toString(),CokeTM,ecType);
             		double d = Double.parseDouble(getmzinfo.get(0).getWsum());
            		c.setMsum(d+"");
            		c.setMsums(d/m4+"");
        		}else if(c.getBrand().equals("Pepsi TM")){
            		//获取月分子
        			List<CateGory> getmzinfo = getmzinfo(sb.toString(),PepsiTM,ecType);
             		double d = Double.parseDouble(getmzinfo.get(0).getWsum());
            		c.setMsum(d+"");
            		c.setMsums(d/m4+"");
        		}else if(c.getBrand().equals("Coca Cola Total")){
            		//获取月分子
        			List<CateGory> getmzinfo = getmzinfo(sb.toString(),CocaColaTotal,ecType);
             		double d = Double.parseDouble(getmzinfo.get(0).getWsum());
            		c.setMsum(d+"");
            		c.setMsums(d/m4+"");
        		}else if(c.getBrand().equals("Pepsi Total")){
            		//获取月分子
        			List<CateGory> getmzinfo = getmzinfo(sb.toString(),PepsiTotal,ecType);
             		double d = Double.parseDouble(getmzinfo.get(0).getWsum());
            		c.setMsum(d+"");
            		c.setMsums(d/m4+"");
        		}else if(c.getBrand().equals("Chun Yue")){//周，分子和分母赋值
        			List<CateGory> getchunYuem2 = getchunYuem(sb.toString(),ecType);
        			if(StringUtils.isNotBlank(getchunYuem2.get(0).getWsum())){
                 		double d = Double.parseDouble(getchunYuem2.get(0).getWsum());
                		c.setMsum(d+"");
                		c.setMsums(d/w5+"");
        			}else{
                		c.setMsum("0");
                		c.setMsums("0");
        			}
        		}else{
            		List<CateGory> getmxinfo = getmxinfo(sb.toString(),c.getCategory(),c.getBrand(),ecType);
            		double d = Double.parseDouble(getmxinfo.get(0).getWsum());
            		c.setMsum(d+"");
            		if(c.getCategory().equals("包装饮用水")){
            			c.setMsums(d/mbao+"");
            		}else if(c.getCategory().equals("果味饮料")){
            			c.setMsums(d/mguo+"");
            		}else if(c.getCategory().equals("碳酸饮料")){
            			c.setMsums(d/msuan+"");
            		}
        		}
        	}
        }
        //算年
        //String yesrday = getYesrday(date);//根据用户日期算年，取上周最后一天日期。
        String yesrday = getYesrday1(date);//根据用户日期算年，取本周最后一天。
        Map yearinfo = getYearinfo(yesrday,ecType);//年分母
        double ybao = (double) yearinfo.get("bao");
        double yguo = (double) yearinfo.get("guo");
        double ysuan = (double) yearinfo.get("suan");
        //碳酸饮料新增四个年分母
        List<CateGory> yinfo = getYinfo(yesrday,ecType);//4个月分母
        String ysum = yinfo.get(0).getWsum();
        double ysums = Double.parseDouble(ysum);
        if(list.size()>0){
        	for(CateGory c:list){
        		if(c.getBrand().equals("Coke TM")){
            		//获取年分子
        			List<CateGory> getmzinfo = getYzinfo(yesrday,CokeTM,ecType);
             		double d = Double.parseDouble(getmzinfo.get(0).getWsum());
            		c.setYsum(d+"");
            		c.setYsums(d/ysums+"");
        		}else if(c.getBrand().equals("Pepsi TM")){
            		//获取年分子
        			List<CateGory> getmzinfo = getYzinfo(yesrday,PepsiTM,ecType);
             		double d = Double.parseDouble(getmzinfo.get(0).getWsum());
            		c.setYsum(d+"");
            		c.setYsums(d/ysums+"");
        		}else if(c.getBrand().equals("Coca Cola Total")){
            		//获取年分子
        			List<CateGory> getmzinfo = getYzinfo(yesrday,CocaColaTotal,ecType);
             		double d = Double.parseDouble(getmzinfo.get(0).getWsum());
            		c.setYsum(d+"");
            		c.setYsums(d/ysums+"");
        		}else if(c.getBrand().equals("Pepsi Total")){
            		//获取年分子
        			List<CateGory> getmzinfo = getYzinfo(yesrday,PepsiTotal,ecType);
             		double d = Double.parseDouble(getmzinfo.get(0).getWsum());
            		c.setYsum(d+"");
            		c.setYsums(d/ysums+"");
        		}else if(c.getBrand().equals("Chun Yue")){
        			List<CateGory> getchunYuem2 = getchunYueyzi(yesrday,ecType);//年分子
        			if(StringUtils.isNotBlank(getchunYuem2.get(0).getWsum())){
                 		double d = Double.parseDouble(getchunYuem2.get(0).getWsum());
                		c.setYsum(d+"");
                		c.setYsums(d/w5+"");
        			}else{
                		c.setYsum("0");
                		c.setYsums("0");
        			}
        		}else{
            		List<CateGory> getmxinfo = getyerarinfo(yesrday,c.getCategory(),c.getBrand(),ecType);
            		double d = Double.parseDouble(getmxinfo.get(0).getWsum());
            		c.setYsum(d+"");
            		if(c.getCategory().equals("包装饮用水")){
            			c.setYsums(d/ybao+"");
            		}else if(c.getCategory().equals("果味饮料")){
            			c.setYsums(d/yguo+"");
            		}else if(c.getCategory().equals("碳酸饮料")){
            			c.setYsums(d/ysuan+"");
            		}
        		}
        	}
        }
        return list;
    }
    public static double formatDouble(double d) {
        // 新方法，如果不需要四舍五入，可以使用RoundingMode.DOWN
        BigDecimal bg = new BigDecimal(d).setScale(2, RoundingMode.UP);
        return bg.doubleValue();
    }
    /**
     * 提供精确的小数位四舍五入处理。
     *
     * @param v 需要四舍五入的数字
     * @param scale 小数点后保留几位
     * @return 四舍五入后的结果
     */
    public static double round(double v, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        }
        BigDecimal b = new BigDecimal(Double.toString(v));
        BigDecimal one = new BigDecimal("1");
        return b.divide(one, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
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
        String sql = "select sum(sales) sales from COLA_SKU_SALES_chushu where ec='"+ecType+"' and sku='"+sku+"' and week in ("+date+")";
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
    /**
     * sku 年 天猫和京东数据
     */
    public static String getSkuyear(String ecType,String sku,String date) {
    	List<CateGory> list=new ArrayList<CateGory>(); 
        //与数据库的连接
        Connection conn = getConnection();
        String sql = "select sum(sales) sales from COLA_SKU_SALES_chushu where ec='"+ecType+"' and sku='"+sku+"' and date BETWEEN '2019-01-01' AND '"+date+"'";
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
    //第一个sheet页面赋值功能
    public static List<CateGory> getSkuInfo(String date) throws Exception{
    	
    	//根据用户日期获取周
    	String weekDay = getWeekDay(date);
    	//根据用户日期获取一个月里面的周
    	List<String> week = getWeek(date);
    	StringBuilder sb = new StringBuilder();
    	for(int i=0;i<week.size();i++){
    		if(i==0){
    			sb.append("'");
    			sb.append(week.get(i));
    			sb.append("'");
    		}else{
    			sb.append(",");
    			sb.append("'");
    			sb.append(week.get(i));
    			sb.append("'");
    		}
    	}
    	//根据用户日期算年，取本周最后一天。
    	String yesrday = getYesrday1(date);
    	//获取中英文对照表信息
    	List<CateGory> skuList = getSkudzb();
    	if(skuList.size()>0){
    		for(CateGory c:skuList){
    			//获取中文类型
    			String brand = c.getBrand();
    			//周
    			c.setWsum(getSkuwm("jd",brand,"'"+weekDay+"'"));
    			c.setWsums(getSkuwm("tm",brand,"'"+weekDay+"'"));
    			//月
    			c.setMsum(getSkuwm("jd",brand,sb.toString()));
    			c.setMsums(getSkuwm("tm",brand,sb.toString()));
    			//年
    			c.setYsum(getSkuyear("jd",brand,yesrday));
    			c.setYsums(getSkuyear("tm",brand,yesrday));
    		}
    	}else{
    		System.out.println("对照表没数据");
    	}
    	//算总数
    	double jdw = 0;
    	double tmw = 0;
    	double jdm = 0;
    	double tmm = 0;
    	double jdy = 0;
    	double tmy = 0;
    	if(skuList.size()>0){
    		for(CateGory c:skuList){
    			jdw += Double.parseDouble(c.getWsum());
    			tmw += Double.parseDouble(c.getWsums());
    			jdm += Double.parseDouble(c.getMsum());
    			tmm += Double.parseDouble(c.getMsums());
    			jdy += Double.parseDouble(c.getYsum());
    			tmy += Double.parseDouble(c.getYsums());
    		}
    		CateGory cg = new CateGory();
    		cg.setCategory("Top SKU Total");
    		cg.setBrand("Top SKU Total");
    		cg.setWsum(jdw+"");
    		cg.setWsums(tmw+"");
    		cg.setMsum(jdm+"");
    		cg.setMsums(tmm+"");
    		cg.setYsum(jdy+"");
    		cg.setYsums(tmy+"");
    		skuList.add(cg);
    	}
    	return skuList;
    }
    
    /**
     * 本地测试用
     * @return
     * @throws Exception 
     */
    public static void getColumnComments1(String date) throws Exception {
    	
    	long startTime = System.currentTimeMillis();
    	
    	String weekDay = getWeekDay(date);
    	List<CateGory> list=new ArrayList<CateGory>(); 
        //与数据库的连接
        Connection conn = getConnection();

        //1碳酸饮料
        String sqlsuan = "select  top 10 CATEGORY, En_brand,sum(SALES_PRICE) SALES_PRICE  from cloa_chushu where week='"+weekDay+"' and CATEGORY='碳酸饮料'   GROUP BY  CATEGORY, En_brand  ORDER BY CATEGORY,SALES_PRICE DESC";
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
        }
        //碳酸饮料新增四个   ===开始
        //1：Coke TM 2：Pepsi TM 3：Coca Cola Total 4：Pepsi Total；
        String CokeTM = getCateGory("Coke TM");
        String PepsiTM = getCateGory("Pepsi TM");
/*        String CocaColaTotal = getCateGory("Coca Cola Total");
        String PepsiTotal = getCateGory("Pepsi Total");*/
        String CocaColaTotal = "可口可乐";
        String PepsiTotal = "百事可乐";
        List<CateGory> getwinfo = getwinfo(weekDay,CokeTM,"");
        List<CateGory> getwinfo2 = getwinfo(weekDay,PepsiTM,"");
        List<CateGory> getwinfo3 = getwinfo(weekDay,CocaColaTotal,"");
        List<CateGory> getwinfo4 = getwinfo(weekDay,PepsiTotal,"");
    	CateGory cg =new CateGory();  
    	cg.setCategory("碳酸饮料");
    	cg.setBrand("Coke TM");
    	cg.setWsum(getwinfo.get(0).getWsum());
        list.add(cg);
        
    	CateGory cg2 =new CateGory();  
    	cg2.setCategory("碳酸饮料");
        cg2.setBrand("Pepsi TM");
        cg2.setWsum(getwinfo2.get(0).getWsum());
        list.add(cg2);
        
    	CateGory cg3 =new CateGory();  
    	cg3.setCategory("碳酸饮料");
        cg3.setBrand("Coca Cola Total");
        cg3.setWsum(getwinfo3.get(0).getWsum());
        list.add(cg3);
        
    	CateGory cg4 =new CateGory();  
    	cg4.setCategory("碳酸饮料");
        cg4.setBrand("Pepsi Total");
        cg4.setWsum(getwinfo4.get(0).getWsum());
        list.add(cg4);
        //碳酸饮料新增四个   =分子==结束
        
        //1果味饮料
        String sqlguo = "select  top 10 CATEGORY, En_brand,sum(SALES_PRICE) SALES_PRICE  from cloa_chushu where week='"+weekDay+"' and CATEGORY='果味饮料'   GROUP BY  CATEGORY, En_brand  ORDER BY CATEGORY,SALES_PRICE DESC";
        try {  
            PreparedStatement pst=conn.prepareStatement(sqlguo);  
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
        }
        //1包装饮用水
        String sqlbao = "select top 10 CATEGORY,En_brand,sum(SALES_PRICE) SALES_PRICE  from cloa_chushu where week='"+weekDay+"' and CATEGORY='包装饮用水' GROUP BY  CATEGORY, En_brand  ORDER BY CATEGORY,SALES_PRICE DESC";
        try {  
            PreparedStatement pst=conn.prepareStatement(sqlbao);  
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
        
        //Chun Yue 分子
        List<CateGory> getchunYuem = getchunYuem("'"+weekDay+"'","");
    	CateGory cg5 =new CateGory();  
    	cg5.setCategory("包装饮用水");
        cg5.setBrand("Chun Yue");
        cg5.setWsum(getchunYuem.get(0).getWsum());
        list.add(cg5);
        
        
        //获取周分母
        Map wxinfo = getwxinfo(weekDay,"");
        double bao = (double) wxinfo.get("bao");
        double guo = (double) wxinfo.get("guo");
        double suan = (double) wxinfo.get("suan");
        //碳酸饮料新增四个周分母
        List<CateGory> getwinfo5 = getwinfo(weekDay,"");
        String wsum2 = getwinfo5.get(0).getWsum();
        double w4 = Double.parseDouble(wsum2);
        //Chun Yue 分母
        List<CateGory> getchunYueMu = getchunYueMu("");
        String wsum3 = getchunYueMu.get(0).getWsum();
        double w5 = Double.parseDouble(wsum3);
        if(list.size()>0){
        	for(CateGory c:list){
        		if(c.getBrand().equals("Coke TM") || c.getBrand().equals("Pepsi TM") || c.getBrand().equals("Coca Cola Total") || c.getBrand().equals("Pepsi Total")){
            		double wsum = Double.parseDouble(c.getWsum())/w4;
            		c.setWsums(wsum+"");
        		}else if(c.getBrand().equals("Chun Yue")){
            		double wsum = Double.parseDouble(c.getWsum())/w5;
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
        //生成月总数==============================
    	List<String> week = getWeek(date);
    	StringBuilder sb = new StringBuilder();
    	for(int i=0;i<week.size();i++){
    		if(i==0){
    			sb.append("'");
    			sb.append(week.get(i));
    			sb.append("'");
    		}else{
    			sb.append(",");
    			sb.append("'");
    			sb.append(week.get(i));
    			sb.append("'");
    		}
    	}
    	Map getwxinfos = getwxinfos(sb.toString(),"");//月分母
        double mbao = (double) getwxinfos.get("bao");
        double mguo = (double) getwxinfos.get("guo");
        double msuan = (double) getwxinfos.get("suan");
        
        List<CateGory> getwinfo6 = getminfo(sb.toString(),"");//碳酸饮料新增四个月分母
        String msum = getwinfo6.get(0).getWsum();
        double m4 = Double.parseDouble(msum);
        if(list.size()>0){
        	for(CateGory c:list){
        		if(c.getBrand().equals("Coke TM")){
            		//获取月分子
        			List<CateGory> getmzinfo = getmzinfo(sb.toString(),CokeTM,"");
             		double d = Double.parseDouble(getmzinfo.get(0).getWsum());
            		c.setMsum(d+"");
            		c.setMsums(d/m4+"");
        		}else if(c.getBrand().equals("Pepsi TM")){
            		//获取月分子
        			List<CateGory> getmzinfo = getmzinfo(sb.toString(),PepsiTM,"");
             		double d = Double.parseDouble(getmzinfo.get(0).getWsum());
            		c.setMsum(d+"");
            		c.setMsums(d/m4+"");
        		}else if(c.getBrand().equals("Coca Cola Total")){
            		//获取月分子
        			List<CateGory> getmzinfo = getmzinfo(sb.toString(),CocaColaTotal,"");
             		double d = Double.parseDouble(getmzinfo.get(0).getWsum());
            		c.setMsum(d+"");
            		c.setMsums(d/m4+"");
        		}else if(c.getBrand().equals("Pepsi Total")){
            		//获取月分子
        			List<CateGory> getmzinfo = getmzinfo(sb.toString(),PepsiTotal,"");
             		double d = Double.parseDouble(getmzinfo.get(0).getWsum());
            		c.setMsum(d+"");
            		c.setMsums(d/m4+"");
        		}else if(c.getBrand().equals("Chun Yue")){
        			List<CateGory> getchunYuem2 = getchunYuem(sb.toString(),"");
             		double d = Double.parseDouble(getchunYuem2.get(0).getWsum());
            		c.setMsum(d+"");
            		c.setMsums(d/w5+"");
        		}else{
            		List<CateGory> getmxinfo = getmxinfo(sb.toString(),c.getCategory(),c.getBrand(),"");//月分子
            		double d = Double.parseDouble(getmxinfo.get(0).getWsum());
            		c.setMsum(d+"");
            		if(c.getCategory().equals("包装饮用水")){
            			c.setMsums(d/mbao+"");
            		}else if(c.getCategory().equals("果味饮料")){
            			c.setMsums(d/mguo+"");
            		}else if(c.getCategory().equals("碳酸饮料")){
            			c.setMsums(d/msuan+"");
            		}
        		}
        	}
        }
        //算年
        //String yesrday = getYesrday(date);//根据用户日期算年，取上周最后一天日期。之前版本
        String yesrday = getYesrday1(date);//根据用户日期算年，取本周最后一天。
        Map yearinfo = getYearinfo(yesrday,"");//年分母
        double ybao = (double) yearinfo.get("bao");
        double yguo = (double) yearinfo.get("guo");
        double ysuan = (double) yearinfo.get("suan");
        //碳酸饮料新增四个年分母
        List<CateGory> yinfo = getYinfo(yesrday,"");//4个年分母
        String ysum = yinfo.get(0).getWsum();
        double ysums = Double.parseDouble(ysum);
        if(list.size()>0){
        	for(CateGory c:list){
        		if(c.getBrand().equals("Coke TM")){
            		//获取年分子
        			List<CateGory> getmzinfo = getYzinfo(yesrday,CokeTM,"");
             		double d = Double.parseDouble(getmzinfo.get(0).getWsum());
            		c.setYsum(d+"");
            		c.setYsums(d/ysums+"");
        		}else if(c.getBrand().equals("Pepsi TM")){
            		//获取年分子
        			List<CateGory> getmzinfo = getYzinfo(yesrday,PepsiTM,"");
             		double d = Double.parseDouble(getmzinfo.get(0).getWsum());
            		c.setYsum(d+"");
            		c.setYsums(d/ysums+"");
        		}else if(c.getBrand().equals("Coca Cola Total")){
            		//获取年分子
        			List<CateGory> getmzinfo = getYzinfo(yesrday,CocaColaTotal,"");
             		double d = Double.parseDouble(getmzinfo.get(0).getWsum());
            		c.setYsum(d+"");
            		c.setYsums(d/ysums+"");
        		}else if(c.getBrand().equals("Pepsi Total")){
            		//获取年分子
        			List<CateGory> getmzinfo = getYzinfo(yesrday,PepsiTotal,"");
             		double d = Double.parseDouble(getmzinfo.get(0).getWsum());
            		c.setYsum(d+"");
            		c.setYsums(d/ysums+"");
        		}else if(c.getBrand().equals("Chun Yue")){
        			List<CateGory> getchunYuem2 = getchunYueyzi(yesrday,"");//年分子
             		double d = Double.parseDouble(getchunYuem2.get(0).getWsum());
            		c.setYsum(d+"");
            		c.setYsums(d/w5+"");
        		}else{
            		List<CateGory> getmxinfo = getyerarinfo(yesrday,c.getCategory(),c.getBrand(),"");//年分子
            		double d = Double.parseDouble(getmxinfo.get(0).getWsum());
            		c.setYsum(d+"");
            		if(c.getCategory().equals("包装饮用水")){
            			c.setYsums(d/ybao+"");
            		}else if(c.getCategory().equals("果味饮料")){
            			c.setYsums(d/yguo+"");
            		}else if(c.getCategory().equals("碳酸饮料")){
            			c.setYsums(d/ysuan+"");
            		}
        		}
        	}
        }
        
        //生成excel  list集合：jd和天猫的数据
        List<CateGory> jdInfo = getJD_tmInfo(date,"jd");
        List<CateGory> tmInfo = getJD_tmInfo(date,"tm");
        List<CateGory> skuInfo = getSkuInfo(date);//第一个sheet页面数据
        JD_TXExcelUtil.exportUserExcel(jdInfo,tmInfo,list,skuInfo);
        long endTime = System.currentTimeMillis();
        System.out.println("四个生成完成耗时:"+ (endTime-startTime)+"ms");
    }
    public static void main(String[] args) throws Exception {
    	getColumnComments1("2019-07-22");//生成excel
    }
}