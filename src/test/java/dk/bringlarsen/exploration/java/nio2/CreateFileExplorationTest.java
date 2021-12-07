package dk.bringlarsen.exploration.java.nio2;

import dk.bringlarsen.exploration.java.JDK;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledOnOs;

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
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.condition.OS.WINDOWS;

@JDK(version = 7, description = "Java NIO 2")
class CreateFileExplorationTest {

    private Path tempFile;

    @BeforeEach
    void createFile() throws IOException {
        Path tempDir = Paths.get("target/");
        tempFile = Files.createTempFile(tempDir, UUID.randomUUID().toString(), ".tmp");
    }

    @AfterEach
    void teardown() throws IOException {
        Files.deleteIfExists(tempFile);
    }

    @Test
    void createFileTest() {
        assertTrue(exists(tempFile));
        assertTrue(isWritable(tempFile));
        assertTrue(isReadable(tempFile));
    }

    @Test
    @DisabledOnOs(WINDOWS)
    void createFileWithAttributesTest() throws IOException {
        Set<PosixFilePermission> filePermissions = PosixFilePermissions.fromString("r--r--r--");
        FileAttribute<Set<PosixFilePermission>> fileAttribute = PosixFilePermissions.asFileAttribute(filePermissions);

        Path tempDir = Paths.get(System.getenv("TEMP"));
        tempFile = Files.createTempFile(tempDir, UUID.randomUUID().toString(), ".tmp", fileAttribute);

        assertFalse(isWritable(tempFile));
        assertTrue(isReadable(tempFile));
    }
}