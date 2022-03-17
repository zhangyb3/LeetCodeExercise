import java.util.concurrent.atomic.AtomicReference;

/**
 * 自旋锁  CAS
 */
public class SpinLockDemo {

    AtomicReference<Thread> threadAtomicReference = new AtomicReference<>();

    public void mySpinLock() {
        Thread thread = Thread.currentThread();
        while (!threadAtomicReference.compareAndSet(null,thread)) {}
    }

    public void mySpinUnlock() {
        Thread thread = Thread.currentThread();
        threadAtomicReference.compareAndSet(thread,null);
    }

    public static void main(String[] args) {

    }

}
