import java.util.concurrent.*;

public class ThreadPoolDemo {
    public static void main(String[] args) {

        System.out.println(Runtime.getRuntime().availableProcessors());

        /**
         * 实际开发中，禁止用Executors去显式创建线程（所以下面三句都不要在实际中用），
         * 而要用它的底层ThreadPoolExecutor，在设置好参数的前提下才使用
         */
//        ExecutorService threadPool = Executors.newFixedThreadPool(5);
//        ExecutorService threadPool = Executors.newSingleThreadExecutor();
//        ExecutorService threadPool = Executors.newCachedThreadPool();
        /**
         * 合理写法
         */
        ExecutorService threadPool = new ThreadPoolExecutor(
                2,
                5,
                1L,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(3),
                Executors.defaultThreadFactory(),
//                new ThreadPoolExecutor.AbortPolicy());
//                new ThreadPoolExecutor.CallerRunsPolicy());//谁调用线程池就找谁去执行
                new ThreadPoolExecutor.DiscardOldestPolicy());
//                new ThreadPoolExecutor.DiscardPolicy());

        try {
            for (int i = 0; i < 10; i++) {
                threadPool.execute(new Runnable() {
                    @Override
                    public void run() {
                        /**
                         * AbortPolicy时，线程数超过maxiumPoolSize+阻塞队列长度时，就会抛出异常
                         *
                         */
                        System.out.println(Thread.currentThread().getName() + " execute");
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
        }



    }
}
