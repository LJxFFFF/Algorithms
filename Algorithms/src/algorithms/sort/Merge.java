package algorithms.sort;

import edu.princeton.cs.algs4.In;

/**
 * 自顶向下的归并排序
 * Created by Ljx on 2017/3/13.
 */
public class Merge {

//    private static Comparable[] aux;

    public static void sort(Comparable[] a) {
        Comparable[] aux = new Comparable[a.length];    //辅助数组aux
        sort(a, aux, 0, a.length - 1);
    }

    /**
     * @param a
     * @param aux
     * @param lo
     * @param hi
     */
    private static void sort(Comparable[] a, Comparable[] aux, int lo, int hi) {
        if (hi <= lo) return;
        int mid = lo + (hi-lo)/2;
        sort(a, aux, lo, mid);
        sort(a, aux, mid+1, hi);
        merge(a, aux, lo, mid, hi);
    }

    /**
     * 原地归并
     * 将 a[lo..mid] 和 a[mid+1..hi] 合并
     * @param a
     * @param lo
     * @param mid
     * @param hi
     */
    public static void merge(Comparable[] a, Comparable[] aux,  int lo, int mid, int hi) {
        int i = lo, j = mid+1;

        for (int k = lo; k <= hi; k++)
            aux[k] = a[k];

        for (int k = lo; k <= hi; k++) {
            if      (i > mid)               a[k] = aux[j++];    //左半边用尽---->取右半边元素
            else if (j > hi)                a[k] = aux[i++];    //右半边用尽---->取左半边元素
            else if (Example.less(aux[j], aux[i]))  a[k] = aux[j++];    //右半边当前元素小于左半边当前元素---->取右半边元素
            else                            a[k] = aux[i++];    //右半边当前元素大于左半边当前元素---->取左半边元素
        }
    }

    public static void main(String[] args) {
        String[] a = new In("tiny.txt").readAllStrings();
        sort(a);
        assert Example.isSorted(a);
        Example.show(a);
    }
}

