package examprep.pkg2_threads_2;

import java.util.Collections;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ExamPrep2_Threads_2 {

    public static BlockingQueue<Long> s1 = new ArrayBlockingQueue(11);
    public static BlockingQueue<Long> s2 = new ArrayBlockingQueue(11);

    private static int producerCount = 4;
    private static int totalThreads = producerCount + 1;

    private static ExecutorService es = Executors.newFixedThreadPool(totalThreads);

    public static boolean[] threadsRunning;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Long[] defaultData = new Long[]{4l, 5l, 8l, 12l, 21l, 22l, 34l, 35l, 36l, 37l, 42l};

        Collections.addAll(s1, defaultData);

        startProducers(producerCount);

        //new Thread(new Consumer(s2)).start();
        es.execute(new Consumer(s2));

        //join on all threads
        es.shutdown();

        try {
            es.awaitTermination(60, TimeUnit.SECONDS);
        } catch (InterruptedException ex) {
            System.out.println("Could not shut down all treads!" + ex.getMessage());
        }

    }

    public static void startProducers(int amount) {

        threadsRunning = new boolean[amount];

        for (int i = 0; i < amount; i++) {

            //new Thread(new Producer(s1, s2, i)).start();
            es.execute(new Producer(s1, s2, i));

        }

    }

}
