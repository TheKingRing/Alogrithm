package word;

import java.util.Set;

/**
 * Created by TheKingRing on 2016/10/27.
 */
public class WordBreakll {
    public static boolean wordBreak(String s, Set<String> wordDict) {
        int length=s.length();
        boolean[] dp=new boolean[length+1];dp[0]=true;
        for (int i=1;i<=length;i++){
            if(wordDict.contains(s.substring(0,i)))dp[i]=true;
            else {
                for (int j=i-1;j>=0;j--){
                    if (dp[j]&&wordDict.contains(s.substring(j,i))){
                        dp[i]=true;break;
                    }
                }
            }
        }
        return dp[length];
    }
}
