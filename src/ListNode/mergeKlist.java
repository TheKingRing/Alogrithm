package ListNode;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Created by TheKingRing on 2016/11/13.
 */
public class mergeKlist {
    //priorityQueue
    public ListNode mergeKLists1(ListNode[] lists) {
        ListNode dummy=new ListNode(0);
        //自己定义优先序列的比较器
        PriorityQueue<ListNode> queue=new PriorityQueue<ListNode>(new Comparator<ListNode>() {
            @Override
            public int compare(ListNode o1, ListNode o2) {
                return o1.val-o2.val;
            }
        });
        for (ListNode node:lists){
            if (node!=null)queue.add(node);
        }
        ListNode start=dummy;
        while (!queue.isEmpty()){
            ListNode node=queue.poll();
            start.next=node;
            if (node.next!=null)queue.add(node.next);
            start=start.next;
        }
        return dummy.next;
    }

    /*divide and conquer*/
    public ListNode mergeKLists(ListNode[] lists) {
        if(lists.length==0){
            return null;
        }
        return mergeHelper(lists,0,lists.length-1);
    }
    private ListNode mergeHelper(ListNode[] lists,int start,int end){
        if(end==start){
            return lists[start];
        }
        int mid=start+(end-start)/2;
        //divide
        ListNode left=mergeHelper(lists,start,mid);
        ListNode right=mergeHelper(lists,mid+1,end);
        //conquer
        return mergeTwoLists(left,right);
    }
    //recursive solve two list
    private ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if(l1==null)return l2;
        if(l2==null)return l1;

        ListNode head;
        if(l1.val>l2.val){
            head=l2;head.next=mergeTwoLists(l1,l2.next);
        }
        else{
            head=l1;head.next=mergeTwoLists(l1.next,l2);
        }
        return head;
    }
}
