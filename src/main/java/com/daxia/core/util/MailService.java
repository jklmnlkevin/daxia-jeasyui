package com.daxia.core.util;

public interface MailService {
	boolean sendTextMail(String toAddress,String subject,String content);
	boolean sendHtmlMail(String toAddress,String subject,String content);
}
