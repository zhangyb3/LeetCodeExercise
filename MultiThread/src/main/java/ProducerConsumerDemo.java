import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class ShareData {
    private int number = 0;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void increment() throws InterruptedException {
        lock.lock();
        try {
            while (number != 0) {
                condition.await();
            }
            number++;
            System.out.println(number + " incre");
            condition.signalAll();
        }finally {
            lock.unlock();
        }

    }

    public void decrement() throws InterruptedException {
        lock.lock();
        try {
            while (number == 0) {
                condition.await();
            }
            number--;
            System.out.println(number + " decre");
            condition.signalAll();
        }finally {
            lock.unlock();
        }

    }
}

public class ProducerConsumerDemo {
    public static void main(String[] args) {

        ShareData shareData = new ShareData();

        for(int count = 0; count < 10; count++) {
            final int tempInt = count;
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        shareData.increment();
                        shareData.decrement();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }, String.valueOf(tempInt));
            thread.start();

        }
    }
}
