

public class Solution {

    public ListNode ReverseList(ListNode head) {
        return reverse(head,null);
    }
    private ListNode reverse(ListNode cur,ListNode pre){
        if(cur == null){  //当前节点为null时，返回
            return pre;
        }
        ListNode node = reverse(cur.next,cur);  //递归下一个节点
        cur.next = pre;    //当前节点指向前一个节点
        return node;
    }



    public static void main(String[] args) {

    }
}