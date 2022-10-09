package com.framework.unitests.httpclient;

import com.framework.entities.User;
import com.framework.handlers.JsonBodyHandler;
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
    void bodyContainsCurrentUserUrl() throws IOException, InterruptedException {
        HttpClient httpClient = newBuilder().build();

        HttpRequest get = HttpRequest.newBuilder(URI.create(BASE_URL + "users/arekkusu6"))
                .setHeader("User-Agent", "Http Bot")
                .build();

        HttpResponse<User> response = httpClient.send(get, JsonBodyHandler.jsonBodyHandler(User.class));
        String actualLogin = response.body().getLogin();

        Assertions.assertEquals("arekkusu6", actualLogin);
    }
}
