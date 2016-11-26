package ListNode;

/**
 * Created by TheKingRing on 2016/11/16.
 */
public class insertionSort {
    public ListNode insertionSortList(ListNode head) {
        if(head==null)return null;
        return insert(head,head,head.next);
    }
    //关键是需要知道插入点的前一个节点和他的下一个节点。
    private ListNode insert(ListNode head,ListNode pre,ListNode tag){
        if(tag==null){
            return head;
        }
        ListNode dummy=new ListNode(0);
        dummy.next=head;
        ListNode tmp=head;//前一个节点
        ListNode str=dummy;//后一个节点
        int tagvalue=tag.val;
        while(tmp!=tag&&tmp.val<tagvalue){
            str=str.next;tmp=tmp.next;
        }
        if(tmp==tag)return insert(dummy.next,tag,tag.next);
        /*插入操作*/
        pre.next=tag.next;
        str.next=tag;tag.next=tmp;
        return insert(dummy.next,pre,pre.next);
    }
}
