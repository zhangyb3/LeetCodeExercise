
class Solution {
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || head.next == null) {
            return head;
        }
        int len = 1, index;
        ListNode temp = head, newHead;
        while (temp.next != null) { //计算链表长度len
            len++;
            temp = temp.next;
        }
        temp.next = head; //将链表连接成循环链表
        k %= len; //旋转链表每len次循环一次，因此计算k对len的取余，避免重复操作
        index = len - k; //找到要断开循环链表的节点纪录链表新的头结点
        while (index-- > 0) {
            temp = temp.next;
        }
        newHead = temp.next;
        temp.next = null; //断开循环链表
        return newHead;
    }
}
