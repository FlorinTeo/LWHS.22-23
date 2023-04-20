package main;

import java.lang.reflect.Array;
import java.util.Arrays;

public class HeapPriorityQueue<E extends Comparable<E>> implements PriorityQueue<E> {

    private E[] elements;
    private int size;
    
    // Region: Helper methods
    private int parent(int index) {
        return index/2;
    }
    
    private int leftChild(int index) {
        return index * 2;
    }
    
    private int rightChild(int index) {
        return index * 2 + 1;
    }
    
    private boolean hasParent(int index) {
        return index > 1;
    }
    
    private boolean hasLeftChild(int index) {
        return leftChild(index) <= size;
    }
    
    private boolean hasRightChild(int index) {
        return rightChild(index) <= size;
    }
    
    private void swap(int index1, int index2) {
        E temp = elements[index1];
        elements[index1] = elements[index2];
        elements[index2] = temp;
    }
    
    private void checkResize(int i) {
        if (i >= elements.length) {
            elements = Arrays.copyOf(elements, 2 * elements.length);
        }
    }
    // EndRegion: Helper methods
    
    public HeapPriorityQueue(Class<E> eClass) {
        elements = (E[]) Array.newInstance(eClass, 2);
        size = 0;
    }
    
    @Override
    public void add(E value) {
        int i = size + 1;
        checkResize(i);
        elements[i] = value;
        size++;
        while (hasParent(i)) {
            int iParent = parent(i);
            if (elements[iParent].compareTo(elements[i]) <= 0) {
                break;
            }
            swap(i, iParent);
            i = iParent;
        }
    }

    @Override
    public void clear() {
        size = 0;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public E peek() {
        return size > 0 ? elements[1] : null;
    }

    @Override
    public E remove() {
        if (isEmpty()) {
            return null;
        }
        E elem = elements[1];
        elements[1] = elements[size];
        size--;
        int i = 1;
        while(hasLeftChild(i)){
            int iChild = leftChild(i);
            if (hasRightChild(i) && elements[rightChild(i)].compareTo(elements[leftChild(i)]) < 0) {
                iChild = rightChild(i);
            }
            if (elements[iChild].compareTo(elements[i]) < 0) {
                swap(i, iChild);
                i = iChild;
            } else {
                break;
            }
        }
        return elem;
    }

    @Override
    public int size() {
        return size;
    }
}
