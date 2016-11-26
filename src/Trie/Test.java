package Trie;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Stack;

/**
 * Created by TheKingRing on 2016/10/25.
 */
public class Test {
    public static void main(String[] args) {
        int[] a={1,2,3,4,7,8,10};

        int b= Arrays.binarySearch(a,1,a.length,5);
        System.out.println(b);


    }
    public int[] singleNumber(int[] nums) {
        PriorityQueue<Integer> queue=new PriorityQueue<>();
        queue.poll();
        // Pass 1 :
        // Get the XOR of the two numbers we need to find
        int diff = 0;
        for (int num : nums) {
            diff ^= num;
        }
        // Get its last set bit
        diff &= -diff;

        // Pass 2 :
        int[] rets = {0, 0}; // this array stores the two numbers we will return
        for (int num : nums)
        {
            if ((num & diff) == 0) // the bit is not set
            {
                rets[0] ^= num;
            }
            else // the bit is set
            {
                rets[1] ^= num;
            }
        }
        return rets;
    }
    public static String reverseWords(String s) {
        String[] strs=s.split(" ");
        Stack<String> st=new Stack<>();
        for(String str:strs){
            if (!"".equals(str))st.push(str);
        }
        StringBuilder builder=new StringBuilder();
        while(true){
            builder.append(st.pop());
            if(st.size()==0)break;
            builder.append(" ");
        }
        return builder.toString();
    }
    /*数组第i位和第j位交换*/
    private void swap(int[] a,int i,int j){
        a[i]^=a[j];
        a[j]^=a[i];
        a[i]^=a[j];
    }
}
