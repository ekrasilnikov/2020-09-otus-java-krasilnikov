package ru.otus;

import java.util.*;

public class DIYarrayList<T> implements List<T> {
    private Object[] DIYarray;
    private static final int DEFAULT_CAPACITY = 10;
    private int currentSize;

    public DIYarrayList() {
        this.DIYarray = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public int size() {
        return this.currentSize;
    }

    private void grow() {
        Object[] oldDIYarray = DIYarray;
        DIYarray = new Object[(currentSize + (currentSize / 2))];
        System.arraycopy(oldDIYarray, 0, DIYarray, 0, oldDIYarray.length);
    }

    @Override
    public boolean isEmpty() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean contains(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<T> iterator() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object[] toArray() {
        Object[] returnDIYarray = new Object[currentSize];
        System.arraycopy(DIYarray, 0, returnDIYarray, 0, currentSize);
        return returnDIYarray;
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean add(T t) {
        if (DIYarray.length == currentSize) grow();
        DIYarray[currentSize++] = t;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException();
    }

    @Override
    public T get(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public T set(int index, T element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void add(int index, T element) {
        if (DIYarray.length == currentSize) grow();
        System.arraycopy(DIYarray, index, DIYarray, index + 1, currentSize - index);
        DIYarray[index] = element;
        currentSize++;
    }

    @Override
    public String toString() {
        List<String> stringList = new ArrayList<>();
        for (Object current : DIYarray
        ) {
            if (current != null) stringList.add(String.valueOf(current));
        }
        return String.join(", ", stringList);
    }


    @Override
    public T remove(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int indexOf(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int lastIndexOf(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public ListIterator<T> listIterator() {
        return new DIYListIterator();
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }

    class DIYListIterator implements ListIterator<T> {
        private int iteratorIndex;
        private int lastIteratorIndex;

        private DIYListIterator() {
            this.iteratorIndex = 0;
        }

        @Override
        public boolean hasNext() {
            throw new UnsupportedOperationException();
        }

        @Override
        public T next() {
            lastIteratorIndex = iteratorIndex;
            return (T) DIYarray[iteratorIndex++];
        }

        @Override
        public boolean hasPrevious() {
            throw new UnsupportedOperationException();
        }

        @Override
        public T previous() {
            throw new UnsupportedOperationException();
        }

        @Override
        public int nextIndex() {
            throw new UnsupportedOperationException();
        }

        @Override
        public int previousIndex() {
            throw new UnsupportedOperationException();
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

        @Override
        public void set(T t) {
            DIYarray[lastIteratorIndex] = t;
        }

        @Override
        public void add(T t) {
            throw new UnsupportedOperationException();
        }
    }
}
