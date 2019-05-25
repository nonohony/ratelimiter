package com.jk.ratelimiter.guava;

import com.google.common.util.concurrent.RateLimiter;
import com.jk.ratelimiter.model.Request;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

    ExecutorService pool = Executors.newFixedThreadPool(10);
    RateLimiter rateLimiter = RateLimiter.create(1);

    public void acceptRequest(Request request) {
        pool.execute(new Thread(() -> {
            rateLimiter.acquire();
            System.out.println(System.currentTimeMillis() + ":" + request.getStr());
        }));
    }


}