package com.margo.lab;

import picocli.CommandLine;

import java.io.*;

public class Console {

    public static void main(String[] args) throws IOException {

        Notebook notebook = new Notebook();

        try {
            new CommandLine(notebook).parseArgs(args);
        } catch (Exception exception) {
            System.out.println("There is something wrong with your arguments!");
            return;
        }

        notebook.takeNotebook();

        notebook.addNote(notebook.newNote);

        if (notebook.keywords != null) {
            if (notebook.keywords.length == 0) {
                notebook.printNotes();
            } else if (notebook.keywords.length > 2) {
                notebook.printNotesWithKeywords(notebook.keywords);
            }
        } else throw new IllegalArgumentException("Keywords = null");

        notebook.removeNote(notebook.removedNote);

        notebook.writeNotebook();
    }
}
