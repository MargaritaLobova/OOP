package com.margo.lab;

import com.google.gson.Gson;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class ConsoleTest {

    @Test
    void createFileAndAddTest() throws IOException {
        Console console = new Console(new String[]{"-p", "File.json", "-a", "Notebook1", "Notebook comment", "-s"});
        console.myMain();
        /*
        Хотела ассертить так, но в JSON пишется время
        Gson gson = new Gson();
        String string = gson.toJson(console.notebook.notes);
        assertEquals(string, "[{\"noteName\":\"Notebook1\",\"noteText\":\"Notebook comment\",\"time\":\"Dec 30, 2020 1:34:36 PM\"}]");*/

    }
    @Test
    void removeFromExistingFileTest() throws IOException {
        Console console = new Console(new String[]{"-p", "..\\File.json", "--remove", "Notebook1", "-s"});
        console.myMain();
    }
    @Test
    void createNewFileTest() throws IOException {
        Console console = new Console(new String[]{"-p", "NewFile.json", "-a", "New Notebook", "New Notebook comment"});
        console.myMain();
    }
    @Test
    void createNewFileWithEmptyStringTest() throws IOException {
        Console console = new Console(new String[]{"-a", "New Notebook", "New Notebook comment"});
        console.myMain();
    }
    @Test
    void fullFunctionalityTest() throws IOException {
        Console console = new Console(new String[]{"-p", "..\\Task_4_2\\File.json", "-a", "Some new note", "Empty text"});
        console.myMain();
        System.out.println("________________________________________________________");
        console.args = new String[]{"-p", "..\\Task_4_2\\File.json", "-a", "Something", "New comment", "-s"};
        console.myMain();
        System.out.println("________________________________________________________");
        console.args = new String[]{"-p", "..\\Task_4_2\\File.json", "-rm", "Something", "-s"};
        console.myMain();
        System.out.println("________________________________________________________");
        console.args = new String[]{"-p", "..\\Task_4_2\\File.json", "-a", "New staff", "Staff comment", "-s", "Something"};
        console.myMain();
        System.out.println("________________________________________________________");
    }
    @Test
    void IOExceptionTest() {
        Console console = new Console(new String[]{"-p", "\\nobodyKnowsWhatIsThat", "-a", "Notebook1", "Notebook comment", "-s"});
        try {
            console.myMain();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}