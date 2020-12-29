package com.margo.lab;

import com.google.gson.annotations.Expose;

import java.util.Date;

/**
 * Class for Note; consists of name of note, text of note and time - when it was created;
 */
public class Note {
    @Expose
    private String noteName;
    @Expose
    private String noteText;
    private Date time;

    /**
     * @param noteName String - brief note name
     * @param noteText String - full note
     */
    Note(String noteName, String noteText) {
        time = new Date();
        this.noteName = noteName;
        this.noteText = noteText;
    }

    public String getNoteName() {
        return noteName;
    }

    public String getNoteText() {
        return noteText;
    }

    public Date getTime() {
        return time;
    }

}
