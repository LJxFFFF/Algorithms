package algorithms.dataStruct;

import java.util.Iterator;

/**
 * 下压(LIFO）栈
 * 动态调整数组大小的实现
 * Created by Ljx on 2017/3/15.
 */
public class ResizingArrayStack<Item> implements Iterable {

    @SuppressWarnings("unchecked")
    private Item[] a = (Item[]) new Object[1];

    private int N = 0;

    public boolean isEmpty() {  return N==0; }
    public int size() { return N;}

    /**
     * 将栈移动到一个大小为max的新数组
     */
    @SuppressWarnings("unchecked")
    private void resize(int max) {
        Item[] temp = (Item[]) new Object[max];
        for (int i = 0; i < N; i++)
            temp[i] = a[i];
        a = temp;
    }

    /**
     * 将元素添加到栈顶
     * 如果容量不够，将栈扩容为原来的两倍
     * @param item
     */
    public void push(Item item) {
        if (N == a.length) resize(2*a.length);
        a[N++] = item;
    }

    /**
     * 从栈顶删除元素
     * 如果元素数量为栈长度的1/4，将栈的长度减半
     * @return 被删除的Item;
     */
    public Item pop() {
        Item item = a[--N];
        a[N] = null;
        if (N > 0 && N == a.length/4) resize(a.length/2);
        return item;
    }

    @Override
    public Iterator iterator() {
        return new ReverseArrayIterator();
    }

    private class ReverseArrayIterator implements Iterator<Item> {

        private int i = N;

        @Override
        public boolean hasNext() {
            return i > 0;
        }

        @Override
        public Item next() {
            return a[--i];
        }

        @Override
        public void remove() {}
    }
}
