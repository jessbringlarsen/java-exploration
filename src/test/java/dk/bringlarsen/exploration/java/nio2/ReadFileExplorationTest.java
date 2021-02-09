package dk.bringlarsen.exploration.java.nio2;

import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ReadFileExplorationTest {

    @Test
    public void readFromResourcesDirTest() throws URISyntaxException, IOException {
        Path file = Paths.get(getClass().getClassLoader().getResource("application.properties").toURI());

        List<String> lines = Files.readAllLines(file);

        assertThat(lines.size(), is(3));
    }
}
