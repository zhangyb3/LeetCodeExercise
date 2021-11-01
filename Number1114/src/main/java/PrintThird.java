public class PrintThird implements Runnable{
    @Override
    public void run() {
        System.out.println("third");
        System.out.println(Thread.currentThread());
    }
}
