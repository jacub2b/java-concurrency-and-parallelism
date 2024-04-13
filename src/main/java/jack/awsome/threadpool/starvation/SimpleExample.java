package jack.awsome.threadpool.starvation;

import java.time.Duration;
import java.util.concurrent.*;
import java.util.stream.IntStream;

public class SimpleExample {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        // Even when the maximum pool size is higher than the amount of tasks,
        // still no new threads are created because one task in the workQueue
        // isn't enough to trigger the creation of new Threads
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10, 100, 10, TimeUnit.SECONDS, new LinkedBlockingQueue<>());

        IntStream.range(0, 11).forEachOrdered(value -> threadPoolExecutor.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println(STR."""
                Value: \{value}
                        """);

                while (true) {
                    try {
                        Thread.sleep(Duration.ofMillis(1));
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }));

        //todo: dead lock examples
    }
}
