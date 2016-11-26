package Dynamic;

import java.util.*;

/**
 * Created by TheKingRing on 2016/11/7.
 */
public class wordBreak {
    public static void main(String[] args) {


        String s = "catsanddog";
        String[]  dict = {"cat","cats","and","sand","dog"};
        Set<String> set=new HashSet<>();
        Collections.addAll(set,dict);
    }


    public static boolean wordBreakAnalyze(String s, Set<String> wordDict) {
            if (wordDict.size()==0)return false;
            int len = s.length();int maxLen=0,minLen=len;
            for (String word : wordDict) {
                int l=word.length();
                if (l>maxLen)maxLen=l;
                if (l<minLen)minLen=l;
            }
            if(len<minLen)return false;
            //the memo array
            boolean[] memo = new boolean[len+1];
            //base case
            memo[0] = true;
            if(wordDict.contains(s.substring(0,1)))memo[1]=true;
            //Iteration
            for(int i = 2;i <= len;i++){
                for(int j = i-minLen; j>=(i-maxLen<0?0:i-maxLen);j--){
                    String cur = s.substring(j,i);
                    if(!wordDict.contains(cur)||!memo[j])continue;
                    memo[i]=true;break;
                }
            }
            return memo[len];
        }

    public  List<String> wordBreak(String s, Set<String> wordDict) {
        if(wordDict.size()==0||"".equals(s)||s==null)return new ArrayList<>();
        int len = s.length();int maxLen=0,minLen=len;
        for (String word : wordDict) {
            int l=word.length();
            if (l>maxLen)maxLen=l;
            if (l<minLen)minLen=l;
        }
        if(len<minLen)return new ArrayList<>();
        //the memo array
        boolean[] memo = new boolean[len+1];
        //base case
        memo[0] = true;
        //Iteration
        for(int i = 1;i <= len;i++){
            for(int j = i-minLen; j>=(i-maxLen<0?0:i-maxLen);j--){
                if(!memo[j])continue;
                String cur = s.substring(j,i);
                if(wordDict.contains(cur)){
                    memo[i]=true;break;
                }

            }
        }
        if(!memo[len])return new ArrayList<>();
        Map<Integer,List<String>> map=new HashMap<>();List<String> l=new ArrayList<>();l.add("");map.put(0,l);
        for(int i=1;i<memo.length;i++){
            DFS(s,map,wordDict,memo,i, i-minLen,i-maxLen<0?0:i-maxLen);
        }
        List<String> res=map.get(memo.length-1);
        return res==null?new ArrayList<>():res;
    }

    private static void DFS(String s, Map<Integer, List<String>> map, Set<String> wordDict, boolean[] dp, int i, int j, int end) {
        List<String> l=new ArrayList<>();
        while (j>=end){
            if(!dp[j]){
                j--;continue;
            }
            String str=s.substring(j,i);
            if(wordDict.contains(str)){
                List<String> list=map.get(j);
                for (String st:list){
                    StringBuilder sb=new StringBuilder(st);
                    if (sb.length()==0){
                        sb.append(str);
                    }else sb.append(" ").append(str);
                    l.add(sb.toString());
                }
                dp[i]=true;
            }
            j--;

        }
        if (dp[i]){
            map.put(i,l);
        }
    }
}
