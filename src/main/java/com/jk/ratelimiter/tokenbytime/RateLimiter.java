package com.jk.ratelimiter.tokenbytime;

/**
 * 参照guava基于时间的令牌
 */
public class RateLimiter {

    // 令牌桶大小
    private int size;
    // 每秒多少个令牌
    private int rate;
    // 令牌生产间隔
    private long inteval;
    // 下一个令牌的执行时间
    private long next;


    public RateLimiter(int size, int rate) {
        this.size = size;
        this.rate = rate;
        this.inteval = 1 * 1000 / rate;
    }

    public void getToken(long now) throws InterruptedException {
        if (now < next) {
            Thread.sleep(next - now);
            next = next + inteval;

        } else {
            next = now + inteval;
        }
    }



}
