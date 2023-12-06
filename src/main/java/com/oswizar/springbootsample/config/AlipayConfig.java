package com.oswizar.springbootsample.config;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;

public class AlipayConfig {

    public static final AlipayClient alipayClient;

    // 应用ID,您的APPID，收款账号即为APPID对应支付宝账号
    public static final String app_id = "2016100900647273";

    // 商户私钥，您的PKCS8格式RSA2私钥
    public static final String merchant_private_key = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCoEIkpzpHYmSfzzaXrm46jt7mZ9+T4rpO15jV8t9mZklDpVt2E6hjhTNGHel0Zh1yB7TFIh0plYF9vp+xNtml6DBYSgRORSkAYf756GkNEjjxGLA3A7ZSRxcs88KUnSJGiz+N/8w+b079XF6dOMtQc49F292n+iaWlvoFMDZ4bwUsDCyDORs0jWZzcafbUJXQ8W2Q2SN8Y1PKNIxJF/D0nUAgyBMeYz3hraQw5ppBSWh/jgJKyroanuPu5x2rl4j9CoaBK8YYgH9cHl8XOSsnItqQAyYqY63LNVZndAWPwHP0m5HflNbgLNc1bDLanqPhJErapiqDLOTGm8dF3VkGxAgMBAAECggEAS794MtGZxofxC9+qYrCv73ZFPvINz0/P33IstVOfzam2shSmrttb71/SKD+sam8p8J1ZUxk3HM389bQ3uyb2Dz4XTKvEh/1xOhmJXjssCmAZJn+Ai24JQlJlsce8iCIbEcik/uwsIs2jNbvBemtKUuMTa41Dxqd/c16TpBWXJ2aSUD/ETkrRVCNWjLDrveuM9UvKkoKNP2aED0dmwFFinSgNCMDGxVw1Tehfl62P0x9LNXs/k8ngciz/XUrh10X6TZ6dxSnRJDWvc0qPxRtkrV4Wc6il6YwkG0uqaAhp+1+uYwxEN4sdUUQdLDGZdOwzpVmBYKuN8+WjpqN6EiBIAQKBgQDPqvoYRPbuj0ZvQ1T3VFVx1/O1bFG9ynwuJZGcvIzZghKfcu8JNa8jt/dELXRyuCjYZ91V//xzQS+tIdBwsXKcRPFZWpwLc1jha1X817TeDTys7LES/28TIoSb8hGaLoBOzRgl/aPfapdJ5EA8Dzw+oi7FRRkLGv4Pc9/WU5sL8QKBgQDPLfZndGXee8R106yYD/cFCXL31IMDru5/4viGRCbJvFb97gamdzUbrCHpmuSw0/kZIE2MRIK35V8EWez3Twtzvjjn5otDQld4/Zo2PLUSOUJ/ynPwuZ8zLUDzw0mgyBAka8R09Gtx3toM+AgKSVWdVSt48IsK2do+HxwHQltRwQKBgCjarRrrLl0eSLAI4gVKTQj5gBN3/NjGpgl6EJxyJ49+3GUL+mSocA0p2vZJrvgGrNpuDIgntSWDdHquBVC2SCLgGU8H2V0TUy/9qsv0L7vjhMVMR0XChLqR9t6rD7VyYoGeLZsDvCJu2NaLlet639I+m87pWhTqOYMWu7lxdzYxAoGAZ6OGb5o6m2EulgKdhDP4Tfy8Fl2obF9Fz5Oq3v+Yp7IfkkdP7FYVqxIgpMtnO03OflM6d8CN45BKR9L0R4KzJ+MwdFFPuQWm3E+ApUEZE1Sxf1u3Q+SXTvKEW2yFmHh8GrwOOrNKTFw+l947p2mn+lxwsr8hzvvLwOevDbKUHAECgYAfj0dd5w8dEWKM3B5slouup5gyG/3/cSZYu8TQixwCdFf/cKoRVC6AwF5bfMZdvaWffJhyVt5L5rEe3x6lmkoGfCu1NUsT+kXuy0sSh3VkkhIyp6mRnBdnUYCKnk9Ct91kkU96B0midywjv9zQFrxFXLJuoY002MZXwUOwj8NlIA==";

    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static final String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA8Fw/J1EMAhqpT67zS5Jg3ITcnLIPgqsaTVP34t/+1MnTx8jdJ75bs99wvzbhhIJ5pbnw6bnqB6qT9Na1WWtD6z1sPy0f6blUdKSpRzBmnDP/XbnxwiQAyrN9Q9a7n9ywxNyoSDzTrO0NWxo93aMovWnSo4OiGXR5qIin1oe/ULvNi5n4qPrWOp9vuzjneLol90JlD0awqhBxSCOXEYBAC/MFK4TamlPihGquHwwl/igO3e6xusS8IcTGZQfenS23ET0WWbTildHtIALPe0zq74t6c79Erk4OCh9L+Cu2uAG52yWkzvKILYg5mOqIXtSnfhsLWfLpgFOqDSSh9fSscwIDAQAB";

    // 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static final String notify_url = "http://b9gtj9.natappfree.cc/springboot/notify";

    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static final String return_url = "http://b9gtj9.natappfree.cc/springboot/return";

    // 签名方式
    public static final String sign_type = "RSA2";

    // 字符编码格式
    public static final String charset = "utf-8";

    // 返回数据格式
    public static final String format = "JSON";

    // 支付宝网关
    public static final String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";
    
    static {
        alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id, AlipayConfig.merchant_private_key,
                AlipayConfig.format, AlipayConfig.charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type);
    }

}

