package com.lev1.interview.thread;


import java.util.concurrent.*;

/**
 * 线程池手写改造和拒绝策略
 * 重要：线程池合理配置！！！
 * 主要涉及到最大线程数的配置
 * 1.当线程是CPU密集型，也就是需要大量计算时，一般是CPU核心数+1
 * 2.当线程是IO密集型，也就是有大量IO操作时，一般是CPU核心数*2或者CPU核心数/(1-阻塞系数);其中阻塞系数在0.8-0.9之间
 */
public class ThreadPoolDemo {

    public static void main(String[] args) {
        ExecutorService executorService = myThreadPool();

        try {
            for (int i = 1; i <= 10; i++) {
                executorService.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + "\t 执行");
                    try {
                        Thread.sleep(300);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            executorService.shutdown();
        }

//        System.out.println(Thread.currentThread().getName() + "\t 执行");
    }

    /**
     * 线程池参数！！！重要
     * 线程池一共7个参数
     * 第一个参数是核心线程数，也就是常驻线程数，当任务刚提交到线程池中时，使用这个空间
     * 第二个参数是最大线程数，也就是线程池中可运行的最大线程数，当核心线程都在执行时，并且阻塞队列已满，就会自动扩容，直到达到最大线程数
     * 第三个参数是多余线程的存活时间，当线程池扩容之后，后续没有任务继续发布，则过了存活时间之后，多余空闲线程会被销毁直到只剩下corePoolSize为止
     * 第四个参数是第三个参数的时间单位
     * 第五个参数是线程池的阻塞队列类型
     * 第6个参数是线程工厂，使用默认的线程工厂Executors.defaultThreadFactory()就可以
     * 最后一个参数为线程池的拒绝策略，当线程池和阻塞队列都满之后对再次请求线程池的线程的拒绝操作：
     * 1.直接抛java.util.concurrent.RejectedExecutionException异常:new ThreadPoolExecutor.AbortPolicy()
     * 2.线程调用运行该任务的execute本身(这里就是main线程)：new ThreadPoolExecutor.CallerRunsPolicy()
     * 3.直接拒绝，删除超出的线程:new ThreadPoolExecutor.DiscardPolicy()
     * 4.删除在阻塞队列中等待时间最长的线程，然后尝试插入：new ThreadPoolExecutor.DiscardOldestPolicy()
     * @return 返回自定义的线程池对象
     */
    public static ExecutorService myThreadPool() {
        return new ThreadPoolExecutor(
                2,
                3,
                2L,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(5),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardPolicy()
        );
    }
}
