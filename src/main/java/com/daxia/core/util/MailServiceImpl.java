package com.daxia.core.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;


public class MailServiceImpl implements MailService {
	/**
	 * 以文本格式发送邮件
	 * 
	 * @param mailInfo
	 *            待发送的邮件的信息
	 */
	public boolean sendTextMail(String toAddress, String subject, String content) {
		// 判断是否需要身份认证
		MailSenderInfo mailInfo = getMailInfo(toAddress, subject, content);
		MailAuthenticator authenticator = null;
		Properties pro = mailInfo.getProperties();
		if (mailInfo.isValidate()) {
			// 如果需要身份认证，则创建一个密码验证器
			authenticator = new MailAuthenticator(mailInfo.getUserName(),
					mailInfo.getPassword());
		}
		// 根据邮件会话属性和密码验证器构造一个发送邮件的session
		Session sendMailSession = Session
				.getDefaultInstance(pro, authenticator);
		try {
			// 根据session创建一个邮件消息
			Message mailMessage = new MimeMessage(sendMailSession);
			// 创建邮件发送者地址
			Address from = new InternetAddress(mailInfo.getFromAddress());
			// 设置邮件消息的发送者
			mailMessage.setFrom(from);

			// 创建邮件的接收者地址，并设置到邮件消息中
			String[] mailTo = mailInfo.getToAddress().split(";");
			Address[] to = new InternetAddress[mailTo.length];// (mailInfo.getToAddress());
			int index = 0;
			for (String mail : mailTo) {
				to[index++] = new InternetAddress(mail);
			}

			mailMessage.setRecipients(Message.RecipientType.TO, to);
			// 设置邮件消息的主题
			mailMessage.setSubject(mailInfo.getSubject());
			// 设置邮件消息发送的时间
			mailMessage.setSentDate(new Date());
			// 设置邮件消息的主要内容
			String mailContent = mailInfo.getContent();
			mailMessage.setText(mailContent);
			// 发送邮件
			Transport.send(mailMessage);
			return true;
		} catch (MessagingException ex) {
			ex.printStackTrace();
		}
		return false;
	}

	/**
	 * 以HTML格式发送邮件
	 * 
	 * @param mailInfo
	 *            待发送的邮件信息
	 */
	public boolean sendHtmlMail(String toAddress, String subject, String content) {
		MailSenderInfo mailInfo = getMailInfo(toAddress, subject, content);
		// 判断是否需要身份认证
		MailAuthenticator authenticator = null;
		Properties pro = mailInfo.getProperties();
		// 如果需要身份认证，则创建一个密码验证器
		if (mailInfo.isValidate()) {
			authenticator = new MailAuthenticator(mailInfo.getUserName(),
					mailInfo.getPassword());
		}
		// 根据邮件会话属性和密码验证器构造一个发送邮件的session
		Session sendMailSession = Session
				.getDefaultInstance(pro, authenticator);
		try {
			// 根据session创建一个邮件消息
			Message mailMessage = new MimeMessage(sendMailSession);
			// 创建邮件发送者地址
			Address from = new InternetAddress(mailInfo.getFromAddress());
			// 设置邮件消息的发送者
			mailMessage.setFrom(from);

			// 创建邮件的接收者地址，并设置到邮件消息中
			String[] mailTo = mailInfo.getToAddress().split(";");
			Address[] to = new InternetAddress[mailTo.length];// (mailInfo.getToAddress());
			int index = 0;
			for (String mail : mailTo) {
				to[index++] = new InternetAddress(mail);
			}
			mailMessage.setRecipients(Message.RecipientType.TO, to);

			// 设置邮件消息的主题
			mailMessage.setSubject(mailInfo.getSubject());
			// 设置邮件消息发送的时间
			mailMessage.setSentDate(new Date());
			// MiniMultipart类是一个容器类，包含MimeBodyPart类型的对象
			Multipart mainPart = new MimeMultipart();
			// 创建一个包含HTML内容的MimeBodyPart
			BodyPart html = new MimeBodyPart();
			// 设置HTML内容
			html.setContent(mailInfo.getContent(), "text/html; charset=utf-8");
			mainPart.addBodyPart(html);
			// 将MiniMultipart对象设置为邮件内容
			mailMessage.setContent(mainPart);
			// 发送邮件
			Transport.send(mailMessage);
			return true;
		} catch (MessagingException ex) {
			//ex.printStackTrace();
		}
		return false;
	}

	private MailSenderInfo getMailInfo(String toAddress, String subject,
			String content) {
		Properties pro = new Properties();
		MailSenderInfo mailInfo = new MailSenderInfo();
		InputStream is = MailServiceImpl.class.getClassLoader().getResourceAsStream("mail.properties");
		try {
			pro.load(is);			
			mailInfo.setMailServerHost(pro.getProperty("Mail.SMTP.Host"));		
			mailInfo.setMailServerPort(pro.getProperty("Mail.SMTP.Port"));
			mailInfo.setValidate(new Boolean(pro
					.getProperty("Mail.SMTP.Validate")));
			mailInfo.setUserName(pro.getProperty("Mail.Sender.UserName"));
			mailInfo.setPassword(pro.getProperty("Mail.Sender.Password"));// 您的邮箱密码
			mailInfo.setFromAddress(pro.getProperty("Mail.FromAddress"));
			mailInfo.setToAddress(toAddress);
			mailInfo.setSubject(subject);
			mailInfo.setContent(content);

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		return mailInfo;
	}
	
	public static void main(String args[]) {
		MailServiceImpl msi = new MailServiceImpl();
		msi.sendHtmlMail("70323985@qq.com", "测试邮件", "<h1>加油！</h1>");
	}

}

class MailAuthenticator extends Authenticator {
	String userName = null;
	String password = null;

	public MailAuthenticator() {
	}

	public MailAuthenticator(String username, String password) {
		this.userName = username;
		this.password = password;
	}

	protected PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication(userName, password);
	}
}
