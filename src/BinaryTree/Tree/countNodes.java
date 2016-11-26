package BinaryTree.Tree;

/**
 * Created by TheKingRing on 2016/11/3.
 */
public class countNodes {

    public int countNodes(TreeNode root) {
        if (root == null)
            return 0;
            /*分治*/
        TreeNode left = root, right = root;
        int height = 0;
        while (right != null) {
            left = left.left;
            right = right.right;
            height++;
        }
        if (left == null)
            return (1 << height) - 1;//Math.pow(2,h-1)-1
        return 1 + countNodes(root.left) + countNodes(root.right);
    }
}
