package ListNode;

/**
 * Created by TheKingRing on 2016/11/14.
 */
public class ListNodeHelper {
    public static void main(String[] args) {
        ListNode n1=new ListNode(1);
        ListNode n2=new ListNode(2);
        ListNode n3=new ListNode(3);
        n1.next=n2;
        n2.next=n3;
        print(n1);
        ListNode tmp=n2.next;
        n2.next=n1;
        n1.next=tmp;
        print(n2);


    }

    //listNode reverse (m,n)
    public ListNode reverseBetween(ListNode head, int m, int n) {
    /* recursive solution */
        if (head == null || m == n) return head;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode pre = dummy;//拼接起点
        ListNode end = dummy;//拼接终点
        for (int i = 0; i <= n; i++) {
            if (i < m - 1) {
                pre = pre.next;
            }
            end = end.next;//find the end
        }
        ListNode str = pre.next;//find the start to be reverse
        pre.next = reverseListInt(str, null, 0, n - m + 1);//旋转
        str.next = end;//拼接
        return dummy.next;
    }

    private ListNode reverseListInt(ListNode head, ListNode newHead, int count, int tag) {
        if (count == tag)
            return newHead;
        ListNode next = head.next;
        head.next = newHead;
        return reverseListInt(next, head, count + 1, tag);
    }

    private ListNode reserveList(ListNode head) {
        return reverseListInt1(head, null);
    }

    private ListNode reverseListInt1(ListNode head, ListNode newHead) {
        if (head == null)
            return newHead;
        ListNode next = head.next;
        head.next = newHead;
        return reverseListInt1(next, head);
    }



    private static ListNode swapall(ListNode dummy, ListNode n1) {
        if (n1 == null || n1.next == null) return dummy.next;
        ListNode n2 = n1.next;
        ListNode node = n2.next;
        n1.next = node;
        n2.next = dummy.next;
        dummy.next = n2;
        return swapall(dummy, n1);
    }

    public static void print(ListNode node) {
        while (node != null) {
            System.out.print(node.val + "->");
            node = node.next;
        }
        System.out.print("NULL");
        System.out.println();
    }
}
