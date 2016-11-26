package ListNode;

/**
 * Created by TheKingRing on 2016/11/13.
 */
public class Partition {
    public ListNode partition(ListNode head, int x) {
        if(head==null)return null;
        ListNode leftdummy=new ListNode(0);//左虚结点
        ListNode rightdummy=new ListNode(0);//右虚结点
        ListNode left=leftdummy,right=rightdummy;
        while(head!=null){
            if(head.val<x){
                left.next=head;//left-->head
                left=head;
            }else{
                right.next=head;//right-->head;
                right=head;
            }
            head=head.next;
        }
        right.next=null;
        left.next=rightdummy.next;//是两个next链接起来。
        return leftdummy.next;
    }
}
