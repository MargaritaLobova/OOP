package com.lobovamargo.oop.lab;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GradeBookTest {

    // -- PARTICULAR TESTS -- //

    @Test
    void fillCurrentTerm() {
        GradeBook gradeBook = new GradeBook("Linda Parenson");
        int[] marksTerm1 = {5, 4, 4, 5, 5, 5, 5, 5, 5};
        int[] marksTerm2 = {5, 4, 4, 2, 5, 5};
        gradeBook.fillCurrentTerm(marksTerm1);
        gradeBook.fillCurrentTerm(marksTerm2);
    }

    @Test
    void averageScore() {
        GradeBook gradeBook = new GradeBook("Kate Milton");
        int[] marksTerm1 = {5, 4, 4, 5, 5, 5, 5, 5, 5};
        int[] marksTerm2 = {5, 4, 4, 2, 5, 5};
        gradeBook.fillCurrentTerm(marksTerm1);
        gradeBook.fillCurrentTerm(marksTerm2);
        double expected = 4.533333333333333;
        assertEquals(expected, gradeBook.averageScore());
    }

    @Test
    void canTakeDiplomaWithHonors() {
        GradeBook gradeBook = new GradeBook("Kate Milton");
        int[] marksTerm1 = {5, 4, 4, 5, 5, 5, 5, 5, 5};
        int[] marksTerm2 = {5, 4, 4, 4, 5, 5};
        int[] marksTerm3 = {5, 4, 4, 5, 5, 5, 5, 5, 5};
        int[] marksTerm4 = {5, 4, 4, 5, 5, 5, 5, 5, 5, 4};
        gradeBook.fillCurrentTerm(marksTerm1);
        gradeBook.fillCurrentTerm(marksTerm2);
        gradeBook.fillCurrentTerm(marksTerm3);
        gradeBook.fillCurrentTerm(marksTerm4);
        gradeBook.addDiplomaMark(5);
        assertFalse(gradeBook.canTakeDiplomaWithHonors());
        GradeBook maryGradeBook = new GradeBook("Mary Wilson");
        int[] maryMarksTerm1 = {5, 5, 5, 5, 5, 5, 5, 5, 5};
        int[] maryMarksTerm2 = {5, 5, 5, 5, 5, 5};
        int[] maryMarksTerm3 = {5, 5, 5, 5, 5, 5, 5, 5, 5};
        int[] maryMarksTerm4 = {5, 5, 5, 5, 5, 5, 5, 5, 5, 5};
        maryGradeBook.fillCurrentTerm(maryMarksTerm1);
        maryGradeBook.fillCurrentTerm(maryMarksTerm2);
        maryGradeBook.fillCurrentTerm(maryMarksTerm3);
        maryGradeBook.fillCurrentTerm(maryMarksTerm4);
        maryGradeBook.addDiplomaMark(5);
        assertTrue(maryGradeBook.canTakeDiplomaWithHonors());

    }

    @Test
    void willBeMeritScholarship() {
        GradeBook gradeBook = new GradeBook("Kate Milton");
        int[] marksTerm1 = {5, 4, 4, 5, 5, 5, 5, 5, 5};
        int[] marksTerm2 = {5, 4, 4, 4, 5, 5};
        gradeBook.fillCurrentTerm(marksTerm1);
        gradeBook.fillCurrentTerm(marksTerm2);
        assertFalse(gradeBook.willBeMeritScholarship());
        int[] marksTerm3 = {5, 5, 5, 5, 5, 5, 5, 5, 5};
        gradeBook.fillCurrentTerm(marksTerm3);
        assertTrue(gradeBook.willBeMeritScholarship());
    }

    @Test
    void addDiplomaMark() {
        GradeBook gradeBook = new GradeBook("Jack Johnson");
        int[] marksTerm1 = {5, 4, 4, 5, 5, 5, 5, 5, 5};
        int[] marksTerm2 = {5, 4, 4, 4, 5, 5};
        int[] marksTerm3 = {5, 5, 5, 5, 5, 5, 5, 5, 5};
        int[] marksTerm4 = {5, 4, 5, 5, 5, 5, 5, 5, 5, 4};
        gradeBook.fillCurrentTerm(marksTerm1);
        gradeBook.fillCurrentTerm(marksTerm2);
        gradeBook.fillCurrentTerm(marksTerm3);
        gradeBook.fillCurrentTerm(marksTerm4);
        gradeBook.addDiplomaMark(5);
        assertEquals(5, gradeBook.getDiplomaMark());
    }

    // -- EXCEPTIONS TESTS -- //

    @Test
    void fillCurrentTermIllegalArgumentTest() {
        GradeBook gradeBook = new GradeBook("Jack Johnson");
        int[] marksTerm1 = {5, 4, 4, -1, 5, 5, 5, 5, 5};
        try {
            gradeBook.fillCurrentTerm(marksTerm1);
        } catch (IllegalArgumentException e) {
            assertEquals("All terms were filled!", e.getMessage());
        }
    }

    @Test
    void addDiplomaMarkIllegalArgumentTest1() {
        GradeBook gradeBook = new GradeBook("Martha Mitchell");
        int[] marksTerm1 = {5, 4, 4, 3, 5, 5, 5, 5, 5};
        gradeBook.fillCurrentTerm(marksTerm1);
        try {
            gradeBook.addDiplomaMark(5);
        }catch (IllegalArgumentException e) {
            assertEquals("Fill up all the terms", e.getMessage());
        }
    }

    @Test
    void addDiplomaMarkIllegalArgumentTest2() {
        GradeBook gradeBook = new GradeBook("Martha Mitchell");
        int[] marksTerm1 = {5, 4, 4, 3, 5, 5, 5, 5, 5};
        int[] marksTerm2 = {5, 4, 4, 3, 5, 5};
        int[] marksTerm3 = {5, 4, 4, 3, 5, 5, 5, 5, 5};
        int[] marksTerm4 = {5, 4, 4, 3, 5, 5, 5, 5, 5, 3};
        gradeBook.fillCurrentTerm(marksTerm1);
        gradeBook.fillCurrentTerm(marksTerm2);
        gradeBook.fillCurrentTerm(marksTerm3);
        gradeBook.fillCurrentTerm(marksTerm4);
        try {
            gradeBook.addDiplomaMark(1);
        }catch (IllegalArgumentException e) {
            assertEquals("Diploma mark should be from 2 to 5", e.getMessage());
        }
    }

    // -- GENERAL TESTS -- //

    @Test
    void myMarksTest() {
        GradeBook myGradeBook = new GradeBook("Margarita Lobova");
        int[] myMarksTerm1 = {5, 4, 4, 4, 5, 5, 5, 5, 5};
        int[] myMarksTerm2 = {4, 4, 5, 3, 5, 5};
        myGradeBook.fillCurrentTerm(myMarksTerm1);
        myGradeBook.fillCurrentTerm(myMarksTerm2);
        assertEquals(4.533333333333333, myGradeBook.averageScore());
        int[] myMarksTerm3 = {5, 4, 4, 3, 5, 5, 5, 5, 5};
        int[] myMarksTerm4 = {5, 4, 4, 3, 5, 5, 5, 5, 5, 3};
        myGradeBook.fillCurrentTerm(myMarksTerm3);
        myGradeBook.fillCurrentTerm(myMarksTerm4);
        assertFalse(myGradeBook.willBeMeritScholarship());
        myGradeBook.addDiplomaMark(4);
        assertFalse(myGradeBook.canTakeDiplomaWithHonors());
    }
}