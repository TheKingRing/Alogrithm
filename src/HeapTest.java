import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by TheKingRing on 2016/9/27.
 */
public class HeapTest {
    public static void main(String[] args) {
        List<String> str=new ArrayList<>();
       Stack<Integer> bd=new Stack<>();
        for (int k=0;k<4;k++){
            int count=0;
            while (count<=4){
                bd.push(count++);
            }
            str.add(bd.toString());
            bd=new Stack<>();
        }
        System.out.println(str);
    }
}
