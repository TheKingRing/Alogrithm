package heapUitily;

import java.util.Map;
import java.util.TreeMap;

/**
 * Created by TheKingRing on 2016/11/1.
 */
public class WiggleSort {

    public static void main(String[] args) {
        int[] nums={1,1,2,1,2,2,1};
        wiggleSort(nums);
    }

    private static void wiggleSort(int[] nums) {
        int med=findKthLargest(nums,nums.length/2);
        int l=0,m=0,n=nums.length-1;
        while (l<n){
            if (nums[l]<med)swap(nums,l++,m++);
            else if (nums[l]>med)swap(nums,l,n--);
            else l++;
        }
        int mid=nums.length/2+1;
        int[] arr=new int[nums.length];
        for (int i=1;i<mid;i++){
            arr[2*i]=nums[i];
        }

        for (int i=1;i<nums.length;i++){
            if (i%2!=0)nums[i]=nums[mid++];
            else nums[i]=arr[i];
        }
        Map<Integer,Integer> map=new TreeMap<>();
        System.out.println();

    }

    public  static int findKthLargest(int[] nums, int k) {
         return quickchoose(nums,0,0,nums.length-1,nums.length-k);
    }

    private static int quickchoose(int[] a, int mid, int left, int right, int tag) {
        int high=right,low=left;
        meadiaan(a,low,(low+high)/2,high);
        while (low<high){
            while (high>left){
                if (a[high]<a[mid])break;
                high--;
            }
            while (low<high){
                if (a[low]>a[mid])break;
                low++;
            }
            if (low<=high){
                swap(a,low,high);
            }
        }
        if (low==high)swap(a,low,mid);
        if (mid==tag)return a[mid];
        else if(low>tag) return quickchoose(a, left, left, low-1,tag);
        else return quickchoose(a,low+1,low+1,right,tag);
    }

    private static void meadiaan(int[] a, int low, int i, int high) {
        if (a[low]>a[high]){
            if (a[i]<a[low]&&a[i]>a[high]){
                swap(a,low,high);swap(a,i,low);
            }else if (a[i]>a[low]){
                swap(a,i,high);
            }else swap(a,low,high);
        }else {
            if (a[i]>a[low]&&a[i]<a[high])swap(a,low,i);
            else if (a[i]>a[high]){
                swap(a,i,high);swap(a,low,i);
            }
        }
    }

    private static void swap(int[] a, int i, int i1) {
        if (i==i1)return;
        a[i]^=a[i1];
        a[i1]^=a[i];
        a[i]^=a[i1];
    }

}
