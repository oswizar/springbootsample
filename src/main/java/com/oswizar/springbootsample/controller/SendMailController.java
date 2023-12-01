package com.oswizar.springbootsample.controller;

import com.oswizar.springbootsample.service.IMailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
public class SendMailController {

    @Autowired
    private IMailService mailService;


    /**
     * 发送普通文本邮件
     * @return
     */
    @PostMapping("/sendSimpleMail")
    public Object sendSimpleMail() {

        SimpleMailMessage message = new SimpleMailMessage();
        String to = "656806549@qq.com";
        String subject = "邮件发送测试";
//        String context = "<html><head></head><body><h3>哈哈，什么都没有</h3></body></html>";
        String context = "哈哈，什么都没有~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";

        mailService.sendSimpleMail(to,subject,context);

        return "Sent Successfully";
    }


    /**
     * 发送内容包含图片的邮件
     * @return
     */
    @PostMapping("/sendInlineResourceMail")
    public Object sendInlineResourceMail() throws MessagingException {

        SimpleMailMessage message = new SimpleMailMessage();
        String to = "656806549@qq.com";
        String subject = "今晚要加班，不用等我了";

        String rscId = "img0";
        String imgPath = "D:/Data/nike-012.jpg";

        String context = "<html><body><img width='250px' src=\'cid:" + rscId + "\'></body></html>";

        mailService.sendInlineResourceMail(to,subject,context,imgPath,rscId);

        return "Sent Successfully";
    }


    /**
     * 发送带有附件的邮件
     * @return
     */
    @PostMapping("/sendAttachmentsMail")
    public Object sendAttachmentsMail() throws Exception {



        SimpleMailMessage message = new SimpleMailMessage();
        String to = "656806549@qq.com";
//        String to = "oswizar@icloud.com";
//        String to = "oswizar@outlook.com";
//        String to = "oswizar@gmail.com";
        String subject = "速度测试：这是有附件的邮件，记得接受文件";

        List<String> filePaths = new ArrayList<>();
        // 待发送附件列表
//        filePaths.add("D:/Data/herolist.json");
//        filePaths.add("D:/Data/JPEGView_1.0.37.zip");
//        filePaths.add("D:/Data/Web.xls");
//        filePaths.add("D:/Data/成本.doc");
        filePaths.add("D:/Data/金融服务平台数据汇总20200831105518.xls");

        String context = "<html><head></head><body><h3>哈哈，什么都没有</h3></body></html>";

        mailService.sendAttachmentsMail(to,subject,context,filePaths);

        return "Sent Successfully";
    }

}
