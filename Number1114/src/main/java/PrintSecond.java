public class PrintSecond implements Runnable{
    @Override
    public void run() {
        System.out.println("second");
        System.out.println(Thread.currentThread());
    }

}
