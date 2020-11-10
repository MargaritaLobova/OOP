package com.oop;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

class SubstringSearchTest {
    @Test
    public void test1() throws IOException {
        SubstringSearch substringSearch = new SubstringSearch("test1.txt");
            String substring = "aba";
            ArrayList<Integer> actual;
            actual = substringSearch.kmpSearch(substring);
            ArrayList<Integer> expected = new ArrayList<>();
            expected.add(10);
            assertEquals(expected, actual);
    }
    @Test
    public void test2() throws IOException {
        SubstringSearch substringSearch = new SubstringSearch("test2.txt");
            String substring = "new";
            ArrayList<Integer> actual;
            actual = substringSearch.kmpSearch(substring);
            ArrayList<Integer> expected = new ArrayList<>();
            expected.add(8);
            assertEquals(expected, actual);
    }
}