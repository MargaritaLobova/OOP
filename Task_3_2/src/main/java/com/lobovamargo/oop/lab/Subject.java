package com.lobovamargo.oop.lab;

public class Subject {
    public Subject (Discipline discipline, boolean isLast) {
        this.discipline = discipline;
        this.isLast = isLast;
    }
    public Discipline discipline;
    public int mark;
    public boolean isLast;

    void addMark(int mark) {
        this.mark = mark;
    }
}

