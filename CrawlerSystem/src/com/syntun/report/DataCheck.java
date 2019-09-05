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
import com.syntun.entity.DataCheckEntry;

/**
 * Created by wangdong on 2019/8/2.
 * 数据核对
 */
public class DataCheck {
	
	private final static Logger LOGGER = LoggerFactory.getLogger(DataCheck.class);

    private static String DRIVER = "com.microsoft.jdbc.sqlserver.SQLServerDriver";
    private static String URL = "jdbc:sqlserver://192.168.0.15:1433;DatabaseName=item";
    private static String USERNAME = "yanfa_wang";
    private static String PASSWORD = "wang_fA_564";
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


    
    public static void getKeleList(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	
    	//品类
    	List<String> pingType = new ArrayList<>();
    	pingType.add("婴幼儿奶粉");
    	pingType.add("成人奶粉");
    	pingType.add("液态奶");
    	pingType.add("冰品");
    	pingType.add("低温酸奶");
    	pingType.add("低温鲜奶");
    	pingType.add("奶酪");
    	pingType.add("麦片");
    	//渠道
    	List<String> channel = new ArrayList<>();
    	channel.add("天猫大盘");
    	channel.add("京东大盘");
    	channel.add("天猫超市");
    	channel.add("京东自营");
    	channel.add("天猫pop");
    	channel.add("天猫国际");
    	
    	List<DataCheckEntry> sheet1 = new ArrayList<>();
    	List<DataCheckEntry> sheet2 = new ArrayList<>();
    	List<DataCheckEntry> sheet3 = new ArrayList<>();
    	List<DataCheckEntry> sheet4 = new ArrayList<>();
    	List<DataCheckEntry> sheet5 = new ArrayList<>();
    	List<DataCheckEntry> sheet6 = new ArrayList<>();
    	List<DataCheckEntry> sheet7 = new ArrayList<>();
    	List<DataCheckEntry> sheet8 = new ArrayList<>();
    	
    	for(int i=0;i<pingType.size();i++){
    		for(String c:channel){
    			List<DataCheckEntry> getwinfo = getwinfo(pingType.get(i),c);
    			if(!getwinfo.isEmpty()){
    				if(i==0){
    					sheet1.addAll(getwinfo);
    				}else if(i==1){
    					sheet2.addAll(getwinfo);
    				}else if(i==2){
    					sheet3.addAll(getwinfo);
    				}else if(i==3){
    					sheet4.addAll(getwinfo);
    				}else if(i==4){
    					sheet5.addAll(getwinfo);
    				}else if(i==5){
    					sheet6.addAll(getwinfo);
    				}else if(i==6){
    					sheet7.addAll(getwinfo);
    				}else if(i==7){
    					sheet8.addAll(getwinfo);
    				}
    			}
    		}
    	}
    	DataCheckExcelUtil.exportUserExcel(sheet1,sheet2,sheet3,sheet4,sheet5,sheet6,sheet7,sheet8,request,response,"数据核对模板");
    	//DataCheckExcelUtil.exportUserExcel(sheet1,sheet2,sheet3,sheet4,sheet5,sheet6,sheet7,sheet8);

        System.out.println("完成");
    	
    }

    public static List<DataCheckEntry> getwinfo(String type,String channel) {
    	List<DataCheckEntry> list=new ArrayList<>(); 
        //与数据库的连接
        Connection conn = getConnection();
        String sql = "select * from ADVANCE_TABLE where 品类 ='"+type+"' and 渠道 = '"+channel+"'";
        try {  
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs=pst.executeQuery();      
            while(rs.next()){  
            	DataCheckEntry c =new DataCheckEntry();
                c.setOne(rs.getString("品牌"));
                c.setTwo(rs.getString("销售额"));
                c.setThree(rs.getString("本月排名"));
                c.setFour(rs.getString("上月排名"));
                c.setFive(rs.getString("本月销售额份额"));
                c.setSix(rs.getString("上月销售额份额"));
                c.setSeven(rs.getString("去年同期销售额份额"));
                c.setEight(rs.getString("本月销售额同比"));
                c.setNine(rs.getString("本月销售额环比"));
                c.setTen(rs.getString("今年YTD销售额份额"));
                c.setEleven(rs.getString("去年YTD销售额份额"));
                c.setTwelve(rs.getString("YTD销售额同比"));
                c.setChannel(rs.getString("渠道"));
                list.add(c);  
            }  
           } catch (Exception e) {  
            e.printStackTrace();  
        }finally {
        	closeConnection(conn);
		}
        return list;
    }

    public static void main(String[] args) throws Exception {
    	getKeleList(null,null);//生成excel
    }
}
