package org.example;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TerminalInteractionTest {

    private PrintStream originalOut;
    private ByteArrayOutputStream out;

    @BeforeEach
    public void setUp() {
        originalOut = System.out;

        String simulatedInput = "Bob\nq\n";
        ByteArrayInputStream in = new ByteArrayInputStream(simulatedInput.getBytes());
        System.setIn(in);

        out = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(out);
        System.setOut(printStream);
    }

    @AfterEach
    public void tearDown() {
        System.setOut(originalOut);
        new File("game_data_test.txt").delete();
    }

    @Test
    void shouldPromptInputNameWhenStart() throws IOException, InterruptedException {
        App.main(new String[] {"game_data_test.txt"});

        String expectedOutput = "Enter your name";
        assertTrue(out.toString().contains(expectedOutput));
    }

    @Test
    void shouldDisplayNameAfterInput() throws IOException, InterruptedException {
        App.main(new String[] {"game_data_test.txt"});

        String expectedOutput = "Bob";
        assertTrue(out.toString().contains(expectedOutput));
    }

    @Test
    void shouldDisplayMapAfterInputName() throws IOException, InterruptedException {
        App.main(new String[] {"game_data_test.txt"});

        assertTrue(out.toString().contains("#"));
        assertTrue(out.toString().contains("."));
        assertTrue(out.toString().contains(" "));
    }
}
