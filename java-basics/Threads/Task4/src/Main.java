public class Main {
    public static void main(String[] args) throws InterruptedException {
        Worker worker1 = new Worker();
        Worker worker2 = new Worker();

        worker1.start();
        worker2.start();

        System.out.println("Main is waiting...");

        worker1.join();
        worker2.join();

        System.out.println("All threads finished.");

    }
}