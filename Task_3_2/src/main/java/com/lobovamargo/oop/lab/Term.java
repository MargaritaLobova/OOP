package com.lobovamargo.oop.lab;

import java.util.ArrayList;
import java.util.List;

public class Term {

    public List<Subject> subjectList = new ArrayList<>();

    Term(int termNumber) {
        switch (termNumber) {
            case 1:
                subjectList.add(new Subject(Discipline.COMPUTING_PLATFORMS, false));
                subjectList.add(new Subject(Discipline.IMPERATIVE_PROGRAMMING, false));
                subjectList.add(new Subject(Discipline.DECLARATIVE_PROGRAMMING, false));
                subjectList.add(new Subject(Discipline.INTRODUCTION_TO_ALGEBRA_AND_MATHEMATICAL_ANALYSIS, false));
                subjectList.add(new Subject(Discipline.INTRODUCTION_TO_DISCRETE_MATHS_AND_MATHEMATICAL_LOGIC, true));
                subjectList.add(new Subject(Discipline.FOREIGN_LANGUAGE, false));
                subjectList.add(new Subject(Discipline.HISTORY, true));
                subjectList.add(new Subject(Discipline.FUNDAMENTALS_OF_SPEECH_CULTURE, true));
                subjectList.add(new Subject(Discipline.PHYSICAL_CULTURE, false));
                break;
            case 2:
                subjectList.add(new Subject(Discipline.COMPUTING_PLATFORMS, true));
                subjectList.add(new Subject(Discipline.IMPERATIVE_PROGRAMMING, true));
                subjectList.add(new Subject(Discipline.DECLARATIVE_PROGRAMMING, true));
                subjectList.add(new Subject(Discipline.INTRODUCTION_TO_ALGEBRA_AND_MATHEMATICAL_ANALYSIS, true));
                subjectList.add(new Subject(Discipline.FOREIGN_LANGUAGE, false));
                subjectList.add(new Subject(Discipline.PHYSICAL_CULTURE, false));
                break;
            case 3:
                subjectList.add(new Subject(Discipline.INTRODUCTION_TO_ARTIFICIAL_INTELLIGENCE, true));
                subjectList.add(new Subject(Discipline.DIFFERENTIAL_EQUATIONS_AND_COMPLEX_ANALYSES, true));
                subjectList.add(new Subject(Discipline.FOREIGN_LANGUAGE, true));
                subjectList.add(new Subject(Discipline.PHYSICAL_CULTURE, false));
                subjectList.add(new Subject(Discipline.COMPUTATIONAL_MODELS, false));
                subjectList.add(new Subject(Discipline.OBJECT_ORIENTED_PROGRAMMING, false));
                subjectList.add(new Subject(Discipline.PROBABILITY_THEORY_AND_MATHEMATICAL_STATISTICS, true));
                subjectList.add(new Subject(Discipline.OPERATING_SYSTEMS, true));
                subjectList.add(new Subject(Discipline.GROUP_PROJECT, false));
                break;
            case 4:
                subjectList.add(new Subject(Discipline.BUSINESS_ENGLISH, true));
                subjectList.add(new Subject(Discipline.PHYSICAL_CULTURE, true));
                subjectList.add(new Subject(Discipline.COMPUTATIONAL_MODELS, true));
                subjectList.add(new Subject(Discipline.OBJECT_ORIENTED_PROGRAMMING, true));
                subjectList.add(new Subject(Discipline.GROUP_PROJECT, true));
                subjectList.add(new Subject(Discipline.PHILOSOPHY, true));
                subjectList.add(new Subject(Discipline.INTRODUCTION_TO_ANALOG_ELECTRONICS_AND_MEASUREMENT_TECHNIQUES, true));
                subjectList.add(new Subject(Discipline.INTRODUCTION_TO_COMPUTER_NETWORKS, true));
                subjectList.add(new Subject(Discipline.PARALLELISM_THEORY, true));
                subjectList.add(new Subject(Discipline.PROGRAMMABLE_MICROCONTROLLERS, true));
                break;
        }
    }

    public void addMarks(int[] marks) {
        for (int i = 0; i < subjectList.size(); i++) {
            subjectList.get(i).addMark(marks[i]);
        }
    }
}
