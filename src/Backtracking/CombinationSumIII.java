package Backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;




/**
 * Created by TheKingRing on 2016/11/22.
 */
public class CombinationSumIII {
    public static void main(String[] args) {
        System.out.println(combinationSum3(3,15));
        int[] a={5,1,8};
        System.out.println(combinationSum4(a,14));
    }
    public static List<List<Integer>> combinationSum3(int k, int n) {
        if(n>maxVlue(k)||n<minValue(k))return new ArrayList<>();
        int max=9,sum=0,m=k;
        while (m!=1){
            sum+=max--;m--;
        }
        int min=n-sum<1?1:n-sum;
        List<List<Integer>> list=new ArrayList<>();
        List<Integer> l=new ArrayList<>();

        dfs(list,l,k,n,min,0);
        return list;
    }
    private static boolean dfs(List<List<Integer>> list, List<Integer> l, int k, int n, int str, int sum){
        if(l.size()==k){
            if(sum==n){
                list.add(new ArrayList<>(l));return true;
            }else{
                return false;
            }
        }

        for(int i=str;i<10;i++){
            l.add(i);
            if(dfs(list,l,k,n,i+1,sum+i)){
                l.remove(l.size()-1);
                break;
            }
            l.remove(l.size()-1);
        }

        return false;
    }
    private static int maxVlue(int k){
        int sum=0;int str=9;
        while (k>0){
            sum+=str--;k--;
        }
        return sum;
    }
    private static int minValue(int k) {
        int sum=0;int str=1;
        while (k>0){
            sum+=str++;k--;
        }
        return sum;
    }
    public static int combinationSum4(int[] nums, int target) {
        Arrays.sort(nums);
        if (target<nums[0])return 0;
        int[] dp=new int[target+1];
        dp[0]=0;int p=0;
        for(int i=1;i<=target;i++){
            if (p<nums.length&&i==nums[p]){dp[i]=1;p++;
            }
            for (int k:nums){
                if (p<nums.length&&k==nums[p])break;
                dp[i]+=dp[i-k];
            }
        }
        return dp[target];
    }
    /**
     * backtracking when sum==target record the list,then return false to end the circle
     * if sum>target return false either;
     * when the for circle end return true to remove the last element;
     * */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);
        if (target<candidates[0])return new ArrayList<>();
        List<List<Integer>> list=new ArrayList<>();
        List<Integer> l=new ArrayList<>();
        dfs(candidates,list,l,target,0,0);
        return list;
    }
    /**
     * @param candidates :the number which can be used
     * @param l :the List<Integer> which is the subresult
     * @param list:what we want to get
     * @param target:the sum we aims to be equaled
     * @param str:the index of the begin item which can used to avoid repeating
     * @param sum:the result of the latter recursion
     * */
    private boolean dfs(int[] candidates, List<List<Integer>> list, List<Integer> l, int target, int sum,int str) {
        if (sum==target){
            list.add(new ArrayList<>(l));
            return true;
        }else if (sum>target)return true;
        for (int i=str;i<candidates.length;i++){
            l.add(candidates[i]);
            if (dfs(candidates, list, l, target, sum+candidates[i],i)){
                l.remove(l.size()-1);break;//when return false will be compiled
            }
            l.remove(l.size()-1);//when return true will be compiled
        }
        return false;
    }

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        if(candidates.length==0)return new ArrayList<>();
        Arrays.sort(candidates);
        if(target<candidates[0])return new ArrayList<>();
        List<List<Integer>> list=new ArrayList<>();
        List<Integer> l=new ArrayList<>();
        dfs1(candidates,list,l,target,0,0);
        return list;
    }
    private boolean dfs1(int[] A,List<List<Integer>> res,List<Integer> l,int tar,int sum,int str){
        if(sum==tar){
            res.add(new ArrayList<>(l));return true;
        }else if(sum>tar)return true;
        for(int i=str;i<A.length;i++){
            l.add(A[i]);
            if(dfs(A,res,l,tar,sum+A[i],i+1)){
                l.remove(l.size()-1);break;
            }
            l.remove(l.size()-1);
            //避免下一位之前的相同而造成的重复
            while(i<A.length-1){
                if(A[i]<A[i+1])break;
                i++;
            }
        }
        return false;
    }
}
