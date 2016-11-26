package ListNode;

import BinaryTree.Tree.TreeNode;

/**
 * Created by TheKingRing on 2016/11/14.
 */
public class ListToTree {

    public static void main(String[] args) {
       ListNode head=new ListNode(1);
        ListNode strat=head;
        int k=2;
        while (k<10){
            strat.next=new ListNode(k++);
            strat=strat.next;
        }
        ListNodeHelper.print(head);
        sortedListToBST(head);
    }
    public static TreeNode sortedListToBST(ListNode head) {
        if(head==null)return null;
        if(head.next==null){
            return new TreeNode(head.val);
        }
        if(head.next.next==null){
            TreeNode root=new TreeNode(head.next.val);
            root.left=new TreeNode(head.val);return root;
        }
        ListNode left=head;ListNode mid=head.next;ListNode right=head.next.next;
        //定义快指针
        ListNode quick=right.next;
        while(quick!=null&&quick.next!=null){
            left=left.next;mid=mid.next;right=right.next;quick=quick.next.next;
        }
        left.next=null;
        TreeNode l=sortedListToBST(head);
        TreeNode r=sortedListToBST(right);
        TreeNode root=new TreeNode(mid.val);
        root.left=l;root.right=r;
        return root;

    }


}
