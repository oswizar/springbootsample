package com.xiexing.springbootdemo.util;

import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import cn.com.infosec.AESUtil;
import cn.com.infosec.Base64;
import cn.com.infosec.RSAUtil;
import lombok.extern.slf4j.Slf4j;
import sun.misc.BASE64Decoder;

@Slf4j
public class SignUtil {

    public static String getMerChantSignH5(String localStr, String merchatPrKey)  throws Exception{

        StringBuilder respMsg = new StringBuilder("{");
        try {
            String scrtData = Base64.encode(localStr.getBytes("UTF-8"));
            JsonUtils.appendField(respMsg, "ScrtData", scrtData, true);

            log.info("Base64编码处理得到ScrtData = [{}]",scrtData);

            String scrtSgn = RSAUtil.sign(localStr.getBytes("UTF-8"), getPrivateKey(merchatPrKey));
            JsonUtils.appendField(respMsg, "ScrtSgn", scrtSgn, false);
        } catch (Exception e) {
            if (log.isErrorEnabled()) {
                log.error("商户服务端要认证H5 SDK发送的签名请求，认证失败", e);
            }
            throw e;
        }
        return respMsg.toString();
    }

    private static PrivateKey getPrivateKey(String privateKey) throws Exception {
        PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(new BASE64Decoder().decodeBuffer(privateKey));
        PrivateKey priKey = KeyFactory.getInstance("RSA").generatePrivate(pkcs8EncodedKeySpec);
        return priKey;
    }

}
