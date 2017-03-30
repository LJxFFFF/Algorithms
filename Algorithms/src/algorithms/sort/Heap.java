package algorithms.sort;

import edu.princeton.cs.algs4.In;

/**
 * Created by Ljx on 2017/3/13.
 */
public class Heap {

    public static void sort(Comparable[] a) {
        int N = a.length;
        for (int k = N / 2; k >= 1; k--)    //构造堆
            sink(a, k, N);
        while (N > 1) {
            exch(a, 1, N--);
            sink(a, 1, N);
        }
    }

    private static void sink(Comparable[] a, int k, int N) {
        while (2 * k <= N) {
            int j = 2 * k;
            if (j < N && less(a, j, j + 1)) j++; //找到子节点中的较大者
            if (!less(a, k, j)) break;            //找不到比父节点大的值则跳出循环
            exch(a, k, j);
            k = j;
        }
    }

    private static void exch(Comparable[] a, int i, int j) {
        Comparable swap = a[i-1];
        a[i-1] = a[j-1];
        a[j-1] = swap;
    }

    private static boolean less(Comparable[] pq, int i, int j) {
        return pq[i-1].compareTo(pq[j-1]) < 0;
    }

    public static void main(String[] args) {
        String[] a = new In("tiny.txt").readAllStrings();
        sort(a);
        assert Example.isSorted(a);
        Example.show(a);
    }

}
