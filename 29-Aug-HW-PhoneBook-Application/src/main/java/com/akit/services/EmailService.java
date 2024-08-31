package com.akit.services;

import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


@Service
public class EmailService {
	
	private JavaMailSender mailSender;
	
	public EmailService(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}
	
	public void sendMail(String to, String subject, String body) {
		try {
			SimpleMailMessage sm = new SimpleMailMessage();
			sm.setTo(to);
			sm.setSubject(subject);
			sm.setText(body);
			mailSender.send(sm);
		} catch (MailException e) {
			e.printStackTrace();
		}
	}

}
