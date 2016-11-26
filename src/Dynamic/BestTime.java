package Dynamic;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TheKingRing on 2016/11/6.
 */
public class BestTime {
    public static void main(String[] args) {
        int[] a={1,4,2,6,4,3,4,5,6,7};
        System.out.println(maxProfit(a));
    }
    public static int maxProfit(int[] prices) {
        int left=0,mid=1;List<Integer> memo=new ArrayList<>();boolean tag1=true,tag2=true;
        while (mid<prices.length){
            if (prices[mid]>prices[left]&&tag1){
                memo.add(left);tag1=false;tag2=true;
            }else if (prices[mid]<prices[left]&&tag2){
                memo.add(left);tag1=true;tag2=false;
            }
            left=mid;mid++;
        }
        memo.add(left);
        int size=memo.size();
        if (size<2)return 0;
        if (size==2)return helper(prices,0,prices.length);
        if (prices[memo.get(0)]>=prices[ memo.get(1)]){
            memo.remove(0);size--;
            if (size==2)return helper(prices,memo.get(0),prices.length);
        }
        if (prices[memo.get(size-1)]<=prices[memo.get(size-2)] ){
            memo.remove(size-1);size--;
            if (size==2)return helper(prices,memo.get(0),memo.get(size-1)+1);
        }
        int[][] dp=new int[2][size];int min=prices[memo.get(0)];
        for (int i=0;i<dp.length;i++){
            for (int j=0;j<size;j++){
                int k=memo.get(j);
                if (j==0)dp[i][j]=0;
                else if (i==0){
                    if (j%2==0){
                        min=min>prices[k]?prices[k]:min;
                        dp[i][j]=dp[i][j-1];
                    }else {
                        dp[i][j]=dp[i][j-1]>prices[k]-min? dp[i][j-1]:prices[k]-min;
                    }
                }else {
                    if (j==1)dp[i][j]=dp[0][j];
                    else if (j%2==0)dp[i][j]=dp[i][j-1];
                    else {
                        int a=prices[k];int b=prices[memo.get(j-2)];int c=prices[memo.get(j-1)];
                        dp[i][j]=a>b?Math.max(Math.max(dp[0][j-1]+(a-c),dp[i][j-1]+(a-b)),dp[0][j]):Math.max(dp[i][j-1],dp[0][j-1]+a-c);
                    }
                }
            }
        }
        return dp[1][size-1];

    }

    private static int helper(int[] prices, int str, int end) {
        int min=Integer.MAX_VALUE;
        int max=0;
        for(int i=str;i<end;i++){
            if(prices[i]<=min){
                min=prices[i];
            }
            else{
                max=max>prices[i]-min? max:prices[i]-min;
            }
        }
        return max;
    }
    // f[k, ii] represents the max profit up until prices[ii] (Note: NOT ending with prices[ii]) using at most k transactions.
    // f[k, ii] = max(f[k, ii-1], prices[ii] - prices[jj] + f[k-1, jj]) { jj in range of [0, ii-1] }
    //          = max(f[k, ii-1], prices[ii] + max(f[k-1, jj] - prices[jj]))
    // f[0, ii] = 0; 0 times transation makes 0 profit
    // f[k, 0] = 0; if there is only one price data point you can't make any money no matter how many times you can trade
    public int maxProfitBest(int[] prices) {
        if(prices == null || prices.length == 0) return 0;
        int K = 2;int[][] dp=new int[K+1][prices.length];int maxProf=0;
        for (int kk = 1; kk <= K; kk++) {
            int tmpMax = dp[kk-1][0] - prices[0];
            for (int ii = 1; ii < prices.length; ii++) {
                dp[kk][ii] = Math.max(dp[kk][ii-1], prices[ii] + tmpMax);
                tmpMax = Math.max(tmpMax, dp[kk-1][ii] - prices[ii]);
                maxProf = Math.max(dp[kk][ii], maxProf);
            }
        }
        return maxProf;
    }
}
