package Dynamic;

/**
 * Created by TheKingRing on 2016/11/5.
 */
public class DistinctSubsequence {


    public static void main(String[] args) {
        System.out.println(numDistinct("aabb","abb"));
    }
    public static int numDistinct(String s, String t) {
        char[] sh=s.toCharArray();char[] ch=t.toCharArray();
        if (ch.length>sh.length)return 0;
        int[]dp =new int[sh.length];
        int pos=0;//寻找第一个相等点。
        for(int i=0;i<ch.length;i++){
            while(pos<dp.length&&sh[pos]!=ch[i]) pos++;
            if (pos==dp.length)return 0;int j=pos;
            if(i==0){
                dp[j]=1;j+=1;
                while(j<dp.length){
                    dp[j]=ch[i]==sh[j]?dp[j-1]+1:dp[j-1];j++;
                }
            }else {
                int pre=0;
                while(j<dp.length){
                    int tmp=dp[j];//压缩时经常会出现前一个变量被更改的情况，所以先记录再一个tmp中
                    dp[j]=ch[i]==sh[j]?dp[j-1]+pre:dp[j-1];//加上pre;
                    pre=tmp;j++;//再把tmp给pre
                }
            }
            pos++;
        }
        return dp[dp.length-1];
    }
}
