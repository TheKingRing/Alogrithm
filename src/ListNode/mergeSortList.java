package ListNode;

/**
 * Created by TheKingRing on 2016/11/16.
 */
public class mergeSortList {
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode mid = findMiddle(head);
        //divide
        ListNode l1 = sortList(mid.next);
        mid.next = null;
        ListNode l2 = sortList(head);
        //conquer
        return mergeTwoLists(l1, l2);
    }

    //快慢指针寻找中点
    private ListNode findMiddle(ListNode head) {
        ListNode slow = head, fast = head.next;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

    private ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        ListNode dummy;
        if (l1.val < l2.val) {
            dummy = l1;
            dummy.next = mergeTwoLists(l1.next, l2);
        } else {
            dummy = l2;
            dummy.next = mergeTwoLists(l1, l2.next);
        }
        return dummy;

    }
}
