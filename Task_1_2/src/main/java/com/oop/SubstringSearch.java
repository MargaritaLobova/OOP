package com.oop;
import com.sun.org.apache.xerces.internal.util.SynchronizedSymbolTable;
import org.junit.jupiter.params.provider.EmptySource;

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
        if(sample.length()==0) {
            return entry;
        }
        int[] prefixFunc = prefixFunction(sample);
        int j = 0;
        int z=0;
        int l=0;
        int length = sample.length()*2;
        char[] buffer = new char[length];
        char[] helpbuffer = new char[length];
        while (bufferedReader.read(buffer) > 0) {
            int i = 0;
            while (i < buffer.length) {
                if (sample.charAt(j) == buffer[i]) {
                    j++;
                    i++;
                }
                if (j == sample.length()) {
                    entry.add(i - j +z* buffer.length + l* buffer.length/2);
                    j = prefixFunc[j - 1];
                } else if (i < buffer.length && sample.charAt(j) != buffer[i]) {
                    if (j != 0) {
                        j = prefixFunc[j - 1];
                    } else {
                        i = i + 1;
                    }
                } else if(i >= buffer.length && bufferedReader.read(helpbuffer)>0 && j!=0 && j!=sample.length()) {
                    /*buffer splits the substring*/
                    for(int k=0; k<length/2; k++) {
                        buffer[k] = buffer[k+length/2];
                    }
                    for(int k=0; k<length/2; k++) {
                        buffer[length/2+k] = helpbuffer[k];
                    }
                    i = buffer.length/2;
                     l++;
                }
            }
            z++;
        }
        return entry;
    }
}