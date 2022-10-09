package com.framework.unitests.httpclient;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static java.net.http.HttpClient.newBuilder;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PostFails {
    private static final String BASE_URL = "https://api.github.com/";

    @Test
    void postWithoutAuthFails() throws IOException, InterruptedException {
        HttpClient httpClient = newBuilder().build();
        HttpRequest post = HttpRequest.newBuilder(URI.create(BASE_URL + "user/repos"))
                .setHeader( "Credentials", "web token or password")
                .POST(HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<Void> response = httpClient.send(post, HttpResponse.BodyHandlers.discarding());
        int actualCode = response.statusCode();
        assertEquals(401, actualCode);
    }
}
