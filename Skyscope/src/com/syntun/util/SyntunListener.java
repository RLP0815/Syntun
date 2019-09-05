package com.syntun.util;

import com.syntun.inspect.SynchroData;

public class SyntunListener {

	public void Listener(String tableName, String email){
		Thread listener = new Thread(new StatusListeners(tableName, email));
        listener.start();
        
	}
	public static void main(String[] args) {
        
    }
	
}
/*
 * 执行操作数据监听
 */
class StatusListeners implements Runnable{
	private String tableName;
	private String email;
	
	boolean status = true;
	
    @Override
    public void run() {
        for(int i=0; i<tableName.split(",").length; i++){
        	String tableN = tableName.split(",")[i];
        	SynchroData.HalfResult(tableN, email);
        }
    }
	public StatusListeners(String tableName, String email) {
		this.tableName= tableName;
		this.email= email;
	}

}