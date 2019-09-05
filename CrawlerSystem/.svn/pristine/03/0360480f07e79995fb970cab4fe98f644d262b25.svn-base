package com.syntun.report;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * excel工具类,支持批量导出
 * @author
 *
 */
public class HbaseExcelUtil {
    
    /**
     * 将用户的信息导入到京东excel文件中去
     * @param userList 用户列表
     * @param out 输出表
     */
    public static void exportUserExcel(List<HashMap<String,String>> list,HttpServletRequest request, HttpServletResponse response,String excelName){
        
    	try{
            //1.创建工作簿
    		 XSSFWorkbook workbook = new XSSFWorkbook();
            //2.创建工作表
    		 XSSFSheet sheet = workbook.createSheet("数据列表");
            
            //4.操作单元格;将用户列表写入excel
            if(list != null){
                //3.1创建头标题行;并且设置头标题
            	XSSFRow row = sheet.createRow(0);
            	int titlei = 0;
            	HashMap<String,String> hashMap = list.get(0);
            	for(Map.Entry<String, String> entry : hashMap.entrySet()){
            		XSSFCell cell = row.createCell(titlei);
            	    String mapKey = entry.getKey();
            	    cell.setCellValue(mapKey);
            	    titlei ++;
            	    //String mapValue = entry.getValue();
            	    //System.out.println(mapKey+":"+mapValue);
            	}
                for(int j=0;j<list.size();j++)
                {
                    //创建数据行,前面有两行,头标题行和列标题行
                    XSSFRow row3 = sheet.createRow(j+1);//创建行
                    
                    HashMap<String,String> hashMap2 = list.get(j);
                    int value = 0;
                	for(Map.Entry<String, String> entry : hashMap2.entrySet()){
                        XSSFCell cell2 = row3.createCell(value);//创建列
                        String mapValue = entry.getValue();
                        cell2.setCellValue(mapValue);
                        value ++;
                	    //String mapValue = entry.getValue();
                	    //System.out.println(mapKey+":"+mapValue);
                	}
                	value = 0;
                }
            }
            
            response.setContentType("application/vnd.ms-excel"); 
            response.setCharacterEncoding("utf-8"); 
            //这里对文件名进行编码，保证下载时汉字显示正常 
            String fileName = URLEncoder.encode(excelName+".xlsx", "utf-8"); 
            //Content-disposition属性设置成以附件方式进行下载 
            response.setHeader("Content-disposition", "attachment;filename="
            + fileName); 
            OutputStream os = response.getOutputStream(); 
            workbook.write(os); 
            os.flush(); 
            os.close(); 
        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
    /**
     * 将用户的信息导入到京东excel文件中去
     * @param userList 用户列表
     * @param out 输出表
     */
    public static void exportUserExcel(List<HashMap<String,String>> list){
    	
        try{
            //1.创建工作簿
    		 XSSFWorkbook workbook = new XSSFWorkbook();
            //2.创建工作表
    		 XSSFSheet sheet = workbook.createSheet("数据列表");
            
            //4.操作单元格;将用户列表写入excel
            if(list != null){
                //3.1创建头标题行;并且设置头标题
            	XSSFRow row = sheet.createRow(0);
            	int titlei = 0;
            	HashMap<String,String> hashMap = list.get(0);
            	for(Map.Entry<String, String> entry : hashMap.entrySet()){
            		XSSFCell cell = row.createCell(titlei);
            	    String mapKey = entry.getKey();
            	    cell.setCellValue(mapKey);
            	    titlei ++;
            	    //String mapValue = entry.getValue();
            	    //System.out.println(mapKey+":"+mapValue);
            	}
                for(int j=0;j<list.size();j++)
                {
                    //创建数据行,前面有两行,头标题行和列标题行
                    XSSFRow row3 = sheet.createRow(j+1);//创建行
                    
                    HashMap<String,String> hashMap2 = list.get(j);
                    int value = 0;
                	for(Map.Entry<String, String> entry : hashMap2.entrySet()){
                        XSSFCell cell2 = row3.createCell(value);//创建列
                        String mapValue = entry.getValue();
                        cell2.setCellValue(mapValue);
                        value ++;
                	    //String mapValue = entry.getValue();
                	    //System.out.println(mapKey+":"+mapValue);
                	}
                	value = 0;
                }
            }
            
            //5.输出
            //输出到磁盘中
            File file = new File("d:\\test.xlsx");
            if(file.exists()){
            	file.delete();
            }
            FileOutputStream fos = new FileOutputStream(file);
            workbook.write(fos);
            fos.close();
           
        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
    	
    	List<HashMap<String,String>> list = new ArrayList<>();
    	HashMap<String,String> map = new HashMap<String,String>();
    	map.put("1", "1");
    	map.put("2", "2");
    	map.put("3", "3");
    	map.put("4", "4");
    	map.put("5", "5");
    	list.add(map);
    	
    	HashMap<String,String> map2 = new HashMap<String,String>();
    	map2.put("11", "11");
    	map2.put("22", "22");
    	map2.put("33", "33");
    	map2.put("44", "44");
    	map2.put("55", "55");
    	list.add(map2);

    	exportUserExcel(list);
	}
}
