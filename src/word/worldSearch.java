package word;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TheKingRing on 2016/10/26.
 */
public class worldSearch {
    public static List<String> findWords(char[][] board, String[] words) {
        Trie trie=new Trie();
        List<String> result=new ArrayList<>();
        boolean[][] tag=new boolean[board.length][board[0].length];
        for (String s:words){
            trie.insert(s);
        }
        for (int i=0;i<board.length;i++){
            for (int j=0;j<board[0].length;j++){
                DFS(board,tag,i,j,trie.root,result);
            }
        }
        return result;
    }

    private static void DFS(char[][] board, boolean[][] tag, int i, int j, TrieNode root, List<String> result) {
        int ch=board[i][j]-'a';
        if (tag[i][j]||root.child[ch]==null)return;
        root=root.child[ch];
        tag[i][j]=true;
        if (root.word!=null){
            result.add(root.word);
            root.word=null;
        }
        if (i>0)DFS(board,tag,i-1,j,root,result);//up
        if (i<board.length-1)DFS(board,tag,i+1,j,root,result);//down
        if (j>0)DFS(board,tag,i,j-1,root,result);//left
        if (i<board[0].length-1)DFS(board,tag,i,j+1,root,result);//right
        tag[i][j]=false;
    }


    private static class TrieNode {
        TrieNode[] child;
        String word=null;
        TrieNode() {
            child=new TrieNode[26];
        }
    }
    private static class Trie {
        private TrieNode root;
        Trie() {
            root = new TrieNode();
        }

        // Inserts a word into the trie.
        void insert(String word) {
            TrieNode p=root;
            for(char ch:word.toCharArray()){
                if(p.child[ch-'a']==null) p.child[ch-'a']=new TrieNode();
                p=p.child[ch-'a'];
            }
            p.word=word;
        }
    }
}



