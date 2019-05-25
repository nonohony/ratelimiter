package com.jk.ratelimiter.funnel;

import com.jk.ratelimiter.model.Request;

import java.util.concurrent.*;

/**
 * 漏桶
 */
public class RateLimiter {

    // 桶大小，超过size将被抛弃
    private int size;
    // 每秒多少个令牌
    private int rate;
    // 漏桶速度
    private int inteval;

    private ExecutorService pool;
    public RateLimiter(int size, int rate) {
        this.size = size;
        this.rate = rate;
        this.inteval = 1 * 1000 / rate;
        pool = new ThreadPoolExecutor(1, 1,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(size),  new ThreadPoolExecutor.DiscardPolicy());
    }

    public void filter(Request request) {
        pool.execute(new Thread(() -> {
            System.out.println(System.currentTimeMillis() + ":" + request.getStr());
            try {
                Thread.sleep(inteval);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }));
    }


}
