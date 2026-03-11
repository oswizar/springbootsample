package com.oswizar.springbootsample.controller;

import com.oswizar.springbootsample.model.ResponseResult;
import com.oswizar.springbootsample.util.RedisUtils;
import com.oswizar.springbootsample.util.ZxingUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
public class HelloWorldController {

    private static final String RESULT = "result";

    @GetMapping("/index")
    public ResponseResult index() throws ExecutionException, InterruptedException {
        CompletableFuture<Void> supplyAsync = CompletableFuture.runAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(5);
//                int a = 1 / 0;
                RedisUtils.set(RESULT, Boolean.TRUE);
                System.out.println("supplyAsync");
            } catch (Exception e) {
                e.printStackTrace();
                RedisUtils.set(RESULT, Boolean.FALSE);
                throw new RuntimeException(e);
            }
            System.out.println("supplyAsync return ");
//            return "supply";
        });
        supplyAsync.whenComplete((s ,ex) -> {
                    try {
                        TimeUnit.SECONDS.sleep(5);
//                int a = 1 / 0;
                        RedisUtils.set(RESULT, Boolean.TRUE);
                        System.out.println("whenComplete");
                        System.out.println(s);
                    } catch (Exception e) {
                        e.printStackTrace();
                        RedisUtils.set(RESULT, Boolean.FALSE);
                        throw new RuntimeException(e);
                    }
                    System.out.println("whenComplete return ");
                }

        );
//        System.out.println(s);
        return ResponseResult.success("Hello World", null);
    }

    @PreAuthorize("hasAuthority({'admin'})")
    @GetMapping("/success")
    public ResponseResult success() {
        return ResponseResult.success("success", null);
    }

    @GetMapping("/helloString")
    public ResponseResult helloString() {
        Map<String, String> map = new HashMap<>();
        map.put("1", "111");
        map.put("2", "222");
        return ResponseResult.success(map);
    }

    @PostMapping("/getResult")
    public ResponseResult getResult() {
        Object result = RedisUtils.get(RESULT);
        return ResponseResult.success(result);
    }

    @GetMapping("/generateQRCode")
    public ResponseResult generateQRCode(HttpServletRequest request) {
        String contents = request.getParameter("contents");
        Map<String, Object> res = new HashMap<>();
        try {
            File qrCodeImage = ZxingUtils.getQRCodeImage(contents, 128, "/Users/oswizar/Temp/test.jpeg");
            System.out.println(qrCodeImage);
            res.put("msg", "生成二维码成功");
            return ResponseResult.success(res);
        } catch (Exception e) {
            res.put("msg", "生成二维码失败");
            res.put("desc", e.getMessage());
            e.printStackTrace();
            return ResponseResult.fail(500, "生成二维码失败：" + e.getMessage());
        }
    }
}
