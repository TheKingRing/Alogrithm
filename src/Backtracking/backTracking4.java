package Backtracking;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by TheKingRing on 2016/10/18.
 */
public class backTracking4 {
    public static void main(String[] args) {
        String[] str={"..9748...","7........",".2.1.9...","..7...24.",".64.1.59.",".98...3..","...8.3.2.","........6","...2759.."};
        char[][] board=new char[9][9];
        int i=0;
        for (String s:str){
            board[i++]=s.toCharArray();
        }
        solveSudoku(board);

    }

    public static void solveSudoku(char[][] board) {
        HashMap<Integer,Set<Character>> num=new HashMap<>();
        int count=0;
        boolean[][] mark=new boolean[9][9];
        for (int i=0;i<9;i++){
            for (int j=0;j<9;j++){
                if (board[i][j]=='.'){
                    count++;
                    int tag=i*8+j;
                    num.put(tag,generate(board,i,j));
                }
            }
        }

        backtrack(num,board,count,mark);
    }

    private static void backtrack(HashMap<Integer, Set<Character>> num, char[][] board, int count,boolean[][] mark) {
        if (count==0){
            return;
        }
        for (int tag:num.keySet()){
            int i=tag/9;int j=tag-i*9;
            if (!mark[i][j]){
                mark[i][j]=true;
                for (char ch:num.get(tag)){
                    board[i][j]=ch;
                    if (!delete(board,num,i,j,ch))return;
                    backtrack(num,board,count-1,mark);
                    mark[i][j]=false;
                    board[i][j]='.';
                    add(board,num,i,j,ch);
                }
            }
        }
    }

    private static void add(char[][] board, HashMap<Integer, Set<Character>> num, int i, int j, char ch) {
        int x=checkbox(i);
        int y=checkbox(j);
        for (int m=0;m<9;m++){
            for (int n=0;n<9;n++){
                if (board[m][n]=='.'&&(m==i||n==j||((y-n)<=3&&(y-n)>0&&(x-m)<=3&&(x-m)>0))){
                    int tag=m*8+n;
                    num.get(tag).add(ch);
                }
            }
        }
    }


    private static boolean delete(char[][] board, HashMap<Integer, Set<Character>> num, int i, int j, char ch) {
        boolean t=true;
        int x=checkbox(i);
        int y=checkbox(j);
        for (int m=0;m<9;m++){
            for (int n=0;n<9;n++){
                if (board[m][n]=='.'&&(m==i||n==j||((y-n)<=3&&(y-n)>0&&(x-m)<=3&&(x-m)>0))){
                    int tag=m*8+n;
                    if (num.get(tag).size()==0){
                        t=false;continue;
                    }
                    if (num.get(tag).contains(ch))num.get(tag).remove(ch);
                }
            }
        }
        return t;
    }

    private static Set<Character> generate(char[][] board, int i, int j) {
        Set<Character> set=new HashSet<>();
        int x=checkbox(i);
        int y=checkbox(j);
        boolean[] mark=new boolean[10];
        for (int m=0;m<9;m++){
            for (int n=0;n<9;n++){
                if (board[m][n]!='.'&&(m==i||n==j||((y-n)<=3&&(y-n)>0&&(x-m)<=3&&(x-m)>0)))mark[board[m][n]-48]=true;
            }
        }
        for (int k=1;k<mark.length;k++){
            if (!mark[k]) set.add((char)(k+48));
        }
        return set;
    }

    private static int checkbox(int i) {
        if (i>=0&&i<=2) return 3;
        else if (i>=3&&i<=5) return 6;
        else if (i>=6&&i<=8) return 9;
        return -1;
    }
}
