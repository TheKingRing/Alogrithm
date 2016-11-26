package word;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * Created by TheKingRing on 2016/11/21.
 */
public class wordLadder {
    /**
     * 两边一起开始找最短路径，BFS，有两个点相遇的时候，输出两个点层数的最小值。
     * */
    public int ladderLength(String start, String end, Set<String> wordList) {
        if (wordList == null) {
            return 0;
        }

        if (start.equals(end)) {
            return 1;
        }

        HashMap<String,Integer> hash1 = new HashMap<>();//记录从起点到终点
        Queue<String> queue1 = new LinkedList<String>();//BFS queue from start to end

        HashMap<String,Integer> hash2 = new HashMap<>();//记录从终点到起点
        Queue<String> queue2 = new LinkedList<String>();//BFS queue from end to start

        queue1.offer(start);
        queue2.offer(end);
        hash1.put(start,1);
        hash2.put(end,1);
        while(!queue1.isEmpty()&&!queue2.isEmpty()) {
            String s1=queue1.poll();
            int layer1=hash1.get(s1);
            //找每个单词在图中的下一个单词
            for (char c = 'a'; c <= 'z'; c++) {
                for (int i = 0; i < s1.length(); i++) {
                    if (c == s1.charAt(i)) {
                        continue;
                    }
                    String nextWord = replace(s1, i, c);
                    //避免环的出现
                    if (hash1.containsKey(nextWord))continue;
                    //如果从终点开始的已经遍历到该单词，则输出两个层数之和
                    if (hash2.containsKey(nextWord))return hash2.get(nextWord)+layer1;
                        //否则看词典里有没有这个单词，有则记录层数，和该单词
                    else if (wordList.contains(nextWord)) {
                        queue1.offer(nextWord);hash1.put(nextWord,layer1+1);
                    }
                }
            }
            String s2=queue2.poll();
            int layer2=hash2.get(s2);
            for (char c = 'a'; c <= 'z'; c++) {
                for (int i = 0; i < s2.length(); i++) {
                    if (c == s2.charAt(i)) {
                        continue;
                    }
                    String nextWord = replace(s2, i, c);
                    //避免环的出现
                    if (hash2.containsKey(nextWord))continue;
                    //如果从起点开始的已经遍历到该单词，则输出两个层数之和
                    if (hash1.containsKey(nextWord))return hash1.get(nextWord)+layer2;
                        //否则看词典里有没有这个单词，有则记录层数，和该单词
                    else if (wordList.contains(nextWord)) {
                        queue2.offer(nextWord);hash2.put(nextWord,layer2+1);
                    }
                }
            }
        }
        return 0;
    }

    // replace character of a string at given index to a given character
    // return a new string
    private String replace(String s, int index, char c) {
        char[] chars = s.toCharArray();
        chars[index] = c;
        return new String(chars);
    }
}
