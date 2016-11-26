package Backtracking;

import java.util.*;

/**
 * Created by TheKingRing on 2016/10/16.
 */
public class BackTracking1 {
    public static void main(String[] args) {
        System.out.println(letterCombinations(""));
    }
    public static List<String> letterCombinations(String digits) {
        if (digits.length()==0)return new ArrayList<>();
        char[] dic={'*','a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
        char[] module={'2','3','4','5','6','7','8','9'};
        char[] tar=digits.toCharArray();
        Map<Character,Integer> begain=new HashMap<>();Map<Character,Integer> end=new HashMap<>();
        for (char ch:module){
            begain.put(ch,generatebegain(ch));
            end.put(ch,generateend(ch));
        }
        List<String> result=new ArrayList<>();
        StringBuilder builder=new StringBuilder();
        getThecombination(dic,tar,result,builder,0,begain,end);
        return result;
    }

    private static void getThecombination(char[] dic, char[] tar, List<String> result, StringBuilder builder,int postition,Map<Character,Integer> begain,Map<Character,Integer> end) {
        if (postition==tar.length) {
            result.add(builder.toString());
            return;
        }
        for (int i=begain.get(tar[postition]);i<end.get(tar[postition]);i++){
            builder.append(dic[i]);
            getThecombination(dic,tar,result,builder,postition+1,begain,end);
            builder.deleteCharAt(builder.length()-1);
        }
    }

    private static int generateend(char num) {
        int point=num-48;
        if (point>=2&&point<=6){
            return 3*point-2;
        }else if (point!=9)return 3*point-1;
        else return 3*point;
    }

    private static int generatebegain(char num) {
        int point=num-48;
        if (point>=2&&point<=7){
            return 3*point-5;
        }else return 3*point-4;
    }
}
