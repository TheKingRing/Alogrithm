package BinaryTree.BinarySearch;

/**
 * Created by TheKingRing on 2016/9/23.
 */
public class sqrt {
    public static void main(String[] args) {
        System.out.println(mySqrt(3));

    }
    public static int mySqrt(int x) {
        if(x==1||x==0){
            return x;
        }
        int right=x/2>Math.pow(2,16)? (int)Math.pow(2,16):x;
        return findInteger(0,right,(double)x);
    }

    private static int findInteger(int l,int r,double target){
        int mid=(l+r)/2;
        if(mid==l||mid==r){
            return l;
        }
        double tmp=  Math.pow(mid,2);
        if(tmp==target)return mid;
        else if(tmp>target) return findInteger(l,mid,target);
        else  return findInteger(mid,r,target);
    }
}
