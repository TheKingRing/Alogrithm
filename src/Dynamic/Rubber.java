package Dynamic;

/**
 * Created by TheKingRing on 2016/11/6.
 */
public class Rubber {
    public static void main(String[] args) {
        int[] a={1,2,3};
        System.out.println(rob(a));
    }
    public static int rob(int[] nums) {
        int[][] dp=new int[2][nums.length+1];
        for(int i=0;i<2;i++){
            for(int j=1;j<=nums.length;j++){
                if(i==0){
                    if(j==1)dp[i][j]=0;
                    else dp[i][j]=Math.max(dp[i][j-1],dp[i][j-2]+nums[j-1]);
                }else{
                    if(j==1)dp[i][j]=nums[j-1];
                    else if(j==2)dp[i][j]=dp[i][j-1];
                    else if(j!=nums.length)dp[i][j]=Math.max(dp[i][j-1],dp[i][j-2]+nums[j-1]);
                    else dp[i][j]=dp[i][j-1];
                }
            }
        }
        return Math.max(dp[1][nums.length],dp[0][nums.length]);
    }

}
