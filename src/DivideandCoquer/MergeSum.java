package DivideandCoquer;

/**
 * Created by TheKingRing on 2016/10/30.
 */
public class MergeSum {

    public static void main(String[] args) {


        int[] arr={1,2,1,3,4,5};
        int[] a={1,2,1,3,2,3,1,2,4};
        for (int i=0;i<a.length-1;i++){
            a[i]&=a[i+1];
        }
        for (int k:a){
            System.out.print(k+"\t");
        }
    }
    static int res=0;
    private static int mergeSum(int[] arr){
       return divde(arr,0,1,arr.length-1,arr.length-2);
    }

    private static int divde(int[] arr, int left, int l1, int right, int r1) {
        if (l1>=r1)return res;
        return 0;
    }

}
