package algorithms.search;

import algorithms.dataStruct.Queue;
import edu.princeton.cs.algs4.BinarySearch;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;

/**
 * 基于有序数组的二分查找
 * Created by Ljx on 2017/3/17.
 */
public class BinarySearchST<Key extends Comparable<Key> , Value> {

    private Key[] keys;
    private Value[] vals;
    private int N;
    @SuppressWarnings("unchecked")
    public BinarySearchST(int capacity) {
        keys = (Key[]) new Comparable[capacity];
        vals = (Value[]) new Object[capacity];
    }

    @SuppressWarnings("unchecked")
    // resize the underlying arrays
    private void resize(int capacity) {
        assert capacity >= N;
        Key[]   tempk = (Key[])   new Comparable[capacity];
        Value[] tempv = (Value[]) new Object[capacity];
        for (int i = 0; i < N; i++) {
            tempk[i] = keys[i];
            tempv[i] = vals[i];
        }
        vals = tempv;
        keys = tempk;
    }

    public int size() { return N; }
    public Boolean isEmpty() { return size()==0; }

    public Value get(Key key) {
        if (isEmpty()) return null;
        int i = rank(key);
        if (i < N && keys[i].compareTo(key) == 0) return vals[i];
        else                                      return null;
    }

    public int rank(Key key) {
        int lo = 0, hi = N-1;
        while (lo <= hi) {
            int mid = lo + (hi - lo)/2;
            int cmp = key.compareTo(keys[mid]);
            if      (cmp < 0) hi = mid - 1;
            else if (cmp > 0) lo = mid + 1;
            else return mid;
        }
        return lo;
    }

    public void put(Key key, Value val) {
        int i = rank(key);
        if (i < N && keys[i].compareTo(key) == 0) {
            vals[i] = val;
            return;
        }

        if (N == keys.length) resize(2*keys.length);

        for (int j = N; j > i; j--) {
            keys[j] = keys[j-1];
            vals[j] = vals[j-1];
        }
        keys[i] = key;
        vals[i] = val;
        N++;
    }

    public Key min() {  return keys[0]; }

    public Key max() {  return keys[N-1]; }

    public Key select(int k) {  return keys[k];}

    public Key ceiling(Key key) {
        int i = rank(key);
        if (i == N) return null;
        else return keys[i];
    }

    public Key floor(Key key) {
        int i = rank(key);
        if (i < N && key.compareTo(keys[i]) == 0) return keys[i];
        if (i == 0) return null;
        else return keys[i-1];
    }

    public void delete(Key key) {
        if (isEmpty()) return;
        int i = rank(key);
        // key not in table
        if (i == N || keys[i].compareTo(key) != 0) {
            return;
        }

        // resize if 1/4 full
        if (N > 0 && N == keys.length/4) resize(keys.length/2);

        for (int j = i; j < N - 1; j++) {
            keys[j] = keys[j + 1];
            vals[j] = vals[j + 1];
        }
        N--;
        keys[N] = null;
        vals[N] = null;
    }

    public Boolean contains(Key key) {
        return get(key) != null;
    }

    public Iterable<Key> keys(Key lo, Key hi) {
        Queue<Key> q = new Queue<Key>();
        for (int i = rank(lo); i < rank(hi); i++)
            q.enqueue(keys[i]);
        if (contains(hi))
            q.enqueue(keys[rank(hi)]);
        return q;
    }

    public static void main(String[] args) {
        int minlen = Integer.parseInt(args[0]);
        BinarySearchST<String, Integer> st = new BinarySearchST<>(10);
        In in = new In("tinyTale.txt");
        while (!in.isEmpty()) {
            String word = in.readString();
            if (word.length() < minlen) continue;
            if (st.contains(word))  st.put(word, st.get(word) + 1);
            else                    st.put(word, 1);
        }
        String max = " ";
        st.put(max, 0);
        for (String word : st.keys(st.min(), st.max())) {
            if (st.get(word) > st.get(max)) max = word;
            StdOut.println(word + " " + st.get(word));
        }

        StdOut.println("max word: " + max + " " + st.get(max));
    }
}
