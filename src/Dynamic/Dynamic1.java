package Dynamic;

/**
 * Created by TheKingRing on 2016/10/16.
 */
public class Dynamic1 {
    public static void main(String[] args) {
        System.out.println(("()(()"));
    }
    public int longestValidParentheses(String s) {
        // l[i] - number of longest valid parentheses ending with s[i]
        // If s[i] is '(', l[i] = 0, because no valid parentheses
        // can end with '('.
        // If s[i] is ')', it will match the last unmatched open
        // parenthesis so two parentheses will be added to l[i-1].
        // Then we check if there is any valid parenthesis ending
        // with the parenthesis previous to the last open parenthesis,
        // of which the index is i-l[i]. Note if all previous parentheses
        // are valid, i-l[i] is -1.
        int[] l = new int[s.length()];
        // num of remaining open parenthesis
        int open = 0;
        int max = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') open++;
            else if (s.charAt(i) == ')' && open > 0) {
                // 2 new valid added
                l[i] = 2 + l[i-1];
                // check if any from previous
                if (i - l[i] > 0)
                    l[i] += l[i-l[i]];
                open--;
                if (l[i] > max) max = l[i];
            }
        }
        return max;
    }
}
