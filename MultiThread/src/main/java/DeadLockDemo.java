import java.util.concurrent.TimeUnit;

class HoldLockThread implements Runnable {

    private String lock1;
    private String lock2;

    public HoldLockThread(String lock1, String lock2) {
        this.lock1 = lock1;
        this.lock2 = lock2;
    }

    @Override
    public void run() {
        synchronized (lock1) {
            System.out.println(Thread.currentThread().getName() + " get " + lock1 + " want " + lock2);
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (lock2) {
                System.out.println(Thread.currentThread().getName()+ " get " + lock2 + " want " + lock1);
            }
        }
    }
}

public class DeadLockDemo {
    public static void main(String[] args) {
        String lockA = "LOCK-A";
        String lockB = "LOCK-B";

        new Thread(new HoldLockThread(lockA,lockB),"Thread-A").start();
        new Thread(new HoldLockThread(lockB,lockA),"Thread-B").start();
    }



}
