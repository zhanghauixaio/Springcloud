package org.example.utils;

import javax.annotation.PostConstruct;
import java.util.concurrent.atomic.AtomicInteger;

public class RateLimiterUtils {
    // 阈值
    public static Integer QPS = 2;
    // 固定窗口
    public static long TIME_WINDOW = 1000L;
    // 窗口数量
    public static Integer subWindowInfo;
    // 子窗口数组
    public static WindowInfo[] windowInfos = new WindowInfo[subWindowInfo];

    public static AtomicInteger req_count = new AtomicInteger();

    public static long START_TIME = System.currentTimeMillis();

    @PostConstruct
    public void init() {
        for (int i = 0; i < subWindowInfo; i++) {
            windowInfos[i] = new WindowInfo(System.currentTimeMillis(), new AtomicInteger(0));
        }
    }

    /**
     * 固定窗口限流算法
     */
    public static synchronized boolean fixedWindowRateLimiter() {
        long currentTime = System.currentTimeMillis();
        if ((currentTime - START_TIME) > TIME_WINDOW) {
            req_count.set(0);
            START_TIME = System.currentTimeMillis();
        }
        return req_count.incrementAndGet() <= QPS;
    }

    /**
     * 1. 计算当前时间窗口
     * 2. 更新当前窗口计数 & 重置过期窗口计数
     * 3. 当前 QPS 是否超过限制
     *
     * @return
     */
    public static synchronized boolean slidingWindowRateLimiter() {
        long currentTime = System.currentTimeMillis();
        // 1. 计算当前时间窗口
        int index = (int) (currentTime % TIME_WINDOW / (TIME_WINDOW / subWindowInfo));
        // 2. 更新当前窗口计数 & 重置过期窗口计数
        int sum = 0;
        for (int i = 0; i < subWindowInfo; i++) {
             WindowInfo windowInfo = windowInfos[i];
             if ((currentTime - windowInfo.getStartTime()) > TIME_WINDOW) {
                 windowInfo.getCount().set(0);
                 windowInfo.setStartTime(currentTime);
             }
             if (index == i && windowInfo.getCount().get() < QPS) {
                 windowInfo.getCount().incrementAndGet();
             }
             sum += windowInfo.getCount().get();
        }
        // 3. 当前 QPS 是否超过限制
        return sum <= QPS;
    }

    /**
     * 窗口小格
     */
    private class WindowInfo {
        // 窗口开始时间
        private Long startTime;
        // 窗口请求计数
        private AtomicInteger count;

        public WindowInfo(Long startTime, AtomicInteger count) {
            this.startTime = startTime;
            this.count = count;
        }

        public Long getStartTime() {
            return startTime;
        }

        public void setStartTime(Long startTime) {
            this.startTime = startTime;
        }

        public AtomicInteger getCount() {
            return count;
        }

        public void setCount(AtomicInteger count) {
            this.count = count;
        }
    }
}
