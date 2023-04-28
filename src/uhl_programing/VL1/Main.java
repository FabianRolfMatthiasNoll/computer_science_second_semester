package uhl_programing.VL1;

public class Main {
    public static void main(String[] args) {
        SavingsAccount account = new SavingsAccount();

        // Create two threads for depositing and two threads for withdrawing
        Thread[] depositThreads = new Thread[2];
        Thread[] withdrawThreads = new Thread[2];

        for (int i = 0; i < 2; i++) {
            depositThreads[i] = new Thread(() -> {
                try {
                    while (true) {
                        float amount = (float) (Math.random() * 1000);
                        account.deposit(amount);
                        System.out.println(Thread.currentThread().getName() + " deposited " + amount + ", balance = " + account.getBalance());
                        Thread.sleep(1000);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });

            withdrawThreads[i] = new Thread(() -> {
                try {
                    while (true) {
                        float amount = (float) (Math.random() * 1000);
                        account.withdraw(amount);
                        System.out.println(Thread.currentThread().getName() + " withdrew " + amount + ", balance = " + account.getBalance());
                        Thread.sleep(1000);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

        // Start the threads
        for (Thread t : depositThreads) {
            t.start();
        }
        for (Thread t : withdrawThreads) {
            t.start();
        }
    }
}
