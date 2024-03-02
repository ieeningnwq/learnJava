package com.ieening.learnGenricConatiner;

import java.lang.reflect.Array;
import java.util.Arrays;

public class DynamicArray<E> {
    private static final int DEFAULT_CAPACITY = 10;
    private int size;
    private Object[] elementData;

    public Object[] getElementData() {
        return elementData;
    }

    public DynamicArray() {
        this.elementData = new Object[DEFAULT_CAPACITY];
    }

    private void ensureCapacity(int minCapacity) {
        int oldCapacity = elementData.length;
        if (oldCapacity >= minCapacity) {
            return;
        }
        int newCapacity = oldCapacity * 2;
        if (newCapacity < minCapacity)
            newCapacity = minCapacity;
        elementData = Arrays.copyOf(elementData, newCapacity);
    }

    public void add(E e) {
        ensureCapacity(size + 1);
        elementData[size++] = e;
    }

    public <T extends E> void addAll(DynamicArray<T> addedDynamicArray) {
        for (int i = 0; i < addedDynamicArray.size(); i++) {
            add(addedDynamicArray.get(i));
        }
    }

    @SuppressWarnings("unchecked")
    public E get(int index) {
        return (E) elementData[index];
    }

    public int size() {
        return size;
    }

    public E set(int index, E element) {
        E oldValue = get(index);
        elementData[index] = element;
        return oldValue;
    }

    public static int indexOf(DynamicArray<?> arr, Object elm) {
        for (int i = 0; i < arr.size(); i++) {
            if (arr.get(i).equals(elm)) {
                return i;
            }
        }
        return -1;
    }

    private static <T> void swapInternal(DynamicArray<T> arr, int i, int j) {
        T tmp = arr.get(i);
        arr.set(i, arr.get(j));
        arr.set(j, tmp);
    }

    public static void swap(DynamicArray<?> arr, int i, int j) {
        swapInternal(arr, i, j);
    }

    public static <D> void copy(DynamicArray<D> dest, DynamicArray<? extends D> src) {
        for (int i = 0; i < src.size(); i++) {
            dest.add(src.get(i));
        }
    }

    public static <T extends Comparable<? super T>> T max(DynamicArray<T> arr) {
        T max = arr.get(0);
        for (int i = 1; i < arr.size(); i++) {
            if (arr.get(i).compareTo(max) > 0) {
                max = arr.get(i);
            }
        }
        return max;
    }

    public void copyTo(DynamicArray<? super E> dest) {
        for (int i = 0; i < size; i++) {
            dest.add(get(i));
        }
    }

    @SuppressWarnings("unchecked")
    public E[] toArray(Class<E> type) {
        Object copy = Array.newInstance(type, size);
        System.arraycopy(elementData, 0, copy, 0, size);
        return (E[]) copy;
    }

    public static void main(String[] args) {
        DynamicArray<Integer> ints = new DynamicArray<Integer>();
        ints.add(100);
        ints.add(34);
        DynamicArray<Number> numbers = new DynamicArray<Number>();
        ints.copyTo(numbers);
    }

}
