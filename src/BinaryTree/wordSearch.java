package BinaryTree;

/**
 * Created by TheKingRing on 2016/10/13.
 */
public class  wordSearch {
    public static boolean exist(char[][] board, String word) {
        if (word.length()>board.length*board[0].length) return false;
        char[] ch=word.toCharArray();
        boolean[][] tag=new boolean[board.length][board[0].length];
        for (int i=0;i<board.length;i++){
            for (int j=0;j<board[0].length;j++){
                if (board[i][j]==word.charAt(0)){
                    tag[i][j]=true;
                    if (DFS(board,i,j,ch,1,tag))return true;
                    else tag[i][j]=false;
                }
            }
        }
        return false;
    }
//DFS搜索
    private static boolean DFS(char[][] board, int i, int j,char[] ch, int position, boolean[][] tag) {
        if (position==ch.length) {
            return true;
        }
        //向左移动
        if (i>0&&!tag[i-1][j]&&board[i-1][j]==ch[position]){
            tag[i-1][j]=true;
            if (DFS(board,i-1,j,ch,position+1,tag)) return true;
            else tag[i-1][j]=false;
        }
        //向上移动
        if (j>0&&!tag[i][j-1]&&board[i][j-1]==ch[position]){
            tag[i][j-1]=true;
            if (DFS(board,i,j-1,ch,position+1,tag)) return true;
            else tag[i][j-1]=false;
        }
        //向右移动
        if (i<board.length-1&&!tag[i+1][j]&&board[i+1][j]==ch[position]){
            tag[i+1][j]=true;
            if (DFS(board,i+1,j,ch,position+1,tag)) return true;
            else tag[i+1][j]=false;
        }
        //向下移动
        if (j<board[0].length-1&&!tag[i][j+1]&&board[i][j+1]==ch[position]){
            tag[i][j+1]=true;
            if (DFS(board,i,j+1,ch,position+1,tag)) return true;
            else tag[i][j+1]=false;//不符合则回溯；将其职位没有访问过
        }

        return false;
    }
}
