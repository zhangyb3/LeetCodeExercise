public class Solution {
    public ListNode detectCycle(ListNode head) {
        if (head == null) {
            return null;
        }

        ListNode fast = head;
        ListNode slow = head;

        /**
         * 思路，追及问题，fast是slow的两倍速，如果有环，fast肯定会和slow重叠
         * 然后将路程分为3段，链头到入环处长度为A，入环处到fast追及slow处为B，追及处向后回到入环处为C
         * Dslow = A + B
         * Dfast = A + N(B+C) + B = 2*Dslow
         * A = C + (N-1)*(B+C)
         * 此时censor从链头出发，与slow同速，两个相遇时必然在入环处重叠
         *
         */
        while(fast != null){
            slow = slow.next;
            if(fast.next != null){
                fast = fast.next.next;
            }else{
                return null;
            }
            if (fast == slow) {//追及了
                ListNode censor = head;
                while (censor != slow) {
                    censor = censor.next;
                    slow = slow.next;
                }
                return censor;
            }

        }
        return null;
    }
}