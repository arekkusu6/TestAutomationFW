package com.framework.unitests.httpclient;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static java.net.http.HttpClient.newBuilder;

public class GetBody {
    private static final String BASE_URL = "https://api.github.com/";

    @Test
    void bodyContainsCurrentUserUrl() {
        HttpClient httpClient = newBuilder().build();

        HttpRequest get = HttpRequest.newBuilder(URI.create(BASE_URL + "users/arekkusu6"))
                .setHeader("User-Agent", "Http Bot")
                .build();

        HttpResponse<String> response = null;
        try {
            response = httpClient.send(get, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        String body = response.body();

        Assertions.assertTrue(body.contains("\"login\":\"arekkusu6\""));
    }
}
