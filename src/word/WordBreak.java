package word;

import java.util.*;

/**
 * Created by TheKingRing on 2016/10/27.
 */
public class WordBreak {
    public static void main(String[] args) {
        String[] s= {"aaaa","aa"};
        Set<String> set=new HashSet<>();
        for (String str:s){
            set.add(str);
        }
        String st="aaaaaaa";
        System.out.println(wordBreak(st,set));
    }
    public static List<String> wordBreak(String s, Set<String> wordDict) {
        if (wordDict.size()==0||s==null||"".equals(s))return new ArrayList<>();
        boolean[] tag=new boolean[26];
        for(String str1:wordDict){
            for(char ch:str1.toCharArray()){
                tag[ch-'a']=true;
            }
        }
        for(char ch:s.toCharArray()){
            if(!tag[ch-'a'] ) return new ArrayList<>();
        }
        boolean[] dp=new boolean[s.length()+1];
        Map<Integer,Set<String>> map=new HashMap<>();
        for(int i=1;i<dp.length;i++){
            String str=s.substring(0,i);
            Set<String> l=new HashSet<>();
            if(wordDict.contains(str)){
                l.add(str);
            }
            findother(dp,wordDict,s,map,l,i);
            if (l.size()!=0){ map.put(i,l);dp[i]=true;}
            if (l.size()==0&&i==dp.length - 1)return new ArrayList<>();
        }
        List<String> result=new ArrayList<>();
        result.addAll(map.get(dp.length - 1));
        return result;
    }

    private static void findother(boolean[] dp, Set<String> wordDict, String s, Map<Integer, Set<String>> map, Set<String> l, int i) {
        for( int j:map.keySet()){
            String tmpstr=s.substring(j,i);
            if(dp[j]&&wordDict.contains(tmpstr)){
                for(String sj:map.get(j)){
                    StringBuilder sb=new StringBuilder(sj);
                    sb.append(" ");sb.append(tmpstr);
                    l.add(sb.toString());
                }
            }
        }
    }

}
