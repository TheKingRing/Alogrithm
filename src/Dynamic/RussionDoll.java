package Dynamic;

import java.util.*;

/**
 * Created by TheKingRing on 2016/11/11.
 */
public class RussionDoll {
    public static void main(String[] args) {
        int[][] a={{1,15},{7,18},{7,6},{7,100},{2,200},{17,30},{17,45},{3,5},{7,8},{3,6},{3,10},{7,20},{17,3},{17,45}};

        Arrays.sort(a, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0]==o2[0])
                return o2[1]-o1[1];
                else return o1[0]-o2[0];
            }
        });
        for (int[] x:a){
            System.out.print(x[0]+"\t"+x[1]);
            System.out.println();
        }
        System.out.println();
    }

    public static int maxEnvelopes(int[][] envelopes) {
       Arrays.sort(envelopes, new Comparator<int[]>() {
           @Override
           public int compare(int[] o1, int[] o2) {
               if(o1[0] == o2[0])
                   return o2[1] - o1[1];
               else
                   return o1[0] - o2[0];
           }
       });

        return lengthOfLIS(envelopes);
    }

    public static int lengthOfLIS(int[][] b) {
        int[] dp=new int[b.length];
        int len=0;
        for (int[] x:b){
            int i=Arrays.binarySearch(dp,0,len,x[1]);
            if (i<0)i=-(i+1);
            dp[i]=x[1];
            if (i==len)len++;
        }
        return len;
    }
/*快拍*/
    private static void quickSort(int[] a, int[] b, int left, int mid, int right){
        if (left>right)return;
        int l=left,r=right;
        median(a,b,left,right);
        while (l<r){
            while (r>left){
                if (a[r]<a[mid])break;
                r--;
            }
            while (l<r){
                if (a[l]>a[mid])break;
                l++;
            }
            if (r>=l){swap(a,l,r);swap(b,l,r);}
        }
        if (l==r){
            swap(a,l,mid);
            swap(b,l,mid);
        }
        quickSort(a,b,left,left,l-1);
        quickSort(a,b,l+1,l+1,right);
    }

    private static void median(int[] a, int[] b, int l, int r){
        int m=(l+r)/2;
        if(a[l]<a[r]){
            if(a[l]<a[m]&&a[m]<a[r]){
                swap(a,l,m);swap(b,l,m);
            }else if(a[m]>a[l]){
                swap(a,l,r);swap(b,l,r);
            }
        }else {
            if (a[m]>a[r]&&a[m]<a[l]){
                swap(a,m,l);swap(a,r,l);
                swap(b,m,l);swap(b,r,l);
            }else if (a[m]>a[l]){
                swap(a,m,r);
                swap(b,m,r);
            }
        }
    }
    private static void swap(int[] a, int l, int r){
        if(a[l]==a[r])return;
        a[l]^=a[r];a[r]^=a[l];a[l]^=a[r];
    }
}
