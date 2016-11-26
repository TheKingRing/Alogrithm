package Backtracking;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by TheKingRing on 2016/10/16.
 */
public class BackTracking2 {
    public static void main(String[] args) {
        System.out.println(generateParenthesis(3));
    }
    public static List<String> generateParenthesis1(int n) {
        List<String> r=new ArrayList<>();
        StringBuilder builder=new StringBuilder();
        Set<Character> set=new HashSet<>();
        set.add('(');set.add(')');
        findtheParenthesis(r,set,builder,0,n,n);
        return r;
    }

    private static void findtheParenthesis(List<String> r, Set<Character> s, StringBuilder builder, int i, int left,int right) {
        if (right==0){
            r.add(builder.toString());
            return;
        }
        for (Character c:s){
            if (i!=0){
                if (c=='(') {
                    if (left==0) continue;
                    i++;left--;
                }
                else {
                    i--;right--;
                }
                builder.append(c);
            }else {
                if (c=='('&&left!=0){
                    builder.append(c);i++;left--;
                }else continue;
            }
            findtheParenthesis(r, s, builder, i, left,right);
            int k=builder.length()-1;
            if (builder.charAt(k)=='(') {left++;i--;}
            else{right++;i++;}
            builder.deleteCharAt(builder.length()-1);
        }
    }
    public static List<String> generateParenthesis(int n) {
        List<String> r=new ArrayList<>();
        StringBuilder builder=new StringBuilder();
        backtrack(r,builder,0,0,n);
        return r;
    }

    public static void backtrack(List<String> list, StringBuilder builder, int open, int close, int max){
        if(builder.length() == max*2){
            list.add(builder.toString());
            return;
        }

        if(open < max){
            backtrack(list, builder.append('('), open+1, close, max);
            builder.deleteCharAt(builder.length()-1);
        }
        if(close < open){
            backtrack(list, builder.append(')'), open, close+1, max);
            builder.deleteCharAt(builder.length()-1);
        }
    }
}
