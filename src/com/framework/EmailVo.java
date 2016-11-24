/**
 * Email类
 * @author fengtingxin
 */
package com.framework;

import java.io.File;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

public class EmailVo {

	/**
	 * 邮件发送者
	 */
	private String sender;

	/**
	 * 邮件接收者
	 */
	private String[] receivers;

	/**
	 * 邮件抄送人
	 */
	private String[] cc;

	/**
	 * 邮件抄送人
	 */
	private String[] bcc;

	/**
	 * Email发送的内容
	 */
	private String emailContent;

	/**
	 * 邮件主题
	 */
	private String subject;

	/**
	 * 邮件附件
	 */
	private File[] attachFile;

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getEmailContent() {
		return emailContent;
	}

	public void setEmailContent(String emailContent) {
		this.emailContent = emailContent;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public File[] getAttachFile() {
		return attachFile;
	}

	public void setAttachFile(File[] attachFile) {
		this.attachFile = attachFile;
	}

	public String[] getReceivers() {
		return receivers;
	}

	public void setReceivers(String[] receivers) {
		this.receivers = receivers;
	}

	public String[] getCc() {
		return cc;
	}

	public void setCc(String[] cc) {
		this.cc = cc;
	}

	public String[] getBcc() {
		return bcc;
	}

	public void setBcc(String[] bcc) {
		this.bcc = bcc;
	}

	public void sendOneEmail(EmailVo emailVo) throws MessagingException {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		JavaMailSenderImpl sender = (JavaMailSenderImpl) ctx.getBean("mailSender");
		MimeMessage mailMessage = sender.createMimeMessage();
		MimeMessageHelper messageHelper = null;
		messageHelper = new MimeMessageHelper(mailMessage, true);
		messageHelper.setFrom(emailVo.getSender());
		messageHelper.setTo(emailVo.getReceivers()[0]);
		messageHelper.setSubject("欢迎注册nullpointer");

		// true 表示启动HTML格式的邮件
		messageHelper.setText(emailVo.getEmailContent(), true);

		sender.send(mailMessage);
		System.out.println("邮件发送成功...");
	}
}
