import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class OriginalMultiThread {
    private static int count = 0;

    private static void action() throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(5);
        /**
         * 三步：
         * 1: a = count;
         * 2: b = a + 1；
         * 3: count = b;
         */
        count++;
    }

    private static void actionCAS() throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(5);
        int expectCount;
        while (!CompareAndSwap(expectCount = count,expectCount+1)) {}
    }

    /**
     * CompareAndSwap（简写CAS）
     * @param expecCount
     * @param newCount
     * @return
     */
    private static synchronized boolean CompareAndSwap(int expecCount, int newCount) {
        if(count == expecCount) {
            /**
             * 同时有多个线程取值赋值，要先比较取到的值是否为初始条件的值，再做计算和赋值
             */
            count = newCount;
            System.out.println(count);
            return true;
        }else {
            return false;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        long startTime = System.currentTimeMillis();
        int threadSize = 100;
        CountDownLatch countDownLatch = new CountDownLatch(threadSize);

        for (int i = 0; i < threadSize; i++) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        for (int j = 0; j < 10; j++) {
//                            action();
                            actionCAS();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }finally {
                        countDownLatch.countDown();
                    }
                }
            });
            thread.start();
        }

        countDownLatch.await();
        long endTime = System.currentTimeMillis();

        System.out.println("TIME: " + (endTime-startTime) + " , COUNT: " + count);
    }
}
