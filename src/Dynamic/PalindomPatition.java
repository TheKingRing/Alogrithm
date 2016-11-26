package Dynamic;

/**
 * Created by TheKingRing on 2016/11/6.
 */
public class PalindomPatition {
    public static void main(String[] args) {
        System.out.println(minCut("aab"));
    }

    public static int minCut(String s) {
        boolean[][] mark=getIsPalindrome(s);
        int[] dp=new int[s.length()+1];dp[0]=0;
        for(int i=1;i<dp.length;i++){
            dp[i]=i;
            for(int j=i-1;j>=0;j--){
                if(!mark[j][i-1]){
                    continue;
                }
                dp[i]=Math.min(dp[i],dp[j]+1);
            }
        }
        return dp[dp.length-1]-1;
    }

    private static boolean[][] getIsPalindrome(String s) {
        boolean[][] isPalindrome = new boolean[s.length()][s.length()];

        for (int i = 0; i < s.length(); i++) {
            isPalindrome[i][i] = true;
        }
        for (int i = 0; i < s.length() - 1; i++) {
            isPalindrome[i][i + 1] = (s.charAt(i) == s.charAt(i + 1));
        }

        for (int length = 2; length < s.length(); length++) {
            for (int start = 0; start + length < s.length(); start++) {
                isPalindrome[start][start + length]
                        = isPalindrome[start + 1][start + length - 1] && s.charAt(start) == s.charAt(start + length);
            }
        }

        return isPalindrome;
    }
}
