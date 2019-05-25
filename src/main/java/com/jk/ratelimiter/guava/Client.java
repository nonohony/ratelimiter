package com.jk.ratelimiter.guava;

import com.jk.ratelimiter.model.Request;

public class Client {

    public static void main(String[] args) {
        Server server = new Server();
        for (int i = 0; i < 5; i++) {
            Request request = new Request("str"+ i);
            server.acceptRequest(request);
        }
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int i = 5; i < 15; i++) {
            Request request = new Request("str"+ i);
            server.acceptRequest(request);
        }
    }
}
