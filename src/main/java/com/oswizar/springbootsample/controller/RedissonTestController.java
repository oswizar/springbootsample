package com.oswizar.springbootsample.controller;

import com.oswizar.springbootsample.util.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
@Slf4j
public class RedissonTestController {

    @Autowired
    private RedissonClient redissonClient;

    final static String FLASH_SALE_GOODS_STOCK = "FLASH_SALE_GOODS_STOCK";
    final static String FLASH_SALE_LOCK_KEY = "FLASH_SALE_LOCK_KEY";

    @GetMapping("/redisLockTest")
    public void redisLockTest() {
        // 初始化秒杀库存数量
        RedisUtils.set(FLASH_SALE_GOODS_STOCK, 10);
        List<Future<Integer>> futureList = new ArrayList<>();
        // 多线程异步执行
        ExecutorService executors = Executors.newCachedThreadPool();
        // 模拟用户秒杀
        for (int i = 0; i < 1000; i++) {
            futureList.add(executors.submit(this::lockStock));
            try {
                Thread.sleep((long) (Math.random() * 10));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        // 等待结果，防止主线程退出
        AtomicInteger stockSum = new AtomicInteger();
        futureList.forEach(t -> {
            try {
                stockSum.addAndGet(t.get());
                log.info("成功秒杀数量:{}", stockSum);
            } catch (Exception e) {
                log.error("get stock num error", e);
            }
        });
    }

    private int lockStock() {
        RLock lock = redissonClient.getLock(FLASH_SALE_LOCK_KEY);
        if (lock.tryLock()) {
            try {
                log.info("加锁成功😁😁😁");
                int stockNum = (int) RedisUtils.get(FLASH_SALE_GOODS_STOCK);
                if (stockNum > 0) {
                    RedisUtils.decr(FLASH_SALE_GOODS_STOCK, 1);
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    stockNum--;
                    log.info("秒杀成功,剩余库存:{}", stockNum);
                    //获取库存扣减数量
                    return 1;
                } else {
                    log.error("秒杀失败,剩余库存:{}", stockNum);
                }
            } catch (Exception e) {
                log.error("decry stock error", e);
            } finally {
                lock.unlock();
            }
        } else {
            log.error("加锁失败😭😭😭");
        }
        return 0;
    }
}
