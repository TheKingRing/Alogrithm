package BinaryTree.Tree;

import java.util.*;

/**
 * Created by TheKingRing on 2016/10/29.
 */
public class TreeTraverse {
    public static void main(String[] args) {
        int[] pre={1,-1,2,3};
        int[] in={-1,1,3,2};
        TreeNode root=buildTree(pre,in);

        String st=serialize(root);
        deserialize(st);

    }
    public static String serialize(TreeNode root) {
        if(root==null)return null;
        StringBuilder builder=new StringBuilder();
        Queue<TreeNode> que=new LinkedList<>();
        que.offer(root);TreeNode cur;
        while(!que.isEmpty()){
            cur=que.poll();
            builder.append(cur==null?" ":cur.val);builder.append('%');
            if(cur!=null){
                que.offer(cur.left);que.offer(cur.right);
            }
        }
        return builder.toString();
    }
    public static TreeNode deserialize(String data) {
        if(data==null)return null;
        String[] chs=data.split("%");
        TreeNode root=new TreeNode(Integer.parseInt(chs[0]));
        Queue<TreeNode> que=new LinkedList<>();
        que.offer(root);int i=1;
        TreeNode cur;
        if (i==chs.length)return root;
        while(!que.isEmpty()){
            cur=que.poll();
            cur.left=chs[i].equals(" ")?null:new TreeNode(Integer.parseInt(chs[i]));
            i++;
            if(i==chs.length)break;
            if(cur.left!=null)que.offer(cur.left);
            cur.right=chs[i].equals(" ") ?null:new TreeNode(Integer.parseInt(chs[i]));
            i++;
            if(i==chs.length)break;
            if(cur.right!=null)que.offer(cur.right);
        }
        return root;

    }
    public static int maxPathSum(TreeNode root) {
         return helper(root);
    }

    public static List<Integer> postorderTraversal(TreeNode root) {
        Stack<TreeNode> stack=new Stack<>();
        List<Integer> l=new ArrayList<>();
        stack.push(root);TreeNode cur;TreeNode preleft=null;TreeNode preright=null;
        while(!stack.isEmpty()&&(cur=stack.peek())!=null){
            while(true){
                TreeNode tmp=null;
                if(cur.right!=null&&cur.right!=preright){
                    stack.push(cur.right);
                    tmp=cur.right;
                }
                if(cur.left!=null&&cur.left!=preleft){
                    stack.push(cur.left);
                    tmp=cur.left;
                }
                if(tmp==null)break;
                cur=tmp;
            }
            stack.pop(); l.add(cur.val);
            if(!stack.isEmpty()){
                preright=stack.peek().right==cur?cur:preright;
                preleft=stack.peek().left==cur?cur:preleft;
            }

        }
        return l;
    }

    private static int helper(TreeNode root) {
        if(root==null)return Integer.MIN_VALUE/2+1;
        int rightvalue=helper(root.left);
        int leftvalue=helper(root.right);
        int cross=rightvalue+leftvalue+root.val;
        return Math.max(Math.max(leftvalue,rightvalue),cross);
    }

    public static TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder.length == 0) return null;
        Stack<TreeNode> s = new Stack<>();
        TreeNode root = new TreeNode(preorder[0]), cur = root;
        for (int i = 1, j = 0; i < preorder.length; i++) {
            if (cur.val != inorder[j]) {
                cur.left = new TreeNode(preorder[i]);
                s.push(cur);
                cur = cur.left;
            } else {
                j++;
                while (!s.empty() && s.peek().val == inorder[j]) {
                    cur = s.pop();
                    j++;
                }
                cur = cur.right = new TreeNode(preorder[i]);
            }
        }
        return root;
    }


    private static void preorder(TreeNode root){
        if (root!=null){
            Stack<TreeNode> stack=new Stack<>();
            stack.push(root);
            while (!stack.isEmpty()){
               TreeNode node= stack.pop();
                visited(node);
                if (node.right!=null){
                    stack.push(node.right);
                }
                if (node.left!=null){
                    stack.push(node.left);
                }
            }
        }
    }
    private static  void inorder(TreeNode root){
        if (root!=null){
            Stack<TreeNode> stack=new Stack<>();
            stack.push(root);
            TreeNode cur=root;
            while (!stack.isEmpty()){
                TreeNode node=stack.peek();
                if (stack.isEmpty()||cur==node.left)stack.pop();
                while (node.left!=null) {
                    stack.push(node.left);
                    node = node.left;
                }
                cur=stack.pop();
                visited(cur);
                if (node.right!=null){
                    stack.push(node.right);
                    cur=node.right;
                }
            }
        }
    }


    public static void postorder(TreeNode root){
        if (root!=null){
            Stack<TreeNode> nodes=new Stack<>();
            nodes.push(root);
            while (!nodes.isEmpty()){
                TreeNode node=nodes.pop();
                if (nodes.isEmpty()||node==nodes.peek().left)nodes.push(node);
                else visited(node);
                while (node.left!=null){
                    nodes.push(node.left);
                    node=node.left;
                }
                if (node.right!=null){
                    nodes.push(node.right);
                }else {
                    node= nodes.pop();
                    if (node==nodes.peek().left){
                        visited(node);
                    }else nodes.push(node);
                }
            }
        }
    }


    private static void visited(TreeNode pop) {
        System.out.print(pop.val+"\t");
    }
}
