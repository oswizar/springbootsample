package com.xiexing.springbootdemo.controller;

import lombok.extern.slf4j.Slf4j;
import com.xiexing.springbootdemo.util.JsonUtils;
import com.xiexing.springbootdemo.util.SignUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

//@RestController
@Slf4j
@Controller
public class CCBController {


    @Value("${app_key}")
    private String appKey;

    @Value("${private_key}")
    private String privateKey;


    @PostMapping("/getMerchantSign")
    public void service(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        byte[] reqBytes = null;
        InputStream in = request.getInputStream();
        ByteArrayOutputStream outt = new ByteArrayOutputStream();
        int n = 0;
        byte[] val = new byte[1024];
        while ((n = in.read(val)) > 0) {
            outt.write(val, 0, n);
        }
        reqBytes = outt.toByteArray();
        if (log.isDebugEnabled()) {
            log.debug("收到的结果：" + new String(reqBytes));
        }
        // 在协议头里面获取APP_KEY
//        String APP_KEY = request.getHeader("APP_KEY");
        String APP_KEY = appKey;
        if (log.isDebugEnabled()) {
            log.debug("获取到的APP_KEY是：" + APP_KEY);
        }
        // 获取私钥
        // Config.getInstance().getMerPriKey(APP_KEY);
//        String merchatPrKey = "privatekey string or file read";
        String merchatPrKey = privateKey;
        if (log.isDebugEnabled()) {
            log.debug("获取到的商户私钥是：" + merchatPrKey);
        }

        log.info("从配置文件读取的appKey=[{}]/n,privateKey=[{}]", appKey, privateKey);

        byte[] resp = null;
        String scrtMsg = "{}";
        String randomKey = cn.com.infosec.Random.nextString(16);
        String localStr = APP_KEY + "|" + System.currentTimeMillis() + "|" + randomKey + "|" + request.getRemoteHost();
        StringBuilder respMsg = new StringBuilder("{");
        StringBuilder head = new StringBuilder("{");
        JsonUtils.appendField(head, "Rsp_Tm", "", true);
        JsonUtils.appendField(head, "Rsp_Dt", "", true);
        JsonUtils.appendField(head, "Rqs_Jrnl_No", "", true);
        JsonUtils.appendField(head, "Sys_Evt_Trace_Id", "", true);

        if (null == APP_KEY || APP_KEY.isEmpty()) {
            JsonUtils.appendField(head, "Txn_Rsp_Cd_Dsc", "MCS0100001", true);
            JsonUtils.appendField(head, "Txn_Rsp_Inf", "未获取到APP_KEY或者APP-Key", false);

        } else if (null == merchatPrKey || merchatPrKey.isEmpty()) {
            JsonUtils.appendField(head, "Txn_Rsp_Cd_Dsc", "MCS0100002", true);
            JsonUtils.appendField(head, "Txn_Rsp_Inf", "未正确获取到商户私钥", false);
        } else {
            try {
                scrtMsg = SignUtil.getMerChantSignH5(localStr, merchatPrKey);
                JsonUtils.appendField(head, "Txn_Rsp_Cd_Dsc", "000000", true);
                JsonUtils.appendField(head, "Txn_Rsp_Inf", "商户端加签成功", false);
            } catch (Exception e) {
                if (log.isErrorEnabled()) {
                    log.error("商户加签失败：", e);
                }
                JsonUtils.appendField(head, "Txn_Rsp_Cd_Dsc", "MCS0100003", true);
                JsonUtils.appendField(head, "Txn_Rsp_Inf", "商户加签失败", false);
            }

        }
        JsonUtils.appendStruct(respMsg, "Head", head.toString(), true);
        JsonUtils.appendStruct(respMsg, "Data", scrtMsg, false);
        resp = respMsg.toString().getBytes("UTF-8");
        response.setContentLength(resp.length);
        OutputStream outputStream = response.getOutputStream();
        outputStream.write(resp);
        outputStream.flush();

    }


}
