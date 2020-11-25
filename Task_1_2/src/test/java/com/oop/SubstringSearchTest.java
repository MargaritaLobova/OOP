package com.oop;

import com.sun.org.apache.xerces.internal.impl.io.UTF8Reader;
import org.junit.jupiter.api.Test;
import java.io.*;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class SubstringSearchTest {
    @Test
    public void easyTest() throws IOException {
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        try (InputStream is = classloader.getResourceAsStream("input1.txt")) {
            SubstringSearch substringSearch = new SubstringSearch();
            String s = "okay";
            List<Integer> res = substringSearch.kmpSearch(s, is);
            ArrayList<Integer> correct = new ArrayList<>();
            correct.add(7);
            assertEquals(correct, res);
        }
    }
    @Test
    void emptyFileTest() throws IOException {
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        try (InputStream is = classloader.getResourceAsStream("input2.txt")) {
            SubstringSearch substringSearch = new SubstringSearch();
            String s = "okay";
            List<Integer> res = substringSearch.kmpSearch(s, is);
            ArrayList<Integer> correct = new ArrayList<>();
            assertEquals(correct, res);
        }
    }
    @Test
    void noSubstringTest() throws IOException {
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        try (InputStream is = classloader.getResourceAsStream("input3.txt")) {
            SubstringSearch substringSearch = new SubstringSearch();
            String s = "";
            List<Integer> res = substringSearch.kmpSearch(s, is);
            ArrayList<Integer> correct = new ArrayList<>();
            assertEquals(correct, res);
        }
    }
    @Test
    void smallBufferTest() throws IOException {
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        try (InputStream is = classloader.getResourceAsStream("input4.txt")) {
            SubstringSearch substringSearch = new SubstringSearch();
            String s = "bb";
            List<Integer> res = substringSearch.kmpSearch(s, is);
            ArrayList<Integer> correct = new ArrayList<>();
            correct.add(3);
            assertEquals(correct, res);
        }

    }
    @Test
    void smallTest() throws IOException {
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        try (InputStream is = classloader.getResourceAsStream("input5.txt")) {
            SubstringSearch substringSearch = new SubstringSearch();
            String s = "hey";
            List<Integer> actual = substringSearch.kmpSearch(s, is);
            List<Integer> expected = new ArrayList<>();
            expected.add(0);
            expected.add(33);
            expected.add(66);
            assertEquals(expected, actual);
        }
    }
    @Test
    void utfTest() throws IOException {
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        try (InputStream is = classloader.getResourceAsStream("input6.txt")) {
            SubstringSearch substringSearch = new SubstringSearch();
            String s = "привет";
            byte[] v = s.getBytes(StandardCharsets.UTF_8);
            s = new String(v, StandardCharsets.UTF_8);
            List<Integer> actual = substringSearch.kmpSearch(s, is);
            List<Integer> expected = new ArrayList<>();
            expected.add(17);
            assertEquals(expected, actual);
        }
    }
   @Test/*big test file size increased up to 20 Gb*/
    void bigTest() throws IOException {
        String s = "bigInput.txt";
        SubstringSearch substringSearch = new SubstringSearch();
        FileWriter myWriter = new FileWriter(s);
        Integer[] expected = new Integer[300];
        try {
            for (int i = 0; i < 300; i++){
                myWriter.write("heyrandomtextrandomtextrandomtext");
                expected[i] = i*33;
            }
            for (int i = 0; i < 400000000; i++) {
                myWriter.write("wordwordwordwordwordwordwordwordwordwordwordwordwordword");
            }
            myWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        InputStream inputStream = new FileInputStream(s);
        String sub = "hey";
        List<Integer> actual = substringSearch.kmpSearch(sub,inputStream);
        assertArrayEquals(expected, actual.toArray());
    }
}