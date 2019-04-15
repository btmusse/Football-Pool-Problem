package HillClimbing;

import HillClimbing.hillClimbingRunnable;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;


public class hillClimbingThreaded {
    public static void main(String[] args){
        int numThreads=8;
        ExecutorService executor = Executors.newFixedThreadPool(numThreads);
        List<Future> futures = new ArrayList<Future>();

        for (int i=0; i<numThreads; i++) {
            futures.add(executor.submit(new hillClimbingRunnable(i)));
        }
        for (Future future : futures) {
            try {
                future.get();
            } catch (Exception e) {
                e.printStackTrace();
                System.exit(-1);
            }
        }
        executor.shutdownNow();
    }
}