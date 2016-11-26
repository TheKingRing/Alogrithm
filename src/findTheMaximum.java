/**
 * Created by TheKingRing on 2'0''1'6/'1''0'/'1'5.
 */
public class findTheMaximum {
    public static void main(String[] args) {
        char[][] matrix={{'1' ,'0' ,'1' ,'0' ,'0'},
                {'1' ,'0' ,'1' ,'1' ,'1'},
                {'1' ,'1' ,'1' ,'1' ,'1'},
                {'1' ,'0' ,'0' ,'1' ,'0'}};
        maximalRectangle(matrix);
    }
    public  static int maximalRectangle(char[][] matrix) {
        int left=0,right=0;
        int max=0;
        BinaryNode root=new BinaryNode(matrix[left][right]);
        generateBinaryTree(root,matrix,left+1,right+1);

        return 0;
    }

    private static void generateBinaryTree(BinaryNode root, char[][] matrix, int left, int right) {
        if (left==matrix.length&&right==matrix[0].length)return;
        if (left<matrix.length){
            root.left=new BinaryNode(matrix[left][right-1]);
            generateBinaryTree(root.left,matrix,left+1,right);
        }if (right<matrix[0].length){
            root.right=new BinaryNode(matrix[left-1][right]);
            generateBinaryTree(root.right,matrix,left,right+1);
        }
    }
}
