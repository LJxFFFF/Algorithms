package algorithms.dataStruct;

import java.util.Iterator;

/**
 * 下压堆栈(链表实现)
 * Created by Ljx on 2017/3/15.
 */
public class Stack<Item> implements Iterable<Item> {

    private Node first; //栈顶
    private int N;  //元素数量

    private class Node {
        Item item;
        Node next;
    }

    public boolean isEmpty() {  return first == null; } //或: N == 0
    public int size() { return N; }

    /**
     * 向栈顶添加元素
     * @param item
     */
    public void push(Item item) {
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.next = oldfirst;
        N++;
    }

    /**
     * 从栈顶删除元素
     * @return 被删除的Item;
     */
    public Item pop() {
        Item item = first.item;
        first = first.next;
        N--;
        return item;
    }

    @Override
    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {

        private Node current = first;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            Item item = current.item;
            current = current.next;
            return item;
        }
    }
}
