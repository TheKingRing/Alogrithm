package Stack;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Created by TheKingRing on 2016/11/20.
 */
public class RemoveDumplicate {

    public static void main(String[] args) {
        String str="bbcaac";
        System.out.println(removeDuplicateLetters(str));
    }
    /**
     * 关键：将题目中的the smallest in lexicographical order among all possible results转化为
     *      一个数值的比较。
     * */
    public static String removeDuplicateLetters(String s) {
        char[] data = s.toCharArray();
        int[] count = new int[26];
        boolean[] tag=new boolean[26];//record the word whether be used
        //is que not stack because we can get the sequence without reserve;
        Deque<Character> st=new LinkedList<>();
        for (char ch : data) {
            int i = ch - 'a';
            count[i]++;//record the character nums
        }
        for (char ch:data){
            int i=ch-'a';
            if (st.isEmpty()){
                st.addLast(ch);count[i]--;tag[i]=true;
            }
            else {
                if (!tag[i]){
                    char top=st.getLast();
                    while (top>ch&&count[top-'a']>0){
                        st.removeLast();tag[top-'a']=false;
                        if (st.isEmpty())break;
                        top=st.getLast();
                    }
                    st.addLast(ch);tag[i]=true;count[i]--;
                }else {
                    count[i]--;
                }

            }
        }
        StringBuilder builder=new StringBuilder();
        while (!st.isEmpty()){
            builder.append(st.pollFirst());
        }
        return builder.toString();
    }
}
