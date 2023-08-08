package dk.bringlarsen.exploration.java.http;

import org.hamcrest.CoreMatchers;
import org.junit.Test;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;

import static org.hamcrest.MatcherAssert.assertThat;

public class HttpClientSSLExplorationTest {

    private final TrustManager[] trustManager = new TrustManager[]{new X509TrustManager(){
        public X509Certificate[] getAcceptedIssuers(){return null;}
        public void checkClientTrusted(X509Certificate[] certs, String authType){}
        public void checkServerTrusted(X509Certificate[] certs, String authType){}
    }};
   

    @Test
    public void simpleGetByPassingSSL() throws Exception {
        SSLContext sslContext = SSLContext.getInstance("TLS");
        sslContext.init(null, trustManager, new SecureRandom());

        HttpRequest request = HttpRequest.newBuilder(URI.create("https://expired.badssl.com/")).GET().build();
        HttpClient httpClient = HttpClient.newBuilder().sslContext(sslContext).build();
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        
        assertThat(response.body(), CoreMatchers.containsString("expired.badssl.com"));
    }
}