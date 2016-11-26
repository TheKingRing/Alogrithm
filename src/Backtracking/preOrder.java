package Backtracking;


import BinaryTree.Tree.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TheKingRing on 2016/10/29.
 */
public class preOrder {
    public static void main(String[] args) {
        int[] pre={1,2,3};
        int[] in={3,2,1};
        System.out.println(buildTree(pre,in));
    }
    public static TreeNode buildTree(int[] preorder, int[] inorder) {
        if(preorder.length==0)return null;
        TreeNode root=new TreeNode(preorder[0]);
        if (preorder.length==1)return root;
        List<TreeNode> nodes=new ArrayList<>();
        List<Integer> in=new ArrayList<>();
        int position=0;
        for (int k=0;k<inorder.length;k++){
            if (k!=0)nodes.add(new TreeNode(preorder[k]));
            if (inorder[k]!=preorder[0])   in.add(inorder[k]);
            else position=k;
        }
        traverse(nodes,in,position,preorder.length-1);
        if (position==0&&nodes.size()==1)root.right=nodes.get(0);
        else if (position==preorder.length-1) root.left=nodes.get(0);
        else {root.left=nodes.get(0);root.right=nodes.get(1);}
        return root;
    }

    private static void traverse(List<TreeNode> nodes, List<Integer> in, int p, int l) {
        for (int k=0;k<l;){
            if (k<l-1&&nodes.get(k).val==in.get(k+1)&&nodes.get(k+1).val==in.get(k)){
                nodes.get(k).left=nodes.get(k+1);
                nodes.remove(k+1);
                in.remove(k);
                l=in.size();
            }else if (nodes.get(k).val==in.get(k)){
                TreeNode node=nodes.get(k);
                while (k<l-1){
                    if (nodes.get(k+1).val==in.get(k+1)){
                        node.right=nodes.get(k+1);
                        in.remove(k+1);
                        nodes.remove(k+1);
                        node=node.right;l=in.size();
                        if ((p==0||p==l)&&nodes.size()==1)return;
                        else if (nodes.size()==2)return;
                    }else break;
                }
                k++;
            }else {
                l=in.size();k++;
            }
            if ((p==0||p==l)&&nodes.size()==1)return;
            else if (nodes.size()==2)return;
        }
        if ((p==0||p==l)&&nodes.size()==1)return;
        else if (nodes.size()==2)return;
        traverse(nodes,in,p,in.size());
    }

}
