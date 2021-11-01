public class Solution {
    public ListNode swapPairs(ListNode head) {
        ListNode pre = new ListNode(0);
        pre.next = head;
        ListNode temp = pre;
        while(temp.next != null && temp.next.next != null) {
            ListNode start = temp.next;
            ListNode end = temp.next.next;
            temp.next = end;
            start.next = end.next;
            end.next = start;
            temp = start;
        }
        return pre.next;
    }


    public ListNode reverseList(ListNode head){

        ListNode remain,current,newList;
        newList = null;
        remain = head;
        current = head;
        while (current != null){
            remain = current.next;
            current.next = newList;
            newList = current;
            current = remain;
        }

        return head;
    }

    public void recursiveReverseList(ListNode head){

        if(head.next == null || head == null){
            return;
        }
        recursiveReverseList(head.next);
        head.next.next = head;
        head.next = null;

    }


    public static void main(String[] args) {
        ListNode node4 = new ListNode(4,null);
        ListNode node3 = new ListNode(3,node4);
        ListNode node2 = new ListNode(2,node3);
        ListNode node1 = new ListNode(1,node2);

//        new Solution().reverseList(node1);
        new Solution().recursiveReverseList(node1);
        System.out.println("finish");
    }

}
