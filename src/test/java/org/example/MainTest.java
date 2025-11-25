package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MainTest {

    @Test
    public void testGetMessage() {
        assertEquals("🚀 Bonjour DevOps - CI/CD avec Jenkins, Maven et Tomcat !", Main.getMessage());
    }

    @Test
    public void testGreet() {
        Main main = new Main();
        assertEquals("Bonjour Malek !", main.greet("Malek"));
    }

    @Test
    public void testMainMethod() {
        assertDoesNotThrow(() -> Main.main(new String[]{}));
    }
}