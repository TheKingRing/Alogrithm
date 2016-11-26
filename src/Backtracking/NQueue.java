package Backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TheKingRing on 2016/11/20.
 */
public class NQueue {
    public static void main(String[] args) {
        System.out.println(solveNQueens(4));
        System.out.println(totalNQueens(4));
    }
    public static List<List<String>> solveNQueens(int n) {
        List<Integer> l=new ArrayList<>(n);
        List<List<Integer>> res=new ArrayList<>();
        boolean[] isVisit=new boolean[n];
        permutation(res,l,isVisit,n);
        return getFinalResult(res,n);
    }

    /**
     * transform the column and row the a permutation problem
     * for example give n=4,[1,2,3,4]means the first queen is on (1,1);second (2,2)...(n,n)
     * which can avoid the attack on the row and column
     * */
    private static void  permutation(List<List<Integer>> res, List<Integer> l, boolean[] isVisit, int tag){
        if(l.size()==tag){
            res.add(new ArrayList<>(l));
            return;
        }
        for(int i=0;i<tag;i++){
            if(isVisit[i])continue;
            if(!check(l,i))continue;
            isVisit[i]=true;
            l.add(i);
            permutation(res,l,isVisit,tag);
            l.remove(l.size()-1);
            isVisit[i]=false;
        }
    }
    /**
     * if x+y==x'+y' or x-y=x'-y' we can find the two point is on a A diagonal line;
     * we should find all  diagonal line that be used to avoid the queens attack
     * */
    private static boolean check(List<Integer> l, int k){
        int len=l.size();int sum=k+len;int D_value=len-k;
        for(int i=0;i<len;i++){
            if(i+l.get(i)==sum)return false;
            if(i-l.get(i)==D_value)return false;
        }
        return true;
    }

    private static List<List<String>> getFinalResult(List<List<Integer>> l, int tag){
        List<List<String>> res=new ArrayList<>();
        for(List<Integer> list:l){
            List<String> tmp=new ArrayList<>();
            for(int k:list){
                StringBuilder sb=new StringBuilder();
                int i=0;
                while(i<tag){
                    if(i==k)sb.append('Q');
                    else sb.append('.');
                    i++;
                }
                tmp.add(sb.toString());
            }
            res.add(tmp);
        }
        return res;
    }
    public static int totalNQueens(int n) {
        int[] res=new int[1];
        boolean[] isVisited=new boolean[n];
        List<Integer> l=new ArrayList<>(n);
        dfs(n,res,isVisited,l);
        return res[0];
    }
    private static void dfs(int n, int[] res, boolean[] isVisited, List<Integer> l){
        if(l.size()==n){
            res[0]++;
            return;
        }
        for(int i=0;i<n;i++){
            if(isVisited[i])continue;
            if(!check1(l,i))continue;
            isVisited[i]=true;
            l.add(i);
            dfs(n,res,isVisited,l);
            isVisited[i]=false;
            l.remove(l.size()-1);
        }
    }

    private static boolean check1(List<Integer> l, int k){
        int len=l.size();
        int sum=len+k;int D_value=len-k;
        for(int i=0;i<len;i++){
            if(i+l.get(i)==sum||i-l.get(i)==D_value)return false;
        }
        return true;
    }
}
