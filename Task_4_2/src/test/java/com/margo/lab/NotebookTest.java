package com.margo.lab;

import org.junit.jupiter.api.Test;

import java.io.IOException;

public class NotebookTest {

    @Test
    public void fullFunctionalityTest() throws IOException {
        /* Ничего умнее "Тестово-отладочного вывода" не придумала
        Создадим Notebook и добавим туда несколько записей, удалим одну, выведем на экран,
        выведем те, в которых есть keywordы */
        Notebook notebook = new Notebook();
        notebook.takeNotebook();
        notebook.addNote("1st note", "1st Comment");
        notebook.addNote("2d note", "2d Comment");
        notebook.addNote("3d note", "3d Comment");
        notebook.removeNote("2d note");
        notebook.addNote("4th note", "4th Comment");
        System.out.println("___________________________________________");
        notebook.printNotes();
        System.out.println("___________________________________________");
        notebook.printNotesWithKeywords("1st", "2d");
        notebook.writeNotebook();
    }

    @Test
    public void secondFullFunctionalityTest() throws IOException {
        /* Теперь используем предыдущий Notebook и выведем его содержимое */
        Notebook notebook = new Notebook();
        notebook.path = "..\\Task_4_2\\src\\main\\resources\\File.json";
        notebook.takeNotebook();
        System.out.println("___________________________________________");
        notebook.printNotes();
    }

    @Test
    public void emptyArgsTest() throws IOException {
        Notebook notebook = new Notebook();
        notebook.path = "..\\Task_4_2\\src\\main\\resources\\newFile.json";
        notebook.takeNotebook();
        try {
            notebook.addNote();
        } catch (IllegalArgumentException ignored) {}
    }

    @Test
    public void emptyArgs2Test() throws IOException {
        Notebook notebook = new Notebook();
        notebook.path = "..\\Task_4_2\\src\\main\\resources\\newFile.json";
        notebook.takeNotebook();
        try {
            notebook.addNote("Hello");
        } catch (IllegalArgumentException ignored) {}
    }
}