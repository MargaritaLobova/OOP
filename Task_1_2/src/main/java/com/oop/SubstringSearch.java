package com.oop;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * Class that can be used to find all the instances of the substring into the string.
 * Implementation of KMP algorithm.
 */
public class SubstringSearch {
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
    public List<Integer> kmpSearch(String sample, InputStream inputStream) throws IOException {
        List<Integer> res= new ArrayList<>(); /*name and type of returned variable changed*/
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) { /*try-catch used
         to close BufferedReader*/
            if (sample.length() == 0) {
                return res;
            }
            int[] prefixFunc = prefixFunction(sample);
            int j = 0;
            int z = 0;
            int l = 0;
            int length = sample.length() * 2;
            char[] buffer = new char[length];
            char[] subsidiaryBuffer = new char[length];
            while (bufferedReader.read(buffer) > 0) {
                int i = 0;
                while (i < buffer.length) {
                    if (sample.charAt(j) == buffer[i]) {
                        j++;
                        i++;
                    }
                    if (j == sample.length()) {
                        res.add(i - j + z * buffer.length + l * buffer.length / 2);
                        j = prefixFunc[j - 1];
                    } else if (i < buffer.length && sample.charAt(j) != buffer[i]) {
                        if (j != 0) {
                            j = prefixFunc[j - 1];
                        } else {
                            i = i + 1;
                        }
                    } else if (i >= buffer.length && bufferedReader.read(subsidiaryBuffer) > 0 && j != 0) {
                        /*buffer splits the substring*/
                        System.arraycopy(buffer, length / 2, buffer, 0, length / 2);
                        System.arraycopy(subsidiaryBuffer, 0, buffer, length / 2, length / 2);
                        i = buffer.length / 2;
                        l++;
                    }
                }
                z++;
            }
        }
        return res;
    }
}