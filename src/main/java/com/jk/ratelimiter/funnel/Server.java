package com.jk.ratelimiter.funnel;

import com.jk.ratelimiter.model.Request;

public class Server {

    RateLimiter rateLimiter = new RateLimiter(3, 1);

    public void acceptRequest(Request request) {
        rateLimiter.filter(request);
    }


}