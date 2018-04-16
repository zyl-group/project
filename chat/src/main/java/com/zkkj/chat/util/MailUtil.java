package com.zkkj.chat.util;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailUtil {
	static int port = 25;
	  
//    static String server = PropertiesConfig.getProperties("mail_server");//邮件服务器mail.cpip.net.cn
//  
//    static String from =  PropertiesConfig.getProperties("mail_from");//发送者,显示的发件人名字
//  
//    static String user =  PropertiesConfig.getProperties("mail_user");//发送者邮箱地址
//  
//    static String password =  PropertiesConfig.getProperties("mail_password");//密码
	
	static String server ="smtp.sina.com";
	
	static String from ="zxhd_yf@sina.com";
	 
	static String user = "zxhd_yf@sina.com";
	
//	static String password = "ufzqaxzutmxibfcb";
	static String password = "111213";
  
     
    public static void sendEmail(String email, String subject, String body) throws UnsupportedEncodingException {
        try {
            Properties props = new Properties();
            props.put("mail.smtp.host", server);
            props.put("mail.smtp.port", port);
            props.put("mail.smtp.auth", "true");
            Transport transport = null;
            Session session = Session.getDefaultInstance(props, null);
            transport = session.getTransport("smtp");
            transport.connect(server, 587, user, password);
            MimeMessage msg = new MimeMessage(session);
            msg.setSentDate(new Date());
            InternetAddress fromAddress = new InternetAddress(user,from,"UTF-8");
            msg.setFrom(fromAddress);
            InternetAddress[] toAddress = new InternetAddress[1];
            toAddress[0] = new InternetAddress(email);
            msg.setRecipients(Message.RecipientType.TO, toAddress);
            msg.setSubject(subject, "UTF-8");  
            msg.setText(body, "UTF-8");
            msg.saveChanges();
            transport.sendMessage(msg, msg.getAllRecipients());
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
    public static void main(String args[]) throws UnsupportedEncodingException{
			sendEmail("936718688@qq.com","众享互动批量发送测试最终邮件","hello，Bulk E-mail test，please do not reply，Final interpretation lyer！The rear have a contact number，Thanks to cooperate。该邮件为批量测试邮件请勿回复，</br>The email server：smtp.163.com，The sender：lyer，QQ：936718688。");
			System.out.println("ok..");
    }

}
