package BinaryTree;


import BinaryTree.Tree.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by TheKingRing on 2016/11/4.
 */
public class UniqueBST2 {
    public static List<TreeNode> generateTrees(int n) {
        if(n==0)return new ArrayList<>();
        List<TreeNode> l=new ArrayList<>();
        int i=1;
        TreeNode root=new TreeNode(i);
        l.add(root);
        generate(i+1,l,l.size(),n);
        return l;
    }
    private static void generate(int n, List<TreeNode> l, int size, int tag){
        if(n>tag)return;
        for(int i=0;i<size;i++){
            RightInsert(l.get(i),l,n,i);
        }
        generate(n+1,l,l.size(),tag);
    }

    private static void RightInsert(TreeNode root, List<TreeNode> l, int n,int tag){
        Stack<TreeNode> stack=new Stack<>();
        stack.push(root);TreeNode cur=root;TreeNode pre=null;
        while (cur.right != null) {
            stack.push(cur.right);
            cur=cur.right;
        }
        while(!stack.isEmpty()) {
            cur = stack.peek();
            TreeNode tmp = new TreeNode(n);
            cur.right = tmp;
            tmp.left=pre;
            l.add(clone(root));
            cur.right=pre;
            pre = stack.pop();
        }
        TreeNode tmp = new TreeNode(n);
        tmp.left=pre;
        l.set(tag,tmp);
    }
    private static TreeNode clone(TreeNode n) {
        if (n == null) {
            return null;
        }
        TreeNode node = new TreeNode(n.val);
        node.left=n.left;
        node.right = clone(n.right);
        return node;
    }
}
