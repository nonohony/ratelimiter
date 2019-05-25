package com.jk.ratelimiter.tokenbytime;

import com.jk.ratelimiter.model.Request;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

    private ExecutorService pool = Executors.newSingleThreadExecutor();

    RateLimiter rateLimiter = new RateLimiter(2, 1);

    public void acceptRequest(Request request) {
        pool.execute(() -> dealRequest(request));
    }

    public void dealRequest(Request request) {
        try {
            rateLimiter.getToken(System.currentTimeMillis());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(System.currentTimeMillis() + ":" + request.getStr());
    }
}