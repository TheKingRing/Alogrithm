package DivideandCoquer;

/**
 * Created by TheKingRing on 2016/10/30.
 */
public class MergeSort {
    public static void main(String[] args) {
        int[] arr={1,2,1,3,4,5};
        mergesort(arr);
        for (int k:arr){
            System.out.print(k+"\t");
        }

    }

    private static void mergesort(int[] arr){
        divde(arr,0,arr.length-1);
    }

    private static void divde(int[] arr, int left, int right) {
        if(left>=right)return;
        int mid=(left+right)/2;
        divde(arr,left,mid);
        divde(arr,mid+1,right);
        sort(arr,left,mid,right);
    }

    private static void sort(int[] arr, int left,int mid, int right) {
        int[] tmp=new int[arr.length];
        int index=left,tmpr=mid+1;
        int bg=left;
        while (left<=mid&&tmpr<=right){
            if (arr[left]<arr[right])tmp[index++]=arr[left++];
            else tmp[index++]=arr[tmpr++];
        }
        while (left<=mid){
            tmp[index++]=arr[left++];
        }
        while (tmpr<=right){
            tmp[index++]=arr[tmpr++];
        }
        System.arraycopy(tmp,bg,arr,bg,right-bg);
    }
}
