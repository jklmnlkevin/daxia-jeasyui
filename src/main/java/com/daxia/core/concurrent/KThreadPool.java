package com.daxia.core.concurrent;

import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.SyncTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

public class KThreadPool {
    private static SimpleAsyncTaskExecutor asyncExecutor = new SimpleAsyncTaskExecutor();
    static {
        /**
         * 设置并发数，可同时执行的任务数。
         * 如：将该值设置成1，但同时提交了两个任务
         * asyncExecutor.execute(task1);
         * asyncExecutor.execute(task2);
         * 由于线程数的限制，会先执行完task1之后再执行task2。
         * 如果将该值设置成2或大于2，同时提交了两个任务，
         * 那这两个任务会同时执行，不会阻塞。
         */
        asyncExecutor.setConcurrencyLimit(100);
    }
    
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        
        SleepThread sleepThread = new SleepThread();
        // executeAndWait(new SleepThread(), 10000);
         execute(sleepThread);
         execute(sleepThread);
         execute(sleepThread);
         execute(sleepThread);
         execute(sleepThread);
//        executeAndWait(sleepThread);
        System.out.println("done, took " + (System.currentTimeMillis() - start) + " ms.");
    }
    
    public static void execute(Runnable task) {
        long start = System.currentTimeMillis();
        
        System.out.println("took " + (System.currentTimeMillis() - start) + "ms to construct executor.");
        asyncExecutor.execute(task);
    }
    
    public static void executeAndWait(Runnable task) {
        SyncTaskExecutor executor = new SyncTaskExecutor();
        executor.execute(task);
    }
    
    public static void executeAndWait(Runnable runnable, long timeToWait) {
       // ArrayBlockingQueue<Runnable> queue = new ArrayBlockingQueue<Runnable>(100);
//        ThreadPoolExecutor executor = new ThreadPoolExecutor(10, 50,
//                1, TimeUnit.SECONDS, queue);
//        // executor.execute(runnable);
//        System.out.println("hello");
//        Future<?> f = executor.submit(runnable);
//        try {
//            System.out.println("world, " + f.get().toString());
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(10);
        executor.execute(runnable);
    }
    
    public static void submit() {
        
    }
    
    public static void submitAndWait() {
        
    }

}

class SleepThread extends Thread {
    @Override
    public void run() {
        System.out.println("before sleep");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("after sleep");
    }
};
