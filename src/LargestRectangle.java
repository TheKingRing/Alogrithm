import java.util.Stack;

/**
 * Created by TheKingRing on 2016/10/14.
 */
public class LargestRectangle {
    public static void main(String[] args) {
        int[] a={0,1,0,2,1,0,1,3,2,1,2,1};
        System.out.println(largestRectangleArea(a));
    }
    public static int largestRectangleArea(int[] heights) {
        if(heights.length==0) return 0;
        int position=0;
        int max=0;
        int min=heights[0];
        int maxposition=0;
        while (position<heights.length){
            if (heights[position]<=min){
                min=heights[position++];
                if(min*position>max){
                    max=min*position;
                    maxposition=position-1;
                }
                continue;
            }
            int i=position-1,tmpmax=heights[position],minheight=heights[position],tmpmaxp=0;
            while (i>=0){
                if(heights[i]==min){
                    tmpmax=tmpmax>min*(position+1)?tmpmax:min*(position+1);
                    break;
                }else if(i==maxposition-1) {
                    while(heights[i]>=minheight){
                        tmpmax+=minheight;i--;
                    }
                    break;
                }
                if (heights[i]<=minheight) minheight=heights[i];
                if(tmpmax<=(position-i+1)*minheight){
                    tmpmax=(position-i+1)*minheight;
                    tmpmaxp=i;
                }
                i--;
            }
            if(max<=tmpmax){
                maxposition=tmpmaxp;
                max=tmpmax;
            }
            position++;
        }
        return max;
    }
    public class Solution {
        public int largestRectangleArea(int[] height) {
            if (height == null || height.length == 0) {
                return 0;
            }

            Stack<Integer> stack = new Stack<Integer>();//最小栈
            int max = 0;
            for (int i = 0; i <= height.length; i++) {
                int curt = (i == height.length) ? -1 : height[i];
                while (!stack.isEmpty() && curt <= height[stack.peek()]) {
                    int h = height[stack.pop()];
                    int w = stack.isEmpty() ? i : i - stack.peek() - 1;
                    max = Math.max(max, h * w);
                }
                stack.push(i);
            }

            return max;
        }
    }
}
