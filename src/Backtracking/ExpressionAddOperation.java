package Backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TheKingRing on 2016/11/24.
 */

public class ExpressionAddOperation {
    public static void main(String[] args) {
        System.out.println(addOperators("123456789" ,45));
    }

    public static List<String> addOperators(String num, int target) {
        if ("".equals(num))return new ArrayList<>();
        List<String> l=new ArrayList<>();
        int len=num.length();
        StringBuilder builder;
        List<Character> sign=new ArrayList<>();
        sign.add('*');sign.add('+');sign.add('-');
        for(int i=1;i<=len;i++){
            builder=new StringBuilder();
            String st=num.substring(0,i);
            long tmp=Long.parseLong(st);
            if (tmp>Integer.MAX_VALUE)break;
            builder.append(st);
            backtracking(num,l,sign,builder,i,i,target,len,(int)tmp);
            if (st.equals("0"))break;
        }

        return l;
    }

    private static boolean backtracking(String num, List<String> l, List<Character> sign, StringBuilder builder, int p, int len, int tar, int length,int result) {
        if (p==length){
            if (result==tar){
                l.add(builder.toString());return true;
            }else return false;

        }
        for (int i=p+1;i<=length;i++){
            if (i-p>=2&&num.charAt(p)=='0')break;
            String str=num.substring(p,i);
            for (char s:sign){
                builder.append(s);
                builder.append(str);
                result=calculate(builder,len+i-p+1);
                if (backtracking(num, l, sign, builder, p+i-p, len+i-p+1, tar,length,result)){
                    builder.delete(len,len+i-p+1);
                    break;
                }
                builder.delete(len,len+i-p+1);
            }
        }

        return false;
    }
    //calculate the result of three number
    private static int calculate(StringBuilder sb, int len) {
        if (len==0) return 0;
        int res = 0;
        long preVal = 0; // initial preVal is 0
        char sign = '+'; // initial sign is +
        int i = 0;
        while (i < len) {
            if (sb.charAt(i)==' '){i++;continue;}
            long curVal = 0;
            int num=sb.charAt(i)-'0';
            while (num>=0&&num<=9) {// int
                curVal = curVal*10 + num;
                i++;if (i==len)break;
                num=sb.charAt(i)-'0';
            }
            if (sign == '+') {
                res += preVal;  // update res
                preVal = curVal;
            } else if (sign == '-') {
                res += preVal;  // update res
                preVal = -curVal;
            } else if (sign == '*') {
                preVal = preVal * curVal; // not update res, combine preVal & curVal and keep loop
            } else if (sign == '/') {
                preVal = preVal / curVal; // not update res, combine preVal & curVal and keep loop
            }
            while (i<len){
                if (sb.charAt(i)==' ')i++;
                else break;
            }
            if (i < len) {// getting new sign
                sign = sb.charAt(i);
                i++;
            }
        }
        res += preVal;
        return res;
    }
}
