package simplethreads;

public class SimpleThreads {

    /**
     * @param args the command line arguments
     */
    static void threadMessage(String message) {
        String threadName = Thread.currentThread().getName();
        System.out.format("%s: %s%n", threadName, message);
    }

    private static class MessageLoop implements Runnable {

        public void run() {
            String importantInfo[] = {
                "Message 1",
                "Message 2",
                "Message 3",
                "Message 4",
                "Message 5"};
            Thread.currentThread().setName("MessageLoop");
            try {
                for (int i = 0; i < importantInfo.length; i++) {
// Pause for 4 seconds

// Print a message
                    threadMessage(importantInfo[i]);
                    Thread.sleep(10000);
                }
            } catch (InterruptedException e) {
                threadMessage("I wasn't done!");
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {

        long patience = 1000;

//        if (args.length > 0) {
//            try {
//                patience = Long.parseLong(args[0]) * 1000;
//            } catch (NumberFormatException e) {
//                System.err.println("Argument must be an integer.");
//                System.exit(1);
//            }
//        }
        threadMessage("Starting MessageLoop thread");
        long startTime = System.currentTimeMillis();
        Thread t = new Thread(new MessageLoop());
        t.start();

        threadMessage("Waiting for MessageLoop thread to finish");
// loop until MessageLoop
// thread exits
        while (t.isAlive()) {

            threadMessage("Still waiting...");
// Wait maximum of 1 second
// for MessageLoop thread
// to finish.

            t.join();
            
            if (((System.currentTimeMillis() - startTime) > patience) && t.isAlive()) {
                threadMessage("Tired of waiting!");
                t.interrupt();
// Shouldn't be long now
// -- wait indefinitely
                t.join();
            }
        }
        threadMessage("Finally!");
    }
}
