package org.example;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import org.junit.jupiter.api.Test;

public class TerminalInteractionTest {

    @Test
    void shouldPromptInputNameWhenStart() throws IOException, InterruptedException {

        PrintStream originalOut = System.out;

        String simulatedInput = "q\n";
        ByteArrayInputStream in = new ByteArrayInputStream(simulatedInput.getBytes());
        System.setIn(in);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(out);
        System.setOut(printStream);

        App.main(new String[0]);

        System.setOut(originalOut);

        String expectedOutput = "Enter your name";
        assertTrue(out.toString().contains(expectedOutput));
    }

    @Test
    void shouldDisplayNameAfterInput() throws IOException, InterruptedException {

        PrintStream originalOut = System.out;

        String simulatedInput = "Bob\nq\n";
        ByteArrayInputStream in = new ByteArrayInputStream(simulatedInput.getBytes());
        System.setIn(in);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(out);
        System.setOut(printStream);

        App.main(new String[0]);

        System.setOut(originalOut);

        String expectedOutput = "Bob";
        assertTrue(out.toString().contains(expectedOutput));
    }
}
