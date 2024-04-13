package jack.awsome.threadpool.starvation;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

//Tasks aren't starved only on a machine with 10+ cores
public class MachineSpecificExample {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        IntStream.range(0, 20).forEach(value -> executorService.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println(value);
                while (true) {
                    //Eat gefilte fish
                }
            }
        }));
    }

}
