package algs4.chpt2.part4.A6;

import java.util.Arrays;
import java.util.Iterator;

/**
 * Created by hao on 6/30/16.
 */
public class MinPQ<Item extends Comparable<Item>> implements Iterable<Item> {
    private Item[] pq;
    private int N = 0;

    public MinPQ(int maxN) {
        pq = (Item[]) new Comparable[maxN + 1];
    }

    public void insert(Item item) {
        pq[++N] = item;
        swim(N);
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public boolean isFull() {
        return N == pq.length - 1;
    }

    public int size() {
        return N;
    }

    public Item delMin() {
        Item min = pq[1];
        exch(1, N);
        pq[N] = null;
        N--;
        sink(1);
        return min;
    }

    public Item min() {
        return pq[1];
    }

    private void sink(int k) {
        while (2 * k <= N) {
            int j = 2 * k;
            if (j < N && !less(2 * k, 2 * k + 1)) j++;
            if (less(j, k)) {
                exch(j, k);
                k = j;
            } else {
                break;
            }
        }
    }

    private void swim(int k) {
        while (k / 2 >= 1 && less(k, k / 2)) {
            exch(k, k / 2);
            k /= 2;
        }
    }

    private void exch(int i, int j) {
        Item t = pq[i];
        pq[i] = pq[j];
        pq[j] = t;
    }

    public String toString() {
        return Arrays.toString(pq);
    }

    private boolean less(int i, int j) {
        return pq[i].compareTo(pq[j]) < 0;
    }

    public Iterator<Item> iterator() {
        return new MinPQIterator();
    }

    private class MinPQIterator implements Iterator<Item> {
        private int flag = 1;
        public Item next() {
            Item item = pq[flag];
            flag++;
            return item;
        }
        public boolean hasNext() {
            if (flag <= N) return true;
            return false;
        }
    }

    public static void main(String[] args) {
        Integer[] a = {6,3,7,0,2,8,7,4,9,1,4,2,0};
        MinPQ mpq = new MinPQ(a.length);
        mpq.insert(235592);
        mpq.insert(235592);
        mpq.delMin();
        mpq.insert(245592);

        System.out.println(mpq.toString());
    }
}
