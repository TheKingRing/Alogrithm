package heapUitily;

/**
 * Created by TheKingRing on 2016/10/31.
 */
public class findkthMax {
    public static void main(String[] args) {
        int[] a={3,2,3,1,2,4,5,5,6};
        System.out.println(findKthLargest(a,5));
    }
    /*找出第k大，且k是不重复的*/
    public static int findKthLargest(int[] nums, int k) {
        int result=Integer.MIN_VALUE;
        int l=nums.length;int m=0;
        buildHeap(nums);
            for(int i=nums.length-1;i>=0;i--){
                m++;
                if (m==k){
                    result=nums[0];
                    break;
                }
                swap(nums,0,i);
                l--;
                maxHepyfy(l,i,nums);
            }
        return result;
    }

    private static void buildHeap(int[] nums){
        for(int k=nums.length/2-1;k>=0;k--){
            maxHepyfy(nums,k);
        }
    }
    private static void maxHepyfy(int i, int k, int[] nums){
        int left=2*k+1;
        int right=2*k+2;
        int max=k;
        if(left<i&&nums[left]>nums[max]){
            max=left;
        }
        if(right<i&&nums[right]>nums[max]){
            max=right;
        }
        if(max!=k){
            swap(nums,max,k);
            maxHepyfy(i,max,nums);
        }
    }
    public static void maxHepyfy(int[] nums, int k){
        int left=2*k+1;
        int right=2*k+2;
        int max=k;
        if(left<nums.length&&nums[left]>nums[max]){
            max=left;
        }
        if(right<nums.length&&nums[right]>nums[max]){
            max=right;
        }
        if(max!=k){
            swap(nums,max,k);
            maxHepyfy(nums,max);
        }

    }

    private static void swap(int[] nums, int i, int j){
        if(nums[i]!=nums[j]);
        nums[i]^=nums[j];
        nums[j]^=nums[i];
        nums[i]^=nums[j];
    }
}
