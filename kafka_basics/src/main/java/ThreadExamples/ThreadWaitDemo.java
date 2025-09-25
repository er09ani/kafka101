package ThreadExamples;

import static java.lang.System.exit;

public class ThreadWaitDemo {

    public static void main(String[] args) {
        Thread mainThread = Thread.currentThread();

        Thread newThread = new Thread(() -> {
            for(int i = 0; i < 15000; i++) {
                System.out.println(Thread.currentThread().getName() + "_" + i);
            }
        });

        newThread.start();

        //before exisitng I want to wait for the newThread to complete
        exit(0);
    }
}
