package com.lobovamargo.oop.lab;

enum Discipline {
    INTRODUCTION_TO_ALGEBRA_AND_MATHEMATICAL_ANALYSIS,
    INTRODUCTION_TO_DISCRETE_MATHS_AND_MATHEMATICAL_LOGIC,
    DECLARATIVE_PROGRAMMING,
    IMPERATIVE_PROGRAMMING,
    FOREIGN_LANGUAGE,
    FUNDAMENTALS_OF_SPEECH_CULTURE,
    HISTORY,
    PHYSICAL_CULTURE,
    COMPUTING_PLATFORMS,
    INTRODUCTION_TO_ARTIFICIAL_INTELLIGENCE,
    DIFFERENTIAL_EQUATIONS_AND_COMPLEX_ANALYSES,
    COMPUTATIONAL_MODELS,
    OBJECT_ORIENTED_PROGRAMMING,
    PROBABILITY_THEORY_AND_MATHEMATICAL_STATISTICS,
    OPERATING_SYSTEMS,
    GROUP_PROJECT,
    INTRODUCTION_TO_ANALOG_ELECTRONICS_AND_MEASUREMENT_TECHNIQUES,
    INTRODUCTION_TO_COMPUTER_NETWORKS,
    BUSINESS_ENGLISH,
    PARALLELISM_THEORY,
    PROGRAMMABLE_MICROCONTROLLERS,
    PHILOSOPHY
}

public class GradeBook {

    public GradeBook(String studentName) {
        this.studentName = studentName;
    }

    public String studentName;
    private int diplomaMark;
    private int currentTerm = 1;
    private static final int numberOfSubjects = 22;
    private Term term1 = new Term(1);
    private Term term2 = new Term(2);
    private Term term3 = new Term(3);
    private Term term4 = new Term(4);

    /*private Term term5 = new Term(5);
     private Term term6 = new Term(6);
     private Term term7 = new Term(7);
     private Term term8 = new Term(8);*/

    private Term takeTerm(int termNumber) {
        switch (termNumber) {
            case 1:
                return term1;
            case 2:
                return term2;
            case 3:
                return term3;
            case 4:
                return term4;
            /*case 5:
                return term5;
            case 6:
                return term6;
            case 7:
                return term7;
            case 8:
                return term8;*/
            default:
                throw new IllegalArgumentException("Incorrect lastTerm value!");
        }
    }

    public int getDiplomaMark() {
        return diplomaMark;
    }

    public void fillCurrentTerm(int[] marks) {
        if (currentTerm < 5) {
            takeTerm(currentTerm).addMarks(marks);
            currentTerm++;
        } else {
            throw new IllegalArgumentException("All terms were filled!");
        }
    }

    public double averageScore() {
        double averageScore;
        double sum = 0;
        double numberOfSubjects = 0;
        Term temp;
        for (int i = 1; i <= currentTerm-1; i++) {
            temp = takeTerm(i);
            for (int j = 0; j < temp.subjectList.size(); j++) {
                numberOfSubjects++;
                sum += temp.subjectList.get(j).mark;
            }
        }
        averageScore = sum / numberOfSubjects;
        return averageScore;
    }


    public boolean canTakeDiplomaWithHonors() {
    /*Требования для диплома с отличием:
      75% оценок в приложении к диплому(последняя оценка) – «отлично»
      Нет в зачетной книжке итоговых оценок «удовлетворительно»
      Квалификационная работа защищена на «отлично»
     */
        if (diplomaMark != 5)
            return false;
        Term temp;
        int numberOfExcelentMarks = 0;
        for (int i = 1; i < currentTerm; i++) {
            temp = takeTerm(i);
            for (int j = 0; j < temp.subjectList.size(); j++) {
                if (temp.subjectList.get(j).mark <= 3) {
                    return false;
                } else if (temp.subjectList.get(j).mark == 5 && temp.subjectList.get(j).isLast) {
                    numberOfExcelentMarks++;
                }
            }
        }
        return numberOfSubjects * 75 / 100 <= numberOfExcelentMarks;
    }

    public boolean willBeMeritScholarship() {
        Term temp = takeTerm(currentTerm-1);
        for (int i = 0; i < temp.subjectList.size(); i++) {
            if (temp.subjectList.get(i).mark != 5) {
                return false;
            }
        }
        return true;
    }

    public void addDiplomaMark(int diplomaMark) {
        if (currentTerm < 5) {
            throw new IllegalArgumentException("Fill up all the terms");
        }
        if (diplomaMark < 2 || diplomaMark > 5) {
            throw new IllegalArgumentException("Diploma mark should be from 2 to 5");
        }
        this.diplomaMark = diplomaMark;
    }
}
