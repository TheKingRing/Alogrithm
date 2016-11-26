package BinaryTree.Tree;


import java.util.Deque;
import java.util.LinkedList;

/**
 * Created by TheKingRing on 2016/11/3.
 */
public class mirror {
    public boolean isSymmetric(TreeNode root) {
        if(root==null) return true;
        Deque<TreeNode> deque=new LinkedList<>();
        deque.add(root);
        if (root.left!=null||root.right!=null){
            deque.addFirst(root.left);
            deque.addLast(root.right);
        }
        TreeNode left,right;
        while (deque.peek()!=root){
            left=deque.pollFirst();
            right=deque.pollLast();
            if (!equals(left,right))return false;
            if ((left!=null)&&(left.left!=null||left.right!=null)){
                deque.addFirst(left.right);
                deque.addFirst(left.left);
            }
            if ((right!=null)&&(right.left!=null||right.right!=null)){
                deque.addLast(right.left);
                deque.addLast(right.right);
            }

        }
        return true;

    }
    public boolean isSymmetric2(TreeNode root) {
        if (root==null)return true;
        return solve(root.left,root.right);
    }

    private boolean solve(TreeNode left, TreeNode right) {
        if (!equals(left, right)) return false;
        if(left==null)return true;
        return solve(left.left, right.right) && solve(left.right, right.left);
    }


    private boolean equals(TreeNode n1,TreeNode n2){
        if (n1==null&&n2==null)return true;
        if (n1==null)return false;
        if (n2==null)return false;
        return n1.val == n2.val;
    }
}
