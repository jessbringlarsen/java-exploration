package dk.bringlarsen.exploration.java.nio2;

import static org.junit.Assert.assertThat;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.hamcrest.CoreMatchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class RandomAccessExplorationTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    private Path tempFile;

    @Before
    public void setup() throws IOException {
        Path tempDir = Paths.get("target/");
        tempFile = Files.createTempFile(tempDir, UUID.randomUUID().toString(), ".tmp");
        
    }

    @After
    public void teardown() throws IOException {
        Files.deleteIfExists(tempFile);
    }

    @Test
    public void testWriteAndReadFile() throws Exception {
        try(RandomAccessFile writeFile = new RandomAccessFile(tempFile.toFile(), "rw")) {
            writeFile.writeBytes("first line");
        }

        try(RandomAccessFile readFile = new RandomAccessFile(tempFile.toFile(), "r")) {
            String firstLine = readFile.readLine();
            assertThat(firstLine, CoreMatchers.is("first line"));
        }
    }

    @Test
    public void whenFileIsOpenInReadOblyModeExpectExceptionOnWrite() throws Exception {
        expectedException.expect(IOException.class);
        expectedException.expectMessage("Bad file descriptor");

        try(RandomAccessFile writeFile = new RandomAccessFile(tempFile.toFile(), "r")) {
            writeFile.writeBytes("first line");
        }
    }
}