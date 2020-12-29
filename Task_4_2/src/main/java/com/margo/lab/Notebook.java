package com.margo.lab;

import com.google.gson.*;
import com.google.gson.annotations.Expose;
import com.google.gson.reflect.TypeToken;
import picocli.CommandLine;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Notebook class created for transactions with Notes(see the class Note above);
 * Class created to work with console.
 */
public class Notebook {

    @CommandLine.Option(names = {"-a", "--add"}, arity = "2")
    String[] newNote = {};

    @CommandLine.Option(names = "--remove")
    String removedNote = "";

    @CommandLine.Option(names = "--show", arity = "0..*")
    String[] keywords = {};

    @CommandLine.Option(names = "--path", arity = "1")
    String path = "";

    @Expose
    List<Note> notes = new ArrayList<Note>();

    /**
     * Adding new Note to the list of notes.
     *
     * @param newNote supposed to come from the console;
     *                two Strings - noteName, noteText;
     */
    public void addNote(String... newNote) {
        if (newNote[0] != null && newNote[1] != null) {
            notes.add(new Note(newNote[0], newNote[1]));
        } else throw new IllegalArgumentException("Some troubles with adding notes. It is possible that the number of transmitted arguments is not correct");
    }

    /**
     * Removing Note from the list of notes (by noteName);
     *
     * @param removedNote String value - noteName, that's supposed to be removed from the list of notes.
     */
    public void removeNote(String removedNote) {
        for (int i = 0; i < notes.size(); i++) {
            if (notes.get(i).getNoteName().equals(removedNote)) {
                notes.remove(i);
                break;
            }
        }
    }

    /**
     * Printing all notes from the current list of notes.
     */
    public void printNotes() {
        for (Note note : notes) {
            System.out.println("noteName:" + note.getNoteName() + " " + "noteText:" + note.getNoteText());
        }
    }

    /**
     * Printing all the notes selected according to this keywords from the current list of notes.
     *
     * @param keywords supposed to came from the console
     */
    public void printNotesWithKeywords(String[] keywords) {
        for (String keyword : keywords) {
            for (Note note : notes) {
                if (checkKeywords(keyword, note.getNoteName())) {
                    System.out.println("noteName:" + note.getNoteName() + "noteText:" + note.getNoteText());
                }
                if (checkKeywords(keyword, note.getNoteText())) {
                    System.out.println("noteName:" + note.getNoteName() + "noteText:" + note.getNoteText());
                }
            }
        }
    }

    /**
     * Subsidiary method for printNotesWithKeywords method(see above)
     * Dividing by words noteName and noteText and checking if they are equal to keyword
     * @param keyword one of keywords came from the console
     * @param note noteText or noteName
     * @return true if keyword == wordFromNoteString
     *          false otherwise;
     */
    private boolean checkKeywords(String keyword, String note) {
        String[] noteDivided = note.split(" ");
        for (String s : noteDivided) {
            if (s.equals(keyword)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Method for taking Notebook JSON file and converting it to list of notes, if it's not empty
     *
     * @throws IOException if the path argument transmitted not correctly and we could not create new file
     */
    public void takeNotebook() throws IOException {

        if (path.equals("")) {
            path = "..\\Task_4_2\\src\\main\\resources\\File.json";
            return;
        }

        File database = new File(path);
        database.createNewFile();

        byte[] data = new byte[(int) database.length()];
        try (FileInputStream fis = new FileInputStream(database)) {
            fis.read(data);
        }

        String json = new String(data, StandardCharsets.UTF_8);

        Gson gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .create();
        Type datasetListType = new TypeToken<Collection<Note>>() {
        }.getType();
        notes = gson.fromJson(json, datasetListType);
        if (notes == null) {
            notes = new ArrayList<>();

        }
    }

    /**
     * Method for "saving your changes" - writing new Notebook JSON file and converting it from list of notes to
     * JSON file
     *
     * @throws IOException if something wrong with file access
     */
    public void writeNotebook() throws IOException {
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        System.out.println("Your current path to Notebook json file: "+ path);
        File file = new File(path);
        FileWriter fileWriter = new FileWriter(file);
        fileWriter.write(gson.toJson(notes));
    }
}
