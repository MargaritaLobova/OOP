package com.oop;
import java.io.*;
import java.util.ArrayList;

public class SubstringSearch {
    private String fileName;

    SubstringSearch (String fName) {
        fileName = fName;
    }

    private BufferedReader getReader() {
        InputStream inputStream =
                SubstringSearch.class.getClassLoader().getResourceAsStream(fileName);
        return new BufferedReader(new InputStreamReader(inputStream));
    }

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

    public ArrayList<Integer> kmpSearch(String sample) throws IOException {
        BufferedReader bufferedReader = getReader();
        ArrayList<Integer> entry = new ArrayList<>();
        int[] prefixFunc = prefixFunction(sample);
        int i = 0;
        int j = 0;
        char[] buffer = new char[1000];
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