public class PrintFirst implements Runnable{
    @Override
    public void run() {
        System.out.println("first");
        System.out.println(Thread.currentThread());
    }


}
