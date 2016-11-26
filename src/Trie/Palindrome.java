package Trie;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TheKingRing on 2016/11/15.
 */
public class Palindrome {
    public static void main(String[] args) {
        Palindrome p=new Palindrome();
        String[] word={"s","ss","sss","ssss"};
        System.out.println(p.palindromePairs(word));
    }

    public List<List<Integer>> palindromePairs(String[] words) {
        List<List<Integer>> res = new ArrayList<>();
        Trie trie = new Trie();
        trie.insert(words[0].toCharArray(), 0);
        for (int i = 1; i < words.length; i++) {
            char[] chs = words[i].toCharArray();
            for (int k = -1; k < chs.length - 1; k++) {
                if (k == -1) {
                    List<Integer> l = trie.containsWord(chs, 0);
                    if (l != null && l.size() != 0) {
                        int po = l.get(0);
                        List<Integer> list = new ArrayList<>();
                        list.add(i);
                        list.add(po);
                        res.add(list);
                        l.add(i);
                        res.add(l);
                    } else if (l != null) {
                        if (isPalindrome(chs, chs.length - 1)) {
                            char[] ch1 = tarverseArray(chs);
                            TrieNode node = trie.insert(ch1, i);
                            List<Integer> list = new ArrayList<>();
                            StringBuilder builder = new StringBuilder();
                            findPalindrome(list, node, builder);
                        }
                    }
                } else {
                    if (isPalindrome(chs, k)) {
                        List<Integer> list = trie.containsWord(chs, k + 1);
                        if (list != null&&list.size()!=0) {
                            list.add(i);
                            res.add(list);
                        }
                    }
                }
            }
            trie.insert(chs,i);
        }
        return res;
    }

    private void findPalindrome(List<Integer> list, TrieNode node, StringBuilder builder) {
        if (node.isLeaf) {
            int length = builder.length();
            char[] chars = new char[length];
            builder.getChars(0, length - 1, chars, 0);
            if (isPalindrome(chars, length - 1)) {
                list.add(node.pos);
            }
            return;
        }
        for (int i = 0; i < 26; i++) {
            if (node.child[i] == null) continue;
            builder.append(i + 'a');
            findPalindrome(list, node.child[i], builder);
            builder.deleteCharAt(builder.length() - 1);
        }
    }

    private char[] tarverseArray(char[] chs) {
        char[] chars = new char[chs.length];
        int i = chs.length - 1;
        int count = 0;
        while (i >= 0) {
            chars[count++] = chs[i--];
        }
        return chars;
    }

    class TrieNode {
        TrieNode[] child;
        boolean isLeaf = false;
        int pos;

        TrieNode() {
            child = new TrieNode[26];
        }
    }

    class Trie {
        TrieNode root;

        Trie() {
            root = new TrieNode();
        }

        private TrieNode insert(char[] chs, int po) {
            TrieNode p = root;
            for (char ch : chs) {
                if (p.child[ch - 'a'] == null) p.child[ch - 'a'] = new TrieNode();
                p = p.child[ch - 'a'];
            }
            if (p.isLeaf) {
                p.isLeaf = false;
                return p;
            }
            p.isLeaf = true;
            p.pos = po;
            return null;
        }

        private List<Integer> containsWord(char[] chs, int end) {
            TrieNode p = root;
            List<Integer> l = new ArrayList<>();
            for (int i = chs.length - 1; i >= end; i--) {
                if (p.child[chs[i] - 'a'] == null) return null;
                p = p.child[chs[i] - 'a'];
            }
            if (p.isLeaf) {
                l.add(p.pos);
                return l;
            }
            p.isLeaf = true;
            return l;
        }
    }


    private boolean isPalindrome(char[] chs, int end) {
        int i = 0;
        while (i <= end) {
            if (chs[i++] != chs[end--]) return false;
        }
        return true;
    }
}
