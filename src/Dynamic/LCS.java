package Dynamic;

/**
 * Created by TheKingRing on 2016/11/7.
 */
public class LCS {
    public static void main(String[] args) {
        int[] a={10,9,2,5,3,7,101,18};
        System.out.println(lengthOfLIS(a));
    }
    public static int lengthOfLIS(int[] nums) {
        int[] dp=new int[nums.length];dp[0]=1;int maxvale=nums[0];
        for(int i=1;i<nums.length;i++){
               if (nums[i]>nums[i-1]){
                   if (nums[i]>maxvale){maxvale=i;dp[i]=dp[i-1]+1;}
                   else {
                       dp[i]=dp[i-1];
                       int k=i-1;
                       while (k>=0){
                           if (nums[k]>=nums[i]){k--;continue;}
                           if (dp[k]+1>dp[i]){dp[i]=dp[k]+1;maxvale=nums[i];break;}
                           k--;
                       }
                   }
               }else {
                   dp[i]=dp[i-1];
                   if (dp[i]==1)maxvale=nums[i];
               }
        }
        return dp[nums.length-1];
    }
}
