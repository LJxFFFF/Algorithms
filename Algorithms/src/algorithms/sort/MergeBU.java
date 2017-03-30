package algorithms.sort;

import edu.princeton.cs.algs4.In;

import static algorithms.sort.Merge.merge;

/**
 * 自底向上的归并排序
 * Created by Ljx on 2017/3/14.
 */
public class MergeBU {

//    private static Comparable[] aux;

    /**

     * @param a
     */
    public static void sort(Comparable[] a) {
        int N = a.length;
        Comparable[] aux = new Comparable[N];
        for (int sz = 1; sz < N; sz = sz+sz)//sz: 子数组大小
            for (int lo = 0; lo < N-sz; lo += sz+sz)//lo:子数组索引
                merge(a, aux, lo, lo+sz-1, Math.min(lo+sz+sz-1, N-1));
    }

    public static void main(String[] args) {
        String[] a = new In("tiny.txt").readAllStrings();
        sort(a);
        assert Example.isSorted(a);
        Example.show(a);
    }
}
