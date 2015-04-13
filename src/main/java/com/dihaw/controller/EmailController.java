package com.dihaw.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(EmailController.CONTROLLER_BASE_PATH)
public class EmailController {
	
	public final static String CONTROLLER_BASE_PATH = "/email";
	
	private static String RESULT_VIEW 	= "view/email/result";
	private static String FORM_VIEW 	= "view/email/emailForm";

	@Autowired
	private JavaMailSender mailSender;
	
	@RequestMapping()
	public String showEmailForm() {
		
		return FORM_VIEW;
	}
	
	@RequestMapping(value="/send", method = RequestMethod.POST)
	public String doSendEmail(HttpServletRequest request) {
		
		// takes input from e-mail form
		String recipientAddress = request.getParameter("recipient");
		String subject = request.getParameter("subject");
		String message = request.getParameter("message");
		
		// prints debug info
		System.out.println("To: " + recipientAddress);
		System.out.println("Subject: " + subject);
		System.out.println("Message: " + message);
		
		// creates a simple e-mail object
		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
		
		simpleMailMessage.setTo(recipientAddress);
		simpleMailMessage.setSubject(subject);
		simpleMailMessage.setText(message);
		
		// sends the e-mail
		mailSender.send(simpleMailMessage);
		
		// forwards to the view named "Result"
		return RESULT_VIEW;
	}

}
