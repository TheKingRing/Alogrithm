package BinaryTree.Tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by TheKingRing on 2016/11/4.
 */
public class Serilize {
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder builder=new StringBuilder();
        Queue<TreeNode> que=new LinkedList<>();
        que.offer(root);TreeNode cur;
        while(!que.isEmpty()){
            cur=que.poll();
            builder.append(cur==null?" ":cur.val);
            if(cur!=null){
                que.offer(cur.left);que.offer(cur.right);
            }
        }
        return builder.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        char[] chs=data.toCharArray();
        TreeNode root=new TreeNode(chs[0]-48);
        Queue<TreeNode> que=new LinkedList<>();
        que.offer(root);int i=1;
        TreeNode cur;
        while(que.isEmpty()){
            cur=que.poll();
            cur.left=chs[i]==' '?null:new TreeNode(chs[i++]-48);
            if(i==chs.length)break;
            if(cur.left!=null)que.offer(cur.left);
            cur.right=chs[i]==' '?null:new TreeNode(chs[i++]-48);
            if(i==chs.length)break;
            if(cur.right!=null)que.offer(cur.left);
        }
        return root;

    }
}
