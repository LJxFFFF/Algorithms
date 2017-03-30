package algorithms.sort;

import edu.princeton.cs.algs4.In;

import static algorithms.sort.Example.*;

/**
 * Created by Ljx on 2017/3/13.
 */
public class Shell {

    public static void sort(Comparable[] a) {
        int N = a.length;
        int h = 1;
        while(h < N/3) h = 3*h + 1;
        while (h >= 1) {
            for (int i = h; i < N; i++) {
                for (int j = i; j >= h && less(a[j], a[j-h]); j -= h)
                    exch(a, j, j-h);
            }
            h = h/3;
        }
    }

    public static void main(String[] args) {
        String[] a = new In("tiny.txt").readAllStrings();
        sort(a);
        assert isSorted(a);
        show(a);
    }
}
