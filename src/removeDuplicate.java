import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by TheKingRing on 2016/10/14.
 */
public class removeDuplicate {

    public static void main(String[] args) {
        int[] a={1,2,2,2,2,2,2,2,2};
        System.out.println(subsetsWithDup(a));
    }
    public static List<List<Integer>> subsetsWithDup(int[] nums) {
        int position=0;
        int[] dp=new int[nums.length+1];
        List<List<Integer>> result=new ArrayList<>();
        result.add(new ArrayList<>());
        dp[0]=1;
        Arrays.sort(nums);
        while(position<nums.length){
            int i=0;
            if(position>=1){
                if(nums[position]==nums[position-1]) i=dp[position-1];
            }
            int length=result.size();
            for(;i<length;i++){
                List<Integer> list=generate(result.get(i),nums[position]);
                result.add(list);
            }
            dp[position+1]=result.size();
            position++;
        }
        return result;
    }
    public static List<Integer> generate(List<Integer> tmp, int k){
        List<Integer> list=new ArrayList<>();
        list.addAll(tmp);
        list.add(k);
        return list;
    }
}
