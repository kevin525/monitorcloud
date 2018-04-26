package com.apps.utils;

import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import com.apps.msgRemind.mail.domain.AsopMail;
import com.apps.warn.domain.WarningLog;
import com.common.constants.Constant;
import com.sys.util.Config;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

/**
 * Created by cuizhixiang on 2017/5/27.
 * 硬编码方式
 **/
public class MailUtil {
    private static final String HOST = "smtp.163.com";
    private static final Integer PORT = 25;
//    private static final String USERNAME = "15953185712@163.com";
//    private static final String PASSWORD = "czxsqm521";
//    private static final String EMAILFORM = "15953185712@163.com";
    private static final String USERNAME = "monitorCloud2018@163.com";
    private static final String PASSWORD = "htsc2018";
    private static final String EMAILFORM = "monitorCloud2018@163.com";
    private static JavaMailSenderImpl mailSender = createMailSender();
    /**
     * 邮件发送器
     *
     * @return 配置好的工具
     */
    private static JavaMailSenderImpl createMailSender() {
        JavaMailSenderImpl sender = new JavaMailSenderImpl();
        sender.setHost(HOST);
        sender.setPort(PORT);
        sender.setUsername(USERNAME);
        sender.setPassword(PASSWORD);
        sender.setDefaultEncoding("Utf-8");
        Properties p = new Properties();
        p.setProperty("mail.smtp.timeout", "2000");
        p.setProperty("mail.smtp.auth", "true");
        sender.setJavaMailProperties(p);
        return sender;
    }

    /**
     * 发送邮件
     *
     * @param to 接受人
     * @param subject 主题
     * @param html 发送内容
     * @throws MessagingException 异常
     * @throws UnsupportedEncodingException 异常
     */
    public static void sendHtmlMail(String to, String subject, String html) throws MessagingException,UnsupportedEncodingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        // 设置utf-8或GBK编码，否则邮件会有乱码
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
        messageHelper.setFrom(EMAILFORM, "云智能运维监控管理平台");
        messageHelper.setTo(to);
        messageHelper.setSubject(subject);
        messageHelper.setText(html, true);
        mailSender.send(mimeMessage);
    }

    /**
     * 发送邮件
     *
     * @param mailMap 收件人与邮件内容集合
     * @throws MessagingException 异常
     */
    public static void sendHtmlMail(Map<String, String> mailMap) throws MessagingException{
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        // 设置utf-8编码
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
        messageHelper.setFrom(EMAILFORM);
        Iterator<String> iterator = mailMap.keySet().iterator();
        while (iterator.hasNext()) {
            messageHelper.setTo(iterator.next());
            //messageHelper.setSubject(subject);
            messageHelper.setText(mailMap.get(iterator.next()), true);
            mailSender.send(mimeMessage);
        }
    }
    public static AsopMail buildAsopMail(WarningLog log,String content,String subject,String receiveMail){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		AsopMail mail = new AsopMail();
		mail.setMonitorId(log.getMonitorId());
		mail.setMonitorType(log.getMonitorType());
		mail.setContent(content);
		mail.setSubject(subject);
		mail.setReceiveAddress(receiveMail);
		mail.setSendAddress(USERNAME);
		mail.setSendDate(sdf.format(new Date()));
		mail.setMailOrder(0);
		mail.setSendStatus(Constant.MAIL_SEND_FAIL);
		return mail;
	}
}