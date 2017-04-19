package algorithms.string;

import edu.princeton.cs.algs4.StdOut;

/**
 * Created by Ljx on 2017/3/31.
 */
public class BoyerMoore {
    private int[] right;    //记录字母表中每个字符在模式中出现的最靠右的位置，不存在表示为-1
    private String pat;

    public BoyerMoore(String pat) {
        this.pat = pat;
        int M = pat.length();
        int R = 256;
        right = new int[R];
        for (int c = 0; c < R; c++) right[c] = -1;
        for (int j = 0; j < M; j++) right[pat.charAt(j)] = j;
    }

    public int search(String txt) {
        int N = txt.length();
        int M = pat.length();
        int skip;
        for (int i = 0; i <= N-M; i += skip) {
           skip = 0;
           for (int j = M-1; j >= 0; j--) {
               if (pat.charAt(j) != txt.charAt(i+j)) {
                   skip = Math.max(1, j - right[txt.charAt(i+j)]);
                   break;
               }
           }
           if (skip == 0) return i;
        }
        return N;
    }

    public static void main(String[] args) {
        String pat = args[0];
        String txt  = args[1];
        BoyerMoore boyerMoore = new BoyerMoore(pat);
        StdOut.println("text:    " + txt);
        int offset = boyerMoore.search(txt);
        StdOut.print("pattern: ");
        for (int i = 0; i < offset; i++)
            StdOut.print(" ");
        StdOut.println(pat);
    }

}
