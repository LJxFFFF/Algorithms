package algorithms.sort;

import edu.princeton.cs.algs4.StdRandom;

/**
 * 快速排序
 * Created by Ljx on 2017/3/13.
 */
public class Quick {

    public static void sort(Comparable[] a) {
        StdRandom.shuffle(a);
        sort(a, 0, a.length-1);
    }

    private static void sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo) return;
        int j = partition(a, lo, hi);
        sort(a, lo, j-1);
        sort(a, j+1, hi);
    }

    /**
     * 切分过程使得数组满足下面三个条件：
     * 1. 对于某个j, a[j]已经排定
     * 2. a[lo] 到 a[j-1] 中的所有元素都不大于a[j]
     * 3. a[j+1] 到 a[hi]中的所有元素都不小于a[j]
     * @param a
     * @param lo
     * @param hi
     * @return 已排定元素的索引j;
     */
    private static int partition(Comparable[] a, int lo, int hi) {
        int i = lo, j = hi + 1;
        Comparable v = a[lo];
        while (true) {
            //扫描左右，检查是否结束并交换元素
            while (Example.less(a[++i], v)) if (i == hi) break;
            while (Example.less(v, a[--j])) if (j == lo) break;
            if (i >= j) break;
            Example.exch(a, i, j);
        }
        Example.exch(a, lo, j);
        return j;
    }

    /**
     * 找到一组数中第k小的元素(0开头)
     * @param a
     * @param k
     * @return
     */
    public static Comparable select(Comparable[] a, int k) {
        StdRandom.shuffle(a);
        int lo = 0, hi = a.length - 1;
        while (hi > lo) {
            int j = partition(a, lo, hi);
            if     (j == k) return a[k];
            else if (j > k) hi = j - 1;
            else if (j < k) lo = j + 1;
        }
        return a[k];
    }

    public static void main(String[] args) {
//        String[] a = new In("tiny.txt").readAllStrings();
        Integer[] a = {1, 2, 3, 4, 5, 6};
        System.out.println(select(a, 2));
//        sort(a);
//        assert isSorted(a);
//        show(a);
    }
}
