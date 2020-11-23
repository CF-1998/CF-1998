package com.myProject.EndOfTheJob.service;


import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class SendEmailService {
	@Autowired
	private JavaMailSenderImpl mailSender;
	@Value("${spring.mail.username}")
	private String from;
//    发送模板邮件方法
    public void sendTempateEmail(String to,String subject,String content) {
    	MimeMessage message = mailSender.createMimeMessage();
    	try {
    		MimeMessageHelper helper = new MimeMessageHelper(message,true);
    		helper.setFrom(from);
			helper.setTo(to);
			helper.setSubject(subject);
			helper.setText(content,true);
			mailSender.send(message);
			System.out.println("模板邮件发送成功");
		} catch (MessagingException e) {
			System.out.println("模板邮件发送失败"+e.getMessage());
			e.printStackTrace();
		}
    }
}
