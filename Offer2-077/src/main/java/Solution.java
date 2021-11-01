

class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}


class Solution
{
    public ListNode sortList(ListNode head)
    {
        return merge_sort(head);
    }

    /**
     * 递归分拆两条链
     */
    public ListNode merge_sort(ListNode head)
    {
        if (head == null || head.next == null)
            return head;
        ListNode slow = head;
        ListNode fast = head.next;
        while (fast != null && fast.next != null)
        {
            slow = slow.next;
            fast = fast.next.next;
        }

        ListNode head2 = slow.next;
        slow.next = null;

        ListNode L = merge_sort(head);
        ListNode R = merge_sort(head2);
        //分拆后归并排序
        return merge(L, R);
    }

    //归并操作
    public ListNode merge(ListNode L, ListNode R)
    {
        ListNode dummy = new ListNode(-1);
        ListNode x = dummy;
        while (L != null && R != null)
        {
            if (L.val < R.val)
            {
                x.next = L;
                L = L.next;
            }
            else
            {
                x.next = R;
                R = R.next;
            }
            x = x.next;
        }
        if (L != null)
            x.next = L;
        if (R != null)
            x.next = R;
        return dummy.next;
    }

}
