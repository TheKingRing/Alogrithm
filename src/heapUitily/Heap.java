package heapUitily;

/**
 * Created by TheKingRing on 2016/9/27.
 */
public abstract class Heap implements HeapAbstrct{
     int[] arr;
    private int ArrayCapacity;
    Heap(int[] arr){
        this.arr=arr;
        this.ArrayCapacity=arr.length;
    }

    @Override
    public int pop() {
        int tmp=arr[0];
        swap(0,arr.length-1);
        arr=generateArray(arr.length-1);
        Heapify(0);
        return tmp;
    }

    public abstract void sort();

    public abstract void Heapify(int i, int j);

    public abstract void Heapify(int i);

    @Override
    public int[] generateArray(int Capacity) {
        int length=Math.min(Capacity,ArrayCapacity);
        int[] tmp=new int[Capacity];
        System.arraycopy(arr, 0, tmp, 0, length);
        ArrayCapacity=Capacity;
        return tmp;
    }



    @Override
    public void printArray() {
        for (int k:arr){
            System.out.print(k+"\t");
        }
        System.out.println();
    }

    @Override
    public void swap(int r, int l) {
        if (r==l){
            return;
        }
        arr[r]=arr[r]^arr[l];
        arr[l]=arr[r]^arr[l];
        arr[r]=arr[l]^arr[r];
    }
}
