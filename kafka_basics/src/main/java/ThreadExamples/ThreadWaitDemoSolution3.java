package ThreadExamples;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicReference;

import static java.lang.System.exit;

public class ThreadWaitDemoSolution3 {

    private static final Logger log = LoggerFactory.getLogger(ThreadWaitDemoSolution3.class);

    public static void main(String[] args) throws InterruptedException {
        Thread mainThread = Thread.currentThread();
        AtomicReference<CountDownLatch> countDownLatch = null;
        Thread newThread = new Thread(() -> {
            System.out.println("Starting new thread");
            countDownLatch.set(new CountDownLatch(1));
            for (int i = 0; i < Integer.MAX_VALUE; i++) {
                try {
                    Thread.sleep(1000); // Simulate work
                } catch (InterruptedException e) {
                    System.out.println("newThread was interrupted during sleep");
                    // Optional: break if you want to stop the loop on interrupt
                    countDownLatch.get().countDown();
                    break;
                }
                System.out.println(Thread.currentThread().getName() + "_" + i);
            }
        });

        newThread.start();
        Thread.sleep(1000);
        Thread stoppingThread = new Thread(() -> {
            try {
            Thread.sleep(5000);
            newThread.interrupt();
        } catch (InterruptedException e) { }});
        try {
            stoppingThread.start();
            countDownLatch.get().await();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }

        exit(0); // Terminate JVM explicitly
    }
}
