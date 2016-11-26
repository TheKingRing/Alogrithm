/**
 * Created by TheKingRing on 2016/9/22.
 */
public class CloneTest implements Cloneable {

    public static void main(String[] ars) {
        int[] nums={1,2,3,4,6,7,9};
       rotate(nums,1);
        for (int k:nums
             ) {
            System.out.print(k+"\t");
        }


    }
    public static void rotate(int[] nums, int k) {
        k=k%nums.length;
        if(k==0) return;
        int position=k;
        int[] tmp=new int[k];
        System.arraycopy(nums, 0, tmp, 0, k);
        solve(nums,tmp,k,position);
    }

    private static void solve(int[] nums, int[] tmp,int k, int position) {
        int j=position, i=0;
        if (position+k>nums.length){
            while(j<nums.length){
                int t=tmp[i];
                tmp[i++]=nums[j];
                nums[j++]=t;
            }
            j=0;
            while (i<tmp.length) nums[j++]=tmp[i++];
            i=0;
            while (j<k) nums[j++]=tmp[i++];
            return;
        }
        while (j<position+k){
            int t=tmp[i];
            tmp[i++]=nums[j];
            nums[j++]=t;
        }
        solve(nums,tmp,k,position+k);
    }
}



