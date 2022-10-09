package com.framework.unitests.httpclient;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static java.net.http.HttpRequest.newBuilder;

public class GetHeader {
    private static final String BASE_URL = "https://api.github.com";

    static HttpClient client = HttpClient.newBuilder().build();
    static HttpResponse<Void> response;

    @BeforeAll
    static void sendGetToBaseEndpoint() throws IOException, InterruptedException {
        HttpRequest get = newBuilder(URI.create(BASE_URL))
                .setHeader("User-Agent", "Java 11 HttpClient Bot")
                .build();
        response = client.send(get, HttpResponse.BodyHandlers.discarding());
    }

    @Test
    void getReturns200() {
        int actualCode = response.statusCode();
        Assertions.assertEquals(200, actualCode);
    }

    @ParameterizedTest
    @CsvSource({
            "X-Ratelimit-Limit, 60",
            "content-type,application/json; charset=utf-8",
            "server, GitHub.com",
            "x-frame-options, deny"
    })
    void parametrizedTestForHeaders(String header, String expectedValue) {
        String contentType = response.headers().firstValue(header).get();
        Assertions.assertEquals(expectedValue, contentType);
    }
}
