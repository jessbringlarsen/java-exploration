package dk.bringlarsen.exploration.java.nio2;



import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.FileTime;
import java.time.Instant;
import java.util.Date;
import java.util.UUID;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * FileAttributeView enable querying file attibutes.
 */
class FileAttributeViewExplorationTest {

    private Path tempFile;

    @BeforeEach
    void createFile() throws IOException {
        Path tempDir = Paths.get("target/");
        tempFile = Files.createTempFile(tempDir, UUID.randomUUID().toString(), ".tmp");
        Files.write(tempFile, "test".getBytes(), StandardOpenOption.APPEND);
    }

    @AfterEach
    void teardown() throws IOException {
        Files.deleteIfExists(tempFile);
    }

    @Test
    void readFileAttributes() throws IOException {
        BasicFileAttributeView fileAttributeView = Files.getFileAttributeView(tempFile, BasicFileAttributeView.class);

        assertThat(fileAttributeView.readAttributes().isRegularFile(), is(true));
        assertThat(fileAttributeView.readAttributes().isDirectory(), is(false));
        assertThat(fileAttributeView.readAttributes().size(), is(4L));
    }

    @Test
    void setFileAttributes() throws IOException {
        Instant now = new Date().toInstant();

        Files.setLastModifiedTime(tempFile, FileTime.from(now));

        BasicFileAttributeView fileAttributeView = Files.getFileAttributeView(tempFile, BasicFileAttributeView.class);
        assertThat(fileAttributeView.readAttributes().lastModifiedTime(), is(FileTime.from(now)));
    }
}