package algorithms.sort;

import edu.princeton.cs.algs4.In;

import static algorithms.sort.Example.*;

/**
 * Created by Ljx on 2017/3/13.
 */
public class Selection {
    public static void sort(Comparable[] a) {
        int N = a.length;
        for (int i = 0; i < N; i++) {
            int min = i;
            for (int j = i+1; j< N; j++) {if (less(a[j], a[min])) min = j;}
            exch(a, i, min);
        }
    }

    public static void main(String[] args) {
        String[] a = new In("tiny.txt").readAllStrings();
        sort(a);
        assert isSorted(a);
        show(a);
    }
}
