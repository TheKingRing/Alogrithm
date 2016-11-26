package heapUitily;

/**
 * Created by TheKingRing on 2016/9/27.
 */
public class MinHeap extends Heap {


    public MinHeap(int[] arr) {
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
        int min=j;
        if (left<l&&arr[left]<arr[min]){
            min=left;
        }
        if (right<l&&arr[right]<arr[min]){
            min=right;
        }
        if (j!=min){
            swap(min,j);
            Heapify(min,l);
        }
    }

    private void buildHeap() {
        for (int k=arr.length/2;k>=0;k--) Heapify(k);
    }

    @Override
    public void Heapify(int i) {
        int r=2*i+1;
        int l=2*i+2;
        int min=i;
        if (l<i&&arr[l]<arr[min])min=l;
        if (r<i&&arr[r]<arr[min])min=r;
        if (i!=min){
            swap(i,min);
            Heapify(min);
        }
    }
}
