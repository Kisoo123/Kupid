package com.kupid.common;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import lombok.Builder;
import lombok.Data;
@Data
@Builder
public class MemberSendEmail {
	final String ENCODING = "utf-8";
	final String PORT = "465"; //POP3 / SMTP 설정에서 본 smtp 포트 넘버
	final String SMTPHOST = "smtp.naver.com";
	private String to;
	public MemberSendEmail(String to) {
		super();
		this.to = to;
	}
	
	public Session setting(Properties props, String user_name, String pw) {
		Session session = null;
		
		try {
			props.put("mail.transport.protocol", "smtp");
			props.put("mail.smtp.host", SMTPHOST);
			props.put("mail.smtp.port", PORT);
			props.put("mail.smtp.auth", true);
			props.put("mail.smtp.ssl.enable", true);
			props.put("mail.smtp.ssl.trust", SMTPHOST);
			props.put("mail.smtp.starttls.requeired", true);
			props.put("mail.smtp.starttls.enable", true);
			props.put("mail.smtp.ssl.protocols", "TLSv1.2");
			//props.put("mail.debug", "true"); // 디버깅 활성화

			
			props.put("mail.smtp.quit-wait", "false");
			props.put("mail.smtp.socketFactory.port", PORT);
			props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			props.put("mail.smtp.socketFactory.fallback", "false");
			
			session = Session.getInstance(props, new Authenticator() {
				@Override
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(user_name, pw);
				}
			});
		}catch(Exception e) {
			e.printStackTrace();
		}
		return session;
	}
	public int SendMail(Session session, String title, String content) {
		Message msg = new MimeMessage(session);
		int result = 0;
		try {
			msg.setFrom(new InternetAddress("kupid_79@naver.com", "KUPID 인증센터", ENCODING));//발신자 메일
			msg.addRecipient(Message.RecipientType.TO, new InternetAddress(to));//수신자 설정
			msg.setSubject(title);
			msg.setContent(content, "text/html; charset=utf-8");
			
			Transport.send(msg); //발송
			System.out.println("메일 전송 성공");
			result = 1;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("메일 전송 실패");
		}
		return result;
	}
	
}
