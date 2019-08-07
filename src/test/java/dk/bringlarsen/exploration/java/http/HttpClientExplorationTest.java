package dk.bringlarsen.exploration.java.http;

import static org.junit.Assert.assertThat;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.core.Options;
import com.github.tomakehurst.wiremock.junit.WireMockRule;

import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class HttpClientExplorationTest {

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(Options.DYNAMIC_PORT);

    @Before
    public void setup() {
        WireMock.stubFor(WireMock.get(WireMock.urlEqualTo("/persons/1"))
        .willReturn(WireMock.aResponse()
        .withBody("Some Name")));
    }

    @Test
    public void simpleGet() throws URISyntaxException, IOException, InterruptedException {
        String url = wireMockRule.url("/persons/1");
        HttpResponse<String> response = HttpClient.newHttpClient()
        .send(HttpRequest.newBuilder(new URI(url)).GET().build(), 
            HttpResponse.BodyHandlers.ofString());
    
        assertThat(response.body(), CoreMatchers.is("Some Name"));
    }

    @Test
    public void asyncGet() throws URISyntaxException, IOException, InterruptedException {
        List<URI> targets = Arrays.asList(new URI(wireMockRule.url("/persons/1")));

        HttpClient client = HttpClient.newHttpClient();
        LinkedList<CompletableFuture<String>> responses = targets.stream()
          .map(target -> client
            .sendAsync(
              HttpRequest.newBuilder(target).GET().build(), HttpResponse.BodyHandlers.ofString())
            .thenApply(response -> response.body()))
          .collect(Collectors.toCollection(LinkedList::new));

        assertThat(responses.getFirst().join(), CoreMatchers.is("Some Name"));
    }
}