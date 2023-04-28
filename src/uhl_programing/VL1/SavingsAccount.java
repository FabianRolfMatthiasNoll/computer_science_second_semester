package uhl_programing.VL1;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

class SavingsAccount {
    private static final float BALANCE_MAX = 20000;
    private float balance;
    private final ReentrantLock lock;
    private final Condition depositCondition;
    private final Condition withdrawCondition;

    public SavingsAccount() {
        this.balance = 0;
        this.lock = new ReentrantLock();
        this.depositCondition = lock.newCondition();
        this.withdrawCondition = lock.newCondition();
    }

    public float getBalance() {
        return this.balance;
    }

    public void deposit(float amount) throws InterruptedException {
        lock.lock();
        try {
            while (this.balance + amount > BALANCE_MAX) {
                depositCondition.await();
            }
            this.balance += amount;
            withdrawCondition.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public void withdraw(float amount) throws InterruptedException {
        lock.lock();
        try {
            while (this.balance < amount) {
                withdrawCondition.await();
            }
            this.balance -= amount;
            depositCondition.signalAll();
        } finally {
            lock.unlock();
        }
    }
}
