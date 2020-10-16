package com.oop;

import java.util.Arrays;
import java.util.EmptyStackException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * implements Iterable interface and provides functionality for data structures as Stack.
 * @param <T>
 */
public class Stack<T> implements Iterable<T> {

    int maxSize = 2;
    T[] array;
    int head;

    /**
     * Constructor for Stack with 2 fields:
     * T-typed array with minimum length - 2;
     * Counter(head) = 0 ;
     */
    @SuppressWarnings("unchecked")
    public Stack() {
       array = (T[]) new Object[maxSize];
       head = 0;
    }

    /**
     * Nested class that implements Iterator interface for Stack class.
     */
    public class StackIterator implements Iterator<T> {
        Stack<T> stack;
        int c;

        /**
         *Constructor for nested class StackIterator with 2 fields.
         */
       private StackIterator() {
            stack = Stack.this;
            c = stack.head-1;
        }

        /**
         * @return true if there is nest element in the Stack, false otherwise.
         */
        @Override
        public boolean hasNext() {
            return c >= 0;
        }

        /**
         * @return next element of the Stack (T-type) if there is one, trows exception otherwise.
         */
        @Override
        public T next() {
            if (hasNext()) {
                return stack.array[c--];
            } else {
                throw new NoSuchElementException();
            }
        }
    }

    /**
     * @return  new Stack Iterator object.
     */
    @Override
    public Iterator<T> iterator() {
        return new StackIterator();
    }

    /**
     * Adds new element into Stack. Increases the Stack size if necessary.
     * @param a element, that you want to add into Stack.
     */
    public void push(T a) {
        if(head < maxSize - 1) {
            array[head] = a;
            head++;
        } else {
            maxSize = maxSize*2;
            array = Arrays.copyOf(array, array.length * 2);
            push(a);
        }
    }

    /**
     * Delete element from Stack. If there are no elements in the Stack, throws exception.
     */
    public void pop() {
        if(head!=0) {
            head--;
        } else {
            throw new EmptyStackException();
        }
    }

    /**
     * @return how much elements are there in Stack.
     */
    public int count() {
       return head;
    }
}
