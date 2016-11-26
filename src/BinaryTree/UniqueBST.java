package BinaryTree;

/**
 * Created by TheKingRing on 2016/11/2.
 */
public class UniqueBST {
    public static void main(String[] args) {
        System.out.println(numTrees(3));
    }
    public static int numTrees(int n){
        int[] dp=new int[n+1];
        dp[0]=1;dp[1]=1;
        for(int i=2;i<=n;i++){
            int j=1;
            while (j<=i){
                dp[i]+=dp[j-1]*dp[i-j];
                j++;
            }
        }
        return dp[n];
    }
}
