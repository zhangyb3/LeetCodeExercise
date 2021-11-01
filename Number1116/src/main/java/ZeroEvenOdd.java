import java.util.function.IntConsumer;

class ZeroEvenOdd {
    private int n;

    private volatile int state;

    private volatile int swithControl = 1;//路径切换器，不打0时有两个选择，1时切换到奇数，2时切换到偶数


    public ZeroEvenOdd(int n) {
        this.n = n;
    }

    public void zero(IntConsumer printNumber) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            while (state != 0) {
                Thread.yield();
            }
            printNumber.accept(0);
            if (swithControl == 1) {
                state = 1;
            } else {
                state = 2;
            }
        }
    }

    public void even(IntConsumer printNumber) throws InterruptedException {
        for (int i = 2; i <= n; i += 2) {
            while (state != 2) {//当state不为2的时候，为就绪状态
                Thread.yield();
            }
            printNumber.accept(i);
            swithControl = 1;
            state = 0;
        }
    }

    public void odd(IntConsumer printNumber) throws InterruptedException {
        for (int i = 1; i <= n; i += 2) {
            while (state != 1) {
                Thread.yield();
            }
            printNumber.accept(i);
            swithControl = 2;
            state = 0;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ZeroEvenOdd zeo = new ZeroEvenOdd(2);
        MyIntConsumer myIntConsumer = new MyIntConsumer();

        Thread thread1 = new Thread();
        Thread thread2 = new Thread();
        Thread thread3 = new Thread();



        zeo.zero(myIntConsumer);
        zeo.odd(myIntConsumer);
        zeo.even(myIntConsumer);

    }
}