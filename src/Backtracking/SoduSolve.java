package Backtracking;

/**
 * Created by TheKingRing on 2016/11/23.
 */
public class SoduSolve {
    public void solveSudoku(char[][] board) {
        boolean[][] row=new boolean[9][9];
        boolean[][] column=new boolean[9][9];
        boolean[][] cube=new boolean[9][9];
        initialData(board,row,column,cube);
        backtrace(board,row,column,cube,0,0);
    }
    private boolean backtrace(char[][] board, boolean[][] row, boolean[][] column, boolean[][] cube, int i,int j) {
        while (i<9){
            if (board[i][j]=='.')break;
            if (j==8){i++;j=0;}
            else j++;
        }
        if (i==9)return true;
        for (int num=0;num<9;num++){
            if (row[i][num]||column[j][num]||cube[(i/3)*3+j/3][num])continue;
            board[i][j]=(char)(num+49);
            row[i][num]=true;
            column[j][num]=true;
            cube[(i/3)*3+j/3][num]=true;
            if (backtrace(board, row, column, cube, i, j))return true;
            //backtrack point
            row[i][num]=false;
            column[j][num]=false;
            cube[(i/3)*3+j/3][num]=false;
            board[i][j]='.';
        }
        return false;
    }
   /**
    * initial the data that has been used in sodu;the label transfer from i,j to cube
    * is Number=(i/3)*3+(j/3).so we can find out each column , row,cube the element whether
    * it is used in former
    * */
    private void initialData(char[][] board, boolean[][] row, boolean[][] column, boolean[][] cube) {
        for(int i=0;i<9;i++){
            for(int j=0;j<9;j++){
                if(board[i][j]=='.')continue;
                int loc=board[i][j]-'1';
                row[i][loc]=true;
                column[j][loc]=true;
                cube[(i/3)*3+j/3][loc]=true;
            }
        }
    }

}
