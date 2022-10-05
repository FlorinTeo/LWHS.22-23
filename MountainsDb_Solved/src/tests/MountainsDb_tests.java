package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

import main.MountainsDb;

class MountainsDb_tests {

    @AfterAll
    static void tearDownAfterClass() throws Exception {
        // general cleanup after tests were all run: delete temporary files, if any
        Path cleanPath = Paths.get("src/tests/test_clean.tsv");
        if (Files.exists(cleanPath)) {
            Files.delete(cleanPath);
        }
        
        Path errPath = Paths.get("src/tests/test_err.tsv");
        if (Files.exists(errPath)) {
            Files.delete(errPath);
        }
    }

    @Test
    void test_MountainsDb() throws IOException {
        MountainsDb testDb = new MountainsDb("src/tests/test_db.tsv");
        testDb.clean();
        String expected = "test_db.tsv: 159 bytes\n"
                + "test_clean.tsv: 104 bytes\n"
                + "Count of valid records: 1\n"
                + "Count of corrupted records: 1\n"
                + "Tallest mountain: [Aruba][Mountain][Jamanota][12.4899966,-69.9399918][188 meters]\n"
                + "Shortest mountain: [Aruba][Mountain][Jamanota][12.4899966,-69.9399918][188 meters]\n";
        assertEquals(expected, testDb.toString());
        File cleanFile = new File("src/tests/test_clean.tsv");
        assertTrue(cleanFile.exists());
        File errFile = new File("src/tests/test_err.tsv");
        assertTrue(errFile.exists());
    }
}
