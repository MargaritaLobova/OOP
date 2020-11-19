package com.oop;
import java.io.*;
import java.util.ArrayList;

/**
 * Class that can be used to find all the instances of the substring into the string.
 * Implementation of KMP algorithm.
 */
public class SubstringSearch {
    private String fileName;
    SubstringSearch (String fName) {
        fileName = fName;
    }

    /**
     * @param sample substring
     * @return the array of prefix-function values
     * This is a subsidiary method for kmpSearch method.
     */
    private int[] prefixFunction(String sample) {
        int [] values = new int[sample.length()];
        for (int i = 1; i < sample.length(); i++) {
            int j = 0;
            while (i + j < sample.length() && sample.charAt(j) == sample.charAt(i + j)) {
                values[i + j] = Math.max(values[i + j], j + 1);
                j++;
            }
        }
        return values;
    }

    /**
     * @param sample Substring
     * @param inputStream from which input stream bufferedReader is going to read
     * @return ArrayList<Integer> of indexes of all the instances of the substring into the text(inputStream).
     * @throws IOException if bufferedReader throws IOException
     */
    public ArrayList<Integer> kmpSearch(String sample, InputStream inputStream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        ArrayList<Integer> entry = new ArrayList<>();
        int[] prefixFunc = prefixFunction(sample);
        int i = 0;
        int j = 0;
        char[] buffer = new char[100000000];
        while (bufferedReader.read(buffer) > 0) {
            while (i < buffer.length) {
                if (sample.charAt(j) == buffer[i]) {
                    j++;
                    i++;
                }
                if (j == sample.length()) {
                    entry.add(i - j);
                    j = prefixFunc[j - 1];
                } else if (i < buffer.length && sample.charAt(j) != buffer[i]) {
                    if (j != 0) {
                        j = prefixFunc[j - 1];
                    } else {
                        i = i + 1;
                    }
                }
            }
        }
        return entry;
    }
}