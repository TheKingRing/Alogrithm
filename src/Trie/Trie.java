package Trie;

/**
 * Created by TheKingRing on 2016/10/25.
 */
public class Trie {
    public TrieNode root;
    public Trie() {
        root = new TrieNode();
    }

    // Inserts a word into the trie.
    public void insert(String word) {
        TrieNode p=root;
        for(char ch:word.toCharArray()){
            if(p.next[ch-'a']==null) p.next[ch-'a']=new TrieNode();
            p=p.next[ch-'a'];
        }
        p.word=word;
    }

    // Returns if the word is in the trie.
    public boolean search(String word) {
        TrieNode p=root;
        for(char ch:word.toCharArray()){
            if(p.next[ch-'a']==null)return false;
            p=p.next[ch-'a'];
        }

        return p.word!=null;
    }

    // Returns if there is any word in the trie
    // that starts with the given prefix.
    public boolean startsWith(String prefix) {
        TrieNode p=root;
        for(char ch:prefix.toCharArray()){
            if(p.next[ch-'a']==null)return false;
            p=p.next[ch-'a'];
        }
        return true;
    }
}
