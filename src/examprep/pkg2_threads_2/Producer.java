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

        //While - as long as there is remaining numbers 
        while (!s1.isEmpty()) {

            //Retrieves and Removes the top number from the BlockingQueue. Return Null is there is none
            Long temp = s1.poll();
            
            //start Timer
            long lStartTime = System.nanoTime();

            if (temp == null) {

                continue;

            }

            s2.add(fibonacci(temp));
            
            //End Timer
            long lEndTime = System.nanoTime();
            
            //Deduct elapsedtime
            long output = lEndTime - lStartTime;
            
            //Print elapsedtime in miliseconds
            System.out.println("Thread: " + threadId + " Elapsed time in milliseconds: " + output / 1000000);
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