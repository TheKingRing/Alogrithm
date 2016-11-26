package Dynamic;

import java.util.Hashtable;
import java.util.Iterator;

/**
 * Created by TheKingRing on 2016/10/24.
 */
public class Dynamic3 {
    public static void main(String[] args) {
        String[] str={"1111","0111","0011","0001"};
        char[][] chars=new char[str.length][str[0].length()];
        int count=0;
        for (String s:str){
            chars[count++]=s.toCharArray();
        }
        System.out.println(maximalRectangle(chars));
    }
    private final static int self=0;
    private static int maximalRectangle(char[][] matrix) {
        int m=matrix.length;int n=matrix[0].length;
        Hashtable<Integer,Point>[][] dp=new Hashtable[m][n];
        for (int i=0;i<m;i++){
            for (int j=0;j<n;j++){
                if (matrix[i][j]=='0'){
                    Hashtable<Integer,Point> t=new Hashtable<>();
                    t.put(self,new Point(i,j));
                    dp[i][j]=t;
                }else {
                    if (i==0&&j==0){
                        Hashtable<Integer,Point> t=new Hashtable<>();
                        t.put(self,new Point(i,j));
                        dp[i][j]=t;
                    }else if (i==0){
                        if (matrix[i][j-1]=='0'){
                            Hashtable<Integer,Point> t=new Hashtable<>();
                            t.put(self,new Point(i,j));
                            dp[i][j]=t;
                        }else {
                            dp[i][j]=dp[i][j-1];
                        }
                    }else if (j==0){
                        if (matrix[i-1][j]=='0'){
                            Hashtable<Integer,Point> t=new Hashtable<>();
                            t.put(self,new Point(i,j));
                            dp[i][j]=t;
                        }else {
                            dp[i][j]=dp[i-1][j];
                        }
                    }else{
                        if (matrix[i-1][j]=='0'&&matrix[i][j-1]=='0'){
                            Hashtable<Integer,Point> t=new Hashtable<>();
                            t.put(self,new Point(i,j));
                            dp[i][j]=t;
                        }else if (matrix[i-1][j]=='0'){
                            Hashtable<Integer,Point> t=new Hashtable<>();
                            Iterator<Point> it=dp[i][j-1].values().iterator();
                            int y=n;
                            while (it.hasNext()){
                                Point p=it.next();
                                if (p.x==i){
                                    y=Math.min(y,p.y);
                                }
                            }
                            t.put(self,new Point(i,y==n?j-1:y));
                            dp[i][j]=t;
                        }else if (matrix[i][j-1]=='0'){
                            Hashtable<Integer,Point> t=new Hashtable<>();
                            Iterator<Point> it=dp[i-1][j].values().iterator();
                            int x=m;
                            while (it.hasNext()){
                                Point p=it.next();
                                if (p.y==j){
                                    x=Math.min(x,p.x);
                                }
                            }
                            t.put(self,new Point(x==m?i-1:x,j));
                            dp[i][j]=t;
                        }else dp[i][j]=finddmax(dp,i,j);
                    }
                }
            }
        }
        int max=0;
        for (int i=0;i<m;i++){
            for (int j=0;j<n;j++){
                Point p=dp[i][j].values().iterator().next();
                max=Math.max(max,(i-p.x+1)*(j-p.y+1)*(matrix[i][j]-48));
            }
        }
        return max;

    }
    //找到与该点构成的最大矩形点的坐标，若有多个则存储一遍后面动态规划使用
    private static Hashtable<Integer, Point> finddmax( Hashtable[][] dp, int i, int j) {
        Hashtable<Integer,Point> table=new Hashtable<>();
        if (dp[i-1][j].containsKey(self)&&dp[i][j-1].containsKey(self)){
            Point up= (Point) dp[i-1][j].get(self);
            Point left= (Point) dp[i][j-1].get(self);
            int l=left.x<=up.x? (i-up.x+1)*(j-left.y+1):(i-left.x+1)*(j-left.y+1);
            int u=up.y<=left.y? (j-left.y+1)*(i-up.x+1):(j-up.y+1)*(i-up.x+1);
            if (l>u){
                table.put(self,new Point(left.x<=up.x?up.x:left.x,left.y));
            }else if (l<u){
                table.put(self,new Point(up.x,up.y<=left.y? left.y:up.y));
            }else {
                table.put(-1,new Point(left.x<=up.x?up.x:left.x,left.y));
                table.put(1,new Point(up.x,up.y<=left.y? left.y:up.y));
            }
        }else if (!dp[i-1][j].containsKey(self)&&!dp[i][j-1].containsKey(self)){
            int tag1=1,tag2=-1;
           for (Object k:dp[i-1][j-1].keySet()){
               int key= new Integer(String.valueOf(k));
               Point p= (Point) dp[i][j-1].get(k);
               if (key<0){
                   table.put(tag2--,p);
               }else if (key>0){
                   table.put(tag1++,p);
               }else table.put(self,p);
           }
        }else if (!dp[i][j-1].containsKey(self)){
            int tag=1,tag1=-1;
            Point point= (Point) dp[i-1][j].get(self);
            int llf=0;int lup=point.x;
            int maxleft = 0;
            for (Object k:dp[i][j-1].keySet()){
                int key= new Integer(String.valueOf(k));
                Point point1= (Point) dp[i][j-1].get(k);
                if (key<0){
                    llf=Math.max( point1.y,llf);
                }
                if (table.size()==0)table.put(tag++,point1);
                else {
                    Point p=table.get(tag-1);
                    int u1=point1.x<=lup?(i-lup+1)*(j-point1.y+1):(i-point1.x+1)*(j-point1.y+1);int u2=p.x<=lup?(i-lup+1)*(j-p.y+1):(i-p.x+1)*(j-p.y+1);
                    maxleft=Math.max(u1,u2);
                    if (u1>u2){
                        Point pt=new Point(point1.x<=lup?lup:point1.x,point1.y);
                        table.put(tag-1,pt);
                    }else if (u1==u2) {
                        Point pt=new Point(point1.x<=lup?lup:point1.x,point1.y);
                        Point p1=new Point(p.x<=lup?lup:p.x,p.y);
                        table.put(tag-1,p1);
                        table.put(tag++,pt);
                    }
                }
            }
            if ((point.y<=llf?(j-llf+1)*(i-point.x+1):(i-point.x+1)*(j-point.y+1))>maxleft){
                Point point1=new Point(point.x,point.y<=llf?llf:point.y);
                table.clear();table.put(tag1,point1);
            }else if ((point.y<=llf?(j-llf+1)*(i-point.x+1):(i-point.x+1)*(j-point.y+1))==maxleft){
                Point point1=new Point(point.x,point.y<=llf?llf:point.y);
                table.put(tag1,point1);
            }
        }else {
            int tag=-1;int tag1=1;
            Point point= (Point) dp[i][j-1].get(self);
            int lup=0;int llf=point.y;
            int maxup = 0;
            for (Object k:dp[i-1][j].keySet()){
                int key= new Integer(String.valueOf(k));
                Point point1= (Point) dp[i-1][j].get(k);
                if (key>0){
                    lup=Math.max( point1.x,lup);
                }
                if (table.size()==0)table.put(tag--,point1);
                else {
                    Point p=table.get(tag+1);
                    int u1=point1.y<=llf?(j-llf+1)*(i-point1.x+1):(j-point1.y+1)*(i-point1.x+1);int u2=p.y<=llf?(j-llf+1)*(i-p.x+1):(j-p.y+1)*(i-p.x+1);
                    maxup=Math.max(u1,u2);
                    if (u1>u2){
                        Point pt=new Point(point1.x,point1.y<=llf?llf:point1.y);
                        table.put(tag+1,pt);
                    }else if (u1==u2) {
                        Point pt=new Point(point1.x,point1.y<=llf?llf:point1.y);
                        Point p1=new Point(p.x,p.y<=llf?llf:p.y);
                        table.put(tag+1,p1);
                        table.put(tag--,pt);
                    }
                }
            }
            if ((point.x<=lup?(i-lup+1)*(j-llf+1):(i-point.x+1)*(j-llf+1))>maxup){
                table.clear();table.put(tag1,point);
            }else if ((point.x<=lup?(i-lup+1)*(j-llf+1):(i-point.x+1)*(j-llf+1))==maxup){
                table.put(tag1,point);
            }
        }
        return table;
    }

    private static class Point{
        int x;int y;
        Point(int x,int y){
            this.x=x;
            this.y=y;
        }
    }
}
