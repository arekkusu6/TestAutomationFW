package com.framework.unitests.httpclient;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class GetHeader {
    private static final String BASE_URL = "https://api.github.com";

    @Test
    void getReturns200() throws IOException, InterruptedException {
        HttpClient client = HttpClient.newBuilder().build();
        HttpRequest get = HttpRequest.newBuilder(URI.create(BASE_URL))
                .setHeader("User-Agent", "Http Bot")
                .build();

        HttpResponse<Void> response = client.send(get, HttpResponse.BodyHandlers.discarding());
        int actualCode = response.statusCode();

        Assertions.assertEquals(200, actualCode);
    }
}
