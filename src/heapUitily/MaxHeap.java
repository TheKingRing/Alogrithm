package heapUitily;

/**
 * Created by TheKingRing on 2016/9/27.
 */
public class MaxHeap extends Heap {

    public MaxHeap(int[] arr) {
        super(arr);
        buildHeap();
    }


    @Override
    public void sort() {
        int length=arr.length;
        for (int k=arr.length-1;k>0;k--){
            swap(0,k);
            length--;
            Heapify(0,length);
        }
    }

    @Override
    public void Heapify(int j, int l) {
        int left=2*j+1;
        int right=2*j+2;
        int max=j;
        if (left<l&&arr[left]>arr[max]){
            max=left;
        }
        if (right<l&&arr[right]>arr[max]){
            max=right;
        }
        if (j!=max){
            swap(max,j);
            Heapify(max,l);
        }
    }


    private void buildHeap() {
        for (int i=arr.length/2;i>=0;i--){
            Heapify(i);
        }
    }

    @Override
    public void Heapify(int i) {
        int r=2*i+1;
        int l=2*i+2;
        int max=i;
        if (l<i&&arr[l]>arr[max])max=l;
        if (r<i&&arr[r]>arr[max])max=r;
        if (i!=max){
            swap(i,max);
            Heapify(max);
        }
    }

}
