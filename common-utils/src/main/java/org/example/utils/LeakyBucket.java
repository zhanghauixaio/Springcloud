package org.example.utils;

/**
 * LeakyBucket 类表示一个漏桶,
 * 包含了桶的容量和漏桶出水速率等参数，
 * 以及当前桶中的水量和上次漏水时间戳等状态。
 */
public class LeakyBucket {
    private final long capacity;    // 桶的容量
    private final long rate;        // 漏桶出水速率
    private long water;             // 当前桶中的水量
    private long lastLeakTimestamp; // 上次漏水时间戳

    public LeakyBucket(long capacity, long rate) {
        this.capacity = capacity;
        this.rate = rate;
        this.water = 0;
        this.lastLeakTimestamp = System.currentTimeMillis();
    }

    /**
     * tryConsume() 方法用于尝试向桶中放入一定量的水，如果桶中还有足够的空间，则返回 true，否则返回 false。
     * @param waterRequested
     * @return
     */
    public synchronized boolean tryConsume(long waterRequested) {
        leak();
        if (water + waterRequested <= capacity) {
            water += waterRequested;
            return true;
        } else {
            return false;
        }
    }

    /**
     * 。leak() 方法用于漏水，根据当前时间和上次漏水时间戳计算出应该漏出的水量，然后更新桶中的水量和漏水时间戳等状态。
     */
    private void leak() {
        long now = System.currentTimeMillis();
        long elapsedTime = now - lastLeakTimestamp;
        long leakedWater = elapsedTime * rate / 1000;
        if (leakedWater > 0) {
            water = Math.max(0, water - leakedWater);
            lastLeakTimestamp = now;
        }
    }
}