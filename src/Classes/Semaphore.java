package org.ps;

public class Semaphore {
    private int permits;

    public Semaphore(int signals) {
        this.permits = signals;
    }

    public synchronized void acquire() throws InterruptedException {
        while (permits <= 0) {
            wait();
        }
        permits--;
    }

    public synchronized boolean tryAcquire() {
        if (permits > 0) {
            permits--;
            return true;
        }
        return false;
    }

    public synchronized void release() {
        permits++;
        notify();
    }

    public synchronized int availablePermits() {
        return permits;
    }
}