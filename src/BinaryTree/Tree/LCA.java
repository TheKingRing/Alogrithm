package BinaryTree.Tree;

/**
 * Created by TheKingRing on 2016/11/4.
 */
public class LCA {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
       if (root==null||root==p ||root==q){
           return root;
       }
       //divide the problem to two child
        TreeNode left=lowestCommonAncestor(root.left,p,q);
        TreeNode right=lowestCommonAncestor(root.right,p,q);
       //for each result conquer

        if (left!=null&&right!=null)return root;//如果左右两边都找到了1个返回根
        if (left!=null)return left;//左节点找到，右为null返回左
        if (right!=null)return right;//右节点找到，左为null返回左
        return null;//都没找到

    }

}
