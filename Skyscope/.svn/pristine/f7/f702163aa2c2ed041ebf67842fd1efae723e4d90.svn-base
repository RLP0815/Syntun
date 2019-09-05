package com.syntun.util;

import java.io.File;

public class FileListener {
	public static void main(String[] args) {
        String path = "e:/test";
        Thread listener = new Thread(new FileListenerThread(path));
        listener.start();
    }
	
}
 
class FileListenerThread implements Runnable{
 
    private String path;
    @Override
    public void run() {
        //需要监听的文件夹
        File file = new File(path);
        //原始文件中的文件数量
        int orginalSize = file.list().length;
        while(true){
            int size = file.list().length;
            if(size > orginalSize){
                System.out.println("文件新增： "+(size-orginalSize));
                orginalSize = size;
            }
            else if(size < orginalSize){
                System.out.println("文件删除，数量为： "+(orginalSize-size));
                orginalSize = size;
            }
            try {
                //睡1秒
                Thread.sleep(3000);
            } catch (InterruptedException e) {
            }
        }
    }
     
    public void setPath(String path) {
        this.path = path;
    }
    public FileListenerThread(String path){
        this.path= path;
    }
}
