package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import main.Program;

class Program_tests {

    @Test
    void test_normalize() {
        assertEquals("ABC", Program.normalize("abc"));
        assertEquals("ABC", Program.normalize("abc!"));
        assertEquals("ABC", Program.normalize("~AbC#"));
        assertEquals("ABC", Program.normalize("   ~AbC# "));
        assertEquals("AB-C", Program.normalize("   Ab-C@"));
    }

}
