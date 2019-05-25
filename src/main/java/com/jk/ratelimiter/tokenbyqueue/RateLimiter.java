package com.jk.ratelimiter.tokenbyqueue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 基于生产者消费者的令牌桶
 */
public class RateLimiter {

    // 令牌桶大小
    private int size;
    // 每秒多少个令牌
    private int rate;
    // 令牌生产间隔
    private int inteval;

    BlockingQueue queue;

    public RateLimiter(int size, int rate) {
        this.size = size;
        this.rate = rate;
        this.inteval = 1 * 1000 / rate;
        queue = new LinkedBlockingQueue(size);
        startProduceToken();
    }

    private void startProduceToken() {
        new Thread(() -> {
            while (true) {
                try {
                    addToken();
                    Thread.sleep(inteval);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public Object getToken() throws InterruptedException {
        return queue.take();
    }

    public void addToken() throws InterruptedException {
        queue.put(new Object());
    }


}
