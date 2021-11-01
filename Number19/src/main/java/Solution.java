public class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {

        ListNode currentList = new ListNode(0,head).next;
//        int length = getLength(currentList);
        ListNode detectNode = currentList;


        for(int count = 0; count < n ; count++){
            detectNode = detectNode.next;
        }
        if(detectNode == null){
            currentList = currentList.next;
            return currentList;
        }
        for(;detectNode.next != null;){
            detectNode = detectNode.next;
            currentList = currentList.next;
        }
        currentList.next = currentList.next.next;

        return head;
    }

    public int getLength(ListNode listNode){
        int len = 0;
        while(listNode.next != null){
            len++;
            listNode = listNode.next;
        }
        return len;
    }
}
