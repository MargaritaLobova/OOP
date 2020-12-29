package com.margo.lab;

import org.junit.jupiter.api.Test;

public class NotebookTest {
    Notebook notebook = new Notebook();

    @Test
    public void testTime() {
        String[] newNotes = {"newNotes", "comment"};
        notebook.addNote(newNotes);
        newNotes = new String[]{"Ok", "small text"};
        notebook.addNote(newNotes);
        newNotes = new String[]{"One more", " text"};
        notebook.addNote(newNotes);
        for (int i = 0; i < notebook.notes.size(); i++) {
            System.out.println(notebook.notes.get(i).getTime());
        }
    }

    @Test
    public void testAddNotes() {
        String[] newNotes = {"newNotes", "comment"};
        notebook.addNote(newNotes);
        System.out.println(notebook.notes.get(0).getNoteName());
    }

    @Test
    public void testRemoveNotes() {
        String[] newNotes = {"newNotes", "Ok"};
        notebook.addNote(newNotes);
        System.out.println(notebook.notes.get(0).getNoteName());
        notebook.removeNote("Ok");
        System.out.println(notebook.notes.get(0).getNoteName());
    }

}