package DivideandCoquer;

import BinaryTree.Tree.TreeNode;

/**
 * Created by TheKingRing on 2016/11/7.
 */
public class RobberHouse {
    //建立输出类，记录选取根节点和不选根节点两种情况的值以及他们最大值
    class Result{
        int containroot;
        int noroot;
        int max;
        Result(int containroot,int noroot,int max){
            this.containroot=containroot;
            this.noroot=noroot;
            this.max=max;
        }
    }
    public int rob(TreeNode root) {
        Result res=findmax(root);
        return res.max;
    }

    private Result findmax(TreeNode root){
        if(root==null)return new Result(0,0,0);
        /**divide*/
        Result left=findmax(root.left);
        Result right=findmax(root.right);
        /**conquer*/
        int containroot=root.val+left.noroot+right.noroot;
        int noroot=left.max+right.max;
        int max=Math.max(containroot,noroot);
        return new Result(containroot,noroot,max);
    }
}
