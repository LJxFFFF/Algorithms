package algorithms.search;

import algorithms.dataStruct.Stack;
import edu.princeton.cs.algs4.In;

/**
 * 基于二叉查找树的符号表
 * Created by Ljx on 2017/3/17.
 */
public class BST<Key extends Comparable<Key>, Value> {

    private Node root;  //二叉查找树的根结点

    private class Node {
        private Key key;            //键
        private Value val;          //值
        private Node left, right;   //指向子树的连接
        private int N;              //以该节点为根的子树中的节点总数

        public Node(Key key, Value val, int N) {
            this.key = key;
            this.val = val;
            this.N = N;
        }
    }

    public int size() { return size(root);}

    private int size(Node x) {
        if (x == null) return 0;
        else           return x.N;
    }

    public Value get(Key key) {
        return get(root, key);
    }

    /**
     * 在以x为根节点的子树中查找(递归)
     * @param x
     * @param key
     * @return 返回key所对应的值,如果找不到，则返回null
     */
    private Value get(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if      (cmp < 0)   return get(x.left, key);
        else if (cmp > 0)   return get(x.right, key);
        else                return x.val;
    }

    /**
     * 在以x为根节点的子树中查找(非递归）
     * @param key
     * @return 返回key所对应的值,如果找不到，则返回null
     */
    private Value getVal(Key key) {
        Node x = root;
        while (x != null) {
            int cmp = key.compareTo(x.key);
            if (cmp == 0) return x.val;
            else if (cmp < 0) x = x.left;
            else if (cmp > 0) x = x.right;
        }
        return null;
    }

    public void put(Key key, Value val) {
        root  = put(root, key, val);
    }

    /**
     *  如果key存在与以x为根节点的子树中则更新它的值
     *  否则将以key和val为键值对的新节点插入到该子树中
     * @param x
     * @param key
     * @param val
     * @return
     */
    private Node put(Node x, Key key, Value val) {
        if (x == null) return new Node(key, val, 1);
        int cmp = key.compareTo(x.key);
        if      (cmp < 0) x.left = put(x.left, key, val);
        else if (cmp > 0) x.right = put(x.right, key, val);
        else x.val = val;
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    public Key min() {
        return min(root).key;
    }

    private Node min(Node x) {
        if (x.left == null) return x;
        return min(x.left);
    }

    public Key max() {
        return max(root).key;
    }

    private Node max(Node x) {
        if (x.right == null) return x;
        return max(x.right);
    }

    public Key floor(Key key) {
        Node x = floor(root, key);
        if (x == null) return null;
        return x.key;
    }

    private Node floor(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp == 0) return x;
        if (cmp < 0)  return floor(x.left, key);
        Node t = floor(x.right, key);
        if (t != null) return t;
        else return x;
    }

    public Key ceiling(Key key) {
        Node x = ceiling(root, key);
        if (x == null) return null;
        return x.key;
    }

    private Node ceiling(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp == 0) return x;
        if (cmp > 0) return ceiling(x.right, key);
        Node t = ceiling(x.left, key);
        if (t != null) return t;
        else return x;
    }

    public Key select(int k) {
        return select(root, k).key;
    }

    private Node select(Node x, int k) {
        if (x == null) return null;
        int t = size(x.left);
        if      (t > k) return select(x.left, k);
        else if (t > k) return select(x.right, k-t-1);
        else            return x;
    }

    public int rank(Key key) { return rank(key, root); }

    private int rank(Key key, Node x) {
        if (x == null) return 0;
        int cmp = key.compareTo(x.key);
        if      (cmp < 0) return rank(key, x.left);
        else if (cmp > 0) return 1 + size(x.left) + rank(key, x.right);
        else              return size(x.left);
    }

    public void deletMin(Key key) {
        deleteMin(root.left);
    }

    private Node deleteMin(Node x) {
        if (x.left == null) return x.right;
        x.left = deleteMin(x.left);
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    public void delete(Key key) {
        root = delete(root, key);
    }

    private Node delete(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if      (cmp < 0) x.left = delete(x.left, key);
        else if (cmp > 0) x.right = delete(x.right, key);
        else {
            if (x.right == null) return x.left;
            if (x.left == null)  return x.right;
            Node t =x;
            x = min(t.right);
            x.right = deleteMin(t.right);
            x.left = t.left;
        }
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }
}