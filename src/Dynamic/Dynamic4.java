package Dynamic;

/**
 * Created by TheKingRing on 2016/11/5.
 */
public class Dynamic4 {
    public static void main(String[] args) {

    }
    public static boolean isInterleave(String s1, String s2, String s3) {
       char[] ch1=s1.toCharArray();char[] ch2=s2.toCharArray();char[] ch3=s3.toCharArray();
        if (ch1.length + ch2.length != ch3.length) return false;
        if (ch1.length == 0 && ch2.length == 0) return ch3.length == 0;
        if (ch1.length == 0) return s2.equals(s3);
        if (ch2.length == 0) return s1.equals(s3);
        boolean[][] dp=new boolean[ch1.length+1][ch2.length+1];//动态矩阵
        for(int i=0;i<=ch1.length;i++){
            boolean tag=true;//标志位，判断这一行是不是都为false；是则提前结束
            for(int j=0;j<=ch2.length;j++){
                if(i==0&&j==0)dp[i][j]=true;
                else if(i==0){
                    if (dp[i][j-1]){
                        tag=false;
                    dp[i][j]=(ch3[i+j-1]==ch2[j-1]);
                    }
                }else if(j==0){
                    if (dp[i-1][j]){
                        tag=false;
                        dp[i][j]=(ch3[i+j-1]==ch1[i-1]);
                    }
                }else{
                    //动态方程关系：
                    if (dp[i-1][j]&&dp[i][j-1]){
                        dp[i][j]=(ch3[i+j-1]==ch1[i-1])||(ch3[i+j-1]==ch2[j-1]); tag=false;
                    }
                    else if (dp[i-1][j]){
                        dp[i][j]=ch3[i+j-1]==ch1[i-1];tag=false;
                    }
                    else if (dp[i][j-1]){
                        dp[i][j]=ch3[i+j-1]==ch2[j-1];  tag=false;
                    }

                }
            }
            if (tag)break;
        }
        return dp[ch1.length][ch2.length];
    }

    //backtrack
    public static boolean isInterleave1(String s1, String s2, String s3) {
        int l1 = s1.length();
        int l2 = s2.length();
        int l = s3.length();
        if (l1 + l2 != l) return false;
        if (l1 == 0 && l2 == 0) return l == 0;
        if (l1 == 0) return s2.equals(s3);
        if (l2 == 0) return s1.equals(s3);
        int p1 = 0, p2 = 0, p = 0;
        /*动态规划指针*/
        return solve(p1, p2, p, s1, s2, s3, l1, l2, l);
    }

    private static boolean solve(int p1, int p2, int p, String s1, String s2, String s3, int l1, int l2, int l) {
        for (;p<l;){
            char ch=s3.charAt(p);
            char ch1=p1==l1?' ':s1.charAt(p1);
            char ch2=p2==l2?' ':s2.charAt(p2);
            if (ch==ch1&&ch==ch2){
                if (p1>p2){
                    if (solve(p1, p2+1, p+1, s1, s2, s3, l1, l2, l))return true;
                    return solve(p1+1, p2, p+1, s1, s2, s3, l1, l2, l);
                }else {
                    if (solve(p1+1, p2, p+1, s1, s2, s3, l1, l2, l))return true;
                    return solve(p1, p2+1, p+1, s1, s2, s3, l1, l2, l);
                }
            }else if (ch==ch1){
                p1++;p++;
            }else if (ch==ch2){
                p2++;p++;
            }else return false;
        }
        return true;
    }
}
