package algorithms.search;

import algorithms.dataStruct.Queue;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;


/**
 * 基于无序链表的顺序查找
 * Created by Ljx on 2017/3/16.
 */
public class SequentialSearchST<Key extends Comparable<Key>, Value> {

    private Node first;

    private class Node {
        Key key;
        Value val;
        Node next;
        Node(Key key, Value val, Node next) {
            this.key = key;
            this.val = val;
            this.next = next;
        }
    }

    public Value get(Key key) {
        for (Node x = first; x != null; x = x.next)
            if (key.equals(x.key))
                return x.val;    //命中
        return null;
    }

    public void put(Key key, Value val) {
        for (Node x = first; x != null; x = x.next) {
            if (key.equals(x.key)) {
                x.val = val;
                return;
            }
        }
        first = new Node(key, val, first);
    }

    public Iterable<Key> keys() {
        Queue<Key> queue = new Queue<Key>();
        for (Node x = first; x != null; x = x.next)
            queue.enqueue(x.key);
        return queue;
    }

    public Boolean contains(Key key) {
        return get(key) != null;
    }

    public static void main(String[] args) {
        int minlen = Integer.parseInt(args[0]);
        SequentialSearchST<String, Integer> st = new SequentialSearchST<>();
        In in = new In("tinyTale.txt");
        while (!in.isEmpty()) {
            String word = in.readString();
            if (word.length() < minlen) continue;
            if (!st.contains(word)) st.put(word, 1);
            else                    st.put(word, st.get(word) + 1);
        }
        String max = " ";
        st.put(max, 0);
        for (String word : st.keys()) {
            if (st.get(word) > st.get(max)) max = word;
            StdOut.println(word + " " + st.get(word));
        }

        StdOut.println("max word: " + max + " " + st.get(max));
    }
}
