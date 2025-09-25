package ThreadExamples;

import static java.lang.System.exit;

public class ThreadWaitDemoSolution1 {

    public static void main(String[] args) {
        Thread mainThread = Thread.currentThread();

        Thread newThread = new Thread(() -> {
            for(int i = 0; i < 15000; i++) {
                System.out.println(Thread.currentThread().getName() + "_" + i);
            }
        });

        newThread.start();
        try {
            newThread.join();
            System.out.println("mainThread joins the queue behind newThread — it’s waiting its turn.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        exit(0);
    }
}
