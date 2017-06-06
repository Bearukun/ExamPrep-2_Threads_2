package examprep.pkg2_threads_2;

import java.util.concurrent.BlockingQueue;

public class Consumer implements Runnable {

    protected BlockingQueue<Long> s2;

    Long total = 0l;

    //Constructor
    public Consumer(BlockingQueue s2) {

        this.s2 = s2;

    }

    public void run() {

        while (isThreadsRunning() || !s2.isEmpty()) {

            Long temp = s2.poll();

            if (temp == null) {
                
                continue;

            }

            total += temp;
            
            System.out.println(temp);

        }
        
        System.out.println("Total: " + total );

    }
    
    
    private boolean isThreadsRunning(){
        
        boolean returnbool = false;
        
        for (int i = 0; i < ExamPrep2_Threads_2.threadsRunning.length; i++) {
            
            if(!ExamPrep2_Threads_2.threadsRunning[i]){
                
                returnbool = true;
                break;
                
                
            }
                
        }
        
        return returnbool;
        
    }

}
