/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode reverseList(ListNode head) {

        if(head == null){
            return null;
        }
        if(head.next == null){
            return head;
        }

        ListNode result = new ListNode();

        ListNode current = head;
        ListNode remain = head.next;

        result = current;
        current.next = null;
        while(remain.next != null){
            current = remain;
            remain = remain.next;
            current.next = result;
            result = current;
        }
        remain.next = result;
        result = remain;

        return result;

    }

    public ListNode recursiveReverseList(ListNode head){

        if(head.next == null){
            return head;
        }

        ListNode current = head;

        ListNode tail = current.next;

        ListNode newHead = null;

        if(tail != null){
            current.next = null;

            newHead = recursiveReverseList(tail);
            ListNode temp = newHead;
            while (temp.next != null){
                temp = temp.next;
            }

            temp.next = current;
        }

        return newHead;
    }
}