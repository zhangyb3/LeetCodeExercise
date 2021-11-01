import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class Solution {

    public void bottomUpGetSum(Iterator<Integer> aIterator, Iterator<Integer> bIterator, Stack<Integer> s) {
        s.clear();
        int carry = 0;
        while (aIterator.hasNext()  || bIterator.hasNext() || carry != 0) {
            int a = aIterator.hasNext() ? aIterator.next(): 0;
            System.out.println("a:" + a);
            int b = bIterator.hasNext() ? bIterator.next(): 0;
            System.out.println("b:" + b);
            int sum = a + b + carry;//求两个节点相加的值
            carry = sum / 10;//取进位的值
            System.out.println("sumTemp:" + sum);
            s.push(sum % 10);//sum对10求余后放到节点中

        }
    }




    Stack<Integer> getSum( LinkedList<Integer> a, LinkedList<Integer> b, Stack<Integer> s, Stack<Integer> c){
        s.clear();

        Iterator<Integer> aIterator = a.listIterator();
        Iterator<Integer> bIterator = b.listIterator();
        topDownRecursiveGetSum(0,aIterator,bIterator, s,c);
        Integer topCarry = c.pop();
        if(topCarry > 0){
            s.push(topCarry);
        }
        return s;
    }

    private void topDownRecursiveGetSum(Integer c, Iterator<Integer> aIterator, Iterator<Integer> bIterator, Stack<Integer> s, Stack<Integer> carries) {

        Integer a,b;
        if(!aIterator.hasNext() && !bIterator.hasNext()){
            return ;
        }else {
            if(!aIterator.hasNext()){
                a = 0;
            }else {
                a = aIterator.next();
                System.out.println("a:" + a);
            }

            if(!bIterator.hasNext()){
                b = 0;
            }else {
                b = bIterator.next();
                System.out.println("b:" + b);
            }
        }



        Integer sumTemp = c + a + b,sumToPush,carryToTop;
        System.out.println("sumTemp:" + sumTemp);
        if(sumTemp >= 10){
            sumToPush = sumTemp - 10;

            carryToTop = sumTemp / 10;
        }else {
            sumToPush = sumTemp;
            carryToTop = 0;
        }

        System.out.println("sumToPush:" + sumToPush);
        System.out.println("carryToTop:" + carryToTop);
        s.push(sumToPush);
        carries.push(carryToTop);


        topDownRecursiveGetSum(carryToTop, aIterator,bIterator,s,carries);

    }



    public static void main(String[] args) {

        Solution newSolution = new Solution();

        Stack<Integer> sumStack = new Stack<>();
        Stack<Integer> carryStack = new Stack<>();

        LinkedList<Integer> aNumber = new LinkedList<>(),bNumber = new LinkedList<>(),sumNumber = new LinkedList<>();;

        aNumber.add(9);
        aNumber.add(9);
        aNumber.add(9);
        aNumber.add(0);

        bNumber.add(2);
        bNumber.add(5);
        bNumber.add(9);
        bNumber.add(9);
        bNumber.add(9);

        sumStack = newSolution.getSum(aNumber,bNumber,sumStack, carryStack);
        for(Iterator<Integer> sIterator = sumStack.iterator();sIterator.hasNext();){
            System.out.println(sIterator.next());
        }

        sumStack.clear();
        newSolution.bottomUpGetSum(aNumber.iterator(),bNumber.iterator(),sumStack);

        for(Iterator<Integer> sIterator = sumStack.iterator();sIterator.hasNext();){
            System.out.println(sIterator.next());
        }
    }
}
