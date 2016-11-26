package Dynamic;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TheKingRing on 2016/11/21.
 */
public class Palindrome {
    public static void main(String[] args) {
        partition("aab");

    }

    public static List<List<String>> partition(String s) {
        if(s.equals(""))return new ArrayList<>();
        int len=s.length();
        boolean[][] dp=getIsPalindrome(s,len);
        List<List<String>> res=new ArrayList<>();
        List<String> l=new ArrayList<>();
        dfs(s,res,l,dp,0,len);
        return res;
    }

    private static void dfs(String s, List<List<String>> res, List<String> l, boolean[][] dp, int p, int len) {
        if (p==len){
            res.add(new ArrayList<>(l));
            return;
        }
        int k=p;
        while (k<len){
            if (dp[p][k]){
                l.add(s.substring(p,k+1));
                dfs(s, res, l, dp, k+1, len);
                l.remove(l.size()-1);
            }
            k++;
        }

    }

    private static boolean[][] getIsPalindrome(String s, int len) {
        boolean[][] isPalindrome = new boolean[len][len];

        for (int i = 0; i <len; i++) {
            isPalindrome[i][i] = true;
        }
        for (int i = 0; i <len - 1; i++) {
            isPalindrome[i][i + 1] = (s.charAt(i) == s.charAt(i + 1));
        }

        for (int length = 2; length <len; length++) {
            for (int start = 0; start + length < len; start++) {
                isPalindrome[start][start + length]
                        = isPalindrome[start + 1][start + length - 1] && s.charAt(start) == s.charAt(start + length);
            }
        }

        return isPalindrome;
    }
}
