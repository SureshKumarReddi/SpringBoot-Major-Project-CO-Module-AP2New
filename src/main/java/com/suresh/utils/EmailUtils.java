package com.suresh.utils;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class EmailUtils {
	@Autowired
	private JavaMailSender mailSender;

	public boolean sendEmail(String subject, String body, String to) {
		boolean isMailSent = false;
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormatter.format(new Date());
		File fileName = new File("Users\\sresh\\Downloads\\"+"Triggers_" + currentDateTime + ".pdf");
		System.out.println(fileName);
		MimeMessageHelper mimeMessageHelper;
		try {
			mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
			mimeMessageHelper.setSubject(subject);
			mimeMessageHelper.setTo(to);
			mimeMessageHelper.setText(body);
			mimeMessageHelper.addAttachment("PlanNotice.pdf", fileName);
			mailSender.send(mimeMessageHelper.getMimeMessage());
			isMailSent = true;
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		return isMailSent;
	}

}
