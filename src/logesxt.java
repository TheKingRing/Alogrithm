/**
 * Created by TheKingRing on 2016/9/24.
 */
public class logesxt {
    public static void main(String[] args) {
        System.out.println(longestPalindrome("ccd"));
    }
    public static String longestPalindrome(String s) {
        if (s.length() == 1) {
            return s;
        }
        char[] chs = s.toCharArray();
        int left , right ;
        int[] result = new int[2];
        int position=0;
        while (chs.length-position>(result[1]-result[0])/2){
            int tmp=position+1;
            if (tmp==chs.length)break;
            while (chs[position]==chs[tmp]){
                tmp++;
                if (tmp==chs.length)break;
            }
            if (tmp-1==position)
            {
                right=left=position;
                int[] tmptag=findThelongest(left,right,chs);
                if (tmptag[1]-tmptag[0]>result[1]-result[0]) result=tmptag;
            }else if ((tmp-position)%2!=0)
            {
                left=right=(position+tmp-1)/2;
                int[] tmptag=findThelongest(left,right,chs);
                if (tmptag[1]-tmptag[0]>result[1]-result[0]) result=tmptag;
            }else
            {
                right=(tmp+position)/2;
                left=right-1;
                int[] tmptag=findThelongest(left,right,chs);
                if (tmptag[1]-tmptag[0]>result[1]-result[0]) result=tmptag;
            }
            position=tmp;
        }
        StringBuilder bd=new StringBuilder();
        for(int i=result[0];i<=result[1];i++){
            bd.append(chs[i]);
        }
        return bd.toString();
    }

    private static int[] findThelongest(int left, int right, char[] chs) {
        int[] r=new int[2];
        while (left>=0&&right<chs.length){
            if (chs[left]!=chs[right]){
               break;
            }
            left--;right++;
        }
        r[0]=left+1;r[1]=right-1;
        return r;
    }
}
