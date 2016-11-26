package Backtracking;

/**
 * Created by TheKingRing on 2016/10/17.
 */
public class BackTracking3 {
    public static void main(String[] args) {
        System.out.println(isMatch("aab", "*aa*b"));
    }
    public static boolean isMatch(String s, String p) {
        char[] fund = p.toCharArray();
        char[] tag = s.toCharArray();
        int tp = 0, fp = 0;
        if (tag.length==0){
           while (fp<fund.length){
               if (fund[fp++]!='*')return false;
           }
           return true;
        }
        boolean[] result=new boolean[1];
        while (fp < fund.length) {
            if (fund[fp] == tag[0] || fund[fp] == '?' || fund[fp] == '*') break;
            fp++;
        }
       if (fp!=0)return false;
        backtrack(tag,fund,tp,fp,result);
        return result[0];
    }

    private static void backtrack(char[] tag, char[] fund, int tp, int fp,boolean[] result) {
        if (tp==tag.length) {
            result[0]=true;
            int i=fp;
            while (i<fund.length){
                if (fund[i++]!='*'){
                    result[0]=false;
                    break;
                }
            }
            return;
        }else if (fp==fund.length) return;
        if (fund[fp]=='*'){
            for (int i=0;i<=tag.length-tp;i++){
                backtrack(tag,fund,tp+i,fp+1,result);
                if (result[0])break;
            }
        }else if (fund[fp]=='?'||fund[fp]==tag[tp]) {
            backtrack(tag, fund, tp+1, fp+1,result);
        }
    }
}
