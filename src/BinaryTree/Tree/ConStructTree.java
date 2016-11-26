package BinaryTree.Tree;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * Created by TheKingRing on 2016/11/4.
 */
public class ConStructTree {
    /**
     * divide and conquer
     * for example:inorder [2,1,4,3,5,7,6]
     *           postorder [2,4,7,6,5,3,1]
     * first the root val is 1,and it can cut the inorder in two part[2],[4,3,5,7,6] whose length is 1,5;
     * the length can divide post order into two part:[2],[4,7,6,5,3].
     * we can see that: the two pairs of subset of postorder and inorder can construct two subtree
     * which is the root left and right
     * */
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        if (inorder.length == 0) return null;
        Map<Integer, Integer> index = new HashMap<>();//get the value-index
        for (int i = 0; i < inorder.length; i++) index.put(inorder[i], i);
        return helper(postorder, index, 0, postorder.length - 1, postorder.length - 1);
    }
    /**
     * @param l :the left boarder of the inorder in each recursion
     * @param r :the right boarder of the inorder in each recursion
     * @param postOrder :the array of postorder traverse
     * @param p :the postOrder index of the root in each recursion
     * */
    private TreeNode helper(int[] postOrder, Map<Integer, Integer> map, int l, int r, int p) {
        if (l > r) return null;//when it is the leaf,the left and right will be null

        int tmp = r - map.get(postOrder[p]);
        TreeNode root = new TreeNode(postOrder[p]);
        //divide
        TreeNode leftN = helper(postOrder, map, l, map.get(postOrder[p]) - 1, p - tmp - 1);
        TreeNode rightN = helper(postOrder, map, map.get(postOrder[p]) + 1, r, p - 1);

        //conquer
        root.left = leftN;
        root.right = rightN;
        return root;

    }
    public TreeNode buildTree1(int[] inorder, int[] postorder) {
        if (inorder == null || inorder.length < 1) return null;
        int i = inorder.length - 1;
        int p = i;
        TreeNode node;
        TreeNode root = new TreeNode(postorder[postorder.length - 1]);
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        p--;

        while (true) {
            if (inorder[i] == stack.peek().val) { // inorder[i] is on top of stack, pop stack to get its parent to get to left side
                if (--i < 0) break;
                node = stack.pop();
                if (!stack.isEmpty() && inorder[i] == stack.peek().val) {// continue pop stack to get to left side
                    continue;
                }
                node.left = new TreeNode(postorder[p]);
                stack.push(node.left);
            } else { // inorder[i] is not on top of stack, postorder[p] must be right child
                node = new TreeNode(postorder[p]);
                stack.peek().right = node;
                stack.push(node);
            }
            p--;
        }

        return root;
    }
}
