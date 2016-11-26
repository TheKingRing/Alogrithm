package Dynamic;

/**
 * Created by TheKingRing on 2016/10/19.
 */
public class Dynamic2 {
    public static void main(String[] args) {
        int[] a={0,0};
        System.out.println(largestRectangleArea(a));
    }
    public static int largestRectangleArea(int[] heights) {
        if(heights.length==0) return 0;
        if (heights.length==1)return heights[0];
        int[] dpleft=new int[heights.length];
        int[] dpright=new int[heights.length];
        int max=0;
        dpleft[0]=0;
        for (int i=1;i<heights.length;i++){
            int j=i-1;
            while (j>=0&&heights[j]>=heights[i]){
               j=dpleft[j]-1;
            }
            dpleft[i]=j+1;
        }
        dpright[heights.length-1]=heights.length-1;
        for (int i=heights.length-2;i>=0;i--){
            int j=i+1;
            while (j<heights.length&&heights[j]>=heights[i]){
                j=dpright[j]+1;
            }
            dpright[i]=j-1;
        }

        for (int i=0;i<heights.length;i++){
            max=Math.max(max,(dpright[i]-dpleft[i]+1)*heights[i]);
        }
        return max;
    }

}
