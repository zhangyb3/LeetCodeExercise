import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Lock_State_ABC {

    private static Lock lock=new ReentrantLock();
    private static int state=0;

    static class ThreadC extends Thread{
        @Override
        public void run(){
            for (int i = 0; i < 3 ; ) {
                try{
                    lock.lock();
                    while(state % 3 == 0){
                        System.out.print("A");
                        state++;
                        i++;
                    }
                }finally{
                    lock.unlock();
                }
            }
        }
    }

    static class ThreadA extends Thread{
        @Override
        public void run(){
            for (int i = 0; i < 3 ; ) {
                try{
                    lock.lock();
                    while(state % 3 == 1){
                        System.out.print("B");
                        state++;
                        i++;
                    }
                }finally{
                    lock.unlock();
                }
            }
        }
    }


    static class ThreadB extends Thread{
        @Override
        public void run(){
            for (int i = 0; i < 3 ; ) {
                try{
                    lock.lock();
                    while(state % 3 == 2){
                        System.out.print("C");
                        state++;
                        i++;
                    }
                }finally{
                    lock.unlock();
                }
            }
        }
    }

    public static void main(String[] args) {
//        new ThreadA().start();
//        new ThreadB().start();
//        new ThreadC().start();


        String str = "11,15,13,15,17,12";
        String s[] = str.split(",");
        int[] ints = new int[s.length];
        for (int count = 0 ; count < s.length; count++) {
            ints[count] = Integer.parseInt(s[count]);
        }

//        for(int surface = 0; surface <= s.length - 1; surface++){
//            for(int bottom = s.length-1; bottom > surface; bottom--){
//                if(ints[bottom] > ints[bottom - 1]){
//                    int temp = ints[bottom];
//                    ints[bottom] = ints[bottom-1];
//                    ints[bottom-1] = temp;
//                }
//            }
//        }
        quickSort(ints,0,s.length-1);
        System.out.println("finish");
    }

    static void quickSort(int[] array, int start, int end){
        int left = start;
        int right = end;
        int pivot = array[left];

        while(right > left){
            while(left < right && array[right] >= pivot)
                right--;
            array[left] = array[right];
            while(left < right && array[left] <= pivot)
                left++;
            array[right] = array[left];
        }
        array[left] = pivot;

        if(start < left - 1){
            quickSort(array,start,left-1);
        }
        if(end > right + 1){
            quickSort(array,right+1,end);
        }

    }
}