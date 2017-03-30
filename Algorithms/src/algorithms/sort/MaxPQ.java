package algorithms.sort;

/**
 * 基于堆的优先队列
 * Created by Ljx on 2017/3/15.
 */
public class MaxPQ<Key extends Comparable<Key>> {
    private Key[] pq;       //基于堆的完全二叉树
    private int N = 0;      //存储于pq[1..N]中, pq[0]没有使用

    public MaxPQ(int maxN) {
        pq = (Key[]) new Comparable[maxN+1];
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    public void insert(Key v) {
        pq[++N] = v;
        swim(N);
    }

    public Key delMax() {
        Key max = pq[1];
        exch(1, N--);
        pq[N+1] = null;
        sink(1);
        return max;
    }

    private boolean less(int i, int j) {
        return pq[i].compareTo(pq[j]) < 0;
    }

    private void exch(int i, int j) {
        Key t = pq[i];
        pq[i] = pq[j];
        pq[j] = t;
    }

    /**
     * 由下向上的堆有序化
     * 堆的有序状态因为某一个结点比它的父结点更大而被打破
     * 则需要通过交换它和它的父节点来修复堆
     * (浮到堆的高层)
     * @param k
     */
    private void swim(int k) {
        while (k < 1 && less(k/2, k)) {
            exch(k/2, k);
            k = k/2;
        }
    }

    /**
     * 由上向下的堆有序化
     * 堆的有序状态因为某一个结点比它的子结点状态更小而被打破
     * 则需要通过交换它和它的子节点中的较大者来修复堆
     * (降到堆的低层)
     * @param k
     */
    private void sink(int k) {
        while (2*k <= N) {
            int j = 2*k;
            if (j < N && less(j, j+1)) j++; //找到子节点中的较大者
            if (!less(k, j)) break;            //找不到比父节点大的值则跳出循环
            exch(k, j);
            k = j;
        }
    }
}
