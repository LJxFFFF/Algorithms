package algorithms.sort;

/**
 * 插入排序
 * Created by Ljx on 2017/3/13.
 */
public class Insertion {
    public static void sort(Comparable[] a) {
        int N = a.length;
        for (int i = 1; i < N; i++) {
            for (int j = i; j > 0 && Example.less(a[j], a[j-1]); j--) {
                Example.exch(a, j, j-1);}
        }
    }

    public static void main(String[] args) {
//        String[] a = new In("tiny.txt").readAllStrings();
        Integer[] a = {0, 3, 1, 6, 2, 5, 4};
        sort(a);
        assert Example.isSorted(a);
        Example.show(a);
    }
}
