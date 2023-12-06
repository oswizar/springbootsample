package com.oswizar.springbootsample.service;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.util.List;

public interface MailService {

    /**
     * 发送普通的文本邮件
     * @param to
     * @param subject
     * @param content
     */
    void sendSimpleMail(String to, String subject, String content);

    /**
     * 发送html类型的邮件
     * @param to
     * @param subject
     * @param content
     * @param rscPath
     * @param rscId
     * @throws MessagingException
     */
    void sendInlineResourceMail(String to, String subject, String content, String rscPath, String rscId) throws MessagingException;

    /**
     * 发送带有附件的邮件
     * @param to
     * @param subject
     * @param content
     * @param filePaths
     * @throws MessagingException
     */
    void sendAttachmentsMail(String to, String subject, String content, List<String> filePaths) throws MessagingException, UnsupportedEncodingException, MessagingException;
}
