package ThreadExamples;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static java.lang.System.exit;

public class ThreadWaitDemoSolution2 {

    private static final Logger log = LoggerFactory.getLogger(ThreadWaitDemoSolution2.class);

    public static void main(String[] args) {
        Thread mainThread = Thread.currentThread();

        Thread newThread = new Thread(() -> {
            System.out.println("Starting new thread");
            for (int i = 0; i < Integer.MAX_VALUE; i++) {
                try {
                    Thread.sleep(1000); // Simulate work
                } catch (InterruptedException e) {
                    System.out.println("newThread was interrupted during sleep");
                    // Optional: break if you want to stop the loop on interrupt
                    break;
                }
                System.out.println(Thread.currentThread().getName() + "_" + i);
            }
        });

        newThread.start();

        // main thread waits up to 4 seconds for newThread to finish
        try {
            newThread.join(4000); // 4 seconds
            System.out.println("mainThread resumed after waiting on newThread");
        } catch (InterruptedException e) {
            System.out.println("mainThread was interrupted during join()");
        }

        exit(0); // Terminate JVM explicitly
    }
}
