package com.lev1.interview.juc;


import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * volatile/CAS/AtomicInteger/BlockingQueue/线程交互
 *
 * synchronousQueue是一个空阻塞队列，如果有线程向队列中插入元素，那么线程将会阻塞，直到有另外一个线程来取出元素
 */
public class ProdConsumer_BlockingQueueDemo {

    public static void main(String[] args) throws Exception {
        MyResource resource = new MyResource(new ArrayBlockingQueue<>(3));
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName()+"\t 生产者线程启动");
            try {
                resource.myProd();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "Prod").start();

        new Thread(()->{
            System.out.println(Thread.currentThread().getName()+"\t 消费者线程启动");
            try {
                resource.myConsumer();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"Consumer").start();

        TimeUnit.SECONDS.sleep(5);
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println(Thread.currentThread().getName()+"\t 停止生产消费");
        resource.stop();
    }
}

class MyResource {
    // 用于判断是否继续生产消费,因此需要volatile关键字让消费和生产线程都可见
    private volatile boolean FLAG = true;
    private AtomicInteger atomicInteger = new AtomicInteger();
    // 传接口不传具体实现类，拓展性高，低耦合
    private BlockingQueue<String> blockingQueue = null;

    public MyResource(BlockingQueue<String> blockingQueue) {
        this.blockingQueue = blockingQueue;
        System.out.println(blockingQueue.getClass().getName());
    }

    // 生产
    public void myProd() throws Exception {
        String data = null;
        boolean retValue;
        while (FLAG) {
            data = atomicInteger.incrementAndGet() + "";
            retValue = blockingQueue.offer(data, 2L, TimeUnit.SECONDS);
            if (retValue) {
                System.out.println(Thread.currentThread().getName() + "\t 插入队列" + data + "成功");
            } else {
                System.out.println(Thread.currentThread().getName() + "\t 插入队列" + data + "失败");
            }
            TimeUnit.SECONDS.sleep(1);
        }
        System.out.println("===========生产叫停==========");
    }

    // 消费
    public void myConsumer() throws Exception {
        String result = null;
        while (FLAG) {
            result = blockingQueue.poll(2, TimeUnit.SECONDS);
            if (result == null || result.equalsIgnoreCase("")) {
                FLAG = false;
                System.out.println(Thread.currentThread().getName() + "\t 超过2秒钟没有取到，消费退出");
                System.out.println();
                System.out.println();
                return;
            }
            System.out.println(Thread.currentThread().getName() + "\t 消费队列" + result + "成功");
        }
    }

    // 停止生产消费
    public void stop() {
        this.FLAG = false;
    }
}