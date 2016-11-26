package Dynamic;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Created by TheKingRing on 2016/11/18.
 */
public class findKSmallest {
    public static void main(String[] args) {
        String st="112";
        System.out.println(removeKdigits(st,2));

    }
    public static String removeKdigits(String num, int k) {
        char[] chs=num.toCharArray();
        int need=chs.length-k;
        int i=0;int len=0;
        Deque<Integer> deque=new LinkedList<>();
        for (;i<chs.length;){
            int tmp=len;
            while (!deque.isEmpty()&&chs[deque.peekLast()]>chs[i]&&chs.length-i>need-len){
                deque.pollLast();len--;
            }
           if (len==tmp){
               if (deque.size()<need){
                   deque.addLast(i);len++;
               }
               i++;
               continue;
           }
            while (len!=tmp){
               deque.addLast(i++);len++;
            }
        }
        StringBuilder sb=new StringBuilder();
        boolean tag=true;
        while (!deque.isEmpty()){
            char ch=chs[deque.pollFirst()];
            if (tag&&ch=='0')continue;
            sb.append(ch);tag=false;
        }
        if (tag)return "0";
        return sb.toString();
    }
}
