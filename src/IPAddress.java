import java.util.ArrayList;
import java.util.List;

/**
 * Created by TheKingRing on 2016/9/29.
 */
public class IPAddress {
    private static int mainpoint=0;//the point on the chs
        public static List<String> restoreIpAddresses(String s) {
            List<String> res=new ArrayList<>();
            char[] chs=s.toCharArray();
            if(chs.length<4||chs.length>12) return res;
            int blank=4;//the bit of IP
            StringBuilder builder=new StringBuilder();
            return findIPAddress(res,chs,builder,blank,1);
        }

        private static List<String> findIPAddress(List<String> res, char[] chs, StringBuilder builder, int blank, int i) {
            int count=0;
            for (int k=(chs.length-mainpoint-(blank-1))>3?3:(chs.length-mainpoint-(blank-1));i<=k;i++){
                if (i>=chs.length-(blank-1)*3-mainpoint) {
                    if (!check(chs,mainpoint,i,count))continue; //analyse if it can be one bit of IP
                    while (count<i){
                        builder.append(chs[mainpoint++]);
                        count++;
                    }
                    if (mainpoint==chs.length){
                        res.add(builder.toString());//if the ip is satisfying we will add it
                        break;//go for the delete step below out the for circle
                    }
                    builder.append('.');
                    findIPAddress(res,chs,builder,blank-1,1);//when the return execute it will return here
                }

            }
            //when the for circle end it will execute here
            mainpoint=mainpoint-count;
            if (builder.indexOf(".")!=-1) builder.delete(builder.lastIndexOf("."),builder.length());//delete the leaf and search other nodes
            return res;
        }
        private static boolean check(char[] chs, int mainpoint, int i,int count) {
            int cur;
            int point=mainpoint-count;
            if (chs[point]=='0'&&i!=1)return false;//if it begin with '0'
            else if (i==3){
                cur=(chs[point++]-48)*100+(chs[point++]-48)*10+(chs[point]-48);//caculate the value of every bit
                return cur<=255;
            }
           return true;
        }

}
