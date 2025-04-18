package com.peterpet.demo.module.member;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.internet.MimeMessage;

@Service
public class MailService {
	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	MemberDao memberDao;
	
	public void sendMailWelcome(MemberDto memberDto) throws Exception{
		MemberVo vo = new MemberVo();
    	MimeMessage mimeMessage = mailSender.createMimeMessage();
    	MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, false, "UTF-8");
    	mimeMessageHelper.setTo(memberDto.getUserEmail()); 
    	mimeMessageHelper.setSubject("PeterPet 알림");
    	mimeMessageHelper.setText("회원가입을 축하드립니다!", true);
    	mailSender.send(mimeMessage);
    }
}
