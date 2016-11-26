package Backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TheKingRing on 2016/10/29.
 */
public class Combination {
    public static void main(String[] args) {

    }
    public  List<List<Integer>> combine1(int n, int k) {
        List<List<Integer>> result=new ArrayList<>();
        boolean[] tag=new boolean[n+1];
        dfs(result,tag,n,k,0,1);
        return result;
    }
    private void dfs(List<List<Integer>> result, boolean[] tag, int n, int k,int count,int p){
        if(count==k){
            List<Integer> l=new ArrayList<>();
            for (int i=1;i<tag.length;i++){
                if (tag[i])l.add(i);
            }
            result.add(l);
            return;
        }
        for(int i=p;i<n+1;i++){
            tag[i]=true;
            dfs(result,tag,n,k,count+1,i+1);
            tag[i]=false;
        }
    }
    public  List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result=new ArrayList<>();
        List<Integer> l=new ArrayList<>();
        dfs(result,l,n,k,1);
        return result;
    }
    private  void dfs(List<List<Integer>> result, List<Integer> l,  int n, int k,int p){
        if(l.size()==k){
            result.add(generate(l));
            return;
        }
        for(int i=p;i<n+1;i++){
            l.add(i);
            dfs(result,l,n,k,i+1);
            l.remove(l.size()-1);
        }
    }
    private  List<Integer> generate(List<Integer> l){
        List<Integer> r=new ArrayList<Integer>();
        for(int k:l)r.add(k);
        return r;
    }
}
