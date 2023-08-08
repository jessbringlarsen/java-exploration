package dk.bringlarsen.exploration.java.httpserver;

import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.SimpleFileServer;
import dk.bringlarsen.exploration.java.JDK;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;

import java.net.InetSocketAddress;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Path;

import static org.hamcrest.MatcherAssert.assertThat;

/**
 * The "Simple Web Server" can be started on the commandline be invoking <i>jwebserver</i> and it will
 * serve up the current directory, but is can also be started programmatically as shown here.
 */
@JDK(version = 18)
class HttpServerTest {

    @Test
    void simpleFileServerTest() throws Exception {
        var server = createServer();
        try {
            server.start();

            HttpRequest request = HttpRequest.newBuilder(URI.create("http://localhost:8001/")).GET().build();
            HttpResponse<String> response = HttpClient.newHttpClient()
                    .send(request, HttpResponse.BodyHandlers.ofString());

            assertThat(response.body(), CoreMatchers.containsString("Hello world!"));
        } finally {
            server.stop(0);
        }
    }

    private static HttpServer createServer() throws URISyntaxException {
        return SimpleFileServer.createFileServer(new InetSocketAddress(8001),
                Path.of(HttpServerTest.class.getResource(".").toURI()),
                SimpleFileServer.OutputLevel.NONE);
    }
}
