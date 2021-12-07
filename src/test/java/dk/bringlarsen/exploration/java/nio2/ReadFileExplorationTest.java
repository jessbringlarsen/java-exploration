package dk.bringlarsen.exploration.java.nio2;

import dk.bringlarsen.exploration.java.JDK;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ReadFileExplorationTest {

    @Test
    void readFromResourcesDirTest() throws URISyntaxException, IOException {
        Path file = Paths.get(getClass().getClassLoader().getResource("application.properties").toURI());

        List<String> lines = Files.readAllLines(file);

        assertThat(lines.size(), is(3));
    }


    @Test
    @JDK(version = 12, description = "Files#mismatch")
    public void findMismatchBetweenFiles() throws IOException {
        Path file1 = createFileWithContent("123");
        Path file2 = createFileWithContent("12");

        long mismatch = Files.mismatch(file1, file2);

        assertEquals(2, mismatch);
    }

    private Path createFileWithContent(String content) throws IOException {
        Path path = Files.createTempFile(UUID.randomUUID().toString(), ".txt");
        Files.writeString(path, content);
        return path;
    }
}
