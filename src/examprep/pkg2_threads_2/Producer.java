package examprep.pkg2_threads_2;

import java.util.concurrent.BlockingQueue;

public class Producer implements Runnable {

    private BlockingQueue<Long> s1;
    private BlockingQueue<Long> s2;
    private int threadId;

    public Producer(BlockingQueue s1, BlockingQueue s2, int threadId) {

        this.s1 = s1;
        this.s2 = s2;
        this.threadId = threadId;

    }

    public void run() {

        while (!s1.isEmpty()) {

            Long temp = s1.poll();

            if (temp == null) {

                continue;

            }

            s2.add(fibonacci(temp));

        }

        ExamPrep2_Threads_2.threadsRunning[threadId] = true;

    }

    private long fibonacci(long n) {
        
        if ((n == 0) || (n == 1)) {
            
            return n;
            
        } else {
            
            return fibonacci(n - 1) + fibonacci(n - 2);
            
        }
        
    }

}