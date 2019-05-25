package com.jk.ratelimiter.tokenbyqueue;

import com.jk.ratelimiter.model.Request;

public class Server {

    RateLimiter rateLimiter = new RateLimiter(2, 1);

    public void acceptRequest(Request request) {
        new Thread(() -> dealRequest(request)).start();
    }

    public void dealRequest(Request request) {
        try {
            rateLimiter.getToken();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(System.currentTimeMillis() + ":" + request.getStr());
    }
}