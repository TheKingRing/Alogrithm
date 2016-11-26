package Dynamic;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/**
 * Created by TheKingRing on 2016/11/8.
 */
public class largestproduct {
    public static void main(String[] args) {
        int[] nums={7,-2,-4};
        System.out.println(maxProduct(nums));
    }
    public static int maxProduct(int[] nums) {
        int[] dp=new int[nums.length];
        Stack<Integer> stack=new Stack<>();
        dp[0]=nums[0];if(nums[0]<0)stack.push(nums[0]);Set<Integer> set=new HashSet<>();
        for(int i=1;i<dp.length;i++){
            if(nums[i]==0){
                dp[i]=0; stack.clear();set.add(i-1);
            }
            else if(nums[i]<0){
                if(stack.isEmpty()){
                    if(dp[i-1]<0){
                        dp[i]=dp[i-1]*nums[i];
                    }else if(dp[i-1]>0&&nums[i-1]<0){
                        dp[i]=Math.max(dp[i-1],nums[i]*nums[i-1]);
                        if(dp[i]==dp[i-1])stack.push(nums[i]);
                        else {
                            int k=i-2;
                            while(k>=0){
                                if(nums[k]<0){
                                    stack.push(nums[k]);break;
                                }
                                k--;
                            }
                        }
                    }else if(dp[i-1]==0){
                        dp[i]=nums[i];stack.push(nums[i]);
                    }else {
                        dp[i]=dp[i-1];stack.push(nums[i]);
                    }
                }
                else{
                    if(dp[i-1]<0){
                        dp[i]=dp[i-1]*nums[i];stack.pop();
                    }
                    else{
                        dp[i]=dp[i-1]*nums[i]*stack.pop();
                    }
                }
            }else{
                if(dp[i-1]<=0)dp[i]=nums[i];
                else dp[i]=dp[i-1]*nums[i];
            }
        }

        int max=Integer.MIN_VALUE;
        if(set.size()==0)return dp[nums.length-1];
        for(int k:set){
            max=Math.max(max,dp[k]);
        }
        return Math.max(max,0);

    }
}
