package com.oop;

import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.util.EmptyStackException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import static org.junit.jupiter.api.Assertions.*;

class StackTest {
    /**
     * This test succeeds, when stack is filled and cleaned correctly. It checks the code on the Integer data type.
     * It checks work of: iterator() method, next() and hasNext() method of nested class StackIterator. Also, it checks
     * push(), pop() methods, constructors of the classes.
     */
    @Test
    public void SimpleTest() {
        Stack<Integer> actual = new Stack<>();
        actual.push(5);
        actual.push(2);
        actual.pop();
        Iterator<Integer> iterator = actual.iterator();
        int a = iterator.next();
        assertEquals(a, 5);
    }

    /**
     * When NoSuchElementException thrown, then this test succeeds.
     */
    @Test
    public void NoSuchElementExceptionTest() {
        Stack<Integer> actual = new Stack<>();
        actual.push(5);
        actual.pop();
        Iterator<Integer> iterator = actual.iterator();
        try {
            int a = iterator.next();
            fail( "My method didn't throw NoSuchElementException when I expected it to" );
        } catch (NoSuchElementException expectedException) {
        }
    }

    /**
     * When EmptyStackException thrown, then this test succeeds.
     */
    @Test
    public void EmptyStackExceptionTest() {
        Stack<Integer> actual = new Stack<>();
        actual.push(5);
        actual.pop();
        try {
            actual.pop();
            fail( "My method didn't throw EmptyStackException when I expected it to" );
        } catch (EmptyStackException expectedException) {
        }
    }

    /**
     * This test succeeds, when stack is filled and cleaned correctly.
     * This test checks the code on the Double data type.
     */
    @Test
    public void OtherDataTypeTest() {
        Stack<Double> actual = new Stack<>();
        actual.push(5.0);
        actual.push(2.0);
        actual.pop();
        Iterator<Double> iterator = actual.iterator();
        double a = iterator.next();
        assertEquals(a, 5.0);
    }

    /**
     * This test succeeds, when count() method works correctly.
     */
    @Test
    public void CountMethodTest() {
        Stack<Integer> stack = new Stack<>();
        for(int i=0; i!=50; i++) {
            stack.push(2);
        }
        for(int i=0; i!=20; i++) {
            stack.pop();
        }
        int numberOfElements =  stack.count();
        assertEquals(numberOfElements, 30);
    }

    /**
     * Test in which the stack is filled and emptied several times. 
     */
    @Test
    public void MoreComplicatedOperationsTest() {
        Stack<Integer> stack = new Stack<>();
        stack.push(2);
        stack.push(4);
        stack.push(19);
        stack.push(2);
        for(int i=0; i<4; i++) {
            stack.pop();
        }
        stack.push(4);
        stack.pop();
        stack.push(6);
        stack.push(7);
        stack.push(8);
        stack.push(9);
        stack.pop();
        stack.push(9);
        stack.push(10);
        int[] expected = {10, 9, 8, 7, 6};
        Iterator<Integer> iterator = stack.iterator();
        for(int i=0; i<5; i++) {
            assertEquals(iterator.next(), expected[i]);
        }
    }
}