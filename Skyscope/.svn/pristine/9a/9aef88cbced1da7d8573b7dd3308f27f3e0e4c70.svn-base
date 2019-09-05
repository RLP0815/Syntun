package com.syntun.inspect;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.syntun.etl.tools.BaseDao;
import com.syntun.etl.tools.BaseDaoSqlServer;
import com.syntun.etl.tools.ConvertSql;
import com.syntun.etl.tools.InsertData138;
import com.syntun.util.SyntunEmail;

public class SynchroData {
	
	public static void HalfResult(String tableName, String email) {
		List<String> sqlResult = new ArrayList<String>();
		String tableNameCopy = BaseDao.copyTable(tableName);
		List<String> filedList = BaseDao.getField138(tableName);
		String tableName16 = "["+tableName.replace("syntun_v2", "syntun_base.dbo").replace(".", "].[")+"]";
		List<HashMap<String, String>> newDataList = BaseDaoSqlServer.getTbaleListAll16(tableName16, "192.168.0.16", "syntun_base");
		for(HashMap<String, String> map : newDataList){
			String mysql = ConvertSql.getSql(tableNameCopy, filedList, map);
			sqlResult.add(mysql);
		}
		Thread t1 = new Thread(new InsertData138(sqlResult));
		t1.start();
		
		boolean isAlice = true;
		
		while(isAlice){
			if(!t1.isAlive()){
				isAlice = false;
				try {
					BaseDao.deleteTbaleData(tableName);
					String text = tableName + "  表格数据导入执行完成，请注意核查!";
					System.out.println(text);
					String[] emails = {email};	
					SyntunEmail.sendSimpleEmail(emails, "数据迁移", text, "b", "text");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
}
