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

import org.apache.commons.lang.StringUtils;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.syntun.entity.CateGory;
import com.syntun.entity.DataCheckEntry;

/**
 * excel工具类,支持批量导出
 * @author
 *
 */
public class DataCheckExcelUtil {
    
    /**
     * 
     * @param userList 用户列表
     * @param out 输出表
     */
    public static void exportUserExcel(List<DataCheckEntry> sheet1,List<DataCheckEntry> sheet2Info,List<DataCheckEntry> sheet3Info,List<DataCheckEntry> sheet4Info,List<DataCheckEntry> sheet5Info,List<DataCheckEntry> sheet6Info,List<DataCheckEntry> sheet7Info,List<DataCheckEntry> sheet8Info,HttpServletRequest request, HttpServletResponse response,String excelName){
    	  
        try{
            //1.创建工作簿
        	//String path = "d:/kele/datacheck.xlsx";//本地excel模板地址
        	String path = "/home/CrawlerSystem/excel/datacheck.xlsx";//服务器excel模板地址
            FileInputStream tps = new FileInputStream(new File(path));
            XSSFWorkbook tpWorkbook = new XSSFWorkbook(tps);
            // 新建一个Excel的工作空间
            XSSFWorkbook workbook = new XSSFWorkbook();
            // 把模板复制到新建的Excel
            workbook = tpWorkbook;
            //第一个sheet页
            //第一个sheet页
            XSSFSheet sheet = workbook.getSheetAt(0);
            //第二个sheet页
            XSSFSheet sheet2 = workbook.getSheetAt(1);
            //第3个sheet页
            XSSFSheet sheet3 = workbook.getSheetAt(2);
            //第4个sheet页
            XSSFSheet sheet4 = workbook.getSheetAt(3);
            //第5个sheet页
            XSSFSheet sheet5 = workbook.getSheetAt(4);
            //第6个sheet页
            XSSFSheet sheet6 = workbook.getSheetAt(5);
            //第7个sheet页
            XSSFSheet sheet7 = workbook.getSheetAt(6);
            //第8个sheet页
            XSSFSheet sheet8 = workbook.getSheetAt(7);
            
            //4.操作单元格;将用户列表写入excel
            if(sheet1 != null){
                for(int j=0;j<sheet1.size();j++){
                	XSSFRow row3 = null;
                	if(j>9 && j <20){
                        //创建数据行,前面有两行,头标题行和列标题行
                        row3 = sheet.getRow(j+3+4);//创建行
                	}else if(j>19 && j <30){
                		row3 = sheet.getRow(j+3+8);//创建行
                	}else if(j>29 && j <40){
                		row3 = sheet.getRow(j+3+12);//创建行
                	}else if(j>39 && j <50){
                		row3 = sheet.getRow(j+3+16);//创建行
                	}else if(j>49 && j <60){
                		row3 = sheet.getRow(j+3+20);//创建行
                	}else{
                		row3 = sheet.getRow(j+3);//创建行
                	}
                    
                    XSSFCell cell2 = row3.getCell(0);//创建列
                    if(StringUtils.isNotBlank(sheet1.get(j).getOne())){
                    	cell2.setCellValue(sheet1.get(j).getOne());
                    }
                    
                    XSSFCell cell3 = row3.getCell(1);
                    if(StringUtils.isNotBlank(sheet1.get(j).getTwo())){
                    	cell3.setCellValue(Double.parseDouble(sheet1.get(j).getTwo()));
                    }
                    
                    XSSFCell cell4 = row3.getCell(2);
                    if(StringUtils.isNotBlank(sheet1.get(j).getThree())){
                    	cell4.setCellValue(sheet1.get(j).getThree());
                    }
                    
                    XSSFCell cell5 = row3.getCell(3);
                    if(StringUtils.isNotBlank(sheet1.get(j).getFour())){
                    	 cell5.setCellValue(sheet1.get(j).getFour());
                    }
                    
                    XSSFCell cell6 = row3.getCell(4);
                    if(StringUtils.isNotBlank(sheet1.get(j).getFive())){
                    	cell6.setCellValue(Double.parseDouble(sheet1.get(j).getFive()));
                    }
                    
                    XSSFCell cell7 = row3.getCell(5);
                    if(StringUtils.isNotBlank(sheet1.get(j).getSix())){
                    	cell7.setCellValue(Double.parseDouble(sheet1.get(j).getSix()));
                    }
                    
                    XSSFCell cell8 = row3.getCell(6);
                    if(StringUtils.isNotBlank(sheet1.get(j).getSeven())){
                    	cell8.setCellValue(Double.parseDouble(sheet1.get(j).getSeven()));
                    }
                    
                    XSSFCell cell9 = row3.getCell(7);
                    if(StringUtils.isNotBlank(sheet1.get(j).getEight())){
                    	cell9.setCellValue(Double.parseDouble(sheet1.get(j).getEight()));
                    }
                    
                    XSSFCell cell10 = row3.getCell(8);
                    if(StringUtils.isNotBlank(sheet1.get(j).getNine())){
                    	cell10.setCellValue(Double.parseDouble(sheet1.get(j).getNine()));
                    }
                    
                    XSSFCell cell11 = row3.getCell(9);
                    if(StringUtils.isNotBlank(sheet1.get(j).getTen())){
                    	cell11.setCellValue(Double.parseDouble(sheet1.get(j).getTen()));
                    }
                    
                    XSSFCell cell12 = row3.getCell(10);
                    if(StringUtils.isNotBlank(sheet1.get(j).getEleven())){
                    	cell12.setCellValue(Double.parseDouble(sheet1.get(j).getEleven()));
                    }
                    
                    XSSFCell cell13 = row3.getCell(11);
                    if(StringUtils.isNotBlank(sheet1.get(j).getTwelve())){
                    	cell13.setCellValue(Double.parseDouble(sheet1.get(j).getTwelve()));
                    }
                    
                }
            }
            //4.操作单元格;将用户列表写入excel
            if(sheet2Info != null){
                for(int j=0;j<sheet2Info.size();j++){
                	XSSFRow row3 = null;
                	if(j>9 && j <20){
                        //创建数据行,前面有两行,头标题行和列标题行
                        row3 = sheet2.getRow(j+3+4);//创建行
                	}else if(j>19 && j <30){
                		row3 = sheet2.getRow(j+3+8);//创建行
                	}else if(j>29 && j <40){
                		row3 = sheet2.getRow(j+3+12);//创建行
                	}else if(j>39 && j <50){
                		row3 = sheet2.getRow(j+3+16);//创建行
                	}else if(j>49 && j <60){
                		row3 = sheet2.getRow(j+3+20);//创建行
                	}else{
                		row3 = sheet2.getRow(j+3);//创建行
                	}
                    
                    XSSFCell cell2 = row3.getCell(0);//创建列
                    if(StringUtils.isNotBlank(sheet2Info.get(j).getOne())){
                    	cell2.setCellValue(sheet2Info.get(j).getOne());
                    }
                    
                    XSSFCell cell3 = row3.getCell(1);
                    if(StringUtils.isNotBlank(sheet2Info.get(j).getTwo())){
                    	cell3.setCellValue(Double.parseDouble(sheet2Info.get(j).getTwo()));
                    }
                    
                    XSSFCell cell4 = row3.getCell(2);
                    if(StringUtils.isNotBlank(sheet2Info.get(j).getThree())){
                    	cell4.setCellValue(sheet2Info.get(j).getThree());
                    }
                    
                    XSSFCell cell5 = row3.getCell(3);
                    if(StringUtils.isNotBlank(sheet2Info.get(j).getFour())){
                    	 cell5.setCellValue(sheet2Info.get(j).getFour());
                    }
                    
                    XSSFCell cell6 = row3.getCell(4);
                    if(StringUtils.isNotBlank(sheet2Info.get(j).getFive())){
                    	cell6.setCellValue(Double.parseDouble(sheet2Info.get(j).getFive()));
                    }
                    
                    XSSFCell cell7 = row3.getCell(5);
                    if(StringUtils.isNotBlank(sheet2Info.get(j).getSix())){
                    	cell7.setCellValue(Double.parseDouble(sheet2Info.get(j).getSix()));
                    }
                    
                    XSSFCell cell8 = row3.getCell(6);
                    if(StringUtils.isNotBlank(sheet2Info.get(j).getSeven())){
                    	cell8.setCellValue(Double.parseDouble(sheet2Info.get(j).getSeven()));
                    }
                    
                    XSSFCell cell9 = row3.getCell(7);
                    if(StringUtils.isNotBlank(sheet2Info.get(j).getEight())){
                    	cell9.setCellValue(Double.parseDouble(sheet2Info.get(j).getEight()));
                    }
                    
                    XSSFCell cell10 = row3.getCell(8);
                    if(StringUtils.isNotBlank(sheet2Info.get(j).getNine())){
                    	cell10.setCellValue(Double.parseDouble(sheet2Info.get(j).getNine()));
                    }
                    
                    XSSFCell cell11 = row3.getCell(9);
                    if(StringUtils.isNotBlank(sheet2Info.get(j).getTen())){
                    	cell11.setCellValue(Double.parseDouble(sheet2Info.get(j).getTen()));
                    }
                    
                    XSSFCell cell12 = row3.getCell(10);
                    if(StringUtils.isNotBlank(sheet2Info.get(j).getEleven())){
                    	cell12.setCellValue(Double.parseDouble(sheet2Info.get(j).getEleven()));
                    }
                    
                    XSSFCell cell13 = row3.getCell(11);
                    if(StringUtils.isNotBlank(sheet2Info.get(j).getTwelve())){
                    	cell13.setCellValue(Double.parseDouble(sheet2Info.get(j).getTwelve()));
                    }
                    
                }
            }
            
            //4.操作单元格;将用户列表写入excel
            if(sheet3Info != null){
                for(int j=0;j<sheet3Info.size();j++){
                	XSSFRow row3 = null;
                	if(j>9 && j <20){
                        //创建数据行,前面有两行,头标题行和列标题行
                        row3 = sheet3.getRow(j+3+4);//创建行
                	}else if(j>19 && j <30){
                		row3 = sheet3.getRow(j+3+8);//创建行
                	}else if(j>29 && j <40){
                		row3 = sheet3.getRow(j+3+12);//创建行
                	}else if(j>39 && j <50){
                		row3 = sheet3.getRow(j+3+16);//创建行
                	}else if(j>49 && j <60){
                		row3 = sheet3.getRow(j+3+20);//创建行
                	}else{
                		row3 = sheet3.getRow(j+3);//创建行
                	}
                    
                    XSSFCell cell2 = row3.getCell(0);//创建列
                    if(StringUtils.isNotBlank(sheet3Info.get(j).getOne())){
                    	cell2.setCellValue(sheet3Info.get(j).getOne());
                    }
                    
                    XSSFCell cell3 = row3.getCell(1);
                    if(StringUtils.isNotBlank(sheet3Info.get(j).getTwo())){
                    	cell3.setCellValue(Double.parseDouble(sheet3Info.get(j).getTwo()));
                    }
                    
                    XSSFCell cell4 = row3.getCell(2);
                    if(StringUtils.isNotBlank(sheet3Info.get(j).getThree())){
                    	cell4.setCellValue(sheet3Info.get(j).getThree());
                    }
                    
                    XSSFCell cell5 = row3.getCell(3);
                    if(StringUtils.isNotBlank(sheet3Info.get(j).getFour())){
                    	 cell5.setCellValue(sheet3Info.get(j).getFour());
                    }
                    
                    XSSFCell cell6 = row3.getCell(4);
                    if(StringUtils.isNotBlank(sheet3Info.get(j).getFive())){
                    	cell6.setCellValue(Double.parseDouble(sheet3Info.get(j).getFive()));
                    }
                    
                    XSSFCell cell7 = row3.getCell(5);
                    if(StringUtils.isNotBlank(sheet3Info.get(j).getSix())){
                    	cell7.setCellValue(Double.parseDouble(sheet3Info.get(j).getSix()));
                    }
                    
                    XSSFCell cell8 = row3.getCell(6);
                    if(StringUtils.isNotBlank(sheet3Info.get(j).getSeven())){
                    	cell8.setCellValue(Double.parseDouble(sheet3Info.get(j).getSeven()));
                    }
                    
                    XSSFCell cell9 = row3.getCell(7);
                    if(StringUtils.isNotBlank(sheet3Info.get(j).getEight())){
                    	cell9.setCellValue(Double.parseDouble(sheet3Info.get(j).getEight()));
                    }
                    
                    XSSFCell cell10 = row3.getCell(8);
                    if(StringUtils.isNotBlank(sheet3Info.get(j).getNine())){
                    	cell10.setCellValue(Double.parseDouble(sheet3Info.get(j).getNine()));
                    }
                    
                    XSSFCell cell11 = row3.getCell(9);
                    if(StringUtils.isNotBlank(sheet3Info.get(j).getTen())){
                    	cell11.setCellValue(Double.parseDouble(sheet3Info.get(j).getTen()));
                    }
                    
                    XSSFCell cell12 = row3.getCell(10);
                    if(StringUtils.isNotBlank(sheet3Info.get(j).getEleven())){
                    	cell12.setCellValue(Double.parseDouble(sheet3Info.get(j).getEleven()));
                    }
                    
                    XSSFCell cell13 = row3.getCell(11);
                    if(StringUtils.isNotBlank(sheet3Info.get(j).getTwelve())){
                    	cell13.setCellValue(Double.parseDouble(sheet3Info.get(j).getTwelve()));
                    }
                    
                }
            }
            
            //4.操作单元格;将用户列表写入excel
            if(sheet4Info != null){
                for(int j=0;j<sheet4Info.size();j++){
                	XSSFRow row3 = null;
                	if(j>9 && j <20){
                        //创建数据行,前面有两行,头标题行和列标题行
                        row3 = sheet4.getRow(j+3+4);//创建行
                	}else if(j>19 && j <30){
                		row3 = sheet4.getRow(j+3+8);//创建行
                	}else if(j>29 && j <40){
                		row3 = sheet4.getRow(j+3+12);//创建行
                	}else if(j>39 && j <50){
                		row3 = sheet4.getRow(j+3+16);//创建行
                	}else if(j>49 && j <60){
                		row3 = sheet4.getRow(j+3+20);//创建行
                	}else{
                		row3 = sheet4.getRow(j+3);//创建行
                	}
                    
                    XSSFCell cell2 = row3.getCell(0);//创建列
                    if(StringUtils.isNotBlank(sheet4Info.get(j).getOne())){
                    	cell2.setCellValue(sheet4Info.get(j).getOne());
                    }
                    
                    XSSFCell cell3 = row3.getCell(1);
                    if(StringUtils.isNotBlank(sheet4Info.get(j).getTwo())){
                    	cell3.setCellValue(Double.parseDouble(sheet4Info.get(j).getTwo()));
                    }
                    
                    XSSFCell cell4 = row3.getCell(2);
                    if(StringUtils.isNotBlank(sheet4Info.get(j).getThree())){
                    	cell4.setCellValue(sheet4Info.get(j).getThree());
                    }
                    
                    XSSFCell cell5 = row3.getCell(3);
                    if(StringUtils.isNotBlank(sheet4Info.get(j).getFour())){
                    	 cell5.setCellValue(sheet4Info.get(j).getFour());
                    }
                    
                    XSSFCell cell6 = row3.getCell(4);
                    if(StringUtils.isNotBlank(sheet4Info.get(j).getFive())){
                    	cell6.setCellValue(Double.parseDouble(sheet4Info.get(j).getFive()));
                    }
                    
                    XSSFCell cell7 = row3.getCell(5);
                    if(StringUtils.isNotBlank(sheet4Info.get(j).getSix())){
                    	cell7.setCellValue(Double.parseDouble(sheet4Info.get(j).getSix()));
                    }
                    
                    XSSFCell cell8 = row3.getCell(6);
                    if(StringUtils.isNotBlank(sheet4Info.get(j).getSeven())){
                    	cell8.setCellValue(Double.parseDouble(sheet4Info.get(j).getSeven()));
                    }
                    
                    XSSFCell cell9 = row3.getCell(7);
                    if(StringUtils.isNotBlank(sheet4Info.get(j).getEight())){
                    	cell9.setCellValue(Double.parseDouble(sheet4Info.get(j).getEight()));
                    }
                    
                    XSSFCell cell10 = row3.getCell(8);
                    if(StringUtils.isNotBlank(sheet4Info.get(j).getNine())){
                    	cell10.setCellValue(Double.parseDouble(sheet4Info.get(j).getNine()));
                    }
                    
                    XSSFCell cell11 = row3.getCell(9);
                    if(StringUtils.isNotBlank(sheet4Info.get(j).getTen())){
                    	cell11.setCellValue(Double.parseDouble(sheet4Info.get(j).getTen()));
                    }
                    
                    XSSFCell cell12 = row3.getCell(10);
                    if(StringUtils.isNotBlank(sheet4Info.get(j).getEleven())){
                    	cell12.setCellValue(Double.parseDouble(sheet4Info.get(j).getEleven()));
                    }
                    
                    XSSFCell cell13 = row3.getCell(11);
                    if(StringUtils.isNotBlank(sheet4Info.get(j).getTwelve())){
                    	cell13.setCellValue(Double.parseDouble(sheet4Info.get(j).getTwelve()));
                    }
                    
                }
            }
            //4.操作单元格;将用户列表写入excel
            if(sheet5Info != null){
                for(int j=0;j<sheet5Info.size();j++){
                	XSSFRow row3 = null;
                	if(j>9 && j <20){
                        //创建数据行,前面有两行,头标题行和列标题行
                        row3 = sheet5.getRow(j+3+4);//创建行
                	}else if(j>19 && j <30){
                		row3 = sheet5.getRow(j+3+8);//创建行
                	}else if(j>29 && j <40){
                		row3 = sheet5.getRow(j+3+12);//创建行
                	}else if(j>39 && j <50){
                		row3 = sheet5.getRow(j+3+16);//创建行
                	}else if(j>49 && j <60){
                		row3 = sheet5.getRow(j+3+20);//创建行
                	}else{
                		row3 = sheet5.getRow(j+3);//创建行
                	}
                    
                    XSSFCell cell2 = row3.getCell(0);//创建列
                    if(StringUtils.isNotBlank(sheet5Info.get(j).getOne())){
                    	cell2.setCellValue(sheet5Info.get(j).getOne());
                    }
                    
                    XSSFCell cell3 = row3.getCell(1);
                    if(StringUtils.isNotBlank(sheet5Info.get(j).getTwo())){
                    	cell3.setCellValue(Double.parseDouble(sheet5Info.get(j).getTwo()));
                    }
                    
                    XSSFCell cell4 = row3.getCell(2);
                    if(StringUtils.isNotBlank(sheet5Info.get(j).getThree())){
                    	cell4.setCellValue(sheet5Info.get(j).getThree());
                    }
                    
                    XSSFCell cell5 = row3.getCell(3);
                    if(StringUtils.isNotBlank(sheet5Info.get(j).getFour())){
                    	 cell5.setCellValue(sheet5Info.get(j).getFour());
                    }
                    
                    XSSFCell cell6 = row3.getCell(4);
                    if(StringUtils.isNotBlank(sheet5Info.get(j).getFive())){
                    	cell6.setCellValue(Double.parseDouble(sheet5Info.get(j).getFive()));
                    }
                    
                    XSSFCell cell7 = row3.getCell(5);
                    if(StringUtils.isNotBlank(sheet5Info.get(j).getSix())){
                    	cell7.setCellValue(Double.parseDouble(sheet5Info.get(j).getSix()));
                    }
                    
                    XSSFCell cell8 = row3.getCell(6);
                    if(StringUtils.isNotBlank(sheet5Info.get(j).getSeven())){
                    	cell8.setCellValue(Double.parseDouble(sheet5Info.get(j).getSeven()));
                    }
                    
                    XSSFCell cell9 = row3.getCell(7);
                    if(StringUtils.isNotBlank(sheet5Info.get(j).getEight())){
                    	cell9.setCellValue(Double.parseDouble(sheet5Info.get(j).getEight()));
                    }
                    
                    XSSFCell cell10 = row3.getCell(8);
                    if(StringUtils.isNotBlank(sheet5Info.get(j).getNine())){
                    	cell10.setCellValue(Double.parseDouble(sheet5Info.get(j).getNine()));
                    }
                    
                    XSSFCell cell11 = row3.getCell(9);
                    if(StringUtils.isNotBlank(sheet5Info.get(j).getTen())){
                    	cell11.setCellValue(Double.parseDouble(sheet5Info.get(j).getTen()));
                    }
                    
                    XSSFCell cell12 = row3.getCell(10);
                    if(StringUtils.isNotBlank(sheet5Info.get(j).getEleven())){
                    	cell12.setCellValue(Double.parseDouble(sheet5Info.get(j).getEleven()));
                    }
                    
                    XSSFCell cell13 = row3.getCell(11);
                    if(StringUtils.isNotBlank(sheet5Info.get(j).getTwelve())){
                    	cell13.setCellValue(Double.parseDouble(sheet5Info.get(j).getTwelve()));
                    }
                    
                }
            }
            //4.操作单元格;将用户列表写入excel
            if(sheet6Info != null){
                for(int j=0;j<sheet6Info.size();j++){
                	XSSFRow row3 = null;
                	if(j>9 && j <20){
                        //创建数据行,前面有两行,头标题行和列标题行
                        row3 = sheet6.getRow(j+3+4);//创建行
                	}else if(j>19 && j <30){
                		row3 = sheet6.getRow(j+3+8);//创建行
                	}else if(j>29 && j <40){
                		row3 = sheet6.getRow(j+3+12);//创建行
                	}else if(j>39 && j <50){
                		row3 = sheet6.getRow(j+3+16);//创建行
                	}else if(j>49 && j <60){
                		row3 = sheet6.getRow(j+3+20);//创建行
                	}else{
                		row3 = sheet6.getRow(j+3);//创建行
                	}
                    
                    XSSFCell cell2 = row3.getCell(0);//创建列
                    if(StringUtils.isNotBlank(sheet6Info.get(j).getOne())){
                    	cell2.setCellValue(sheet6Info.get(j).getOne());
                    }
                    
                    XSSFCell cell3 = row3.getCell(1);
                    if(StringUtils.isNotBlank(sheet6Info.get(j).getTwo())){
                    	cell3.setCellValue(Double.parseDouble(sheet6Info.get(j).getTwo()));
                    }
                    
                    XSSFCell cell4 = row3.getCell(2);
                    if(StringUtils.isNotBlank(sheet6Info.get(j).getThree())){
                    	cell4.setCellValue(sheet6Info.get(j).getThree());
                    }
                    
                    XSSFCell cell5 = row3.getCell(3);
                    if(StringUtils.isNotBlank(sheet6Info.get(j).getFour())){
                    	 cell5.setCellValue(sheet6Info.get(j).getFour());
                    }
                    
                    XSSFCell cell6 = row3.getCell(4);
                    if(StringUtils.isNotBlank(sheet6Info.get(j).getFive())){
                    	cell6.setCellValue(Double.parseDouble(sheet6Info.get(j).getFive()));
                    }
                    
                    XSSFCell cell7 = row3.getCell(5);
                    if(StringUtils.isNotBlank(sheet6Info.get(j).getSix())){
                    	cell7.setCellValue(Double.parseDouble(sheet6Info.get(j).getSix()));
                    }
                    
                    XSSFCell cell8 = row3.getCell(6);
                    if(StringUtils.isNotBlank(sheet6Info.get(j).getSeven())){
                    	cell8.setCellValue(Double.parseDouble(sheet6Info.get(j).getSeven()));
                    }
                    
                    XSSFCell cell9 = row3.getCell(7);
                    if(StringUtils.isNotBlank(sheet6Info.get(j).getEight())){
                    	cell9.setCellValue(Double.parseDouble(sheet6Info.get(j).getEight()));
                    }
                    
                    XSSFCell cell10 = row3.getCell(8);
                    if(StringUtils.isNotBlank(sheet6Info.get(j).getNine())){
                    	cell10.setCellValue(Double.parseDouble(sheet6Info.get(j).getNine()));
                    }
                    
                    XSSFCell cell11 = row3.getCell(9);
                    if(StringUtils.isNotBlank(sheet6Info.get(j).getTen())){
                    	cell11.setCellValue(Double.parseDouble(sheet6Info.get(j).getTen()));
                    }
                    
                    XSSFCell cell12 = row3.getCell(10);
                    if(StringUtils.isNotBlank(sheet6Info.get(j).getEleven())){
                    	cell12.setCellValue(Double.parseDouble(sheet6Info.get(j).getEleven()));
                    }
                    
                    XSSFCell cell13 = row3.getCell(11);
                    if(StringUtils.isNotBlank(sheet6Info.get(j).getTwelve())){
                    	cell13.setCellValue(Double.parseDouble(sheet6Info.get(j).getTwelve()));
                    }
                    
                }
            }
            //4.操作单元格;将用户列表写入excel
            if(sheet7Info != null){
                for(int j=0;j<sheet7Info.size();j++){
                	XSSFRow row3 = null;
                	if(j>9 && j <20){
                        //创建数据行,前面有两行,头标题行和列标题行
                        row3 = sheet7.getRow(j+3+4);//创建行
                	}else if(j>19 && j <30){
                		row3 = sheet7.getRow(j+3+8);//创建行
                	}else if(j>29 && j <40){
                		row3 = sheet7.getRow(j+3+12);//创建行
                	}else if(j>39 && j <50){
                		row3 = sheet7.getRow(j+3+16);//创建行
                	}else if(j>49 && j <60){
                		row3 = sheet7.getRow(j+3+20);//创建行
                	}else{
                		row3 = sheet7.getRow(j+3);//创建行
                	}
                    
                    XSSFCell cell2 = row3.getCell(0);//创建列
                    if(StringUtils.isNotBlank(sheet7Info.get(j).getOne())){
                    	cell2.setCellValue(sheet7Info.get(j).getOne());
                    }
                    
                    XSSFCell cell3 = row3.getCell(1);
                    if(StringUtils.isNotBlank(sheet7Info.get(j).getTwo())){
                    	cell3.setCellValue(Double.parseDouble(sheet7Info.get(j).getTwo()));
                    }
                    
                    XSSFCell cell4 = row3.getCell(2);
                    if(StringUtils.isNotBlank(sheet7Info.get(j).getThree())){
                    	cell4.setCellValue(sheet7Info.get(j).getThree());
                    }
                    
                    XSSFCell cell5 = row3.getCell(3);
                    if(StringUtils.isNotBlank(sheet7Info.get(j).getFour())){
                    	 cell5.setCellValue(sheet7Info.get(j).getFour());
                    }
                    
                    XSSFCell cell6 = row3.getCell(4);
                    if(StringUtils.isNotBlank(sheet7Info.get(j).getFive())){
                    	cell6.setCellValue(Double.parseDouble(sheet7Info.get(j).getFive()));
                    }
                    
                    XSSFCell cell7 = row3.getCell(5);
                    if(StringUtils.isNotBlank(sheet7Info.get(j).getSix())){
                    	cell7.setCellValue(Double.parseDouble(sheet7Info.get(j).getSix()));
                    }
                    
                    XSSFCell cell8 = row3.getCell(6);
                    if(StringUtils.isNotBlank(sheet7Info.get(j).getSeven())){
                    	cell8.setCellValue(Double.parseDouble(sheet7Info.get(j).getSeven()));
                    }
                    
                    XSSFCell cell9 = row3.getCell(7);
                    if(StringUtils.isNotBlank(sheet7Info.get(j).getEight())){
                    	cell9.setCellValue(Double.parseDouble(sheet7Info.get(j).getEight()));
                    }
                    
                    XSSFCell cell10 = row3.getCell(8);
                    if(StringUtils.isNotBlank(sheet7Info.get(j).getNine())){
                    	cell10.setCellValue(Double.parseDouble(sheet7Info.get(j).getNine()));
                    }
                    
                    XSSFCell cell11 = row3.getCell(9);
                    if(StringUtils.isNotBlank(sheet7Info.get(j).getTen())){
                    	cell11.setCellValue(Double.parseDouble(sheet7Info.get(j).getTen()));
                    }
                    
                    XSSFCell cell12 = row3.getCell(10);
                    if(StringUtils.isNotBlank(sheet7Info.get(j).getEleven())){
                    	cell12.setCellValue(Double.parseDouble(sheet7Info.get(j).getEleven()));
                    }
                    
                    XSSFCell cell13 = row3.getCell(11);
                    if(StringUtils.isNotBlank(sheet7Info.get(j).getTwelve())){
                    	cell13.setCellValue(Double.parseDouble(sheet7Info.get(j).getTwelve()));
                    }
                    
                }
            }
            //4.操作单元格;将用户列表写入excel
            if(sheet8Info != null){
                for(int j=0;j<sheet8Info.size();j++){
                	XSSFRow row3 = null;
                	if(j>9 && j <20){
                        //创建数据行,前面有两行,头标题行和列标题行
                        row3 = sheet8.getRow(j+3+4);//创建行
                	}else if(j>19 && j <30){
                		row3 = sheet8.getRow(j+3+8);//创建行
                	}else if(j>29 && j <40){
                		row3 = sheet8.getRow(j+3+12);//创建行
                	}else if(j>39 && j <50){
                		row3 = sheet8.getRow(j+3+16);//创建行
                	}else if(j>49 && j <60){
                		row3 = sheet8.getRow(j+3+20);//创建行
                	}else{
                		row3 = sheet8.getRow(j+3);//创建行
                	}
                    
                    XSSFCell cell2 = row3.getCell(0);//创建列
                    if(StringUtils.isNotBlank(sheet8Info.get(j).getOne())){
                    	cell2.setCellValue(sheet8Info.get(j).getOne());
                    }
                    
                    XSSFCell cell3 = row3.getCell(1);
                    if(StringUtils.isNotBlank(sheet8Info.get(j).getTwo())){
                    	cell3.setCellValue(Double.parseDouble(sheet8Info.get(j).getTwo()));
                    }
                    
                    XSSFCell cell4 = row3.getCell(2);
                    if(StringUtils.isNotBlank(sheet8Info.get(j).getThree())){
                    	cell4.setCellValue(sheet8Info.get(j).getThree());
                    }
                    
                    XSSFCell cell5 = row3.getCell(3);
                    if(StringUtils.isNotBlank(sheet8Info.get(j).getFour())){
                    	 cell5.setCellValue(sheet8Info.get(j).getFour());
                    }
                    
                    XSSFCell cell6 = row3.getCell(4);
                    if(StringUtils.isNotBlank(sheet8Info.get(j).getFive())){
                    	cell6.setCellValue(Double.parseDouble(sheet8Info.get(j).getFive()));
                    }
                    
                    XSSFCell cell7 = row3.getCell(5);
                    if(StringUtils.isNotBlank(sheet8Info.get(j).getSix())){
                    	cell7.setCellValue(Double.parseDouble(sheet8Info.get(j).getSix()));
                    }
                    
                    XSSFCell cell8 = row3.getCell(6);
                    if(StringUtils.isNotBlank(sheet8Info.get(j).getSeven())){
                    	cell8.setCellValue(Double.parseDouble(sheet8Info.get(j).getSeven()));
                    }
                    
                    XSSFCell cell9 = row3.getCell(7);
                    if(StringUtils.isNotBlank(sheet8Info.get(j).getEight())){
                    	cell9.setCellValue(Double.parseDouble(sheet8Info.get(j).getEight()));
                    }
                    
                    XSSFCell cell10 = row3.getCell(8);
                    if(StringUtils.isNotBlank(sheet8Info.get(j).getNine())){
                    	cell10.setCellValue(Double.parseDouble(sheet8Info.get(j).getNine()));
                    }
                    
                    XSSFCell cell11 = row3.getCell(9);
                    if(StringUtils.isNotBlank(sheet8Info.get(j).getTen())){
                    	cell11.setCellValue(Double.parseDouble(sheet8Info.get(j).getTen()));
                    }
                    
                    XSSFCell cell12 = row3.getCell(10);
                    if(StringUtils.isNotBlank(sheet8Info.get(j).getEleven())){
                    	cell12.setCellValue(Double.parseDouble(sheet8Info.get(j).getEleven()));
                    }
                    
                    XSSFCell cell13 = row3.getCell(11);
                    if(StringUtils.isNotBlank(sheet8Info.get(j).getTwelve())){
                    	cell13.setCellValue(Double.parseDouble(sheet8Info.get(j).getTwelve()));
                    }
                    
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
    public static void exportUserExcel(List<DataCheckEntry> sheet1,List<DataCheckEntry> sheet2Info,List<DataCheckEntry> sheet3Info,List<DataCheckEntry> sheet4Info,List<DataCheckEntry> sheet5Info,List<DataCheckEntry> sheet6Info,List<DataCheckEntry> sheet7Info,List<DataCheckEntry> sheet8Info){
        try{
            //1.创建工作簿
        	String path = "d:/kele/datacheck.xlsx";//本地excel模板地址
        	//String path = "/home/CrawlerSystem/excel/kele.xlsx";//服务器excel模板地址
            FileInputStream tps = new FileInputStream(new File(path));
            XSSFWorkbook tpWorkbook = new XSSFWorkbook(tps);
            // 新建一个Excel的工作空间
            XSSFWorkbook workbook = new XSSFWorkbook();
            // 把模板复制到新建的Excel
            workbook = tpWorkbook;
            //第一个sheet页
            XSSFSheet sheet = workbook.getSheetAt(0);
            //第二个sheet页
            XSSFSheet sheet2 = workbook.getSheetAt(1);
            //第3个sheet页
            XSSFSheet sheet3 = workbook.getSheetAt(2);
            //第4个sheet页
            XSSFSheet sheet4 = workbook.getSheetAt(3);
            //第5个sheet页
            XSSFSheet sheet5 = workbook.getSheetAt(4);
            //第6个sheet页
            XSSFSheet sheet6 = workbook.getSheetAt(5);
            //第7个sheet页
            XSSFSheet sheet7 = workbook.getSheetAt(6);
            //第8个sheet页
            XSSFSheet sheet8 = workbook.getSheetAt(7);
            
            //4.操作单元格;将用户列表写入excel
            if(sheet1 != null){
                for(int j=0;j<sheet1.size();j++){
                	XSSFRow row3 = null;
                	if(j>9 && j <20){
                        //创建数据行,前面有两行,头标题行和列标题行
                        row3 = sheet.getRow(j+3+4);//创建行
                	}else if(j>19 && j <30){
                		row3 = sheet.getRow(j+3+8);//创建行
                	}else if(j>29 && j <40){
                		row3 = sheet.getRow(j+3+12);//创建行
                	}else if(j>39 && j <50){
                		row3 = sheet.getRow(j+3+16);//创建行
                	}else if(j>49 && j <60){
                		row3 = sheet.getRow(j+3+20);//创建行
                	}else{
                		row3 = sheet.getRow(j+3);//创建行
                	}
                    
                    XSSFCell cell2 = row3.getCell(0);//创建列
                    if(StringUtils.isNotBlank(sheet1.get(j).getOne())){
                    	cell2.setCellValue(sheet1.get(j).getOne());
                    }
                    
                    XSSFCell cell3 = row3.getCell(1);
                    if(StringUtils.isNotBlank(sheet1.get(j).getTwo())){
                    	cell3.setCellValue(Double.parseDouble(sheet1.get(j).getTwo()));
                    }
                    
                    XSSFCell cell4 = row3.getCell(2);
                    if(StringUtils.isNotBlank(sheet1.get(j).getThree())){
                    	cell4.setCellValue(sheet1.get(j).getThree());
                    }
                    
                    XSSFCell cell5 = row3.getCell(3);
                    if(StringUtils.isNotBlank(sheet1.get(j).getFour())){
                    	 cell5.setCellValue(sheet1.get(j).getFour());
                    }
                    
                    XSSFCell cell6 = row3.getCell(4);
                    if(StringUtils.isNotBlank(sheet1.get(j).getFive())){
                    	cell6.setCellValue(Double.parseDouble(sheet1.get(j).getFive()));
                    }
                    
                    XSSFCell cell7 = row3.getCell(5);
                    if(StringUtils.isNotBlank(sheet1.get(j).getSix())){
                    	cell7.setCellValue(Double.parseDouble(sheet1.get(j).getSix()));
                    }
                    
                    XSSFCell cell8 = row3.getCell(6);
                    if(StringUtils.isNotBlank(sheet1.get(j).getSeven())){
                    	cell8.setCellValue(Double.parseDouble(sheet1.get(j).getSeven()));
                    }
                    
                    XSSFCell cell9 = row3.getCell(7);
                    if(StringUtils.isNotBlank(sheet1.get(j).getEight())){
                    	cell9.setCellValue(Double.parseDouble(sheet1.get(j).getEight()));
                    }
                    
                    XSSFCell cell10 = row3.getCell(8);
                    if(StringUtils.isNotBlank(sheet1.get(j).getNine())){
                    	cell10.setCellValue(Double.parseDouble(sheet1.get(j).getNine()));
                    }
                    
                    XSSFCell cell11 = row3.getCell(9);
                    if(StringUtils.isNotBlank(sheet1.get(j).getTen())){
                    	cell11.setCellValue(Double.parseDouble(sheet1.get(j).getTen()));
                    }
                    
                    XSSFCell cell12 = row3.getCell(10);
                    if(StringUtils.isNotBlank(sheet1.get(j).getEleven())){
                    	cell12.setCellValue(Double.parseDouble(sheet1.get(j).getEleven()));
                    }
                    
                    XSSFCell cell13 = row3.getCell(11);
                    if(StringUtils.isNotBlank(sheet1.get(j).getTwelve())){
                    	cell13.setCellValue(Double.parseDouble(sheet1.get(j).getTwelve()));
                    }
                    
                }
            }
            //4.操作单元格;将用户列表写入excel
            if(sheet2Info != null){
                for(int j=0;j<sheet2Info.size();j++){
                	XSSFRow row3 = null;
                	if(j>9 && j <20){
                        //创建数据行,前面有两行,头标题行和列标题行
                        row3 = sheet2.getRow(j+3+4);//创建行
                	}else if(j>19 && j <30){
                		row3 = sheet2.getRow(j+3+8);//创建行
                	}else if(j>29 && j <40){
                		row3 = sheet2.getRow(j+3+12);//创建行
                	}else if(j>39 && j <50){
                		row3 = sheet2.getRow(j+3+16);//创建行
                	}else if(j>49 && j <60){
                		row3 = sheet2.getRow(j+3+20);//创建行
                	}else{
                		row3 = sheet2.getRow(j+3);//创建行
                	}
                    
                    XSSFCell cell2 = row3.getCell(0);//创建列
                    if(StringUtils.isNotBlank(sheet2Info.get(j).getOne())){
                    	cell2.setCellValue(sheet2Info.get(j).getOne());
                    }
                    
                    XSSFCell cell3 = row3.getCell(1);
                    if(StringUtils.isNotBlank(sheet2Info.get(j).getTwo())){
                    	cell3.setCellValue(Double.parseDouble(sheet2Info.get(j).getTwo()));
                    }
                    
                    XSSFCell cell4 = row3.getCell(2);
                    if(StringUtils.isNotBlank(sheet2Info.get(j).getThree())){
                    	cell4.setCellValue(sheet2Info.get(j).getThree());
                    }
                    
                    XSSFCell cell5 = row3.getCell(3);
                    if(StringUtils.isNotBlank(sheet2Info.get(j).getFour())){
                    	 cell5.setCellValue(sheet2Info.get(j).getFour());
                    }
                    
                    XSSFCell cell6 = row3.getCell(4);
                    if(StringUtils.isNotBlank(sheet2Info.get(j).getFive())){
                    	cell6.setCellValue(Double.parseDouble(sheet2Info.get(j).getFive()));
                    }
                    
                    XSSFCell cell7 = row3.getCell(5);
                    if(StringUtils.isNotBlank(sheet2Info.get(j).getSix())){
                    	cell7.setCellValue(Double.parseDouble(sheet2Info.get(j).getSix()));
                    }
                    
                    XSSFCell cell8 = row3.getCell(6);
                    if(StringUtils.isNotBlank(sheet2Info.get(j).getSeven())){
                    	cell8.setCellValue(Double.parseDouble(sheet2Info.get(j).getSeven()));
                    }
                    
                    XSSFCell cell9 = row3.getCell(7);
                    if(StringUtils.isNotBlank(sheet2Info.get(j).getEight())){
                    	cell9.setCellValue(Double.parseDouble(sheet2Info.get(j).getEight()));
                    }
                    
                    XSSFCell cell10 = row3.getCell(8);
                    if(StringUtils.isNotBlank(sheet2Info.get(j).getNine())){
                    	cell10.setCellValue(Double.parseDouble(sheet2Info.get(j).getNine()));
                    }
                    
                    XSSFCell cell11 = row3.getCell(9);
                    if(StringUtils.isNotBlank(sheet2Info.get(j).getTen())){
                    	cell11.setCellValue(Double.parseDouble(sheet2Info.get(j).getTen()));
                    }
                    
                    XSSFCell cell12 = row3.getCell(10);
                    if(StringUtils.isNotBlank(sheet2Info.get(j).getEleven())){
                    	cell12.setCellValue(Double.parseDouble(sheet2Info.get(j).getEleven()));
                    }
                    
                    XSSFCell cell13 = row3.getCell(11);
                    if(StringUtils.isNotBlank(sheet2Info.get(j).getTwelve())){
                    	cell13.setCellValue(Double.parseDouble(sheet2Info.get(j).getTwelve()));
                    }
                    
                }
            }
            
            //4.操作单元格;将用户列表写入excel
            if(sheet3Info != null){
                for(int j=0;j<sheet3Info.size();j++){
                	XSSFRow row3 = null;
                	if(j>9 && j <20){
                        //创建数据行,前面有两行,头标题行和列标题行
                        row3 = sheet3.getRow(j+3+4);//创建行
                	}else if(j>19 && j <30){
                		row3 = sheet3.getRow(j+3+8);//创建行
                	}else if(j>29 && j <40){
                		row3 = sheet3.getRow(j+3+12);//创建行
                	}else if(j>39 && j <50){
                		row3 = sheet3.getRow(j+3+16);//创建行
                	}else if(j>49 && j <60){
                		row3 = sheet3.getRow(j+3+20);//创建行
                	}else{
                		row3 = sheet3.getRow(j+3);//创建行
                	}
                    
                    XSSFCell cell2 = row3.getCell(0);//创建列
                    if(StringUtils.isNotBlank(sheet3Info.get(j).getOne())){
                    	cell2.setCellValue(sheet3Info.get(j).getOne());
                    }
                    
                    XSSFCell cell3 = row3.getCell(1);
                    if(StringUtils.isNotBlank(sheet3Info.get(j).getTwo())){
                    	cell3.setCellValue(Double.parseDouble(sheet3Info.get(j).getTwo()));
                    }
                    
                    XSSFCell cell4 = row3.getCell(2);
                    if(StringUtils.isNotBlank(sheet3Info.get(j).getThree())){
                    	cell4.setCellValue(sheet3Info.get(j).getThree());
                    }
                    
                    XSSFCell cell5 = row3.getCell(3);
                    if(StringUtils.isNotBlank(sheet3Info.get(j).getFour())){
                    	 cell5.setCellValue(sheet3Info.get(j).getFour());
                    }
                    
                    XSSFCell cell6 = row3.getCell(4);
                    if(StringUtils.isNotBlank(sheet3Info.get(j).getFive())){
                    	cell6.setCellValue(Double.parseDouble(sheet3Info.get(j).getFive()));
                    }
                    
                    XSSFCell cell7 = row3.getCell(5);
                    if(StringUtils.isNotBlank(sheet3Info.get(j).getSix())){
                    	cell7.setCellValue(Double.parseDouble(sheet3Info.get(j).getSix()));
                    }
                    
                    XSSFCell cell8 = row3.getCell(6);
                    if(StringUtils.isNotBlank(sheet3Info.get(j).getSeven())){
                    	cell8.setCellValue(Double.parseDouble(sheet3Info.get(j).getSeven()));
                    }
                    
                    XSSFCell cell9 = row3.getCell(7);
                    if(StringUtils.isNotBlank(sheet3Info.get(j).getEight())){
                    	cell9.setCellValue(Double.parseDouble(sheet3Info.get(j).getEight()));
                    }
                    
                    XSSFCell cell10 = row3.getCell(8);
                    if(StringUtils.isNotBlank(sheet3Info.get(j).getNine())){
                    	cell10.setCellValue(Double.parseDouble(sheet3Info.get(j).getNine()));
                    }
                    
                    XSSFCell cell11 = row3.getCell(9);
                    if(StringUtils.isNotBlank(sheet3Info.get(j).getTen())){
                    	cell11.setCellValue(Double.parseDouble(sheet3Info.get(j).getTen()));
                    }
                    
                    XSSFCell cell12 = row3.getCell(10);
                    if(StringUtils.isNotBlank(sheet3Info.get(j).getEleven())){
                    	cell12.setCellValue(Double.parseDouble(sheet3Info.get(j).getEleven()));
                    }
                    
                    XSSFCell cell13 = row3.getCell(11);
                    if(StringUtils.isNotBlank(sheet3Info.get(j).getTwelve())){
                    	cell13.setCellValue(Double.parseDouble(sheet3Info.get(j).getTwelve()));
                    }
                    
                }
            }
            
            //4.操作单元格;将用户列表写入excel
            if(sheet4Info != null){
                for(int j=0;j<sheet4Info.size();j++){
                	XSSFRow row3 = null;
                	if(j>9 && j <20){
                        //创建数据行,前面有两行,头标题行和列标题行
                        row3 = sheet4.getRow(j+3+4);//创建行
                	}else if(j>19 && j <30){
                		row3 = sheet4.getRow(j+3+8);//创建行
                	}else if(j>29 && j <40){
                		row3 = sheet4.getRow(j+3+12);//创建行
                	}else if(j>39 && j <50){
                		row3 = sheet4.getRow(j+3+16);//创建行
                	}else if(j>49 && j <60){
                		row3 = sheet4.getRow(j+3+20);//创建行
                	}else{
                		row3 = sheet4.getRow(j+3);//创建行
                	}
                    
                    XSSFCell cell2 = row3.getCell(0);//创建列
                    if(StringUtils.isNotBlank(sheet4Info.get(j).getOne())){
                    	cell2.setCellValue(sheet4Info.get(j).getOne());
                    }
                    
                    XSSFCell cell3 = row3.getCell(1);
                    if(StringUtils.isNotBlank(sheet4Info.get(j).getTwo())){
                    	cell3.setCellValue(Double.parseDouble(sheet4Info.get(j).getTwo()));
                    }
                    
                    XSSFCell cell4 = row3.getCell(2);
                    if(StringUtils.isNotBlank(sheet4Info.get(j).getThree())){
                    	cell4.setCellValue(sheet4Info.get(j).getThree());
                    }
                    
                    XSSFCell cell5 = row3.getCell(3);
                    if(StringUtils.isNotBlank(sheet4Info.get(j).getFour())){
                    	 cell5.setCellValue(sheet4Info.get(j).getFour());
                    }
                    
                    XSSFCell cell6 = row3.getCell(4);
                    if(StringUtils.isNotBlank(sheet4Info.get(j).getFive())){
                    	cell6.setCellValue(Double.parseDouble(sheet4Info.get(j).getFive()));
                    }
                    
                    XSSFCell cell7 = row3.getCell(5);
                    if(StringUtils.isNotBlank(sheet4Info.get(j).getSix())){
                    	cell7.setCellValue(Double.parseDouble(sheet4Info.get(j).getSix()));
                    }
                    
                    XSSFCell cell8 = row3.getCell(6);
                    if(StringUtils.isNotBlank(sheet4Info.get(j).getSeven())){
                    	cell8.setCellValue(Double.parseDouble(sheet4Info.get(j).getSeven()));
                    }
                    
                    XSSFCell cell9 = row3.getCell(7);
                    if(StringUtils.isNotBlank(sheet4Info.get(j).getEight())){
                    	cell9.setCellValue(Double.parseDouble(sheet4Info.get(j).getEight()));
                    }
                    
                    XSSFCell cell10 = row3.getCell(8);
                    if(StringUtils.isNotBlank(sheet4Info.get(j).getNine())){
                    	cell10.setCellValue(Double.parseDouble(sheet4Info.get(j).getNine()));
                    }
                    
                    XSSFCell cell11 = row3.getCell(9);
                    if(StringUtils.isNotBlank(sheet4Info.get(j).getTen())){
                    	cell11.setCellValue(Double.parseDouble(sheet4Info.get(j).getTen()));
                    }
                    
                    XSSFCell cell12 = row3.getCell(10);
                    if(StringUtils.isNotBlank(sheet4Info.get(j).getEleven())){
                    	cell12.setCellValue(Double.parseDouble(sheet4Info.get(j).getEleven()));
                    }
                    
                    XSSFCell cell13 = row3.getCell(11);
                    if(StringUtils.isNotBlank(sheet4Info.get(j).getTwelve())){
                    	cell13.setCellValue(Double.parseDouble(sheet4Info.get(j).getTwelve()));
                    }
                    
                }
            }
            //4.操作单元格;将用户列表写入excel
            if(sheet5Info != null){
                for(int j=0;j<sheet5Info.size();j++){
                	XSSFRow row3 = null;
                	if(j>9 && j <20){
                        //创建数据行,前面有两行,头标题行和列标题行
                        row3 = sheet5.getRow(j+3+4);//创建行
                	}else if(j>19 && j <30){
                		row3 = sheet5.getRow(j+3+8);//创建行
                	}else if(j>29 && j <40){
                		row3 = sheet5.getRow(j+3+12);//创建行
                	}else if(j>39 && j <50){
                		row3 = sheet5.getRow(j+3+16);//创建行
                	}else if(j>49 && j <60){
                		row3 = sheet5.getRow(j+3+20);//创建行
                	}else{
                		row3 = sheet5.getRow(j+3);//创建行
                	}
                    
                    XSSFCell cell2 = row3.getCell(0);//创建列
                    if(StringUtils.isNotBlank(sheet5Info.get(j).getOne())){
                    	cell2.setCellValue(sheet5Info.get(j).getOne());
                    }
                    
                    XSSFCell cell3 = row3.getCell(1);
                    if(StringUtils.isNotBlank(sheet5Info.get(j).getTwo())){
                    	cell3.setCellValue(Double.parseDouble(sheet5Info.get(j).getTwo()));
                    }
                    
                    XSSFCell cell4 = row3.getCell(2);
                    if(StringUtils.isNotBlank(sheet5Info.get(j).getThree())){
                    	cell4.setCellValue(sheet5Info.get(j).getThree());
                    }
                    
                    XSSFCell cell5 = row3.getCell(3);
                    if(StringUtils.isNotBlank(sheet5Info.get(j).getFour())){
                    	 cell5.setCellValue(sheet5Info.get(j).getFour());
                    }
                    
                    XSSFCell cell6 = row3.getCell(4);
                    if(StringUtils.isNotBlank(sheet5Info.get(j).getFive())){
                    	cell6.setCellValue(Double.parseDouble(sheet5Info.get(j).getFive()));
                    }
                    
                    XSSFCell cell7 = row3.getCell(5);
                    if(StringUtils.isNotBlank(sheet5Info.get(j).getSix())){
                    	cell7.setCellValue(Double.parseDouble(sheet5Info.get(j).getSix()));
                    }
                    
                    XSSFCell cell8 = row3.getCell(6);
                    if(StringUtils.isNotBlank(sheet5Info.get(j).getSeven())){
                    	cell8.setCellValue(Double.parseDouble(sheet5Info.get(j).getSeven()));
                    }
                    
                    XSSFCell cell9 = row3.getCell(7);
                    if(StringUtils.isNotBlank(sheet5Info.get(j).getEight())){
                    	cell9.setCellValue(Double.parseDouble(sheet5Info.get(j).getEight()));
                    }
                    
                    XSSFCell cell10 = row3.getCell(8);
                    if(StringUtils.isNotBlank(sheet5Info.get(j).getNine())){
                    	cell10.setCellValue(Double.parseDouble(sheet5Info.get(j).getNine()));
                    }
                    
                    XSSFCell cell11 = row3.getCell(9);
                    if(StringUtils.isNotBlank(sheet5Info.get(j).getTen())){
                    	cell11.setCellValue(Double.parseDouble(sheet5Info.get(j).getTen()));
                    }
                    
                    XSSFCell cell12 = row3.getCell(10);
                    if(StringUtils.isNotBlank(sheet5Info.get(j).getEleven())){
                    	cell12.setCellValue(Double.parseDouble(sheet5Info.get(j).getEleven()));
                    }
                    
                    XSSFCell cell13 = row3.getCell(11);
                    if(StringUtils.isNotBlank(sheet5Info.get(j).getTwelve())){
                    	cell13.setCellValue(Double.parseDouble(sheet5Info.get(j).getTwelve()));
                    }
                    
                }
            }
            //4.操作单元格;将用户列表写入excel
            if(sheet6Info != null){
                for(int j=0;j<sheet6Info.size();j++){
                	XSSFRow row3 = null;
                	if(j>9 && j <20){
                        //创建数据行,前面有两行,头标题行和列标题行
                        row3 = sheet6.getRow(j+3+4);//创建行
                	}else if(j>19 && j <30){
                		row3 = sheet6.getRow(j+3+8);//创建行
                	}else if(j>29 && j <40){
                		row3 = sheet6.getRow(j+3+12);//创建行
                	}else if(j>39 && j <50){
                		row3 = sheet6.getRow(j+3+16);//创建行
                	}else if(j>49 && j <60){
                		row3 = sheet6.getRow(j+3+20);//创建行
                	}else{
                		row3 = sheet6.getRow(j+3);//创建行
                	}
                    
                    XSSFCell cell2 = row3.getCell(0);//创建列
                    if(StringUtils.isNotBlank(sheet6Info.get(j).getOne())){
                    	cell2.setCellValue(sheet6Info.get(j).getOne());
                    }
                    
                    XSSFCell cell3 = row3.getCell(1);
                    if(StringUtils.isNotBlank(sheet6Info.get(j).getTwo())){
                    	cell3.setCellValue(Double.parseDouble(sheet6Info.get(j).getTwo()));
                    }
                    
                    XSSFCell cell4 = row3.getCell(2);
                    if(StringUtils.isNotBlank(sheet6Info.get(j).getThree())){
                    	cell4.setCellValue(sheet6Info.get(j).getThree());
                    }
                    
                    XSSFCell cell5 = row3.getCell(3);
                    if(StringUtils.isNotBlank(sheet6Info.get(j).getFour())){
                    	 cell5.setCellValue(sheet6Info.get(j).getFour());
                    }
                    
                    XSSFCell cell6 = row3.getCell(4);
                    if(StringUtils.isNotBlank(sheet6Info.get(j).getFive())){
                    	cell6.setCellValue(Double.parseDouble(sheet6Info.get(j).getFive()));
                    }
                    
                    XSSFCell cell7 = row3.getCell(5);
                    if(StringUtils.isNotBlank(sheet6Info.get(j).getSix())){
                    	cell7.setCellValue(Double.parseDouble(sheet6Info.get(j).getSix()));
                    }
                    
                    XSSFCell cell8 = row3.getCell(6);
                    if(StringUtils.isNotBlank(sheet6Info.get(j).getSeven())){
                    	cell8.setCellValue(Double.parseDouble(sheet6Info.get(j).getSeven()));
                    }
                    
                    XSSFCell cell9 = row3.getCell(7);
                    if(StringUtils.isNotBlank(sheet6Info.get(j).getEight())){
                    	cell9.setCellValue(Double.parseDouble(sheet6Info.get(j).getEight()));
                    }
                    
                    XSSFCell cell10 = row3.getCell(8);
                    if(StringUtils.isNotBlank(sheet6Info.get(j).getNine())){
                    	cell10.setCellValue(Double.parseDouble(sheet6Info.get(j).getNine()));
                    }
                    
                    XSSFCell cell11 = row3.getCell(9);
                    if(StringUtils.isNotBlank(sheet6Info.get(j).getTen())){
                    	cell11.setCellValue(Double.parseDouble(sheet6Info.get(j).getTen()));
                    }
                    
                    XSSFCell cell12 = row3.getCell(10);
                    if(StringUtils.isNotBlank(sheet6Info.get(j).getEleven())){
                    	cell12.setCellValue(Double.parseDouble(sheet6Info.get(j).getEleven()));
                    }
                    
                    XSSFCell cell13 = row3.getCell(11);
                    if(StringUtils.isNotBlank(sheet6Info.get(j).getTwelve())){
                    	cell13.setCellValue(Double.parseDouble(sheet6Info.get(j).getTwelve()));
                    }
                    
                }
            }
            //4.操作单元格;将用户列表写入excel
            if(sheet7Info != null){
                for(int j=0;j<sheet7Info.size();j++){
                	XSSFRow row3 = null;
                	if(j>9 && j <20){
                        //创建数据行,前面有两行,头标题行和列标题行
                        row3 = sheet7.getRow(j+3+4);//创建行
                	}else if(j>19 && j <30){
                		row3 = sheet7.getRow(j+3+8);//创建行
                	}else if(j>29 && j <40){
                		row3 = sheet7.getRow(j+3+12);//创建行
                	}else if(j>39 && j <50){
                		row3 = sheet7.getRow(j+3+16);//创建行
                	}else if(j>49 && j <60){
                		row3 = sheet7.getRow(j+3+20);//创建行
                	}else{
                		row3 = sheet7.getRow(j+3);//创建行
                	}
                    
                    XSSFCell cell2 = row3.getCell(0);//创建列
                    if(StringUtils.isNotBlank(sheet7Info.get(j).getOne())){
                    	cell2.setCellValue(sheet7Info.get(j).getOne());
                    }
                    
                    XSSFCell cell3 = row3.getCell(1);
                    if(StringUtils.isNotBlank(sheet7Info.get(j).getTwo())){
                    	cell3.setCellValue(Double.parseDouble(sheet7Info.get(j).getTwo()));
                    }
                    
                    XSSFCell cell4 = row3.getCell(2);
                    if(StringUtils.isNotBlank(sheet7Info.get(j).getThree())){
                    	cell4.setCellValue(sheet7Info.get(j).getThree());
                    }
                    
                    XSSFCell cell5 = row3.getCell(3);
                    if(StringUtils.isNotBlank(sheet7Info.get(j).getFour())){
                    	 cell5.setCellValue(sheet7Info.get(j).getFour());
                    }
                    
                    XSSFCell cell6 = row3.getCell(4);
                    if(StringUtils.isNotBlank(sheet7Info.get(j).getFive())){
                    	cell6.setCellValue(Double.parseDouble(sheet7Info.get(j).getFive()));
                    }
                    
                    XSSFCell cell7 = row3.getCell(5);
                    if(StringUtils.isNotBlank(sheet7Info.get(j).getSix())){
                    	cell7.setCellValue(Double.parseDouble(sheet7Info.get(j).getSix()));
                    }
                    
                    XSSFCell cell8 = row3.getCell(6);
                    if(StringUtils.isNotBlank(sheet7Info.get(j).getSeven())){
                    	cell8.setCellValue(Double.parseDouble(sheet7Info.get(j).getSeven()));
                    }
                    
                    XSSFCell cell9 = row3.getCell(7);
                    if(StringUtils.isNotBlank(sheet7Info.get(j).getEight())){
                    	cell9.setCellValue(Double.parseDouble(sheet7Info.get(j).getEight()));
                    }
                    
                    XSSFCell cell10 = row3.getCell(8);
                    if(StringUtils.isNotBlank(sheet7Info.get(j).getNine())){
                    	cell10.setCellValue(Double.parseDouble(sheet7Info.get(j).getNine()));
                    }
                    
                    XSSFCell cell11 = row3.getCell(9);
                    if(StringUtils.isNotBlank(sheet7Info.get(j).getTen())){
                    	cell11.setCellValue(Double.parseDouble(sheet7Info.get(j).getTen()));
                    }
                    
                    XSSFCell cell12 = row3.getCell(10);
                    if(StringUtils.isNotBlank(sheet7Info.get(j).getEleven())){
                    	cell12.setCellValue(Double.parseDouble(sheet7Info.get(j).getEleven()));
                    }
                    
                    XSSFCell cell13 = row3.getCell(11);
                    if(StringUtils.isNotBlank(sheet7Info.get(j).getTwelve())){
                    	cell13.setCellValue(Double.parseDouble(sheet7Info.get(j).getTwelve()));
                    }
                    
                }
            }
            //4.操作单元格;将用户列表写入excel
            if(sheet8Info != null){
                for(int j=0;j<sheet8Info.size();j++){
                	XSSFRow row3 = null;
                	if(j>9 && j <20){
                        //创建数据行,前面有两行,头标题行和列标题行
                        row3 = sheet8.getRow(j+3+4);//创建行
                	}else if(j>19 && j <30){
                		row3 = sheet8.getRow(j+3+8);//创建行
                	}else if(j>29 && j <40){
                		row3 = sheet8.getRow(j+3+12);//创建行
                	}else if(j>39 && j <50){
                		row3 = sheet8.getRow(j+3+16);//创建行
                	}else if(j>49 && j <60){
                		row3 = sheet8.getRow(j+3+20);//创建行
                	}else{
                		row3 = sheet8.getRow(j+3);//创建行
                	}
                    
                    XSSFCell cell2 = row3.getCell(0);//创建列
                    if(StringUtils.isNotBlank(sheet8Info.get(j).getOne())){
                    	cell2.setCellValue(sheet8Info.get(j).getOne());
                    }
                    
                    XSSFCell cell3 = row3.getCell(1);
                    if(StringUtils.isNotBlank(sheet8Info.get(j).getTwo())){
                    	cell3.setCellValue(Double.parseDouble(sheet8Info.get(j).getTwo()));
                    }
                    
                    XSSFCell cell4 = row3.getCell(2);
                    if(StringUtils.isNotBlank(sheet8Info.get(j).getThree())){
                    	cell4.setCellValue(sheet8Info.get(j).getThree());
                    }
                    
                    XSSFCell cell5 = row3.getCell(3);
                    if(StringUtils.isNotBlank(sheet8Info.get(j).getFour())){
                    	 cell5.setCellValue(sheet8Info.get(j).getFour());
                    }
                    
                    XSSFCell cell6 = row3.getCell(4);
                    if(StringUtils.isNotBlank(sheet8Info.get(j).getFive())){
                    	cell6.setCellValue(Double.parseDouble(sheet8Info.get(j).getFive()));
                    }
                    
                    XSSFCell cell7 = row3.getCell(5);
                    if(StringUtils.isNotBlank(sheet8Info.get(j).getSix())){
                    	cell7.setCellValue(Double.parseDouble(sheet8Info.get(j).getSix()));
                    }
                    
                    XSSFCell cell8 = row3.getCell(6);
                    if(StringUtils.isNotBlank(sheet8Info.get(j).getSeven())){
                    	cell8.setCellValue(Double.parseDouble(sheet8Info.get(j).getSeven()));
                    }
                    
                    XSSFCell cell9 = row3.getCell(7);
                    if(StringUtils.isNotBlank(sheet8Info.get(j).getEight())){
                    	cell9.setCellValue(Double.parseDouble(sheet8Info.get(j).getEight()));
                    }
                    
                    XSSFCell cell10 = row3.getCell(8);
                    if(StringUtils.isNotBlank(sheet8Info.get(j).getNine())){
                    	cell10.setCellValue(Double.parseDouble(sheet8Info.get(j).getNine()));
                    }
                    
                    XSSFCell cell11 = row3.getCell(9);
                    if(StringUtils.isNotBlank(sheet8Info.get(j).getTen())){
                    	cell11.setCellValue(Double.parseDouble(sheet8Info.get(j).getTen()));
                    }
                    
                    XSSFCell cell12 = row3.getCell(10);
                    if(StringUtils.isNotBlank(sheet8Info.get(j).getEleven())){
                    	cell12.setCellValue(Double.parseDouble(sheet8Info.get(j).getEleven()));
                    }
                    
                    XSSFCell cell13 = row3.getCell(11);
                    if(StringUtils.isNotBlank(sheet8Info.get(j).getTwelve())){
                    	cell13.setCellValue(Double.parseDouble(sheet8Info.get(j).getTwelve()));
                    }
                    
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
