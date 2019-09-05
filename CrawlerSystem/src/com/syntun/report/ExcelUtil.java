package com.syntun.report;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.syntun.entity.CateGory;

/**
 * excel工具类,支持批量导出
 * @author
 *
 */
public class ExcelUtil {
    
    /**
     * 将用户的信息导入到京东excel文件中去
     * @param userList 用户列表
     * @param out 输出表
     */
    public static void exportUserExcel(List<CateGory> jdList,List<CateGory> tmList,List<CateGory> jd_tmList,List<CateGory> skuInfo,HttpServletRequest request, HttpServletResponse response,String excelName){
        try{
            //1.创建工作簿
        	//String path = "d:\\a.xlsx";//本地excel模板地址
        	String path = "/home/CrawlerSystem/excel/b.xlsx";//服务器excel模板地址
            FileInputStream tps = new FileInputStream(new File(path));
            XSSFWorkbook tpWorkbook = new XSSFWorkbook(tps);
            // 新建一个Excel的工作空间
            XSSFWorkbook workbook = new XSSFWorkbook();
            // 把模板复制到新建的Excel
            workbook = tpWorkbook;
            //第一个sheet页
            XSSFSheet sheet = workbook.getSheetAt(1);
            //第二个sheet页
            XSSFSheet sheet2 = workbook.getSheetAt(0);
            
            //4.操作单元格;将用户列表写入excel
            if(jdList != null){
                for(int j=0;j<jdList.size();j++)
                {
                    //创建数据行,前面有两行,头标题行和列标题行
                    XSSFRow row3 = sheet.getRow(j+5);//创建行
                    
                    XSSFCell cell2 = row3.getCell(3);//创建列
                    cell2.setCellValue(jdList.get(j).getBrand());
                    
                    XSSFCell cell3 = row3.getCell(4);
                    cell3.setCellValue(Double.parseDouble(jdList.get(j).getWsum()));
                    
                    XSSFCell cell4 = row3.getCell(5);
                    cell4.setCellValue(Double.parseDouble(jdList.get(j).getWsums()));
                }
            }
            
            //4.操作单元格;将用户列表写入excel
            if(tmList != null){
                for(int j=0;j<tmList.size();j++)
                {
                    //创建数据行,前面有两行,头标题行和列标题行
                    XSSFRow row3 = sheet.getRow(j+5);//创建行
                    
                    XSSFCell cell2 = row3.getCell(9);//创建列
                    cell2.setCellValue(tmList.get(j).getBrand());
                    
                    XSSFCell cell3 = row3.getCell(10);
                    cell3.setCellValue(Double.parseDouble(tmList.get(j).getWsum()));
                    
                    XSSFCell cell4 = row3.getCell(11);
                    cell4.setCellValue(Double.parseDouble(tmList.get(j).getWsums()));
                }
            }
            
            //4.操作单元格;将用户列表写入excel
            if(jd_tmList != null){
                for(int j=0;j<jd_tmList.size();j++)
                {
                    //创建数据行,前面有两行,头标题行和列标题行
                    XSSFRow row3 = sheet.getRow(j+5);//创建行
                    
                    XSSFCell cell2 = row3.getCell(15);//创建列
                    cell2.setCellValue(jd_tmList.get(j).getBrand());
                    
                    XSSFCell cell3 = row3.getCell(16);
                    cell3.setCellValue(Double.parseDouble(jd_tmList.get(j).getWsum()));
                    
                    XSSFCell cell4 = row3.getCell(17);
                    cell4.setCellValue(Double.parseDouble(jd_tmList.get(j).getWsums()));
                }
            }
            //4.操作单元格;将用户列表写入excel
            if(skuInfo != null){
                for(int j=0;j<skuInfo.size();j++)
                {
                    //创建数据行,前面有两行,头标题行和列标题行
                    XSSFRow row3 = sheet2.getRow(j+3);//创建行
                    
                    XSSFCell cell2 = row3.getCell(1);//创建列
                    cell2.setCellValue(skuInfo.get(j).getCategory());
                    
                    XSSFCell cell3 = row3.getCell(2);
                    cell3.setCellValue(Double.parseDouble(skuInfo.get(j).getWsum()));
                    
                    XSSFCell cell4 = row3.getCell(3);
                    cell4.setCellValue(Double.parseDouble(skuInfo.get(j).getWsums()));
                }
            }
            //5.输出
            //输出到磁盘中
/*            File file = new File("d:\\test.xlsx");
            if(file.exists()){
            	file.delete();
            }
            FileOutputStream fos = new FileOutputStream(file);
            workbook.write(fos);
            fos.close();*/
            
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
    public static void exportUserExcel(List<CateGory> jdList,List<CateGory> tmList,List<CateGory> jd_tmList,List<CateGory> skuInfo){
        try{
            //1.创建工作簿
        	String path = "d:/kele/kele.xlsx";//本地excel模板地址
        	//String path = "/home/CrawlerSystem/excel/kele.xlsx";//服务器excel模板地址
            FileInputStream tps = new FileInputStream(new File(path));
            XSSFWorkbook tpWorkbook = new XSSFWorkbook(tps);
            // 新建一个Excel的工作空间
            XSSFWorkbook workbook = new XSSFWorkbook();
            // 把模板复制到新建的Excel
            workbook = tpWorkbook;
            //第一个sheet页
            XSSFSheet sheet = workbook.getSheetAt(1);
            //第二个sheet页
            XSSFSheet sheet2 = workbook.getSheetAt(0);
            
            //4.操作单元格;将用户列表写入excel
            if(jdList != null){
                for(int j=0;j<jdList.size();j++)
                {
                    //创建数据行,前面有两行,头标题行和列标题行
                    XSSFRow row3 = sheet.getRow(j+5);//创建行
                    
                    XSSFCell cell2 = row3.getCell(3);//创建列
                    cell2.setCellValue(jdList.get(j).getBrand());
                    
                    XSSFCell cell3 = row3.getCell(4);
                    cell3.setCellValue(Double.parseDouble(jdList.get(j).getWsum()));
                    
                    XSSFCell cell4 = row3.getCell(5);
                    cell4.setCellValue(Double.parseDouble(jdList.get(j).getWsums()));
                }
            }
            
            //4.操作单元格;将用户列表写入excel
            if(tmList != null){
                for(int j=0;j<tmList.size();j++)
                {
                    //创建数据行,前面有两行,头标题行和列标题行
                    XSSFRow row3 = sheet.getRow(j+5);//创建行
                    
                    XSSFCell cell2 = row3.getCell(9);//创建列
                    cell2.setCellValue(tmList.get(j).getBrand());
                    
                    XSSFCell cell3 = row3.getCell(10);
                    cell3.setCellValue(Double.parseDouble(tmList.get(j).getWsum()));
                    
                    XSSFCell cell4 = row3.getCell(11);
                    cell4.setCellValue(Double.parseDouble(tmList.get(j).getWsums()));
                }
            }
            
            //4.操作单元格;将用户列表写入excel
            if(jd_tmList != null){
                for(int j=0;j<jd_tmList.size();j++)
                {
                    //创建数据行,前面有两行,头标题行和列标题行
                    XSSFRow row3 = sheet.getRow(j+5);//创建行
                    
                    XSSFCell cell2 = row3.getCell(15);//创建列
                    cell2.setCellValue(jd_tmList.get(j).getBrand());
                    
                    XSSFCell cell3 = row3.getCell(16);
                    cell3.setCellValue(Double.parseDouble(jd_tmList.get(j).getWsum()));
                    
                    XSSFCell cell4 = row3.getCell(17);
                    cell4.setCellValue(Double.parseDouble(jd_tmList.get(j).getWsums()));
                }
            }
            //4.操作单元格;将用户列表写入excel
            if(skuInfo != null){
                for(int j=0;j<skuInfo.size();j++)
                {
                    //创建数据行,前面有两行,头标题行和列标题行
                    XSSFRow row3 = sheet2.getRow(j+3);//创建行
                    
                    XSSFCell cell2 = row3.getCell(1);//创建列
                    cell2.setCellValue(skuInfo.get(j).getCategory());
                    
                    XSSFCell cell3 = row3.getCell(2);
                    cell3.setCellValue(Double.parseDouble(skuInfo.get(j).getWsum()));
                    
                    XSSFCell cell4 = row3.getCell(3);
                    cell4.setCellValue(Double.parseDouble(skuInfo.get(j).getWsums()));
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
    	List<CateGory> userList = new ArrayList<CateGory>();
    	CateGory c = new CateGory();
    	c.setBrand("1");
    	c.setWsum("11");
    	c.setWsums("111");
    	c.setMsum("1111");
    	c.setMsums("11111");
    	c.setYsum("111111");
    	c.setYsums("1111111");
    	userList.add(c);
    	CateGory c1 = new CateGory();
    	c1.setBrand("2");
    	c1.setWsum("22");
    	c1.setWsums("222");
    	c1.setMsum("2222");
    	c1.setMsums("22222");
    	c1.setYsum("222222");
    	c1.setYsums("2222222");
    	userList.add(c1);
    	//exportUserExcel(userList,userList,userList,userList);
	}
}
