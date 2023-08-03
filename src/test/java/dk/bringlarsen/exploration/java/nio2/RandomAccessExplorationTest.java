package dk.bringlarsen.exploration.java.nio2;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RandomAccessExplorationTest {

    private Path tempFile;

    @BeforeEach
    void setup() throws IOException {
        Path tempDir = Paths.get("target/");
        tempFile = Files.createTempFile(tempDir, UUID.randomUUID().toString(), ".tmp");
    }

    @AfterEach
    void teardown() throws IOException {
        Files.deleteIfExists(tempFile);
    }

    @Test
    void testWriteAndReadFile() throws Exception {
        try(RandomAccessFile writeFile = new RandomAccessFile(tempFile.toFile(), "rw")) {
            writeFile.writeBytes("first line");
        }

        try(RandomAccessFile readFile = new RandomAccessFile(tempFile.toFile(), "r")) {
            String firstLine = readFile.readLine();
            assertThat(firstLine, is("first line"));
        }
    }

    @Test
    void whenFileIsOpenInReadOnlyModeExpectExceptionOnWrite() throws Exception {
        try(RandomAccessFile writeFile = new RandomAccessFile(tempFile.toFile(), "r")) {
            IOException ioException = assertThrows(IOException.class, () -> writeFile.writeBytes("first line"));
            
            assertThat(ioException.getMessage(), is("Bad file descriptor"));
        }
    }
}