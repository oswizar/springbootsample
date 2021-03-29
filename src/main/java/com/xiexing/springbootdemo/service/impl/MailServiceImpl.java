package com.xiexing.springbootdemo.service.impl;


import com.xiexing.springbootdemo.service.IMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * MailService实现类
 */
@Service
public class MailServiceImpl implements IMailService {

//    static {
//        System.setProperty("mail.mime.splitlongparameters", "false");
//        System.setProperty("mail.mime.charset", "UTF-8");
//    }

    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String from;

    @Override
    public void sendSimpleMail(String to, String subject, String content) throws MailException {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from); // 邮件发送者
        message.setTo(to); // 邮件接受者
        message.setSubject(subject); // 主题
        message.setText(content); // 内容

        mailSender.send(message);
    }


    @Override
    public void sendInlineResourceMail(String to, String subject, String content, String rscPath, String rscId) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setFrom(from);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(content, true);
        File file = new File(rscPath);
        FileSystemResource res = new FileSystemResource(file);
        helper.addInline(rscId, res);

        mailSender.send(message);
    }

    @Override
    public void sendAttachmentsMail(String to, String subject, String content, List<String> filePaths) throws MessagingException, UnsupportedEncodingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true,"UTF-8");
//        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setFrom(from);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(content, true);

        // 多附件支持
        for(String filePath : filePaths) {
            FileSystemResource file = new FileSystemResource(new File(filePath));
            String fileName = MimeUtility.encodeWord(Objects.requireNonNull(file.getFilename(),"发送邮件获取文件名异常"), "utf-8", "B");
//            String fileName = MimeUtility.encodeWord(file.getFilename());
//            String fileName = file.getFilename();
            // 添加附件
            helper.addAttachment(fileName, file);
        }
        mailSender.send(message);
    }
}
