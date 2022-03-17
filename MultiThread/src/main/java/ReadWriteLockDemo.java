import java.util.HashMap;
import java.util.concurrent.locks.ReentrantReadWriteLock;

class CacheDemo {

    private volatile HashMap<String,Object> map = new HashMap<>();
    private ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();

    public void put(String key, Object value) {
        rwLock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + " write start");
            map.put(key,value);
            System.out.println(Thread.currentThread().getName() + " write end");
        } finally {
            rwLock.writeLock().unlock();
        }

    }

    public void get(String key) {
        rwLock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + " read start");
            Object value = map.get(key);
            System.out.println(Thread.currentThread().getName() + " read end: " + value.toString());
        } finally {
            rwLock.readLock().unlock();
        }

    }

}

public class ReadWriteLockDemo {

    public static void main(String[] args) {

        CacheDemo cacheDemo = new CacheDemo();

        for(int count = 0; count < 10; count++) {
            final int tempInt = count;
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    cacheDemo.put(String.valueOf(tempInt),String.valueOf(tempInt));
                }
            }, String.valueOf(tempInt));
            thread.start();

        }

        for(int count = 0; count < 10; count++) {
            final int tempInt = count;
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    cacheDemo.get(String.valueOf(tempInt));
                }
            }, String.valueOf(tempInt));
            thread.start();

        }

    }
}
