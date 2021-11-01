import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class FooBar {
    private int n;

    public FooBar(int n) {
        this.n = n;
    }

    Lock lock = new ReentrantLock(true);
    volatile boolean permitFoo = true;

    public void foo(Runnable printFoo) throws InterruptedException {

        for (int i = 0; i < n; ) {
            lock.lock();
            try {
                if(permitFoo) {
                    printFoo.run();
                    i++;
                    permitFoo = false;
                }
            }finally {
                lock.unlock();
                Thread.yield();
            }
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {
        for (int i = 0; i < n; ) {
            lock.lock();
            try {
                if(!permitFoo) {
                    printBar.run();
                    i++;
                    permitFoo = true;
                }
            }finally {
                lock.unlock();
                Thread.yield();
            }
        }
    }
}