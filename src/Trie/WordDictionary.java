package Trie;

/**
 * Created by TheKingRing on 2016/11/22.
 */
public class WordDictionary {
    private TrieNode root;
    WordDictionary(){
        root=new TrieNode();//use the structure trie
    }
    // Adds a word into the data structure.
    public void addWord(String word) {
        TrieNode p=root;
        char[] chs=word.toCharArray();
        for(char ch:chs){
            int k=ch-'a';
            if(p.next[k]==null)p.next[k]=new TrieNode();
            p=p.next[k];
        }
        p.word=word;
    }

    // Returns if the word is in the data structure. A word could
    // contain the dot character '.' to represent any one letter.
    public boolean search(String word) {
        int len=word.length();
        return dfs(word,root,0,len);//with the backtracking algorithm
    }
    //when meet '.' we  traverse the next node
    private boolean dfs(String word,TrieNode root,int p,int len){
        if(p==len){
            return root.word != null;
        }
        char ch=word.charAt(p);
        if(ch=='.'){
            for(TrieNode node:root.next){
                if(node!=null&&dfs(word,node,p+1,len))return true;
            }
        }else{
            if(root.next[ch-'a']!=null&&dfs(word,root.next[ch-'a'],p+1,len))return true;
        }
        return false;
    }
}
