package heapUitily;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by TheKingRing on 2016/11/1.
 */
public class TopK {
    public static void main(String[] args) {
        int[] a={3,0,1,0};
        System.out.println(topKFrequent(a,1));
    }
    public static List<Integer> topKFrequent(int[] nums, int k) {
        Map<Integer,Integer> map=new HashMap<>();
        List<Integer> l=new ArrayList<>();
        for(int i:nums){
            if(map.containsKey(i)){
                map.put(i,map.get(i)+1);
            }else map.put(i,1);
        }
        int[] num=new int[map.size()];
        int c=0;
        for(int i:map.values()){
            num[c++]=i;
        }
        int frquece=findKthLargest(num,k);
        for(int i:map.keySet()){
            int values=map.get(i);
            if(values>=frquece)l.add(i);
        }
        return l;
    }

    public static int findKthLargest(int[] nums, int k) {
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
        if(low==tag)return a[low];
        else if(low>tag)return quickchoose(a, left, left, low-1,tag);
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
