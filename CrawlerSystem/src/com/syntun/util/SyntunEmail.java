package com.syntun.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

/**
 * 邮件处理公用类
 * 
 * @author mengjie
 * 
 */
public class SyntunEmail {
	// 邮件发送协议
	private static String protocol = "smtp";
	// SMTP邮件服务器
	private static String host = "smtp.exmail.qq.com";
	// SMTP邮件服务器默认端口
	private static String port = "25";
	// 是否要求身份认证
	private static String is_auth = "true";
	// 是否启用调试模式（启用调试模式可打印客户端与服务器交互过程时一问一答的响应消息）
	private static String is_enabled_debug_mod = "false";
	// 发件人
	private static String from = "caiji@syntun.com";
	// 账号
	private static String userName = "caiji@syntun.com";
	// 密码
	private static String userPwd = "Cai1234";
	// 收件人
	private static String to = "";
	// 初始化连接邮件服务器的会话信息
	private static Properties props = null;

	static {

		props = new Properties();
		props.setProperty("mail.transport.protocol", protocol);
		props.setProperty("mail.smtp.host", host);
		props.setProperty("mail.smtp.port", port);
		props.setProperty("mail.smtp.auth", is_auth);
		props.setProperty("mail.debug", is_enabled_debug_mod);
	}

    public static void main(String[] args) throws Exception {

        String[] emails = {  "liping.ren@syntun.com" };
//        String cont="<table style='border-spacing: 0;' ><tbody>"
//        		+ "<tr >"
//        		+ "<td  style='border:1px  solid #000000;' bgcolor=\"red\">123</td>"
//        		+ "<td  style='border:1px  solid #000000;' bgcolor:red>456</td>"
//        		+ "</tr>"
//        		+ "</tbody></table>";
        String cont = "一堆文字：：：：" + "<br>数据补充结果：<br>" + "<table > <tbody><tr data-index=\"0\"  ><td data-minwidth=\"100\">2018-06-21</td><td data-minwidth=\"100\">14</td><td data-minwidth=\"100\">京东</td> </tr><tr data-index=\"0\"  ><td data-minwidth=\"100\">2018-06-21</td><td data-minwidth=\"100\">52</td><td data-minwidth=\"100\">天猫</td> </tr><tr data-index=\"0\"  ><td data-minwidth=\"100\">2018-06-20</td><td data-minwidth=\"100\">13</td><td data-minwidth=\"100\">京东</td> </tr><tr data-index=\"0\"  ><td data-minwidth=\"100\">2018-06-20</td><td data-minwidth=\"100\">42</td><td data-minwidth=\"100\">天猫</td> </tr>  </tbody></table>";
        sendSimpleEmail(emails, "邮件主题下一轮", cont, "b", "html");
        
        // 发送带内嵌图片的HTML邮件
        // sendHtmlWithInnerImageEmail();

        // 发送混合组合邮件
        // sendMultipleEmail();

        // 发送已经生成的eml邮件
        // sendMailForEml();
    }

    /**
     * 发送简单的邮件
     * 
     * @param toEmail
     *            收件人
     * @param title
     *            主题
     * @param content
     *            内容
     * @param ccEmail
     *            抄送
     * @param type
     *            内容格式，text-文本；html-Html
     * @throws Exception 
     * @throws Exception
     */
    public static void sendSimpleEmail(String[] toEmail, String title, String content, String ccEmail, String type) throws Exception
             {
        // 创建Session实例对象
        Session session = Session.getInstance(props, new MyAuthenticator());
        // 创建MimeMessage实例对象
        MimeMessage message = new MimeMessage(session);
        // 设置发件人
			message.setFrom(new InternetAddress(from));
			// 设置邮件主题
	        message.setSubject(title);
	        // 设置收件人
	        ArrayList<String> al = new ArrayList<String>();
	        // 验证邮箱格式
	        for (String e : toEmail) {
	            if (isEmail(e)) {
	                al.add(e);
	            } else {
	                System.out.println(e + " 不是规范的email地址！");
	            }
	        }
	        Address[] adds = new Address[al.size()];
	        int i = 0;
	        for (String mail : al) {
	            adds[i] = new InternetAddress(mail);
	            i++;
	        }
	        message.setRecipients(RecipientType.TO, adds);
	        // 抄送
	        if (ccEmail != null) {
	            if (isEmail(ccEmail)) {
	                message.setRecipient(RecipientType.CC, new InternetAddress(ccEmail));
	            } else {
//	                System.out.println(ccEmail + " 不是规范的email地址");
	            }
	        }
	        // 设置发送时间
	        message.setSentDate(new Date());
	        // 设置内容
	        if (type.equals("text")) {
	            message.setText(content);
	        } else {
	            message.setContent(content, "text/html;charset=utf-8");
	        }
	        // 保存并生成最终的邮件内容
	        message.saveChanges();
	        // 获得Transport实例对象
	        Transport transport = session.getTransport();
	        // 打开连接
	        transport.connect(userName, userPwd);
	        // 将message对象传递给transport对象，将邮件发送出去
	        transport.sendMessage(message, message.getAllRecipients());
	        // 关闭连接
	        transport.close();
	        System.out.println("-----邮件已成功发送-----");
        
    }

    /**
     * 发送带内嵌图片的HTML邮件
     */
    public static void sendHtmlWithInnerImageEmail() throws MessagingException {
        // 创建Session实例对象
        Session session = Session.getInstance(props, new MyAuthenticator());
        // 创建邮件内容
        MimeMessage message = new MimeMessage(session);
        // 邮件主题,并指定编码格式
        message.setSubject("带内嵌图片的HTML邮件", "utf-8");
        // 发件人
        message.setFrom(new InternetAddress(from));
        // 收件人
        message.setRecipients(RecipientType.TO, InternetAddress.parse(to));
        // 抄送
        message.setRecipient(RecipientType.CC, new InternetAddress("java_test@sohu.com"));
        // 密送 (不会在邮件收件人名单中显示出来)
        message.setRecipient(RecipientType.BCC, new InternetAddress("417067629@qq.com"));
        // 发送时间
        message.setSentDate(new Date());

        // 创建一个MIME子类型为“related”的MimeMultipart对象
        MimeMultipart mp = new MimeMultipart("related");
        // 创建一个表示正文的MimeBodyPart对象，并将它加入到前面创建的MimeMultipart对象中
        MimeBodyPart htmlPart = new MimeBodyPart();
        mp.addBodyPart(htmlPart);
        // 创建一个表示图片资源的MimeBodyPart对象，将将它加入到前面创建的MimeMultipart对象中
        MimeBodyPart imagePart = new MimeBodyPart();
        mp.addBodyPart(imagePart);

        // 将MimeMultipart对象设置为整个邮件的内容
        message.setContent(mp);

        // 设置内嵌图片邮件体
        DataSource ds = new FileDataSource(new File("resource/firefoxlogo.png"));
        DataHandler dh = new DataHandler(ds);
        imagePart.setDataHandler(dh);
        imagePart.setContentID("firefoxlogo.png"); // 设置内容编号,用于其它邮件体引用

        // 创建一个MIME子类型为"alternative"的MimeMultipart对象，并作为前面创建的htmlPart对象的邮件内容
        MimeMultipart htmlMultipart = new MimeMultipart("alternative");
        // 创建一个表示html正文的MimeBodyPart对象
        MimeBodyPart htmlBodypart = new MimeBodyPart();
        // 其中cid=androidlogo.gif是引用邮件内部的图片，即imagePart.setContentID("androidlogo.gif");方法所保存的图片
        htmlBodypart.setContent(
                "<span style='color:red;'>这是带内嵌图片的HTML邮件哦！！！<img src=\"cid:firefoxlogo.png\" /></span>",
                "text/html;charset=utf-8");
        htmlMultipart.addBodyPart(htmlBodypart);
        htmlPart.setContent(htmlMultipart);

        // 保存并生成最终的邮件内容
        message.saveChanges();

        // 发送邮件
        Transport.send(message);
    }

    /**
     * 发送带内嵌图片、附件、多收件人(显示邮箱姓名)、邮件优先级、阅读回执的完整的HTML邮件
     */
    public static void sendMultipleEmail() throws Exception {
        String charset = "utf-8"; // 指定中文编码格式
        // 创建Session实例对象
        Session session = Session.getInstance(props, new MyAuthenticator());

        // 创建MimeMessage实例对象
        MimeMessage message = new MimeMessage(session);
        // 设置主题
        message.setSubject("使用JavaMail发送混合组合类型的邮件测试");
        // 设置发送人
        message.setFrom(new InternetAddress(from, "新浪测试邮箱", charset));
        // 设置收件人
        message.setRecipients(RecipientType.TO, new Address[] {
                // 参数1：邮箱地址，参数2：姓名（在客户端收件只显示姓名，而不显示邮件地址），参数3：姓名中文字符串编码
                new InternetAddress("java_test@sohu.com", "张三_sohu", charset),
                new InternetAddress("xyang0917@163.com", "李四_163", charset), });
        // 设置抄送
        message.setRecipient(RecipientType.CC, new InternetAddress("xyang0917@gmail.com", "王五_gmail", charset));
        // 设置密送
        message.setRecipient(RecipientType.BCC, new InternetAddress("xyang0917@qq.com", "赵六_QQ", charset));
        // 设置发送时间
        message.setSentDate(new Date());
        // 设置回复人(收件人回复此邮件时,默认收件人)
        message.setReplyTo(InternetAddress.parse("\"" + MimeUtility.encodeText("田七") + "\" <417067629@qq.com>"));
        // 设置优先级(1:紧急 3:普通 5:低)
        message.setHeader("X-Priority", "1");
        // 要求阅读回执(收件人阅读邮件时会提示回复发件人,表明邮件已收到,并已阅读)
        message.setHeader("Disposition-Notification-To", from);

        // 创建一个MIME子类型为"mixed"的MimeMultipart对象，表示这是一封混合组合类型的邮件
        MimeMultipart mailContent = new MimeMultipart("mixed");
        message.setContent(mailContent);

        // 附件
        MimeBodyPart attach1 = new MimeBodyPart();
        MimeBodyPart attach2 = new MimeBodyPart();
        // 内容
        MimeBodyPart mailBody = new MimeBodyPart();

        // 将附件和内容添加到邮件当中
        mailContent.addBodyPart(attach1);
        mailContent.addBodyPart(attach2);
        mailContent.addBodyPart(mailBody);

        // 附件1(利用jaf框架读取数据源生成邮件体)
        DataSource ds1 = new FileDataSource("resource/Earth.bmp");
        DataHandler dh1 = new DataHandler(ds1);
        attach1.setFileName(MimeUtility.encodeText("Earth.bmp"));
        attach1.setDataHandler(dh1);

        // 附件2
        DataSource ds2 = new FileDataSource("resource/如何学好C语言.txt");
        DataHandler dh2 = new DataHandler(ds2);
        attach2.setDataHandler(dh2);
        attach2.setFileName(MimeUtility.encodeText("如何学好C语言.txt"));

        // 邮件正文(内嵌图片+html文本)
        MimeMultipart body = new MimeMultipart("related"); // 邮件正文也是一个组合体,需要指明组合关系
        mailBody.setContent(body);

        // 邮件正文由html和图片构成
        MimeBodyPart imgPart = new MimeBodyPart();
        MimeBodyPart htmlPart = new MimeBodyPart();
        body.addBodyPart(imgPart);
        body.addBodyPart(htmlPart);

        // 正文图片
        DataSource ds3 = new FileDataSource("resource/firefoxlogo.png");
        DataHandler dh3 = new DataHandler(ds3);
        imgPart.setDataHandler(dh3);
        imgPart.setContentID("firefoxlogo.png");

        // html邮件内容
        MimeMultipart htmlMultipart = new MimeMultipart("alternative");
        htmlPart.setContent(htmlMultipart);
        MimeBodyPart htmlContent = new MimeBodyPart();
        htmlContent.setContent("<span style='color:red'>这是我自己用java mail发送的邮件哦！"
                + "<img src='cid:firefoxlogo.png' /></span>", "text/html;charset=gbk");
        htmlMultipart.addBodyPart(htmlContent);

        // 保存邮件内容修改
        message.saveChanges();
        // 发送邮件
        Transport.send(message);
    }

    /**
     * 将邮件内容生成eml文件
     * 
     * @param message
     *            邮件内容
     */
    public static File buildEmlFile(Message message) throws MessagingException, FileNotFoundException, IOException {
        File file = new File("c:\\" + MimeUtility.decodeText(message.getSubject()) + ".eml");
        message.writeTo(new FileOutputStream(file));
        return file;
    }

    /**
     * 发送本地已经生成好的email文件
     */
    public static void sendMailForEml(File eml) throws Exception {
        // 获得邮件会话
        Session session = Session.getInstance(props, new MyAuthenticator());
        // 获得邮件内容,即发生前生成的eml文件
        InputStream is = new FileInputStream(eml);
        MimeMessage message = new MimeMessage(session, is);
        // 发送邮件
        Transport.send(message);
    }

    /**
     * 检测邮件格式是否正确
     * 
     * @param email
     * @return
     */
    public static boolean isEmail(String email) {
        boolean result = false;
        Pattern pt = Pattern.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");
        Matcher mt = pt.matcher(email);
        result = mt.matches();
        return result;
    }

    /**
     * 向邮件服务器提交认证信息
     */
    static class MyAuthenticator extends Authenticator {

        private String username = userName;

        private String password = userPwd;

        public MyAuthenticator() {
            super();
        }

        public MyAuthenticator(String username, String password) {
            super();
            this.username = username;
            this.password = password;
        }

        @Override
        protected PasswordAuthentication getPasswordAuthentication() {

            return new PasswordAuthentication(username, password);
        }
    }
}