package com.oop;

import org.junit.jupiter.api.Test;
import java.io.*;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class SubstringSearchTest {
    @Test
    public void easyTest() throws IOException {
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        try (InputStream is = classloader.getResourceAsStream("input1.txt")) {
            SubstringSearch substringSearch = new SubstringSearch("input1.txt");
            String s = "okay";
            ArrayList<Integer> res = substringSearch.kmpSearch(s, is);
            ArrayList<Integer> correct = new ArrayList<>();
            correct.add(7);
            assertEquals(correct, res);
        }
    }
    @Test
    void emptyFileTest() throws IOException {
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        try (InputStream is = classloader.getResourceAsStream("input2.txt")) {
            SubstringSearch substringSearch = new SubstringSearch("input2.txt");
            String s = "okay";
            ArrayList<Integer> res = substringSearch.kmpSearch(s, is);
            ArrayList<Integer> correct = new ArrayList<>();
            assertEquals(correct, res);
        }
    }
    @Test
    void noSubstringTest() throws IOException {
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        try (InputStream is = classloader.getResourceAsStream("input3.txt")) {
            SubstringSearch substringSearch = new SubstringSearch("input3.txt");
            String s = "";
            ArrayList<Integer> res = substringSearch.kmpSearch(s, is);
            ArrayList<Integer> correct = new ArrayList<>();
            assertEquals(correct, res);
        }
    }
    @Test
    void smallBufferTest() throws IOException {
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        try (InputStream is = classloader.getResourceAsStream("input4.txt")) {
            SubstringSearch substringSearch = new SubstringSearch("input4.txt");
            String s = "bb";
            ArrayList<Integer> res = substringSearch.kmpSearch(s, is);
            ArrayList<Integer> correct = new ArrayList<>();
            correct.add(3);
            assertEquals(correct, res);
        }

    }
    @Test
    void smallTest() throws IOException {
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        try (InputStream is = classloader.getResourceAsStream("input5.txt")) {
            SubstringSearch substringSearch = new SubstringSearch("input5.txt");
            String s = "hey";
            ArrayList<Integer> actual = substringSearch.kmpSearch(s, is);
            ArrayList<Integer> expected = new ArrayList<>();
            expected.add(0);
            expected.add(33);
            expected.add(66);
            assertEquals(expected, actual);
        }
    }
   @Test
    void bigTest() throws IOException {
        String s = "bigInput.txt";
        SubstringSearch substringSearch = new SubstringSearch(s);
        FileWriter myWriter = new FileWriter(s);
        Integer[] expected = new Integer[300];
        try {
            for (int i = 0; i < 300; i++){
                myWriter.write("heyrandomtextrandomtextrandomtext");
                expected[i] = i*33;
            }
            for (int i = 0; i < 160000000; i++) {
                myWriter.write("wordwordwordwordwordwordwordwordwordwordwordwordwordword");
            }
            myWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        InputStream inputStream = new FileInputStream(s);
        String sub = "hey";
        ArrayList<Integer> actual = substringSearch.kmpSearch(sub,inputStream);
        assertArrayEquals(expected, actual.toArray());
    }
}