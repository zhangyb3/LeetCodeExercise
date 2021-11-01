import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

class Foo {

    //同步队列,没有容量，进去一个元素，必须等待取出来以后，才能再往里面放一个元素
    BlockingQueue<Integer> block1 = new SynchronousQueue<Integer>();
    BlockingQueue<Integer> block2 = new SynchronousQueue<Integer>();

    volatile int flag = 0;
    public Foo() throws InterruptedException {

        flag++;
    }

    public void first(){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("1");
                try {

                    block1.put(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }

    public void second(){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    block1.take();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("2");
            }
        });
        thread.start();
    }


    public void first(Runnable printFirst) throws InterruptedException {
        while (flag != 1);
        // printFirst.run() outputs "first". Do not change or remove this line.
        printFirst.run();
        flag++;
    }

    public void second(Runnable printSecond) throws InterruptedException {
        while (flag != 2);
        // printSecond.run() outputs "second". Do not change or remove this line.
        printSecond.run();
        flag++;
    }

    public void third(Runnable printThird) throws InterruptedException {
        while (flag != 3);
        // printThird.run() outputs "third". Do not change or remove this line.
        printThird.run();
    }

    public static void main(String[] args) throws InterruptedException {
        Foo foo = new Foo();
        Runnable printFirst = new PrintFirst();
        Runnable printSecond = new PrintSecond();
        Runnable printThird = new PrintThird();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Foo foo = new Foo();
                    Runnable printFirst = new PrintFirst();
                    foo.first(printFirst);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t1.start();


//        foo.first(printFirst);
//        foo.second(printSecond);
//        foo.third(printThird);

//        foo.second();
//        foo.first();
    }
}


class ThreadDemo extends Thread implements Runnable{

}
