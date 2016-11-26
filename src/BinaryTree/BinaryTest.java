package BinaryTree;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TheKingRing on 2016/9/23.
 */
public class BinaryTest {
    public static void main(String[] args) {
        int[] a={1,2,3,4,5,6,4,5,6,7,5,6,7,6,7,8,8};
        BinarySearchTable<Integer,List<Integer>> bst=new BinarySearchTable<>();
        for (int i=0;i<a.length;i++){
            List<Integer> list=new ArrayList<>();
            list.add(i);
            bst.addelement(a[i],list);
        }
        System.out.println(bst.Get(6));
    }
}
