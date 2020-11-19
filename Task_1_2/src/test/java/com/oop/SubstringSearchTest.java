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
    void bigTest() throws IOException {
        String s = "input2.txt";
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