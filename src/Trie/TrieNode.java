package Trie;

/**
 * Created by TheKingRing on 2016/10/25.
 */
public class TrieNode {
    public TrieNode[] next;
    String word=null;
    TrieNode() {
        next=new TrieNode[26];
    }
}
