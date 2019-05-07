package dk.bringlarsen.exploration.java.nio2;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileAttribute;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;
import java.util.Set;
import java.util.UUID;

import static java.nio.file.Files.*;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CreateFileExplorationTest {

    private Path tempFile;

    @Before
    public void createFile() throws IOException {
        Path tempDir = Paths.get("target/");
        tempFile = Files.createTempFile(tempDir, UUID.randomUUID().toString(), ".tmp");
    }

    @After
    public void teardown() throws IOException {
        Files.deleteIfExists(tempFile);
    }

    @Test
    public void createFileTest() {
        assertTrue(exists(tempFile));
        assertTrue(isWritable(tempFile));
        assertTrue(isReadable(tempFile));
    }

    @Test
    @Ignore("Only on posix systems")
    public void createFileWithAttributesTest() throws IOException {
        Set<PosixFilePermission> filePermissions = PosixFilePermissions.fromString("r--r--r--");
        FileAttribute<Set<PosixFilePermission>> fileAttribute = PosixFilePermissions.asFileAttribute(filePermissions);

        Path tempDir = Paths.get(System.getenv("TEMP"));
        tempFile = Files.createTempFile(tempDir, UUID.randomUUID().toString(), ".tmp", fileAttribute);

        assertFalse(isWritable(tempFile));
        assertTrue(isReadable(tempFile));
    }
}