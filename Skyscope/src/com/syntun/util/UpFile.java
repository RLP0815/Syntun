package com.syntun.util;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
 
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;


public class UpFile extends JFrame implements ActionListener
{
 
    private static final long serialVersionUID = 1L;
 
    JButton btn1 = null;
    JButton btn2 = null;
 
    public static String filePath = null;
    public static JTextField textField = null;
 
    public UpFile()
    {
        this.setTitle("选择文件窗口");
        FlowLayout layout = new FlowLayout();// 布局
        JLabel label = new JLabel("请选择文件：");// 标签
        textField = new JTextField(30);// 文本域
        btn1 = new JButton("浏览");// 钮1
        btn2 = new JButton("确定");// 钮2
 
        // 设置布局
        layout.setAlignment(FlowLayout.LEFT);// 左对齐
        this.setLayout(layout);
        this.setBounds(400, 200, 600, 70);
        this.setVisible(true);
        this.setResizable(false);
        //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //关闭程序。（所有窗口和进程都会关闭）
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);	//只关闭本窗口
        btn1.addActionListener(this);
        btn2.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent arg0) {
                System.out.println("点击了按钮呢");
                if(!textField.getText().equals("")){
                	filePath = textField.getText();
                	returnFilePath();
                	dispose();
                    //System.exit(0);
                }
                
            }           
        });
        this.add(label);
        this.add(textField);
        this.add(btn1);
        this.add(btn2);
 
    }
 
    public static void main(String[] args)
    {
        new UpFile();
    }
 
    @Override
    public void actionPerformed(ActionEvent e)
    {
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        chooser.showDialog(new JLabel(), "选择");
        File file = chooser.getSelectedFile();
        if(file != null){
        	textField.setText(file.getAbsoluteFile().toString());
        }
       
    }
    
    public static void returnFilePath() {
    	ZZZ.ggg();
    }
    
    public static void filePath() {
    	new UpFile();
    }
    
}
