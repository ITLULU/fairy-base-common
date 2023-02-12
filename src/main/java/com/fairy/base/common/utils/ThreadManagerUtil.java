package com.fairy.base.common.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author hll
 * @version 1.0
 * @date 2022/3/19 17:05
 */
public class ThreadManagerUtil {

    public static ThreadPoolExecutor create(int corePoolSize,
                                            int maximumPoolSize,
                                            Long keepAliveTime,
                                            BlockingQueue workQueue,
                                            ThreadFactory factory,
                                            RejectedExecutionHandler handler) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime,
                TimeUnit.MILLISECONDS, workQueue, factory, handler);
        // 提前创建好核心线程
        threadPoolExecutor.prestartAllCoreThreads();
        // 常驻核心线程的空闲时间超过 keepAliveTime 的时候要被回收
        threadPoolExecutor.allowCoreThreadTimeOut(true);
        return threadPoolExecutor;
    }

    static class ThreadPoolRejectHandler implements RejectedExecutionHandler {

        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            if (!executor.isShutdown()) {
                executor.getQueue().poll();
                executor.execute(r);
            }
        }
    }

    class NamedThreadFactory implements ThreadFactory {

        /**
         * 原子操作保证每个线程都有唯一的
         */
        private AtomicInteger threadNumber = new AtomicInteger(1);

        private AtomicInteger mThreadNum = new AtomicInteger(1);

        private final String prefix;

        private  boolean safeThread;

        private final ThreadGroup threadGroup;

        public NamedThreadFactory(String prefix) {
            this(prefix, true);
        }


        public NamedThreadFactory(String prefix, boolean safeThread) {
            this.prefix = StringUtils.isNotEmpty(prefix) ? prefix + "-thread-" : "";
            safeThread = safeThread;
            SecurityManager s = System.getSecurityManager();
            threadGroup = (s == null) ? Thread.currentThread().getThreadGroup() : s.getThreadGroup();
        }

        @Override
        public Thread newThread(Runnable runnable) {
            String name = prefix + mThreadNum.getAndIncrement();
            Thread ret = new Thread(threadGroup, runnable, name, 0);
            ret.setDaemon(safeThread);
            return ret;
        }
    }
}
