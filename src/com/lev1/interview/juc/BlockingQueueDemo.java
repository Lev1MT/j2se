package com.lev1.interview.juc;


import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * 阻塞队列一共有7种
 * 但是常用的有三种:ArrayBlockingQueue、LinkedBlockingQueue、SynchronousBlockingQueue
 */
public class BlockingQueueDemo {

    public static void main(String[] args) throws Exception {
        BlockingQueue<String> queue= new ArrayBlockingQueue<>(3);// 容量为3的阻塞队列

        System.out.println(queue.add("a"));
        System.out.println(queue.add("b"));
        System.out.println(queue.add("c"));
//        System.out.println(queue.add("d"));   // 此时会抛出java.lang.IllegalStateException: Queue full异常

        System.out.println(queue.remove());
        System.out.println(queue.remove());
        System.out.println(queue.remove());
//        System.out.println(queue.remove());   // 此时会抛出java.util.NoSuchElementException异常

        System.out.println(queue.offer("a"));
        System.out.println(queue.offer("b"));
        System.out.println(queue.offer("c"));
        System.out.println(queue.offer("d"));   // 此时会返回false

        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());   // 此时会返回null

        queue.put("a");
        queue.put("b");
        queue.put("c");
//        queue.put("d");     // 此时线程会阻塞，直到有位置空出来为止

        System.out.println(queue.take());
        System.out.println(queue.take());
        System.out.println(queue.take());
//        System.out.println(queue.take());   // 此时线程会阻塞，直到能从队列取出值为止
    }
}
