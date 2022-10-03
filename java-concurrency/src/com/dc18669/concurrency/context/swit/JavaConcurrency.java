package com.dc18669.concurrency.context.swit;

public class JavaConcurrency {

    private static final long count = 10000L;

    public static void main(String[] args) throws InterruptedException {
        concurrency();
        serial();
    }

    /**
     * -并发
     * @throws InterruptedException
     */
    private static void concurrency() throws InterruptedException {
        long start = System.currentTimeMillis();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                int a = 0;
                for (long i = 0 ; i < count;i++) {
                    a += 5;
                }

            }
        });

        thread.start();

        int b = 0;

        for (long i = 0 ; i < count; i++) {
            b--;
        }

        thread.join();

        long time = System.currentTimeMillis() - start;

        System.out.println("concurrency : " + time + "ms, b = " + b);
    }

    /**
     * 串行
     */
    private static void serial() {
        long start = System.currentTimeMillis();
        int a = 0;
        for (long i = 0; i < count; i++) {
            a += 5;
        }

        int b = 0;
        for (long i = 0; i < count; i++) {
            b--;
        }

        long time = System.currentTimeMillis() - start;
        System.out.println("serial : " + time + "ms,b = " + b + ",a = " + a);

    }

    /**
     * -使用 Lmbench3 可以测量 上下文切换的失常
     * 使用vmstat 可以测量上下文切换的次数
     *
     */


    /**
     * -如何减少上下文切换
     * 1. 无锁并发编程。多线程竞争锁时，会引起上下文切换，所以多线程处理数据时，可以用一些方法来避免使用锁，如将数据的ID按照Hash算法取模分段，不同的线程处理不同段的数据
     * 2. CAS算法。Java的atomic包使用CAS算法来更新数据，而不需要加锁。
     * 3. 使用最少线程。避免创建不需要的线程，比如任务少，但是创建了很多线程来处理，这样会造成大量线程都处于等待状态。
     * 4. 使用协程。在单线程里实现多任务的调度，并在单线程里维持多个任务间的切换。
     */

    /**
     * -多线程一定快吗？
     * 不一定，在累加操作不超过百万级时，多线程处理会慢
     */
}
