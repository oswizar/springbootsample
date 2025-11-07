package com.oswizar.springbootsample.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradeCloseRequest;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.request.AlipayTradePrecreateRequest;
import com.alipay.api.request.AlipayTradeQueryRequest;
import com.alipay.api.response.AlipayTradeCloseResponse;
import com.alipay.api.response.AlipayTradePagePayResponse;
import com.alipay.api.response.AlipayTradePrecreateResponse;
import com.alipay.api.response.AlipayTradeQueryResponse;
import com.oswizar.springbootsample.config.AlipayConfig;
import com.oswizar.springbootsample.util.ZxingUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @date: 2019/7/8 16:44
 * @author: oswizar
 * @description:
 */
@Controller
@Slf4j
public class AliPayController {

//    private ExecutorService executorService = Executors.newFixedThreadPool(20);


    AlipayClient alipayClient = AlipayConfig.alipayClient;


    @GetMapping("/qrTest")
    public ModelAndView qrTest() {
        log.info("Invoke quTest >>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        ModelAndView mv = new ModelAndView("success");
        mv.addObject("qr","wwwwwwwwwwww");
        return mv;
    }


    /**
     * 支付宝当面付
     * @param outTradeNo
     * @param httpResponse
     * @throws Exception
     */
    @GetMapping("/tradePrecreate/{outTradeNo}")
    public void tradePrecreate(@PathVariable String outTradeNo,
                               HttpServletResponse httpResponse) throws Exception {
        AlipayTradePrecreateRequest request = new AlipayTradePrecreateRequest();

        request.setReturnUrl(AlipayConfig.return_url);
        request.setNotifyUrl(AlipayConfig.notify_url);

        Map<String, String> param = new HashMap<>();
        param.put("out_trade_no", outTradeNo);
        param.put("product_code", "FACE_TO_FACE_PAYMENT");
        param.put("total_amount", "88.88");
        param.put("subject", "iPhone8 128G");
        param.put("body", "iPhone8 128G");

        /**
         * 需要对请求的数据进行验证
         * 1.一笔单子只能发起一次创建支付请求,
         */

        String bizContext = JSON.toJSONString(param);
        log.info("请求支付宝支付接口参数为=[{}]", bizContext);
        request.setBizContent(bizContext);

        String form = "";
        try {
            //调用SDK生成表单
            AlipayTradePrecreateResponse response = alipayClient.execute(request);
            log.info("请求支付宝支付接口返回=[{}]", JSON.toJSONString(response));
            if (response.isSuccess()) {
                log.info("调用支付宝当面付接口成功:)");
                String qrCode = response.getQrCode();
                System.out.println("------qrCode-------" + qrCode);
                // 需要修改为运行机器上的路径
                String filePath = String.format("D:/tmp/qr- %s.png", response.getOutTradeNo());
                //将生成的二维码存放到指定路径
                ZxingUtils.getQRCodeImage(response.getQrCode(), 256, filePath);

            }
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        httpResponse.setContentType("text/html;charset=" + AlipayConfig.charset);
        //直接将完整的表单html输出到页面
        httpResponse.getWriter().write(form);
        httpResponse.getWriter().flush();
        httpResponse.getWriter().close();
    }


    /**
     * 支付宝网站支付
     * 对接支付宝的创建订单和支付功能
     * 注意:只有用户通过扫一扫,或者登陆支付宝跳转到支付页面才算真正[创建交易],否则查询为[交易不存在]
     *
     * @param httpResponse
     * @throws Exception
     */
    @GetMapping("/tradePagePay/{outTradeNo}")
    public void tradePagePay(@PathVariable String outTradeNo,
                             HttpServletResponse httpResponse) throws Exception {
        AlipayTradePagePayRequest request = new AlipayTradePagePayRequest();

        request.setReturnUrl(AlipayConfig.return_url);
        request.setNotifyUrl(AlipayConfig.notify_url);

        Map<String, String> param = new HashMap<>();
        param.put("out_trade_no", outTradeNo);
        param.put("product_code", "FAST_INSTANT_TRADE_PAY");
        param.put("total_amount", "88.88");
        param.put("subject", "iPhone8 128G");
        param.put("body", "iPhone8 128G");

        /**
         * 需要对请求的数据进行验证
         * 1.一笔单子只能发起一次创建支付请求,
         */

        String bizContext = JSON.toJSONString(param);
        log.info("请求支付宝支付接口参数为=[{}]", bizContext);
        request.setBizContent(bizContext);

        String form = "";
        try {
            //调用SDK生成表单
            AlipayTradePagePayResponse result = alipayClient.pageExecute(request);
            log.info("请求支付宝支付接口返回=[{}]", JSON.toJSONString(result));
            if (result.isSuccess()) {
                form = result.getBody();
            }
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        httpResponse.setContentType("text/html;charset=" + AlipayConfig.charset);
        //直接将完整的表单html输出到页面
        httpResponse.getWriter().write(form);
        httpResponse.getWriter().flush();
        httpResponse.getWriter().close();
    }


//    /**
//     * 对接支付宝的退款功能
//     *
//     * @param httpRequest
//     * @param httpResponse
//     * @return
//     * @throws Exception
//     */
//    @RequestMapping("/tradeRefund")
//    @ResponseBody
//    public String refund(HttpServletRequest httpRequest,
//                         HttpServletResponse httpResponse) throws Exception {
//        AlipayTradeRefundRequest request = new AlipayTradeRefundRequest();
//
//
//        String bizContext = "{" +
//                "\"out_trade_no\":\"20150320010101001\"," +
//                "\"refund_amount\":88.88," +
//                "\"refund_reason\":\"疑似假货,不想BB,赶紧退款!!!\"" +
//                "  }";
//        log.info("请求支付宝退款接口参数=[{}]", bizContext);
//        request.setBizContent(bizContext);
//
//        AlipayTradeRefundResponse result = alipayClient.execute(request);
//        log.info("请求支付宝退款接口返回=[{}]", JSON.toJSONString(result));
//        if (result.isSuccess()) {
//            return "退款成功!!!";
//        } else {
//            return "退款失败!!!";
//        }
//    }


    /**
     * 对接支付宝统一交易查询接口
     * 注意:只有用户通过扫一扫,或者登陆支付宝跳转到支付页面才算真正[创建交易],否则查询为[交易不存在]
     *
     * @return
     * @throws AlipayApiException
     */
    @GetMapping("/tradeQuery/{outTradeNo}")
    @ResponseBody
    public String tradeQuery(@PathVariable String outTradeNo) throws AlipayApiException {

        AlipayTradeQueryRequest request = new AlipayTradeQueryRequest();
        Map<String, String> param = new HashMap<>();

        param.put("out_trade_no", outTradeNo);

        String bizContext = JSON.toJSONString(param);
        log.info("请求支付宝统一交易查询接口参数=[{}]", bizContext);
        request.setBizContent(bizContext);

        AlipayTradeQueryResponse response = alipayClient.execute(request);
        log.info("请求支付宝统一交易查询接口返回=[{}]", JSON.toJSONString(response));
        String message;
        if (response.isSuccess()) {
            log.info("请求支付宝统一交易查询接口成功!!!");
            String tradeStatus = response.getTradeStatus();
            switch (tradeStatus) {
                case "WAIT_BUYER_PAY":
                    message = "交易创建,等待买家付款,请前往支付宝账单或交易记录完成付款";
                    break;
                case "TRADE_CLOSED":
                    message = "未付款交易超时关闭,或支付完成后全额退款";
                    break;
                case "TRADE_SUCCESS":
                    message = "交易支付成功";
                    break;
                case "TRADE_FINISHED":
                    message = "交易结束,不可退款";
                    break;
                default:
                    message = "其他错误";
                    break;
            }
        } else {
            log.info("请求支付宝统一交易查询接口失败!!!");
            String subCode = response.getSubCode();
            switch (subCode) {
                case "ACQ.SYSTEM_ERROR":
                    message = "系统错误,重新发起请求";
                    break;
                case "ACQ.INVALID_PARAMETER":
                    message = "参数无效,检查请求参数,修改后重新发起请求";
                    break;
                case "ACQ.TRADE_NOT_EXIST":
                    message = "查询的交易不存在,检查传入的交易号是否正确,修改后重新发起请求";
                    break;
                default:
                    message = "其他错误";
                    break;
            }
        }
        return message;
    }

    /**
     * 对接支付宝交易关闭接口
     * (注意两点:
     * 1.只有用户通过扫一扫,或者登陆支付宝跳转到支付页面才算真正[创建交易]
     * 2.用于[交易创建]后,用户在一定时间内未进行支付,才可调用该接口直接将未付款的交易进行关闭,否则返回为[交易不存在]
     * )
     *
     * @return
     * @throws AlipayApiException
     */
    @PostMapping("/tradeClose")
    @ResponseBody
    public String tradeClose(String outTradeNo) throws AlipayApiException {
        AlipayTradeCloseRequest request = new AlipayTradeCloseRequest();

        Map<String, String> param = new HashMap<>();

        param.put("out_trade_no", outTradeNo);

        String bizContext = JSON.toJSONString(param);

        log.info("请求支付宝关闭交易接口参数=[{}]", bizContext);
        request.setBizContent(bizContext);

        AlipayTradeCloseResponse response = alipayClient.execute(request);
        log.info("请求支付宝关闭交易接口返回=[{}]", JSON.toJSONString(response));
        if (response.isSuccess()) {
            return "关闭成功!!!";
        } else {
            return "关闭失败!!!";
        }
    }


//    /**
//     * <pre>
//     * 第一步:验证签名,签名通过后进行第二步
//     * 第二步:按一下步骤进行验证
//     * 1、商户需要验证该通知数据中的out_trade_no是否为商户系统中创建的订单号，
//     * 2、判断total_amount是否确实为该订单的实际金额（即商户订单创建时的金额），
//     * 3、校验通知中的seller_id（或者seller_email) 是否为out_trade_no这笔单据的对应的操作方（有的时候，一个商户可能有多个seller_id/seller_email），
//     * 4、验证app_id是否为该商户本身。上述1、2、3、4有任何一个验证不通过，则表明本次通知是异常通知，务必忽略。
//     * 在上述验证通过后商户必须根据支付宝不同类型的业务通知，正确的进行不同的业务处理，并且过滤重复的通知结果数据。
//     * 在支付宝的业务通知中，只有交易通知状态为TRADE_SUCCESS或TRADE_FINISHED时，支付宝才会认定为买家付款成功。
//     * </pre>
//     *
//     * @param
//     * @return
//     */
//    @RequestMapping("/callback")
//    @ResponseBody
//    public String callback(HttpServletRequest request) {
//        // 将异步通知中收到的待验证所有参数都存放到map中
//        Map<String, String> paramsMap = convertRequestParamsToMap(request);
//        String paramsJson = JSON.toJSONString(paramsMap);
//        log.info("支付宝回调，{}", paramsJson);
//        try {
//            // 调用SDK验证签名
//            boolean signVerified = AlipaySignature.rsaCheckV1(paramsMap, AlipayConfig.alipay_public_key,
//                    AlipayConfig.charset, sign_type);
//            if (signVerified) {
//                log.info("支付宝回调签名认证成功");
//                // 按照支付结果异步通知中的描述，对支付结果中的业务内容进行1\2\3\4二次校验，校验成功后在response中返回success，校验失败返回failure
////                this.check(paramsMap);
//                // 另起线程处理业务
//                executorService.execute(new Runnable() {
//                    @Override
//                    public void run() {
//                        AlipayNotifyParam param = buildAlipayNotifyParam(paramsMap);
//                        String trade_status = param.getTradeStatus();
//                        // 支付成功
//                        if (trade_status.equals(AlipayTradeStatus.TRADE_SUCCESS.getStatus())
//                                || trade_status.equals(AlipayTradeStatus.TRADE_FINISHED.getStatus())) {
//                            // 处理支付成功逻辑
//                            try {
//                                /*
//                                    // 处理业务逻辑。。。
//                                    myService.process(param);
//                                */
//
//                            } catch (Exception e) {
//                                logger.error("支付宝回调业务处理报错,params:" + paramsJson, e);
//                            }
//                        } else {
//                            logger.error("没有处理支付宝回调业务，支付宝交易状态：{},params:{}",trade_status,paramsJson);
//                        }
//                    }
//                });
//                // 如果签名验证正确，立即返回success，后续业务另起线程单独处理
//                // 业务处理失败，可查看日志进行补偿，跟支付宝已经没多大关系。
//                return "success";
//            } else {
//                logger.info("支付宝回调签名认证失败，signVerified=false, paramsJson:{}", paramsJson);
//                return "failure";
//            }
//        } catch (AlipayApiException e) {
//            logger.error("支付宝回调签名认证失败,paramsJson:{},errorMsg:{}", paramsJson, e.getMessage());
//            return "failure";
//        }
//    }


    /**
     * 支付宝服务器异步通知页面
     *
     * @param request
     * @return
     * @throws AlipayApiException
     */
    @PostMapping("/notify")
    @ResponseBody
    public String alipayNotify(HttpServletRequest request) throws AlipayApiException {
        Map<String, String> params = convertRequestParamsToMap(request);
        log.info("notify params=[{}]", JSONObject.toJSON(params));
        // 验证签名
        boolean signVerified = AlipaySignature.rsaCheckV1(params, AlipayConfig.alipay_public_key, AlipayConfig.charset, AlipayConfig.sign_type);
        log.info("notify signVerified=[{}]", signVerified);
        if (signVerified) {
            //处理你的业务逻辑，更细订单状态等
            log.info("支付宝异步通知返回成功");
            return ("验签success");
        } else {
            log.info("验证失败,不去更新状态");
            return ("验签failure");
        }
    }

    /**
     * 支付宝服务器同步通知页面
     *
     * @return
     * @throws AlipayApiException
     */
    @GetMapping("/return")
    @ResponseBody
    public String alipayReturn(HttpServletRequest request) throws AlipayApiException {
        Map<String, String> params = convertRequestParamsToMap(request);
        log.info("支付宝服务器同步通知请求参数params=[{}]", JSONObject.toJSON(params));

        // 验证签名
        boolean signVerified = AlipaySignature.rsaCheckV1(params, AlipayConfig.alipay_public_key, AlipayConfig.charset, AlipayConfig.sign_type);
        log.info("return signVerified=[{}]", signVerified);

        if (signVerified) {
            log.info("支付宝同步通知返回验签成功");
            return ("支付success");
        } else {
            log.info("验证失败,不去更新状态");
            return ("验签failure");
        }
    }


    /**
     * 将request中的参数转换成Map
     *
     * @param request
     * @return
     */
    private static Map<String, String> convertRequestParamsToMap(HttpServletRequest request) {
        Map<String, String> requestMap = new HashMap<>();

        Set<Map.Entry<String, String[]>> entrySet = request.getParameterMap().entrySet();

        for (Map.Entry<String, String[]> entry : entrySet) {
            String name = entry.getKey();
            String[] values = entry.getValue();
            int valLen = values.length;

            if (valLen == 1) {
                requestMap.put(name, values[0]);
            } else if (valLen > 1) {
                StringBuilder sb = new StringBuilder();
                for (String val : values) {
                    sb.append(",").append(val);
                }
                requestMap.put(name, sb.toString().substring(1));
            } else {
                requestMap.put(name, "");
            }
        }

        return requestMap;
    }

//    private AlipayNotifyParam buildAlipayNotifyParam(Map<String, String> params) {
//        String json = JSON.toJSONString(params);
//        return JSON.parseObject(json, AlipayNotifyParam.class);
//    }
//
//    /**
//     * 1、商户需要验证该通知数据中的out_trade_no是否为商户系统中创建的订单号，
//     * 2、判断total_amount是否确实为该订单的实际金额（即商户订单创建时的金额），
//     * 3、校验通知中的seller_id（或者seller_email)是否为out_trade_no这笔单据的对应的操作方（有的时候，一个商户可能有多个seller_id/seller_email），
//     * 4、验证app_id是否为该商户本身。上述1、2、3、4有任何一个验证不通过，则表明本次通知是异常通知，务必忽略。
//     * 在上述验证通过后商户必须根据支付宝不同类型的业务通知，正确的进行不同的业务处理，并且过滤重复的通知结果数据。
//     * 在支付宝的业务通知中，只有交易通知状态为TRADE_SUCCESS或TRADE_FINISHED时，支付宝才会认定为买家付款成功。
//     *
//     * @param params
//     * @throws AlipayApiException
//     */
//    private void check(Map<String, String> params) throws AlipayApiException {
//        String outTradeNo = params.get("out_trade_no");
//
//        // 1、商户需要验证该通知数据中的out_trade_no是否为商户系统中创建的订单号，
//        Order order = getOrderByOutTradeNo(outTradeNo); // 这个方法自己实现
//        if (order == outTradeNo) {
//            throw new AlipayApiException("out_trade_no错误");
//        }
//
//        // 2、判断total_amount是否确实为该订单的实际金额（即商户订单创建时的金额），
//        long total_amount = new BigDecimal(params.get("total_amount")).multiply(new BigDecimal(100)).longValue();
//        if (total_amount != order.getPayPrice().longValue()) {
//            throw new AlipayApiException("error total_amount");
//        }
//
//        // 3、校验通知中的seller_id（或者seller_email)是否为out_trade_no这笔单据的对应的操作方（有的时候，一个商户可能有多个seller_id/seller_email），
//        // 第三步可根据实际情况省略
//
//        // 4、验证app_id是否为该商户本身。
//        if (!params.get("app_id").equals(alipayConfig.getAppid())) {
//            throw new AlipayApiException("app_id不一致");
//        }
//    }


}
