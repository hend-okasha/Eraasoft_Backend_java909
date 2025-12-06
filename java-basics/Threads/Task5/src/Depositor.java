import java.util.Random;

public class Depositor extends Thread{
    private BankAccount account;
    private Random random = new Random();

    public Depositor(BankAccount account) {
        this.account = account;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 5; i++) {
                Thread.sleep(1000 + random.nextInt(1000));
                int amount = (random.nextInt(5) + 1) * 10;
                account.deposit(amount);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println("Depositor finished.");
    }
}
